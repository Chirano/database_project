package pt.org.upskill.domain;

import pt.org.upskill.dto.DTOable;

public class Phone implements DTOable {
    private String phoneNumber;

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Object toDTO() {
        return null;
    }
}
