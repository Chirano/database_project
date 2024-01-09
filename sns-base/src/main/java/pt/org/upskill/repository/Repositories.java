package pt.org.upskill.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
    private Repositories() {  }
    public static Repositories getInstance() {
        return instance;
    }

    RoleRepository roleRepository = new RoleRepository();
    UserRepository userRepository = new UserRepository();

    public RoleRepository roleRepository() {
        return roleRepository;
    }
    public UserRepository userRepository() {
        return userRepository;
    }

    //Your code here
    VaccineTechRepository vaccineTechRepository = new VaccineTechRepository();
    VaccineTypeRepository vaccineTypeRepository = new VaccineTypeRepository();

    public VaccineTechRepository vaccineTechRepository() {return vaccineTechRepository;}
    public VaccineTypeRepository vaccineTypeRepository() {return vaccineTypeRepository;}
}