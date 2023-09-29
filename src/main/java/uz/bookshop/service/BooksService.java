package uz.bookshop.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import uz.bookshop.entity.Books;
import uz.bookshop.entity.dto.BooksDTO;
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

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
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

    public List<BooksDTO> findAllBooksWithPrice() {
        return booksRepository.findAllBooksWithPrice();
    }

    public BooksDTO findBooksWithPrice(Long id) {
        return booksRepository.findBookWithPrice(id);
    }

    public float getThePriceOfBook(Long bookId) {
        return booksRepository.findThePriceOfBook(bookId);
    }

    public ByteArrayResource getBookImage(String imageName) {
        Path absolutePath = Paths.get(
                "D:/Programming/Projects/Experience projects/book-shop/src/main/resources/image/"
                        + imageName);
        ByteArrayResource inputStream = null;
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
