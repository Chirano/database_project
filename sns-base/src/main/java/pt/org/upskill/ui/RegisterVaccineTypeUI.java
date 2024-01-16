package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.VaccineTechController;
import pt.org.upskill.controller.VaccineTypeController;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.dto.VaccineTypeDTO;

import java.util.List;

import static pt.org.upskill.ui.utils.Utils.readIntegerFromConsole;
import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class RegisterVaccineTypeUI extends UI {
    private final VaccineTypeController vaccineTypeController = new VaccineTypeController();
    private final VaccineTechController vaccineTechController = new VaccineTechController();

    public void run() {
        System.out.println("");
        System.out.println("CREATE VACCINE TYPE");
        System.out.println("-----------");

        try {
            //System asks: code, short description, vaccine technology
            String code = readLineFromConsole("Vaccine Type Code: ");
            String shortDescription = readLineFromConsole("Vaccine Type Short Description: ");
            System.out.println("Vaccine Technologies");
            List<KeyValueDTO<Integer>> keyValueDTOList = vaccineTechController.dtoList();
            for (KeyValueDTO<Integer> item : keyValueDTOList) {
                System.out.println(item.key + " - " + item.value);
            }
            int key = readIntegerFromConsole("Select a vaccine technology: ");

            //DTO creation
            VaccineTypeDTO dto = new VaccineTypeDTO();
            dto.code = code;
            dto.shortDescription = shortDescription;
            dto.vaccineTechId = key;

            //Registration
            vaccineTypeController.register(dto);

            //confirmation
            vaccineTypeController.confirm();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
