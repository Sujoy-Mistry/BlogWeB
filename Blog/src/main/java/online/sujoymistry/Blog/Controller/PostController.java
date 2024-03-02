package online.sujoymistry.Blog.Controller;


import online.sujoymistry.Blog.dto.PostDTO;
import online.sujoymistry.Blog.service.AwsS3Service;
import online.sujoymistry.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController()
@RequestMapping("/app")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    AwsS3Service awsS3Service;
    @PostMapping("/addpost")
    public String savePost(@RequestParam("file") MultipartFile file, @RequestPart String postDTO){
        Long imageId=0L;
        if(!file.isEmpty()){
            imageId=awsS3Service.postImage(file);
        }
        if(imageId!=0L){
//            postDTO.setImageId(imageId);
        }
        return  "nnn";
//         return postService.savePost(postDTO);
    }

    @GetMapping("/getData")
    public String getData(){
        return "hi";
    }

}
