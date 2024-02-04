package pt.org.upskill.dto;

public class PhoneDTO implements DTO {
    private String phoneNumber;

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    private PhoneDTO(final Builder builder) {
        this.phoneNumber = builder.phoneNumber;
    }

    public static class Builder {
        private String phoneNumber;

        public Builder withPhoneNumberDTO(final String phoneNumberDTO) {
            this.phoneNumber = phoneNumberDTO;
            return this;
        }

        public PhoneDTO build() {
            return new PhoneDTO(this);
        }
    }
}
