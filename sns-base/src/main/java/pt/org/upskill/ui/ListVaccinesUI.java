package pt.org.upskill.ui;

import pt.org.upskill.controller.BrandController;
import pt.org.upskill.controller.VaccineController;
import pt.org.upskill.controller.VaccineTypeController;
import pt.org.upskill.dto.VaccineDTO;

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
            List<VaccineDTO> vaccineList = vaccineController.vaccineList();
            for (VaccineDTO vaccineDTO : vaccineList) {
                String s = String.format("%s - Type: %s - Technology: %s",
                        vaccineDTO.name,
                        vaccineDTO.vaccineTypeDTO.shortDescription + "(" + vaccineDTO.vaccineTypeDTO.code + ")",
                        vaccineDTO.vaccineTypeDTO.vaccineTechDTO.name + "(" + vaccineDTO.vaccineTypeDTO.vaccineTechDTO.id + ")");
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
