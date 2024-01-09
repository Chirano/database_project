package pt.org.upskill.ui;

import pt.org.upskill.controller.VaccineTechController;

import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class RegisterVaccineTechUI extends UI {
    private final VaccineTechController vaccineTechController = new VaccineTechController();

    public void run() {
        System.out.println("");
        System.out.println("CREATE VACCINE TECHNOLOGY");
        System.out.println("-----------");

        try {
            String name = readLineFromConsole("Vaccine Technology Name: ");
            String description = readLineFromConsole("Vaccine Technology Description: ");

            //Set data
            vaccineTechController.createVaccineTech(name, description);

            //Confirm
            vaccineTechController.confirm();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
