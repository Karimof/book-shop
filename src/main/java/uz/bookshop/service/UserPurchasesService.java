package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.entity.UsersPurchases;
import uz.bookshop.repository.UserPurchasesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserPurchasesService {

    private final UserPurchasesRepository userPurchasesRepository;

    public UserPurchasesService(UserPurchasesRepository userPurchasesRepository) {
        this.userPurchasesRepository = userPurchasesRepository;
    }

    public UsersPurchases save(UsersPurchases userPurchases) {
        return userPurchasesRepository.save(userPurchases);
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
