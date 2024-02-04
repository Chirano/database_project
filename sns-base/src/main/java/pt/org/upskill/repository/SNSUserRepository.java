package pt.org.upskill.repository;

import pt.org.upskill.auth.Email;
import pt.org.upskill.db.FacilityDB;
import pt.org.upskill.db.SNSUserDB;
import pt.org.upskill.domain.*;
import pt.org.upskill.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pt.org.upskill.repository.JdbcRepository.conn;


public class SNSUserRepository implements PersistableRepo<SNSUser, Integer, String> {
    public SNSUserRepository() {
    }

    ;
    List<SNSUser> snsUserList = new ArrayList<>();

    public int nextId() {
        int maxId = 0;
        for (SNSUser snsUser : snsUserList) {
            if (snsUser.id() > maxId) {
                maxId = snsUser.id();
            }
            ;
        }
        return maxId + 1;
    }


    private Boolean validateSave(Object object) {
        return true;
    }

    private Boolean validateDelete(Object object) {
        return true;
    }

    public SNSUser createSNSUser(DTO dto) {
        SNSUserDTO snsUserDTO = (SNSUserDTO) dto;
        return new SNSUser.Builder()
                .withId(nextId())
                .withName(snsUserDTO.name())
                .withBirthdate(snsUserDTO.birthDate())
                .withGender(snsUserDTO.gender())
                .withAddress(new Address.Builder()
                        .withCityName(snsUserDTO.addressDTO().cityName())
                        .withStreetName(snsUserDTO.addressDTO().streetName())
                        .withPostalCode(snsUserDTO.addressDTO().postalCode()).build())
                .withEmail(new Email.Builder()
                        .withAddress(snsUserDTO.emailDTO().getDescription()).build())
                .build();
    }

    @Override
    public boolean save(SNSUser object) {
        try {
            conn.setAutoCommit(false);
            SNSUserDB snsUserDB = new SNSUserDB();
            snsUserDB.save(conn, object);
            conn.commit();
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
    public boolean delete(SNSUser object) {
        try {
            conn.setAutoCommit(false);
            SNSUserDB snsUserDB = new SNSUserDB();
            snsUserDB.delete(conn, (SNSUser) object);
            conn.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public SNSUser getById(Integer id) {
        return new SNSUserDB().getById(conn, id);

    }

    @Override
    public SNSUser getByBusinessId(String businessId) {
        return new SNSUserDB().getByBusinessId(conn, businessId);
    }

    @Override
    public List <SNSUser> getAll() {
        try {
            List<SNSUser> list = new ArrayList<>();
            String sqlCmd = "select * from SNS_USER";
            try (PreparedStatement ps = conn.prepareStatement(sqlCmd)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(buildFromResultSet(rs));
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public SNSUser buildFromResultSet(ResultSet resultSet) {
        try {

            String pattern = "MM-dd-yyyy";
            String date = resultSet.getString("birthDate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDate localdate = LocalDate.from(formatter.parse(date));
            Date birthdate = new Date((localdate.getYear()), localdate.getMonthValue(), localdate.getDayOfMonth());

//            Address address = new Address(
//                    resultSet.getString("address_streetname"),
//                    resultSet.getString("address_postalcode"),
//                    resultSet.getString("address_cityname"));
            Email email = new Email(resultSet.getString("email"));


            return new SNSUser.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    .withBirthdate(birthdate)
                    .withGender(resultSet.getString("gender"))
                    //.withAddress(address)
                    .withEmail(email)
                    .withCardNumber(resultSet.getString("card_number"))
                    .withUserNumber(resultSet.getString("sns_user_number"))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KeyValueDTO> keyValueDTOList(){
        List<KeyValueDTO> dtoList = new ArrayList<>();
        for (SNSUser snsUser : getAll()){
            SNSUserDTO dto = snsUser.toDTO();
            dtoList.add(new KeyValueDTO(dto.id().toString(), dto.name()));
        }
    return dtoList; }
}