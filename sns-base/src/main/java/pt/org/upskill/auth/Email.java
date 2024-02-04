package pt.org.upskill.auth;

import pt.org.upskill.domain.Address;
import pt.org.upskill.dto.DTOable;
import pt.org.upskill.dto.EmailDTO;

public class Email implements DTOable {
    private String description;

    public Email(String address) throws Exception {
        if (!validate(address)) {
            throw new Exception("Invalid email address: " + address);
        }
        this.description = address;
    }

    public String getDescription() {
        return this.description;
    }
    public Email(Builder builder) {
        this.description = builder.address;
    }

    private boolean validate(String email) {
        return email.contains("@");
    }

    public String address() {
        return description;
    }

    @Override
    public EmailDTO toDTO() {
        EmailDTO emailDTO = new EmailDTO.Builder()
                .withDescription(getDescription())
                .build();
        return emailDTO;
    }

    public static class Builder {
        private String address;

        public Email.Builder withAddress(String address){
            this.address = address;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }
}
