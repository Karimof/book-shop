package uz.bookshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.entity.Books;
import uz.bookshop.service.BooksService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

//    @GetMapping
//    public ResponseEntity<String> sayHello(){
//        return ResponseEntity.ok("Hello from secured endpoint");
//    }

    @PostMapping("/books")
    public ResponseEntity<Books> createBook(@RequestBody Books books) {
        Books result = booksService.save(books);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/books")
    public ResponseEntity<Books> updateBook(@RequestBody Books books) {
        Books result = booksService.update(books);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> result = booksService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Books> getBook(@PathVariable Long id) {
        Optional<Books> result = booksService.findOne(id);
        return ResponseEntity.ok(result.orElse(null));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        booksService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
