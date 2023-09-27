package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bookshop.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByLogin(String login);
}
