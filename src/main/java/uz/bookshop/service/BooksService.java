package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.entity.Books;
import uz.bookshop.repository.BooksRepository;

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

    public Optional<Books> findOne(Long id) {
        return booksRepository.findById(id);
    }

    public void delete(Long id) {
        booksRepository.deleteById(id);
    }
}
