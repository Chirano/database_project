package pt.org.upskill.dto;
import java.util.Date;
    public class SNSUserDTO implements DTO {
        private Integer id;
        private String name;
        private Date birthDate;
        private String gender;
        private AddressDTO addressDTO;
        private EmailDTO emailDTO;
        private String cardNumber;
        private String userNumber;

        public Integer id() {
            return this.id;
        }
        public String name() {
            return name;
        }
        public Date birthDate() { return birthDate;  }
        public String gender() {
            return this.gender;
        }
        public AddressDTO addressDTO() {return this.addressDTO; }
        public EmailDTO emailDTO() { return this.emailDTO; }
        public String cardnumber() {return this.cardNumber; }
        public String usernumber() {return this.userNumber;}

        public SNSUserDTO(final SNSUserDTO.Builder builder) {
            this.id = builder.id;
            this.name = builder.name;
            this.birthDate = builder.birthDate;
            this.gender = builder.gender;
            this.addressDTO = builder.addressDTO;
            this.emailDTO = builder.emailDTO;
            this.cardNumber = builder.cardnumber;
            this.userNumber = builder.usernumber;
        }

        public static class Builder {
            private Integer id;
            private String name;
            private Date birthDate;
            private String gender;
            private AddressDTO addressDTO;
            private EmailDTO emailDTO;
            private String cardnumber;
            private String usernumber;

            public SNSUserDTO.Builder withId(final Integer id) {
                this.id = id;
                return this;
            }
            public SNSUserDTO.Builder withName(final String name) {
                this.name = name;
                return this;
            }
            public SNSUserDTO.Builder withBirthdate(final Date birthDate) {
                this.birthDate = birthDate;
                return this;
            }
            public SNSUserDTO.Builder withGender(final String gender) {
                this.gender = gender;
                return this;
            }
            public SNSUserDTO.Builder withAddressDTO(final AddressDTO addressDTO) {
                this.addressDTO = addressDTO;
                return this;
            }
            public SNSUserDTO.Builder  withEmailDTO(final EmailDTO emailDTO) {
                this.emailDTO = emailDTO;
                return this;
            }
            public SNSUserDTO.Builder withCardnumber(final String cardNumber) {
                this.cardnumber = cardNumber;
                return this;
            }
            public SNSUserDTO.Builder withUsernumber(final String userNumber) {
                this.usernumber = userNumber;
                return this;
            }

            public SNSUserDTO build() {
                return new SNSUserDTO(this);
            }
        }
    }



