package pt.org.upskill.domain;

import pt.org.upskill.dto.DTOable;

public class Address {
    private String streetName;
    private String postalCode;
    private String cityName;

    public Address(String streetName, String postalCode, String cityName) {
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.cityName = cityName;
    }
}
