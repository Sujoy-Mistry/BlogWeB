package online.sujoymistry.Blog.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.sujoymistry.Blog.dto.PostDTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 15)
    private String author;

    private String dateAndTime;
    @Column(length = 500)
    private String title;

    @Column(length = 10000)
    private String description;
    private long imageId;

    public static PostEntity fromDTO(PostDTO dto) {
        PostEntity entity = new PostEntity();
        entity.setAuthor(dto.getAuthor());
        entity.setDateAndTime(dto.getDateAndTime());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImageId(dto.getImageId());
        return entity;
    }
}
