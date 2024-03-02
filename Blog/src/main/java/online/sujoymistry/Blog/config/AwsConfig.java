package online.sujoymistry.Blog.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
public class AwsConfig {
    @Value("${aws.s3Service.accessKey}")
    private String accessKey;
    @Value("${aws.s3Service.secretKey}")
    private String secretKey;
    @Value("${aws.s3Service.bucketName}")
    private String bucketName;
    @Value("${aws.s3Service.keyName}")
    private String keyName;
    @Value("${aws.s3Service.regionName}")
    private String regionName;
    private String name="sujoy";
}

