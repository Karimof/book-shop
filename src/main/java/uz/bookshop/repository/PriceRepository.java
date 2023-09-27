package uz.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bookshop.entity.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
