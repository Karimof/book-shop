package uz.bookshop.rest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.bookshop.entity.Author;
import uz.bookshop.entity.Books;
import uz.bookshop.entity.VM.BooksVM;
import uz.bookshop.entity.VM.CreateBooksVM;
import uz.bookshop.service.AuthorService;
import uz.bookshop.service.BooksService;
import uz.bookshop.service.ImageUploadService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
public class AuthorController {

    private final AuthorService authorService;

    private final BooksService booksService;

    private final ImageUploadService imageUploadService;


    public AuthorController(AuthorService authorService,
                            BooksService booksService,
                            ImageUploadService imageUploadService) {
        this.authorService = authorService;
        this.booksService = booksService;
        this.imageUploadService = imageUploadService;
    }

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author result = authorService.save(author);
        return ResponseEntity.ok(result);
    }


    //    @RolesAllowed({""})
    @PostMapping(value = "/author/create-book")
    public ResponseEntity<Books> createBook(@RequestBody CreateBooksVM bookVM) {
        Books createdBook = booksService.createBookWithName(bookVM);
        return ResponseEntity.ok(createdBook);
    }

    @PostMapping(value = "/author/save-book-image",headers = "content-type=multipart/*", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> saveImage(@RequestPart MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            imageUploadService.save(file);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/author")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        Author result = authorService.update(author);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> result = authorService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        Optional<Author> result = authorService.findOne(id);
        return ResponseEntity.ok(result.orElse(null));
    }

    @GetMapping("/author/book")
    public ResponseEntity<List<BooksVM>> getAuthorBooks() {
        List<BooksVM> result = booksService.getAuthorBooks();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/author/books-image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getBookImage(@PathVariable String imageName) {
        ByteArrayResource bookImage = booksService.getBookImage(imageName);
        if (bookImage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookImage);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
