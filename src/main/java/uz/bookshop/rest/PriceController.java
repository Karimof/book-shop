package uz.bookshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.entity.Price;
import uz.bookshop.service.PriceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/price")
    public ResponseEntity<Price> createPrice(@RequestBody Price price) {
        Price result = priceService.save(price);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/price")
    public ResponseEntity<Price> updatePrice(@RequestBody Price price) {
        Price result = priceService.update(price);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/price")
    public ResponseEntity<List<Price>> getAllPrices() {
        List<Price> result = priceService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<Price> getPrice(@PathVariable Long id) {
        Optional<Price> result = priceService.findOne(id);
        return ResponseEntity.ok(result.orElse(null));
    }

    @DeleteMapping("/price/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        priceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
