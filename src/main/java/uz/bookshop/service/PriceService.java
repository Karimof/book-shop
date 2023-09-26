package uz.bookshop.service;

import org.springframework.stereotype.Service;
import uz.bookshop.repository.PriceRepository;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
}
