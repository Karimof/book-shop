package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookshop.entity.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
}
