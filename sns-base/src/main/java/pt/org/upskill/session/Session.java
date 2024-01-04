package pt.org.upskill.session;

import pt.org.upskill.auth.User;

public class Session {
    User user;

    public Session(User user) {
        this.user = user;
    }

    public User user() {
        return this.user;
    }

}
