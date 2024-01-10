package pt.org.upskill.ui;

import pt.org.upskill.controller.VaccineController;
import pt.org.upskill.controller.VaccineTypeController;
import pt.org.upskill.controller.BrandController;
import pt.org.upskill.domain.Brand;
import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.domain.VaccineType;

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
            List<VaccineType> vaccineTypeList = vaccineTypeController.vaccineTypeList();
            System.out.println("Vaccine Types");
            for (VaccineType vaccineType : vaccineTypeList) {
                System.out.println(vaccineType.code() + " - " + vaccineType.shortDescription());
            }
            String vaccineTypeCode = readLineFromConsole("Select a vaccine type: ");

            List<Brand> brandList = brandController.brandList();
            System.out.println("Brands");
            for (Brand brand : brandList) {
                System.out.println(brand.name());
            }
            String brandName = readLineFromConsole("Select a brand: ");

            String vaccineName = readLineFromConsole("Vaccine Name: ");

            //Set data
            vaccineController.createVaccine(vaccineTypeCode, brandName, vaccineName);

            //Confirm
            vaccineController.confirm();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
