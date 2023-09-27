package uz.bookshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.entity.Author;
import uz.bookshop.service.AuthorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public ResponseEntity<Author> createBook(@RequestBody Author author) {
        Author result = authorService.save(author);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/author")
    public ResponseEntity<Author> updateBook(@RequestBody Author author) {
        Author result = authorService.update(author);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAllBooks() {
        List<Author> result = authorService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getBook(@PathVariable Long id) {
        Optional<Author> result = authorService.findOne(id);
        return ResponseEntity.ok(result.orElse(null));
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
