package pt.org.upskill.dto;

public class RoleDTO implements DTO {
    private Integer id;
    private String name;

    private RoleDTO(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Integer getId() { return this.id; }
    public String getName() { return this.name; }

    public static class Builder {
        private String name;
        private Integer id;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }
        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public RoleDTO build() { return new RoleDTO(this);  }
    }
}
