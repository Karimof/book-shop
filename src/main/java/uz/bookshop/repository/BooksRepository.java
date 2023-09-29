package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.bookshop.entity.Books;
import uz.bookshop.entity.dto.BooksDTO;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
    @Query(value = "select new uz.bookshop.entity.dto.BooksDTO(b.id, b.name, b.author, b.createdAt, b.viewCount, b.image, p.price) from Price as p " +
            "inner join Books as b " +
            "on p.books.id = b.id")
    List<BooksDTO> findAllBooksWithPrice();

    @Query(value = "select new uz.bookshop.entity.dto.BooksDTO(b.id, b.name, b.author, b.createdAt, b.viewCount, b.image, p.price) from Price as p " +
            "inner join Books as b " +
            "on p.books.id = b.id " +
            "where b.id = :id")
    BooksDTO findBookWithPrice(Long id);

    @Query(value = "select p.price from Price as p " +
            "inner join Books as b " +
            "on p.books.id = b.id " +
            "where b.id = :bookId")
    float findThePriceOfBook(Long bookId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Books set view_count = view_count+1 where id=:id", nativeQuery = true)
    void increaseBookViewCount(Long id);
}
