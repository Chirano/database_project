package pt.org.upskill.db;


import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.repository.VaccineTechRepository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VaccineTechDB implements PersistableObject<VaccineTech, Integer, String> {
    @Override
    public boolean save(Connection connection, VaccineTech object) {
        String sqlCmd;
        sqlCmd = "select * from vaccinetech where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
            if (object.id() == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, object.id());
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sqlCmd = "update VaccineTech set name = ?, description = ? where id = ?";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                        ps2.setString(1, object.name());
                        ps2.setString(2, object.description());
                        ps2.setInt(3, object.id());
                        ps2.executeUpdate();
                        return true;
                    }
                }
                else {
                    sqlCmd = "insert into VaccineTech(name, description, id) values (?, ?, ?)";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                        ps2.setString(1, object.name());
                        ps2.setString(2, object.description());
                        ps2.setInt(3, object.id());
                        ps2.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Connection connection, VaccineTech object) {
        try {
            String sqlCmd;
            sqlCmd = "delete from VaccineTech where id = ?";
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
    public VaccineTech getById(Connection connection, Integer id) {
        try {
            String sqlCmd;
            sqlCmd = "select * from vaccinetech where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new VaccineTechRepository().buildFromResultSet(rs);
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }

    @Override
    public VaccineTech getByBusinessId(Connection connection, String businessId) {
        try {
            String sqlCmd;
            sqlCmd = "select * from VaccineTech where name = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setString(1, businessId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new VaccineTechRepository().buildFromResultSet(rs);
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
