package pt.org.upskill.controller;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Brand;
import pt.org.upskill.dto.DTOlists;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.BrandRepository;

import java.util.List;

public class BrandController {
    BrandRepository brandRepository = Repositories.getInstance().brandRepository();

    public List<Brand> brandList() {
        return brandRepository.brandList();
    }

    Brand brand;

    public void createBrand(String name) {
        brand = brandRepository.createBrand(name);
    }

    public boolean confirm() {
        brandRepository.save(brand);
        return true;
    }

    public List<KeyValueDTO<String>> dtoList() {
        return new DTOlists().brandDTOList();
    }
}
