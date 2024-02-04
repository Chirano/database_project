package pt.org.upskill.domain;
import pt.org.upskill.auth.Email;
import pt.org.upskill.dto.AddressDTO;
import pt.org.upskill.dto.DTOable;
import pt.org.upskill.dto.SNSUserDTO;
import java.util.Date;
    public class SNSUser implements DTOable<SNSUserDTO> {
        private Integer id;
        private String name;
        private Date birthDate;
        private String gender;
        private Address address;
        private Email email;
        private String cardNumber;
        private String userNumber;

        public SNSUser(Integer id){
            this.id = id;
        }
        private SNSUser(final SNSUser.Builder builder) {
            this.id = builder.id;
            this.name = builder.name;
            this.birthDate = builder.birthDate;
            this.gender = builder.gender;
            this.address = builder.address;
            this.email = builder.email;
            this.cardNumber = builder.cardnumber;
            this.userNumber = builder.usernumber;
        }

        public Integer id() {
            return this.id;
        }
        public String name() {
            return name;
        }
        public Date birthDate() { return birthDate;  }
        public String gender() {  return this.gender;  }
        public Address address() {return this.address; }
        public Email email() { return this.email; }
        public String cardnumber() {return this.cardNumber; }
        public String usernumber() {return this.userNumber;}

        @Override
        public SNSUserDTO toDTO() {

            SNSUserDTO dto = new SNSUserDTO.Builder()
                    .withId(id())
                    .withName(name())
                    .withBirthdate(birthDate())
                    .withGender(gender())
                    //.withAddressDTO(address().toDTO())
                    .withEmailDTO(email().toDTO())
                    .withCardnumber(cardnumber())
                    .withUsernumber(usernumber())
                    .build();
            return dto;
        }

        public static class Builder {
            private Integer id;
            private String name;
            private Date birthDate;
            private String gender;
            private Address address;
            private Email email;
            private String cardnumber;
            private String usernumber;

            public SNSUser.Builder withId(final Integer id) {
                this.id = id;
                return this;
            }
            public SNSUser.Builder withName(final String name) {
                this.name = name;
                return this;
            }
            public SNSUser.Builder withBirthdate(final Date birthDate) {
                this.birthDate = birthDate;
                return this;
            }
            public SNSUser.Builder withGender(final String gender) {
                this.gender = gender;
                return this;
            }
            public SNSUser.Builder withAddress(final Address address) {
                this.address = address;
                return this;
            }
            public SNSUser.Builder withEmail(final Email email) {
                this.email = email;
                return this;
            }
            public SNSUser.Builder withCardNumber(final String cardNumber) {
                this.cardnumber = cardNumber;
                return this;
            }
            public SNSUser.Builder withUserNumber(final String userNumber) {
                this.usernumber = userNumber;
                return this;
            }

            public SNSUser build() {
                return new SNSUser(this);
            }
        }
    }


