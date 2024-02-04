package pt.org.upskill.repository;
import pt.org.upskill.db.AppointmentDB;
import pt.org.upskill.db.ConnectionFactory;
import pt.org.upskill.db.DatabaseConnection;
import pt.org.upskill.db.FacilityDB;
import pt.org.upskill.domain.*;
import pt.org.upskill.dto.*;
import pt.org.upskill.session.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pt.org.upskill.repository.JdbcRepository.conn;

public class AppointmentRepository implements PersistableRepo<Appointment, Integer, Integer> {

    public AppointmentRepository() {}
    List<Appointment> appointmentList = new ArrayList<>();
    public int nextId() {
        int maxId = 0;
        for (Appointment appointment : getAll()) {
            if (appointment.id() > maxId) {
                maxId = appointment.id();
            };
        }
        return maxId+1;
    }
    public Appointment createAppointment(DTO dto){
        AppointmentDTO appointmentDTO = (AppointmentDTO) dto;
        VaccineTypeRepository vaccineTypeRepository = Repositories.getInstance().vaccineTypeRepository();
        FacilityRepository facilityRepository = Repositories.getInstance().facilityRepository();
        SNSUserRepository snsUserRepository = Repositories.getInstance().snsUserRepository();

        return new Appointment.Builder()
                .withFacility(facilityRepository.getById(appointmentDTO.facilityDTO().id()))
                .withVaccineType(vaccineTypeRepository.getByBusinessId(appointmentDTO.vaccineTypeDTO().code()))
                .withSNSUser(snsUserRepository.getByBusinessId(appointmentDTO.snsuserDTO().usernumber()))
                .withDate(appointmentDTO.date())
                .withHour(appointmentDTO.hour())
                .build()
                ;
    }

    private Boolean validateSave(Object object) {
        return true;
    }

    private Boolean validateDelete(Object object) {
        return true;
    }

    public boolean save(Appointment object) {
        ConnectionFactory cf = Context.getConnectionFactory();
        DatabaseConnection dbc = cf.getDatabaseConnection();
        Connection conn = dbc.getConnection();
        //
        try {
            conn.setAutoCommit(false); // indica o inicio da transacao, desativar autocommit
            //
            AppointmentDB appointmentDB = new AppointmentDB();
            appointmentDB.save(conn, object);
            //
            conn.commit(); // grava o que foi feito
            appointmentList.add(object);

            return true;
        } catch (SQLException e) {
            try {
                conn.rollback(); // no caso de erro, fazer o rollback
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean delete(Appointment object) {
        ConnectionFactory cf = Context.getConnectionFactory();
        DatabaseConnection dbc = cf.getDatabaseConnection();
        Connection conn = dbc.getConnection();
        //
        try {
            conn.setAutoCommit(false); // indica o inicio da transacao, desativar autocommit
            //
            AppointmentDB appointmentDB = new AppointmentDB();
            appointmentDB.delete(conn, (Appointment) object);
            //
            conn.commit(); // grava o que foi feito
            appointmentList.remove(object);
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Appointment getById(Integer id) {
        return new AppointmentDB().getById(conn, id);
    }

    @Override
    public Appointment getByBusinessId(Integer businessId) {
        return getById(businessId);
    }

    @Override
    public List<Appointment> getAll() {
        try {
            List<Appointment> list = new ArrayList<>();
            String sqlCmd = "select * from appointment";
            try (PreparedStatement ps = conn.prepareStatement(sqlCmd)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(buildFromResultSet(rs));
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VaccineRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Appointment buildFromResultSet(ResultSet resultSet) {
        try{
        Facility facility = new Facility.Builder().withId(resultSet.getInt("id")).build();
        VaccineType vaccineType = new VaccineType.Builder().withCode(resultSet.getString("code")).build();
        SNSUser snsUser = new SNSUser.Builder().withUserNumber(resultSet.getString("sns_user_number")).build();

        Appointment appointment = new Appointment.Builder()
                .withId(resultSet.getInt("id"))
                .withFacility(facility)
                .withVaccineType(vaccineType)
                .withSNSUser(snsUser)
                .withDate(resultSet.getDate("data"))
                .withHour(resultSet.getString("hour"))
                        .build();
        return appointment;
    }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KeyValueDTO> keyValueDTOList() {
        List<KeyValueDTO> dtoList = new ArrayList<>();
        for (Appointment item : getAll()) {
            AppointmentDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO(dto.id().toString(), dto.snsuserDTO().name() + dto.date().toString()));
        }
        return dtoList;
    }

    public boolean print(Appointment appointment) {
        try{
            AppointmentDTO appointmentDTO = appointment.toDTO();
            System.out.println(appointmentDTO.toString());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
