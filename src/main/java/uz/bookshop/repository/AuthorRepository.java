package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookshop.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
