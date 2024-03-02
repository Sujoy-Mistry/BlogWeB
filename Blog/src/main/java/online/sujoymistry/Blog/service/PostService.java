package online.sujoymistry.Blog.service;

import online.sujoymistry.Blog.dto.PostDTO;
import online.sujoymistry.Blog.entity.PostEntity;
import online.sujoymistry.Blog.repository.PostJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostJpaRepository postJpaRepository;

    public String savePost(PostDTO postDTO){
        postDTO.setDateAndTime(LocalDateTime.now().toString());
        PostEntity postEntity=PostEntity.fromDTO(postDTO);
        postJpaRepository.save(postEntity);
        return "Post added successfully";
    }
    public List<PostDTO> getPost(){
        var list=postJpaRepository.findAll();
        List<PostDTO> dtos = list.stream()
                .map(PostDTO::fromEntity)
                .toList();
        return  dtos;
    }

    public List<PostDTO> searchPostByTitle(String keywordTitle){
        String keywordPattern = "%" + keywordTitle + "%";
        var postEntity=postJpaRepository.findByTitle(keywordPattern);
        if(postEntity.isEmpty()){
            return null;
        }
        var postDTOList=postEntity.get().stream()
                .map(PostDTO::fromEntity)
                .toList();
        return postDTOList;
    }
    public List<PostDTO> searchPostByAuthor(String partialAuthorName){
            var entities = postJpaRepository.findByAuthorContaining(partialAuthorName);
            if(entities.isEmpty()){
                return  null;
            }
            List<PostDTO> dtos = entities.get().stream()
                    .map(PostDTO::fromEntity)
                    .collect(Collectors.toList());

            return dtos;
    }
}
