package pt.org.upskill.auth;

public class Email {
    private String address;

    public Email(String address) throws Exception {
        if (!validate(address)) {
            throw new Exception("Invalid email address: " + address);
        }
        this.address = address;
    }

    protected Email() {
    }

    private boolean validate(String email) {
        return email.contains("@");
    }

    public String address() {
        return address;
    }
}
