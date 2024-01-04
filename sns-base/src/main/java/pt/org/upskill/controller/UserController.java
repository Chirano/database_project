package pt.org.upskill.controller;

import pt.org.upskill.auth.Email;
import pt.org.upskill.auth.Password;
import pt.org.upskill.auth.User;
import pt.org.upskill.domain.Role;
import java.util.List;

public class UserController {
    /*
    public List<KeyValueDTO<String>> userList() {
        UserRepository repo = Repositories.getInstance().userRepository();
        return repo.keyValueList();
    }

    public boolean createUser(UserDTO userDTO) {
        UserRepository repo = Repositories.getInstance().userRepository();
        try {
            RoleController roleController = new RoleController();
            Role role = roleController.roleByName(userDTO.role);
            User user = new User(userDTO.code, role, new Email(userDTO.email), new Password(userDTO.password));
            repo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

     */
}
