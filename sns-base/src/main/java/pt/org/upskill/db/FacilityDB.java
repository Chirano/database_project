package pt.org.upskill.db;

import pt.org.upskill.auth.Email;
import pt.org.upskill.domain.Address;
import pt.org.upskill.domain.Facility;
import pt.org.upskill.domain.Phone;
import pt.org.upskill.domain.Website;
import pt.org.upskill.repository.FacilityRepository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacilityDB implements PersistableObject<Facility, Integer, Integer> {
    @Override
    public boolean save(Connection connection, Facility object) {
        String sqlCmd;
        sqlCmd = "select * from Facility where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
            if (object.id() == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, object.id());
            }
            try (ResultSet rs = ps.executeQuery()) { // result set no banco de dados após a execução do comando
                if (rs.next()) {    // rs.next passa e entra na proxima linha se ela existir
                    sqlCmd = "update Facility set name = ?  where id = '?'";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                        ps2.setString(1, object.name()); //
                        ps2.setInt(2, object.id());
                        ps2.executeUpdate(); //manda executar o comando da string no sql
                        return true;
                    }
                } else { // nesse caso o id não existe, então o comando será o insert
                    sqlCmd = "insert into Facility (name) values (?)";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                        ps2.setString(1, object.name()); //
                        ps2.executeUpdate(); //manda executar o comando da string no sql
                    return true;
                }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    @Override
    public boolean delete(Connection connection, Facility object) {
        try {
            String sqlCmd;
            sqlCmd = "delete from Facility where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, object.id());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Facility getById(Connection connection, Integer id) {
        try {
            String sqlCmd;
            sqlCmd = "select * from Facility where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new FacilityRepository().buildFromResultSet(rs);
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Facility getByBusinessId(Connection connection, Integer businessId) {
        return getById(connection, businessId);
    }

}
