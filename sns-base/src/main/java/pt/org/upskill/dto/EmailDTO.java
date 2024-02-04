package pt.org.upskill.dto;

public class EmailDTO implements DTO {
    private String description;
    private EmailDTO(final Builder builder) {
        this.description = builder.description;
    }

    public String getDescription() { return this.description; }



    public static class Builder {
        private String description;

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public EmailDTO build() { return new EmailDTO(this); }
    }

}
