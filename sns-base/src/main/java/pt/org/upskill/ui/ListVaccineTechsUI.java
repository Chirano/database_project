package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.VaccineTechController;
import pt.org.upskill.dto.KeyValueDTO;

import java.util.List;

public class ListVaccineTechsUI extends UI {
    private final VaccineTechController vaccineTechController = new VaccineTechController();

    public void run() {
        System.out.println("");
        System.out.println("LIST VACCINE TECHNOLOGIES");
        System.out.println("-----------");

        try {
            List<KeyValueDTO<Integer>> dtoList = vaccineTechController.dtoList();
            for (KeyValueDTO<Integer> item : dtoList) {
                System.out.println(item.key + " - " + item.value);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
