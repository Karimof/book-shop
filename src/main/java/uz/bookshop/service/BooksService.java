package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.repository.BooksRepository;

@Service
public class BooksService {

    private final BooksRepository authorRepository;

    public BooksService(BooksRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
}
