package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.entity.Books;
import uz.bookshop.entity.Comments;
import uz.bookshop.entity.Users;
import uz.bookshop.entity.VM.CommentVM;
import uz.bookshop.repository.CommentsRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final BooksService booksService;

    private final UserService userService;

    public CommentsService(CommentsRepository commentsRepository,
                           BooksService booksService,
                           UserService userService) {
        this.commentsRepository = commentsRepository;
        this.booksService = booksService;
        this.userService = userService;
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

    public List<Comments> findAllBookComments(Long bookId) {
        return commentsRepository.findAllByBooksId(bookId);
    }

    public Optional<Comments> findOne(Long id) {
        return commentsRepository.findById(id);
    }

    public List<Comments> create(CommentVM commentVM) {
        Books book = booksService.findOne(commentVM.getBookId()).orElseThrow();
        Users user = userService.getCurrentUser();
        Comments comment = new Comments();
        comment.setBooks(book);
        comment.setUsers(user);
        comment.setContent(commentVM.getContent());
        comment.setCreatedAt(new Date(System.currentTimeMillis()));
        commentsRepository.save(comment);

        return findAll();
    }

    public void delete(Long id) {
        commentsRepository.deleteById(id);
    }
}
