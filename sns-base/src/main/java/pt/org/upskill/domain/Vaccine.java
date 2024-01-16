package pt.org.upskill.domain;

import pt.org.upskill.dto.VaccineDTO;
import pt.org.upskill.dto.DTOable;

import java.util.Objects;

public class Vaccine implements DTOable {
    private Integer id;
    private String name;
    private VaccineType vaccineType;
    private Brand brand;
/*
    public Vaccine(Integer id, String name, VaccineType vaccineType, Brand brand) {
        this.id = id;
        this.name = name;
        this.vaccineType = vaccineType;
        this.brand = brand;
    }
 */
    private Vaccine(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.vaccineType = builder.vaccineType;
        this.brand = builder.brand;
    }

    public Integer id() {
        return this.id;
    }
    public String name() {
        return name;
    }
    public VaccineType vaccineType() {
        return vaccineType;
    }
    public Brand brand() {
        return this.brand;
    }

    @Override
    public VaccineDTO toDTO() {
        VaccineDTO dto = new VaccineDTO();
        dto.id = id();
        dto.name = name();
        dto.vaccineTypeCode = vaccineType().code();
        dto.brandName = brand().name();;
        return dto;
    }

    public static class Builder {

        private Integer id;
        private String name;
        private VaccineType vaccineType;
        private Brand brand;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }
        public Builder vaccineType(final VaccineType vaccineType) {
            this.vaccineType = vaccineType;
            return this;
        }
        public Builder brand(final Brand brand) {
            this.brand = brand;
            return this;
        }

        public Vaccine build() {
            return new Vaccine(this);
        }
    }
}
