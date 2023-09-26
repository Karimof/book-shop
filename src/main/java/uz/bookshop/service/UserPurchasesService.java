package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.repository.UserPurchasesRepository;

@Service
public class UserPurchasesService {

    private final UserPurchasesRepository userPurchasesRepository;

    public UserPurchasesService(UserPurchasesRepository userPurchasesRepository) {
        this.userPurchasesRepository = userPurchasesRepository;
    }
}
