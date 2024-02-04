package pt.org.upskill.db;

import pt.org.upskill.auth.Email;
import pt.org.upskill.domain.Employee;
import pt.org.upskill.domain.Phone;
import pt.org.upskill.domain.Role;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDB implements PersistableObject<Employee, Integer, Integer> {

    @Override
    public boolean save(Connection connection, Employee object) {
        String sqlCmd;
        //Identificar se o employee com o id do object já existe na BD. Se existir, é um update. Se não, é um insert.
        sqlCmd = "select * from Employee where id = ?";
        //PreparedStatement são strings que se cria como quiser e pode ter parâmetros.
        try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
            if (object.getId() == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, object.getId());
            }
            try (ResultSet rs = ps.executeQuery()) {
                //Se há esse id na tabela, faz o update.
                if (rs.next()) {
                    sqlCmd = "update Employee set email = ?, name = ?, roleid = ?, phonenumber = ? where id = ?";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                        ps2.setObject(1, object.getEmail().getDescription());
                        ps2.setString(2, object.getName());
                        ps2.setObject(3, object.getRole().getId());
                        ps2.setObject(4, object.getPhoneNumber().getPhoneNumber());
                        ps2.setObject(5, object.getId());
                        ps2.executeUpdate();
                        return true;
                    }

                } else {
                    //Se não há esse id, faz-se a inserção.
                    sqlCmd = "insert into Employee(email, name, roleid, phonenumber) values (?, ?, ?, ?)";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                        ps2.setObject(1, object.getEmail().getDescription());
                        ps2.setString(2, object.getName());
                        ps2.setObject(3, object.getRole().getId());
                        ps2.setObject(4, object.getPhoneNumber().getPhoneNumber());
                        ps2.executeUpdate();
                        return true;
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    @Override
    public boolean delete(Connection connection, Employee object) {
        try {
            String sqlCmd;
            sqlCmd = "delete from Employee where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, object.getId());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Employee getById(Connection connection, Integer id) {
        try {
            String sqlCmd;
            sqlCmd = "select * from Employee where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Employee employee = null;
                    try {
                        employee = new Employee(rs.getInt("id"),
                                new Email(rs.getString("email")),
                                rs.getString("name"),
                                new Role(rs.getInt("id"), rs.getString("role")),
                                new Phone(rs.getString("phone")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return employee;
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Employee getByBusinessId(Connection connection, Integer id) {
        return getById(connection, id);
    }

}
