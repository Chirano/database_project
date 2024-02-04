package pt.org.upskill.ui;

import pt.org.upskill.controller.EmployeeController;
import pt.org.upskill.controller.RoleController;
import pt.org.upskill.dto.EmailDTO;
import pt.org.upskill.dto.EmployeeDTO;
import pt.org.upskill.dto.PhoneDTO;
import pt.org.upskill.dto.RoleDTO;
import pt.org.upskill.ui.UI;

import static pt.org.upskill.ui.utils.UITools.showKeyValueList;
import static pt.org.upskill.ui.utils.Utils.readIntegerFromConsole;
import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class RegisterEmployeeUI extends UI {

    private final EmployeeController employeeController = new EmployeeController();


    public void run() {
        System.out.println("");
        System.out.println("CREATE EMPLOYEE");
        System.out.println("-----------");

        try {
            String name = readLineFromConsole("Name: ");
            String phone = readLineFromConsole("Phone: ");
            String email = readLineFromConsole("E-mail: ");
            showKeyValueList("Roles", new RoleController().keyValueDTOList());
            int key = readIntegerFromConsole("Select a role: ");


            //DTO creation
            EmployeeDTO employeeDTO = new EmployeeDTO.Builder()
                    .withName(name)
                    .withPhoneDTO(new PhoneDTO.Builder().withPhoneNumberDTO(phone).build())
                    .withEmailDTO(new EmailDTO.Builder().withDescription(email).build())
                    .withRoleDTO(new RoleDTO.Builder().withId(key).build())
                    .build();

            //data
            //CREATING NEW EMPLOYEE:
            employeeController.createEmployee(employeeDTO);

            //CONFIRMS AND SAVE
            employeeController.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
