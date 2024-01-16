package pt.org.upskill.domain;

import pt.org.upskill.dto.BrandDTO;
import pt.org.upskill.dto.DTOable;

public class Brand implements DTOable {
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public BrandDTO toDTO() {
        BrandDTO dto = new BrandDTO();
        dto.name = name();
        return dto;
    }
}
