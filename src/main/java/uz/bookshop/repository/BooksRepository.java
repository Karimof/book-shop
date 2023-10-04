package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.bookshop.entity.Books;
import uz.bookshop.entity.VM.AuthorBooksCount;
import uz.bookshop.entity.VM.AuthorEarnedTotal;
import uz.bookshop.entity.VM.BooksVM;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

    @Query(value = "select new uz.bookshop.entity.VM.BooksVM(b.id, b.name, b.author, b.createdAt, b.viewCount, b.image, p.price) from Price as p " +
            "inner join Books as b " +
            "on p.books.id = b.id")
    List<BooksVM> findAllBooksWithPrice();

    @Query(value = "select new uz.bookshop.entity.VM.BooksVM(b.id, b.name, b.author, b.createdAt, b.viewCount, b.image, p.price) from Books as b " +
            "inner join Price as p " +
            "on p.books.id = b.id " +
            "where b.author.id = :id")
    List<BooksVM> findAuthorsBooks(Long id);

    @Query(value = "select new uz.bookshop.entity.VM.BooksVM(b.id, b.name, b.author, b.createdAt, b.viewCount, b.image, p.price) from Price as p " +
            "inner join Books as b " +
            "on p.books.id = b.id " +
            "where b.id = :id")
    BooksVM findBookWithPrice(Long id);

    @Query(value = "select p.price from Price as p " +
            "inner join Books as b " +
            "on p.books.id = b.id " +
            "where b.id = :bookId")
    float findThePriceOfBook(Long bookId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Books set view_count = view_count+1 where id=:id", nativeQuery = true)
    void increaseBookViewCount(Long id);


    @Query(value = "select distinct h.id, h.name, r.buy, r.comment, h.view_count as view " +
            "from (select res1.book, res1.buy, res1.comment " +
            "      from (select * " +
            "            from (select c.books_id as book, count(c.books_id) as comment " +
            "                  from comments as c " +
            "                  group by c.books_id) as combinedComment " +
            "                     full join (select up.books_id, count(up.books_id) as buy " +
            "                                from users_purchases as up " +
            "                                group by up.books_id) as gup " +
            "                               on combinedComment.book = gup.books_id) as res1) as r " +
            "         full join (select b.id, b.name, b.view_count, up.books_id " +
            "                    from books as b " +
            "                             full join comments c on b.id = c.books_id " +
            "                             full join users_purchases as up on c.books_id = up.books_id " +
            "                    group by b.id, b.view_count, c.id, up.books_id) as h " +
            "                   on h.books_id = r.book " +
            " limit 20", nativeQuery = true)
    List<Object[]> famousBooks();

    @Query(value = "select new uz.bookshop.entity.VM.AuthorBooksCount(b.author.id,a.name,count(b.author.id)) " +
            "from Books as b " +
            "join Author as a " +
            "on a.id = b.author.id " +
            "group by b.author.id, a.name " +
            "order by count(b.author.id) desc ")
    List<AuthorBooksCount> activeAuthors();

    @Query(value = "select new uz.bookshop.entity.VM.AuthorEarnedTotal(b.author.id, b.author.name, sum(up.price)) " +
            "from UsersPurchases as up " +
            "         join Books b " +
            "              on b.id = up.books.id " +
            "group by b.author.id, b.author.name " +
            "order by sum(up.price) desc ")
    List<AuthorEarnedTotal> earnedMoreAuthor();
}
