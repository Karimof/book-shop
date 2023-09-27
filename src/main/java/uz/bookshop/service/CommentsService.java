package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.entity.Comments;
import uz.bookshop.repository.CommentsRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public Comments save(Comments comments) {
        comments.setCreatedAt(new Date(System.currentTimeMillis()));
        return commentsRepository.save(comments);
    }

    public Comments update(Comments comments) {
        return commentsRepository.save(comments);
    }

    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }

    public Optional<Comments> findOne(Long id) {
        return commentsRepository.findById(id);
    }

    public void delete(Long id) {
        commentsRepository.deleteById(id);
    }
}
