package online.sujoymistry.Blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.sujoymistry.Blog.entity.PostEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private long id;
    private String author;
    private String dateAndTime;
    private String title;
    private String description;
    private long imageId;
    public static PostDTO fromEntity(PostEntity entity) {
        PostDTO dto = new PostDTO();
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setDateAndTime(entity.getDateAndTime());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setImageId(entity.getImageId());
        return dto;
    }

}
