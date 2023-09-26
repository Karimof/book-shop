package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
