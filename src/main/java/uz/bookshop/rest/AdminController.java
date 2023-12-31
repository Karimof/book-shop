package uz.bookshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.bookshop.entity.Users;
import uz.bookshop.entity.VM.AuthorBooksCount;
import uz.bookshop.entity.VM.AuthorEarnedTotal;
import uz.bookshop.entity.VM.FamousBooksClassVM;
import uz.bookshop.service.AuthorService;
import uz.bookshop.service.BooksService;
import uz.bookshop.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    private final AuthorService authorService;

    private final BooksService booksService;

    public AdminController(UserService userService, AuthorService authorService, BooksService booksService) {
        this.userService = userService;
        this.authorService = authorService;
        this.booksService = booksService;
    }


    @GetMapping("/last-week-users")
    public ResponseEntity<List<Users>> lastWeekUsers() {
        List<Users> result = userService.weekUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users-month")
    public ResponseEntity<Object> usersMonth() {
        ArrayList<ArrayList<Integer>> users = userService.usersMonth();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/active-users")
    public ResponseEntity<List<Object>> activeUsers() {
        List<Object> result = userService.activeUsers();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/famous-books")
    public ResponseEntity<List<FamousBooksClassVM>> mostBoughtBooks() {
        List<FamousBooksClassVM> result = booksService.FamousBooks();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/active-authors")
    public ResponseEntity<List<AuthorBooksCount>> activeAuthors() {
        List<AuthorBooksCount> result = booksService.activeAuthors();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/earned-authors")
    public ResponseEntity<List<AuthorEarnedTotal>> earnedMoreAuthors() {
        List<AuthorEarnedTotal> result = booksService.getEarnedMoreAuthors();
        return ResponseEntity.ok(result);
    }
}
