package pt.org.upskill.domain;

import pt.org.upskill.dto.DTOable;
import pt.org.upskill.dto.WebsiteDTO;

public class Website implements DTOable {
    private String address;

    public String address() {
        return this.address;
    }

    public Website(String address) {
        this.address = address;
    }

    @Override
    public Object toDTO() {
        return new WebsiteDTO(address());
    }
}
