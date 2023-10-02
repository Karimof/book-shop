package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.entity.Author;
import uz.bookshop.repository.AuthorRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author) {
        author.setCreatedAt(new Date(System.currentTimeMillis()));
        return authorRepository.save(author);
    }

    public Author update(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> findOne(Long id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> findByLogin(String login) {
        return authorRepository.findByLogin(login);
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
