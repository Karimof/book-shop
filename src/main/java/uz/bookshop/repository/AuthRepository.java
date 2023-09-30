package uz.bookshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.bookshop.entity.Auth;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByLogin(String login);
}