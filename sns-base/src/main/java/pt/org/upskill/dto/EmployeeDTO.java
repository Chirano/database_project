package pt.org.upskill.dto;

public class EmployeeDTO implements DTO{
    private Integer id;
    private EmailDTO emailDTO;
    private String name;
    private RoleDTO roleDTO;
    private PhoneDTO phone;

    private EmployeeDTO(final Builder builder) {
        this.id = builder.id;
        this.emailDTO = builder.emailDTO;
        this.name = builder.name;
        this.roleDTO = builder.roleDTO;
        this.phone = builder.phone;
    }
    public Integer getId() { return this.id; }
    public EmailDTO getEmailDTO() {
        return this.emailDTO;
    }
    public String getName() {
        return this.name;
    }
    public RoleDTO getRoleDTO() {
        return this.roleDTO;
    }
    public PhoneDTO getPhoneDTO() {
        return this.phone;
    }


    public static class Builder {
        private Integer id;
        private EmailDTO emailDTO;
        private String name;
        private RoleDTO roleDTO;
        private PhoneDTO phone;

        public Builder withId(final Integer id) {
            this.id = id;
            return this;
        }
        public Builder withEmailDTO(final EmailDTO emailDTO) {
            this.emailDTO = emailDTO;
            return this;
        }
        public Builder withName(final String name) {
            this.name = name;
            return this;
        }
        public Builder withRoleDTO(final RoleDTO roleDTO) {
            this.roleDTO = roleDTO;
            return this;
        }
        public Builder withPhoneDTO(final PhoneDTO phone) {
            this.phone = phone;
            return this;
        }

        public EmployeeDTO build() {
            return new EmployeeDTO(this);
        }
    }
}



