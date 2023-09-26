package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.repository.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
}
