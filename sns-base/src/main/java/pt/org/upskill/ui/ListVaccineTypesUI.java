package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.VaccineTechController;
import pt.org.upskill.controller.VaccineTypeController;
import pt.org.upskill.dto.KeyValueDTO;

import java.util.List;

public class ListVaccineTypesUI extends UI {

    private final VaccineTypeController vaccineTypeController = new VaccineTypeController();
    private final VaccineTechController vaccineTechController = new VaccineTechController();

    public void run() {
        System.out.println("");
        System.out.println("LIST VACCINE TYPES");
        System.out.println("-----------");

        try {
            List<KeyValueDTO<String>> dtoList = vaccineTypeController.dtoList();
            for (KeyValueDTO<String> item : dtoList) {
                System.out.println(item.key + " - " + item.value);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
