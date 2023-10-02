package uz.bookshop.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.bookshop.entity.Auth;
import uz.bookshop.entity.Users;
import uz.bookshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users save(Users users) {
        return userRepository.save(users);
    }

    public Users update(Users users) {
        return userRepository.save(users);
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Optional<Users> findOne(Long id) {
        return userRepository.findById(id);
    }

    public Users getCurrentUser() {
        return userRepository.findByLogin(getAuth().getLogin()).orElseThrow();
    }

    public Auth getAuth() {
        return (Auth) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
