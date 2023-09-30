package uz.bookshop.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.entity.Comments;
import uz.bookshop.entity.VM.CommentVM;
import uz.bookshop.service.CommentsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping
    public ResponseEntity<Comments> createComment(@RequestBody Comments comments) {
        Comments result = commentsService.save(comments);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/content")
    public ResponseEntity<List<Comments>> createCommentByContent(@RequestBody CommentVM commentVm) {
        List<Comments> result = commentsService.create(commentVm);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<Comments> updateComment(@RequestBody Comments comments) {
        Comments result = commentsService.update(comments);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> result = commentsService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<Comments>> getCommentBook(@PathVariable Long id) {
        List<Comments> result = commentsService.findAllBookComments(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comments> getComment(@PathVariable Long id) {
        Optional<Comments> result = commentsService.findOne(id);
        return ResponseEntity.ok(result.orElse(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
