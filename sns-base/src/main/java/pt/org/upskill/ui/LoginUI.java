package pt.org.upskill.ui;

import pt.org.upskill.controller.FacilityController;
import pt.org.upskill.controller.LoginController;
import pt.org.upskill.dto.KeyValueDTO;

import java.util.List;

import static pt.org.upskill.ui.utils.UITools.showKeyValueList;
import static pt.org.upskill.ui.utils.Utils.*;

public class LoginUI extends UI {

    //Controller
    private final LoginController loginController = new LoginController();

    private String email;
    private String password;

    public void run() {
        System.out.println("");
        System.out.println("LOG IN");
        System.out.println("------");

        String email = readLineFromConsole("Email: ");
        String password = readLineFromConsole("Password: ");

        showKeyValueList("Facilities", new FacilityController().keyValueDTOList());
        int key = readIntegerFromConsole("Select a facility: ");

        try {
            if (loginController.logIn(email, password, key)) {
                System.out.print(email + " logged in");
            } else {
                System.out.print("Invalid email/password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
