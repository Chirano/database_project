package pt.org.upskill.ui;

import pt.org.upskill.controller.BrandController;
import pt.org.upskill.controller.VaccineController;
import pt.org.upskill.controller.VaccineTypeController;
import pt.org.upskill.dto.KeyValueDTO;

import java.util.List;

public class ListVaccinesUI extends UI {
    private final VaccineController vaccineController = new VaccineController();
    private final VaccineTypeController vaccineTypeController = new VaccineTypeController();
    private final BrandController brandController = new BrandController();

    public void run() {
        System.out.println("");
        System.out.println("LIST VACCINES");
        System.out.println("-----------");

        try {
            List<KeyValueDTO<Integer>> dtoList = vaccineController.dtoList();
            for (KeyValueDTO<Integer> item : dtoList) {
                System.out.println(item.key + " - " + item.value);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
