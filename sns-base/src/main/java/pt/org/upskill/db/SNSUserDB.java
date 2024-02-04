package pt.org.upskill.db;

import pt.org.upskill.domain.Facility;
import pt.org.upskill.domain.SNSUser;
import pt.org.upskill.repository.FacilityRepository;
import pt.org.upskill.repository.SNSUserRepository;
import pt.org.upskill.repository.VaccineRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SNSUserDB implements PersistableObject<SNSUser, Integer, String> {

    @Override
    public boolean save(Connection connection, SNSUser object) {
        String sqlCmd;
        sqlCmd = "select * from sns_user where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
            ps.setInt(1, object.id());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sqlCmd = "update sns_user set name = ?, birthdate = ?, gender = ?, email = ?, sns_user_number = ?" +
                            ", address_streetname = ?, address_cityname = ?, address_postalcode = ?" +
                            ", card_number = ? where id = ?";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                        ps2.setString(1, object.name());
                        if (object.birthDate() == null) {
                            ps2.setDate(2, null);
                        } else {
                            ps2.setString(2, new SimpleDateFormat("MM-dd-yyyy").format(object.birthDate()));
                        }
                        ps2.setString(3, object.gender());
                        ps2.setString(4, object.email().address());
                        ps2.setString(5, object.usernumber());
                        if (object.address() == null) {
                            ps2.setString(6, null);
                            ps2.setString(7, null);
                            ps2.setString(8, null);
                        } else {
                            ps2.setString(6, object.address().streetName());
                            ps2.setString(7, object.address().cityName());
                            ps2.setString(8, object.address().postalCode());

                        }
                        ps2.setString(9, object.cardnumber());
                        ps2.setInt(10, object.id());
                        ps2.executeUpdate();
                        return true;
                    }
                } else {
                    sqlCmd = "insert into sns_user(id, name, birthdate, gender, email, sns_user_number, " +
                            "address_streetname, address_cityname, address_postalcode, card_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                        ps2.setInt(1, object.id());
                        ps2.setString(2, object.name());
                        if (object.birthDate() == null) {
                            ps2.setDate(3, null);
                        } else {
                            ps2.setString(3, new SimpleDateFormat("MM-dd-yyyy").format(object.birthDate()));
                        }
                        ps2.setString(4, object.gender());
                        ps2.setString(5, object.email().address());
                        ps2.setString(6, object.usernumber());
                       if (object.address() == null) {
                            ps2.setString(7, null);
                            ps2.setString(8, null);
                            ps2.setString(9, null);
                        } else {
                            ps2.setString(7, object.address().streetName());
                            ps2.setString(8, object.address().cityName());
                            ps2.setString(9, object.address().postalCode());
                        }
                        ps2.setString(10, object.cardnumber());
                        ps2.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDB.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    @Override
    public boolean delete(Connection connection, SNSUser object) {
        try {
            String sqlCmd;
            sqlCmd = "delete from sns_user where id = ?";
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
    public SNSUser getById(Connection connection, Integer id) {
        try{
            String sqlCmd;
            sqlCmd = "select * from sns_user where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new SNSUserRepository().buildFromResultSet(rs);
                }
                return null;
        }
    } catch (SQLException ex) {
        Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    @Override
    public SNSUser getByBusinessId(Connection connection, String businessId) {
        try{
            String sqlCmd;
            sqlCmd = "select * from sns_user where sns_user_number = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setString(1, businessId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new SNSUserRepository().buildFromResultSet(rs);
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
