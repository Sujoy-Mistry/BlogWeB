package online.sujoymistry.Blog.repository;

import online.sujoymistry.Blog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity,Long> {
    Optional<List<PostEntity>> findByTitle(String title);
    Optional<List<PostEntity>> findByAuthorContaining(String partialAuthorName);
}
