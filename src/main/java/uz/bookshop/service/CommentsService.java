package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.repository.CommentsRepository;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
}
