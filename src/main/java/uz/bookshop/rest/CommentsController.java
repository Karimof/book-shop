package uz.bookshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.entity.Comments;
import uz.bookshop.service.CommentsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/comments")
    public ResponseEntity<Comments> createComment(@RequestBody Comments comments) {
        Comments result = commentsService.save(comments);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/comments")
    public ResponseEntity<Comments> updateComment(@RequestBody Comments comments) {
        Comments result = commentsService.update(comments);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> result = commentsService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comments> getComment(@PathVariable Long id) {
        Optional<Comments> result = commentsService.findOne(id);
        return ResponseEntity.ok(result.orElse(null));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
