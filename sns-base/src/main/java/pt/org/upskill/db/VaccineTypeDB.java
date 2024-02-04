package pt.org.upskill.db;
import pt.org.upskill.domain.VaccineType;
import pt.org.upskill.repository.VaccineTypeRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VaccineTypeDB implements PersistableObject<VaccineType, String, String> {
    @Override
    public boolean save(Connection connection, VaccineType object) {
        String sqlCmd;
        sqlCmd = "select * from vaccinetype where code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
            ps.setString(1, object.code());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sqlCmd = "update vaccinetype set shortdescription = ?, vaccinetechid = ? where code = ?";
                }
                else {
                    sqlCmd = "insert into vaccinetype(shortdescription, vaccinetechid, code) values (?, ?, ?)";
                }
                //
                try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                    ps2.setString(1, object.shortDescription());
                    ps2.setInt(2, object.vaccineTech().id());
                    ps2.setString(3, object.code());
                    ps2.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Connection connection, VaccineType object) {
        try {
            String sqlCmd;
            sqlCmd = "delete from VaccineType where code = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setString(1, object.code());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public VaccineType getById(Connection connection, String id) {
        try {
            String sqlCmd;
            sqlCmd = "select * from VaccineType where code = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setString(1, String.valueOf(id));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new VaccineTypeRepository().buildFromResultSet(rs);
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public VaccineType getByBusinessId(Connection connection, String businessId) {
        return getById(connection, businessId);
    }
}
