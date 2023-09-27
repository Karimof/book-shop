package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookshop.entity.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
