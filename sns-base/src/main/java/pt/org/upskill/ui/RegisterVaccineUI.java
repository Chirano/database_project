package pt.org.upskill.ui;

import pt.org.upskill.controller.VaccineController;
import pt.org.upskill.controller.VaccineTypeController;
import pt.org.upskill.controller.BrandController;
import pt.org.upskill.domain.Brand;
import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.domain.VaccineType;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.dto.VaccineDTO;

import java.util.List;

import static pt.org.upskill.ui.utils.Utils.readIntegerFromConsole;
import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class RegisterVaccineUI extends UI {

    private final VaccineController vaccineController = new VaccineController();
    private final VaccineTypeController vaccineTypeController = new VaccineTypeController();
    private final BrandController brandController = new BrandController();

    public void run() {
        System.out.println("");
        System.out.println("CREATE VACCINE");
        System.out.println("-----------");

        try {
            //System asks: vaccine type, brand, vaccine name
            System.out.println("Vaccine Types");
            List<KeyValueDTO<String>> keyValueDTOList = vaccineTypeController.dtoList();
            for (KeyValueDTO<String> item : keyValueDTOList) {
                System.out.println(item.key + " - " + item.value);
            }
            String vaccineTypeCode = readLineFromConsole("Select a vaccine type: ");

            System.out.println("Brands");
            List<KeyValueDTO<String>> dtoList = brandController.dtoList();
            for (KeyValueDTO<String> item : dtoList) {
                System.out.println(item.key + " - " + item.value);
            }
            String brandName = readLineFromConsole("Select a brand: ");

            String vaccineName = readLineFromConsole("Vaccine Name: ");

            //DTO
            VaccineDTO dto = new VaccineDTO();
            dto.name = vaccineName;
            dto.vaccineTypeCode = vaccineTypeCode;
            dto.brandName = brandName;

            //Registration
            vaccineController.register(dto);

            //Confirmation
            vaccineController.confirm();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
