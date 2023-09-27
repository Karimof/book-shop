package uz.bookshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.entity.UsersPurchases;
import uz.bookshop.service.UserPurchasesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserPurchasesController {

    private final UserPurchasesService userPurchasesService;

    public UserPurchasesController(UserPurchasesService userPurchasesService) {
        this.userPurchasesService = userPurchasesService;
    }


    @PostMapping("/price")
    public ResponseEntity<UsersPurchases> createUsersPurchases(@RequestBody UsersPurchases userPurchases) {
        UsersPurchases result = userPurchasesService.save(userPurchases);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/userPurchases")
    public ResponseEntity<UsersPurchases> updateUsersPurchases(@RequestBody UsersPurchases userPurchases) {
        UsersPurchases result = userPurchasesService.update(userPurchases);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/userPurchases")
    public ResponseEntity<List<UsersPurchases>> getAllUsersPurchasess() {
        List<UsersPurchases> result = userPurchasesService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/userPurchases/{id}")
    public ResponseEntity<UsersPurchases> getUsersPurchases(@PathVariable Long id) {
        Optional<UsersPurchases> result = userPurchasesService.findOne(id);
        return ResponseEntity.ok(result.orElse(null));
    }

    @DeleteMapping("/userPurchases/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userPurchasesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
