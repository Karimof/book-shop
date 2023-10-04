package uz.bookshop.rest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.entity.Books;
import uz.bookshop.entity.VM.BooksVM;
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

    @GetMapping("/books-price")
    public ResponseEntity<List<BooksVM>> getAllBooksDTO() {
        List<BooksVM> result = booksService.findAllBooksWithPrice();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/books-price/{id}")
    public ResponseEntity<BooksVM> getBooksDTO(@PathVariable Long id) {
        BooksVM result = booksService.findBookWithPrice(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Books> getBook(@PathVariable Long id) {
        Optional<Books> result = booksService.findOne(id);
        return ResponseEntity.ok(result.orElse(null));
    }

    @GetMapping("/books/count/{id}")
    public ResponseEntity<Books> increaseViewCount(@PathVariable Long id) {
        booksService.increaseViewCount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/books/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        ByteArrayResource bookImage = booksService.getBookImage(imageName);
        if (bookImage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookImage);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        booksService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
