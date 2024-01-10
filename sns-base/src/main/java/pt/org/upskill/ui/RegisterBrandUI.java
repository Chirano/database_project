package pt.org.upskill.ui;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.controller.BrandController;

import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class RegisterBrandUI extends UI {
    private final BrandController brandController = new BrandController();

    public void run() {
        System.out.println("");
        System.out.println("CREATE BRAND (Manufacturer)");
        System.out.println("-----------");

        try {
            String name = readLineFromConsole("Brand Name: ");

            //Set data
            brandController.createBrand(name);

            //Confirm
            brandController.confirm();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
