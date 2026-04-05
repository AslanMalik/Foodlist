package kz.main.foodlist.service;

import kz.main.foodlist.entity.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManufacturerService {

    List<Manufacturer> getAllManufacturer();
}
