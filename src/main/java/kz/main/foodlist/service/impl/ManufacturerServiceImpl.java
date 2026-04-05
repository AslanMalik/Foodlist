package kz.main.foodlist.service.impl;

import kz.main.foodlist.entity.Manufacturer;
import kz.main.foodlist.repository.ManRepository;
import kz.main.foodlist.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManRepository manRepository;

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return manRepository.findAll();
    }
}
