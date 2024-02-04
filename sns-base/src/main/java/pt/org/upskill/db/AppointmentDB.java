package pt.org.upskill.db;
import pt.org.upskill.domain.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDB implements PersistableObject<Appointment, Integer, Integer> {
    @Override
    public boolean delete(Connection connection, Appointment object) {
        try {
            String sqlCmd;
            sqlCmd = "delete from Appointment where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, object.id());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean save(Connection connection, Appointment object) {
        String sqlCmd;
        sqlCmd = "select * from appointment where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
            if (object.id() == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, object.id());
            }
            try (ResultSet rs = ps.executeQuery()) { // result set no banco de dados após a execução do comando
                if (rs.next()) {    // rs.next passa e entra na proxima linha se ela existir
                    sqlCmd = "update appointment set vaccination_center_id = ?, vaccinetypecode = ?, snsusernumber = ?, data = ?, hour = ? where id = ?";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                        ps2.setInt(1, object.facility().id());
                        ps2.setString(2, object.vaccineType().code());
                        ps2.setString(3, object.snsuser().usernumber());
                        ps2.setString(4, new SimpleDateFormat("MM-dd-yyyy").format(object.date()));
                        ps2.setString(5, object.strHour());
                        ps2.setInt(6, object.id());
                        ps2.executeUpdate(); //manda executar o comando da string no sql
                        return true;
                    }
                }
                else { // nesse caso o id não existe, então o comando será o insert
                    sqlCmd = "insert into appointment(vaccination_center_id, vaccinetypecode, snsusernumber, data, hour) values (?, ?, ?, ?, ?)";
                    try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)){

                        ps2.setInt(1, object.facility().id());
                        ps2.setString(2, object.vaccineType().code());
                        ps2.setString(3, object.snsuser().usernumber());
                        ps2.setString(4, new SimpleDateFormat("MM-dd-yyyy").format(object.date()));
                        ps2.setString(5, object.strHour());
                        ps2.executeUpdate();
                        return true;
                    }
                }
                //
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDB.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    @Override
    public Appointment getById(Connection connection, Integer id) {
        try {
            String sqlCmd;
            sqlCmd = "select * from appointment where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Appointment appointment = null;
                    try {
                        appointment = new Appointment.Builder()
                                .withId(rs.getInt("id"))
                                .withFacility(new Facility(
                                        rs.getInt("vaccination_center_id")))
                                .withVaccineType(new VaccineType(
                                        rs.getString("vaccinetypecode")))
                                .withSNSUser(new SNSUser(
                                        rs.getInt("snsusernumber")))
                                .withDate(new Date(rs.getString("data")))
                                .withHour((rs.getString("hour")))
                                .build();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return appointment;
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Appointment getByBusinessId(Connection connection, Integer businessId) {
        return getById(connection, businessId);
    }
}
