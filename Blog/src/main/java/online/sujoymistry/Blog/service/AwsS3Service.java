package online.sujoymistry.Blog.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import online.sujoymistry.Blog.config.AwsConfig;
import online.sujoymistry.Blog.utility.SnowflakeIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AwsS3Service {
    @Value("${aws.s3Service.accessKey}")
    private String accessKey;

    @Value("${aws.s3Service.secretKey}")
    private String secretKey;
    @Value("${aws.s3Service.bucketName}")
    private String bucketName;
    @Value("${aws.s3Service.keyName}")
    private String keyname;
    @Autowired
    SnowflakeIdGenerator snowflakeIdGenerator;

    private static Logger logger= LoggerFactory.getLogger(AwsS3Service.class);
    private AmazonS3 getS3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1) // Specify the AWS region here
                .build();

        return s3Client;
    }
    public Long postImage(MultipartFile multipartFile) {
        Long imageId=0L;
        try {
            String name = multipartFile.getOriginalFilename();
            var list = name.split("\\.");
            File file = File.createTempFile("temp", null);
            multipartFile.transferTo(file);
            AmazonS3 s3Client = getS3Client();
            imageId = snowflakeIdGenerator.generateId();
            String keyName = keyname + imageId + "." + list[1];
            s3Client.putObject(new PutObjectRequest(bucketName, keyName, file));
        } catch (Exception exception) {
            logger.info(exception.getMessage());
        }
        return imageId;
    }
}
