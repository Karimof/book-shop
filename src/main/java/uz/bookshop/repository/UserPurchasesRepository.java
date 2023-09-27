package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookshop.entity.UsersPurchases;

@Repository
public interface UserPurchasesRepository extends JpaRepository<UsersPurchases, Long> {
}
