package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.entity.Price;
import uz.bookshop.repository.PriceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price save(Price price) {
        return priceRepository.save(price);
    }

    public Price update(Price price) {
        return priceRepository.save(price);
    }

    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    public Optional<Price> findOne(Long id) {
        return priceRepository.findById(id);
    }

    public void delete(Long id) {
        priceRepository.deleteById(id);
    }

}
