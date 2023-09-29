package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.entity.Books;
import uz.bookshop.entity.Users;
import uz.bookshop.entity.UsersPurchases;
import uz.bookshop.repository.UserPurchasesRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserPurchasesService {

    private final UserPurchasesRepository userPurchasesRepository;

    private final BooksService booksService;

    private final UserService userService;

    public UserPurchasesService(UserPurchasesRepository userPurchasesRepository,
                                BooksService booksService,
                                UserService userService) {
        this.userPurchasesRepository = userPurchasesRepository;
        this.booksService = booksService;
        this.userService = userService;
    }

    public UsersPurchases save(UsersPurchases userPurchases) {
        return userPurchasesRepository.save(userPurchases);
    }

    public UsersPurchases createByBookId(Long bookId) {
        Books book = booksService.findOne(bookId).orElseThrow();
        Users user = userService.getCurrentUser();
        float priceOfBook = booksService.getThePriceOfBook(book.getId());

        UsersPurchases purchases = new UsersPurchases();
        purchases.setBooks(book);
        purchases.setUsers(user);
        purchases.setPrice(priceOfBook);
        purchases.setCreatedAt(new Date(System.currentTimeMillis()));
        return userPurchasesRepository.save(purchases);
    }

    public UsersPurchases update(UsersPurchases userPurchases) {
        return userPurchasesRepository.save(userPurchases);
    }

    public List<UsersPurchases> findAll() {
        return userPurchasesRepository.findAll();
    }

    public Optional<UsersPurchases> findOne(Long id) {
        return userPurchasesRepository.findById(id);
    }

    public void delete(Long id) {
        userPurchasesRepository.deleteById(id);
    }
}
