package uz.bookshop.service;

import jakarta.transaction.Transactional;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import uz.bookshop.entity.Author;
import uz.bookshop.entity.Books;
import uz.bookshop.entity.Price;
import uz.bookshop.entity.VM.BooksVM;
import uz.bookshop.entity.VM.CreateBooksVM;
import uz.bookshop.repository.BooksRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    private final UserService userService;

    private final AuthorService authorService;

    private final PriceService priceService;

    public BooksService(BooksRepository booksRepository, UserService userService, AuthorService authorService, PriceService priceService) {
        this.booksRepository = booksRepository;
        this.userService = userService;
        this.authorService = authorService;
        this.priceService = priceService;
    }

    public Books save(Books books) {
        books.setCreatedAt(new Date(System.currentTimeMillis()));
        return booksRepository.save(books);
    }

    public Books update(Books books) {
        return booksRepository.save(books);
    }

    public List<Books> findAll() {
        return booksRepository.findAll();
    }

    public List<BooksVM> findAllBooksWithPrice() {
        return booksRepository.findAllBooksWithPrice();
    }

    public BooksVM findBooksWithPrice(Long id) {
        return booksRepository.findBookWithPrice(id);
    }

    @Transactional
    public Books createBookWithName(CreateBooksVM booksVM) {
        Books books = new Books();
        books.setName(booksVM.getName());
        books.setViewCount(0);
        books.setAuthor(getLoggedAuthor());
        books.setCreatedAt(new Date(System.currentTimeMillis()));
        Books savedBook = booksRepository.save(books);

        Price price = new Price();
        price.setBooks(savedBook);
        price.setPrice(booksVM.getPrice());
        priceService.save(price);

        return savedBook;
    }

    public List<BooksVM> getAuthorBooks() {
        return booksRepository.findAuthorsBooks(getLoggedAuthor().getId());
    }

    private Author getLoggedAuthor() {
        String authorLogin = userService.getAuth().getUsername();
        return authorService.findByLogin(authorLogin).orElseThrow();
    }

    public float getThePriceOfBook(Long bookId) {
        return booksRepository.findThePriceOfBook(bookId);
    }

    public ByteArrayResource getBookImage(String imageName) {
        Path absolutePath = Paths.get(
                "D:/Programming/Projects/Experience projects/book-shop/src/main/resources/image/"
                        + imageName);
        ByteArrayResource inputStream;
        try {
            inputStream = new ByteArrayResource(Files.readAllBytes(absolutePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputStream;
    }

    public void increaseViewCount(Long id) {
        booksRepository.increaseBookViewCount(id);
    }

    public Optional<Books> findOne(Long id) {
        return booksRepository.findById(id);
    }

    public void delete(Long id) {
        booksRepository.deleteById(id);
    }
}
