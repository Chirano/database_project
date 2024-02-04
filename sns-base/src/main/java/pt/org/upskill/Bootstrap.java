package pt.org.upskill;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.auth.Email;
import pt.org.upskill.auth.Password;
import pt.org.upskill.auth.User;
import pt.org.upskill.domain.*;
import pt.org.upskill.repository.*;
import pt.org.upskill.session.Context;
import pt.org.upskill.ui.*;
import pt.org.upskill.ui.menu.Drawable;
import pt.org.upskill.ui.menu.Menu;
import pt.org.upskill.ui.menu.MenuDrawer;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import static pt.org.upskill.repository.RoleRepository.*;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addRoles();
        addUsers();
        addMenus();
        addVaccineTech();
        addFacilities();
        addSNSUser();
        addVaccineType();
        //addAppointment();

    }
    private void addRoles() {
        //TODO: add application user roles here
        RoleRepository roleRepository = Repositories.getInstance().roleRepository();

        roleRepository.add(new Role(1, ROLE_ADMIN));
        roleRepository.add(new Role(2, ROLE_NURSE));
        roleRepository.add(new Role(3, ROLE_RECEPTIONIST));
        roleRepository.add(new Role(4, ROLE_SNSUSER));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        UserRepository userRepository = Repositories.getInstance().userRepository();
        RoleRepository roleRepository = Repositories.getInstance().roleRepository();

        try {
            userRepository.add(new User("adm", roleRepository.getRoleByName(ROLE_ADMIN), new Email("admin@upskill.pt"), new Password("admin")));
            userRepository.add(new User("nur", roleRepository.getRoleByName(ROLE_NURSE), new Email("nurse@upskill.pt"), new Password("nurse")));
            userRepository.add(new User("rec", roleRepository.getRoleByName(ROLE_RECEPTIONIST), new Email("receptionist@upskill.pt"), new Password("receptionist")));
            userRepository.add(new User("usr", roleRepository.getRoleByName(ROLE_SNSUSER), new Email("snsuser@upskill.pt"), new Password("snsuser")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addVaccineType(){
        VaccineTypeRepository vaccineTypeRepository = Repositories.getInstance().vaccineTypeRepository();
        VaccineTechRepository vaccineTechRepository = Repositories.getInstance().vaccineTechRepository();
        try{
            VaccineType vt1 = new VaccineType.Builder()
                    .withCode("001")
                    .withShortDescription("Type 001")
                    .withVaccineTech(vaccineTechRepository.getById(1))
                    .build();
            VaccineType vt2 = new VaccineType.Builder()
                    .withCode("002")
                    .withShortDescription("Type 002")
                    .withVaccineTech(vaccineTechRepository.getById(2))
                    .build();
            VaccineType vt3 = new VaccineType.Builder()
                    .withCode("003")
                    .withShortDescription("Type 003")
                    .withVaccineTech(vaccineTechRepository.getById(3))
                    .build();
            vaccineTypeRepository.save(vt1);
            vaccineTypeRepository.save(vt2);
            vaccineTypeRepository.save(vt3);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addAppointment() {
        AppointmentRepository appointmentRepository = Repositories.getInstance().appointmentRepository();
        SNSUserRepository snsUserRepository = Repositories.getInstance().snsUserRepository();
        FacilityRepository facilityRepository = Repositories.getInstance().facilityRepository();
        VaccineTypeRepository vaccineTypeRepository = Repositories.getInstance().vaccineTypeRepository();
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String strDate1 = "02-04-2024";
            String strDate2 = "12-03-2024";
            Date date1 = df.parse(strDate1);
            Date date2 = df.parse(strDate2);
            Appointment ap1 = new Appointment.Builder()
                    .withId(1)
                    .withDate(date1)
                    .withHour(LocalTime.of(12,30))
                    .withSNSUser(snsUserRepository.getById(1))
                    .withFacility(facilityRepository.getById(3))
                    .withVaccineType(vaccineTypeRepository.getByBusinessId("001")).build();
            Appointment ap2 = new Appointment.Builder()
                    .withId(2)
                    .withDate(date2)
                    .withHour(LocalTime.of(15,00))
                    .withSNSUser(snsUserRepository.getById(2))
                    .withFacility(facilityRepository.getById(1))
                    .withVaccineType(vaccineTypeRepository.getByBusinessId("002")).build();
            appointmentRepository.save(ap1);
            appointmentRepository.save(ap2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addSNSUser(){
        SNSUserRepository snsUserRepository = Repositories.getInstance().snsUserRepository();
        try{
            SNSUser su1 = new SNSUser.Builder()
                    .withId(1)
                    .withName("Magno Carvalho")
                    .withBirthdate(new Date("1996/07/08"))
                    .withGender("Homem")
                    .withAddress(new Address("Rua X", "Amarante", "4600-011"))
                    .withEmail(new Email.Builder().withAddress("carvalho@gmail.com").build())
                    .withCardNumber("1234")
                    .withUserNumber("123.456")
                    .build();
            SNSUser su2 = new SNSUser.Builder()
                    .withId(2)
                    .withName("Thiago Theodoro")
                    .withBirthdate(new Date("1989/05/04"))
                    .withGender("Homem")
                    .withAddress(new Address("Rua Y", "Porto", "4602-011"))
                    .withEmail(new Email.Builder().withAddress("tt@gmail.com").build())
                    .withCardNumber("5678")
                    .withUserNumber("789.112")
                    .build();
            SNSUser su3 = new SNSUser.Builder()
                    .withId(3)
                    .withName("Eduarda Oliveira")
                    .withBirthdate(new Date("1995/10/02"))
                    .withGender("Mulher")
                    .withAddress(new Address("Rua Z", "Braga", "4609-011"))
                    .withEmail(new Email.Builder().withAddress("eo@gmail.com").build())
                    .withCardNumber("9112")
                    .withUserNumber("131.141")
                    .build();
            snsUserRepository.save(su1);
            snsUserRepository.save(su2);
            snsUserRepository.save(su3);
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    private void addVaccineTech() {
        VaccineTechRepository vaccineTechRepository = Repositories.getInstance().vaccineTechRepository();
        try{
            VaccineTech vt1  = new VaccineTech(1,"Tech01", "Technology 01");
            VaccineTech vt2  = new VaccineTech( 2,"Tech02", "Technology 02");
            VaccineTech vt3  = new VaccineTech(3, "Tech03", "Technology 03");
            vaccineTechRepository.save(vt1);
            vaccineTechRepository.save(vt2);
            vaccineTechRepository.save(vt3);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addFacilities() {
        FacilityRepository facilityRepository = Repositories.getInstance().facilityRepository();

        try {
            Facility f1 = new Facility.Builder()

                    .withName("Centro de Saúde de Amarante")
                    .withAddress(new Address("Rua X", "4600-011", "Amarante"))
                    .withPhone(new Phone("255 123 456"))
                    .withEmail(new Email("csa@csa.pt"))
                    .withOpeningHour(LocalTime.of(9,0))
                    .withClosingingHour(LocalTime.of(19,30))
                    .withMaxVaccinesPerHour(120)
                    .build();
            Facility f2 = new Facility.Builder()

                    .withName("Centro de Saúde do Porto")
                    .withAddress(new Address("Rua da saúde", "4000-311", "Porto"))
                    .build();
            Facility f3 = new Facility.Builder()

                    .withName("Centro de Saúde de Braga")
                    .build();
            facilityRepository.save(f1);
            facilityRepository.save(f2);
            facilityRepository.save(f3);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMenus() {
        //This is where we define the menu structure and respective permissions.
        RoleRepository roleRepository = Repositories.getInstance().roleRepository();

        Menu menu;

        Drawable drawer = new MenuDrawer();
        Context.getInstance().setDrawer(drawer);

        Menu mainMenu = new Menu(null, 1,"Main Menu", null);
        Context.getInstance().setMainMenu(mainMenu);

        //Everyone
        Menu menuLogin = new Menu(Context.getInstance().mainMenu(), 1,"Login", new LoginUI());
        menu = new Menu(Context.getInstance().mainMenu(), 2,"Logout", new LogoutUI());
        menu = new Menu(Context.getInstance().mainMenu(), 9,"About", new AboutUI());

        //Admin
        //menu = new Menu(menuLogin, 1, "Register User", new CreateUserUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
//        menu = new Menu(menuLogin, 2, "Register Vaccine Technology", new RegisterVaccineTechUI());
//        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
//        menu = new Menu(menuLogin, 3, "Register Vaccine Type", new RegisterVaccineTypeUI());
//        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 4, "Register Employee", new RegisterEmployeeUI());
        menu.addPermission((Role) roleRepository.getRoleByName(ROLE_ADMIN));
//        menu = new Menu(menuLogin, 4, "Register Vaccine", new RegisterVaccineUI());
//        menu.addPermission((Role) roleRepository.getRoleByName(ROLE_ADMIN));
        //menu = new Menu(menuLogin, 6, "Register Vaccination Center", new RegisterVaccinationCenterUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        //menu = new Menu(menuLogin, 11, "List Employees With Role", new ListEmployeesWithRoleUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
//        menu = new Menu(menuLogin, 12, "List Vaccine Technologies", new ListVaccineTechsUI());
//        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
//        menu = new Menu(menuLogin, 13, "List Vaccine Types", new ListVaccineTypesUI());
//        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
//        menu = new Menu(menuLogin, 14, "List Vaccines", new ListVaccinesUI());
//        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));

        //Receptionist
        menu = new Menu(menuLogin, 1, "Register SNS User", new RegisterUserUI());
        menu.addPermission((Role) roleRepository.getRoleByName(ROLE_RECEPTIONIST));
        menu = new Menu(menuLogin, 2, "Schedule Vaccination", new ScheduleVaccinationOnBehalfOfUserUI());
        menu.addPermission((Role) roleRepository.getRoleByName(ROLE_RECEPTIONIST));
        //menu = new Menu(menuLogin, 3, "Register SNS User Arrival", new RegisterUserArrivalUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_RECEPTIONIST));

        //SNS USer
        //menu = new Menu(menuLogin, 1, "Schedule Vaccination", new ScheduleVaccinationUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_RECEPTIONIST));

        //Nurse
        //menu = new Menu(menuLogin, 1, "List User Waiting", new ListUsersWaitingUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_NURSE));
    }
}