package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.entity.Auth;
import uz.bookshop.repository.AuthRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public Auth save(Auth auth) {
        return authRepository.save(auth);
    }

    public Auth update(Auth auth) {
        return authRepository.save(auth);
    }

    public List<Auth> findAll() {
        return authRepository.findAll();
    }

    public Optional<Auth> findOne(Long id) {
        return authRepository.findById(id);
    }

    public void delete(Long id) {
        authRepository.deleteById(id);
    }
}
