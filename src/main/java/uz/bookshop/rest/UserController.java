package uz.bookshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.entity.Users;
import uz.bookshop.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users")
    public ResponseEntity<Users> createUsers(@RequestBody Users users) {
        Users result = userService.save(users);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/users")
    public ResponseEntity<Users> updateUsers(@RequestBody Users users) {
        Users result = userService.update(users);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUserss() {
        List<Users> result = userService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUsers(@PathVariable Long id) {
        Optional<Users> result = userService.findOne(id);
        return ResponseEntity.ok(result.orElse(null));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
