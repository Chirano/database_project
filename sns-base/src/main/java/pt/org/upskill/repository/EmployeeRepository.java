package pt.org.upskill.repository;

import pt.org.upskill.auth.Email;
import pt.org.upskill.db.ConnectionFactory;
import pt.org.upskill.db.DatabaseConnection;
import pt.org.upskill.db.EmployeeDB;
import pt.org.upskill.domain.Employee;
import pt.org.upskill.domain.Phone;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.EmployeeDTO;
import pt.org.upskill.session.Context;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Persistable {
    private List<Employee> employeeList = new ArrayList<>();

    /**
     * Criar um novo employee a partir de um objeto do tipo DTO
     * @param dto Objeto do tipo DTO
     * @return Um novo employee
     * @throws Exception
     */
    public Employee createEmployee(DTO dto) throws Exception {
        //Converter o dto passado como parâmetro para EmployeeDTO
        EmployeeDTO employeeDTO = (EmployeeDTO) dto;
        RoleRepository roleRepository = Repositories.getInstance().roleRepository();
        //Criar um Employee a partir da extração dos valores dos atributos de EmployeeDTO
        return new Employee(
                new Email(employeeDTO.getEmailDTO().getDescription()),
                employeeDTO.getName(),
                roleRepository.getById(employeeDTO.getRoleDTO().getId()),
                new Phone(employeeDTO.getPhoneDTO().getPhoneNumber()));
    }

    /**
     * Método para definir o identificador do employee
     * @return Identificador do employee
     */
    public int nextId() {
        int maxId = 0;
        for (Employee employee : employeeList) {
            if (employee.getId() > maxId) {
                maxId = employee.getId();
            };
        }
        return maxId+1;
    }


    /**
     * Method that returns a list of all employees
     * @return List of all employees
     */
    public List<Employee> getAllEmployeesList() { return new ArrayList<>(this.employeeList) ;}

    @Override
    public boolean save(Object object) {
        ConnectionFactory connectionFactory = Context.getConnectionFactory();
        DatabaseConnection databaseConnection = connectionFactory.getDatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        //Usar try catch para poder fazer rollback em caso de erros.
        try {
            //O default do java é autocommit true, por isso faz-se o false para colocar a minha sessão da base de dados num modo não autocommit.
            connection.setAutoCommit(false);
            //Cada objeto persistível deve ter uma classe DB.
            EmployeeDB employeeDB = new EmployeeDB();
            //Gravar usando essa connection criada o object.
            employeeDB.save(connection, (Employee) object);
            //Torna a informação persistente na base de dados.
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                //Em caso de erros, faz rollback.
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean delete(Object object) {
        this.employeeList.remove((Employee) object);
        return true;
    }

}
