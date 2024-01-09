package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.VaccineTechController;
import pt.org.upskill.domain.VaccineTech;

import java.util.List;

public class ListVaccineTechsUI extends UI {
    private final VaccineTechController vaccineTechController = new VaccineTechController();

    public void run() {
        System.out.println("");
        System.out.println("LIST VACCINE TECHNOLOGIES");
        System.out.println("-----------");

        try {
            List<VaccineTech> vaccineTechList = vaccineTechController.vaccineTechList();
            for (VaccineTech vaccineTech : vaccineTechList) {
                System.out.println(vaccineTech.id() + " - " + vaccineTech.name() + " - " + vaccineTech.description());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
