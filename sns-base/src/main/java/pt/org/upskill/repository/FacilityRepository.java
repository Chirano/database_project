package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.auth.Email;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pt.org.upskill.repository.JdbcRepository.conn;

public class FacilityRepository implements PersistableRepo <Facility, Integer, Integer> {

    public FacilityRepository() {}

    private List<Facility> facilityList = new ArrayList<>();

    public int nextId() {
        int maxId = 0;
        for (Facility facility : facilityList) {
            if (facility.id() > maxId) {
                maxId = facility.id();
            };
        }
        return maxId+1;
    }


    public Facility getById(Integer id) {
        return new FacilityDB().getById(conn, id);
    }


    @Override
    public Facility getByBusinessId(Integer businessId) {
        return new FacilityDB().getById(conn, businessId);
    }


    private Boolean validateSave(Facility facility) {
        return true;
    }

    private Boolean validateDelete(Facility facility) {
        return true;
    }

    public Facility createFacility(DTO dto) throws Exception {
        FacilityDTO facilityDTO = (FacilityDTO) dto;
        return new Facility.Builder()
                .withId(facilityDTO.id())
                .withName(facilityDTO.name())
                .withAddress(new Address(
                        facilityDTO.addressDTO().streetName(),
                        facilityDTO.addressDTO().postalCode(),
                        facilityDTO.addressDTO().cityName()))
                .withPhone(new Phone(facilityDTO.phoneDTO().getPhoneNumber()))
                .withEmail(new Email(facilityDTO.emailDTO().getDescription()))
                .withFax(new Phone(facilityDTO.faxDTO().getPhoneNumber()))
                .withWebsite(new Website(facilityDTO.websiteDTO().address()))
                //.withOpeningHour(facilityDTO.openingHour())
                //.withClosingingHour(facilityDTO.closingHour())
                .withMaxVaccinesPerHour(facilityDTO.maxVaccinesPerHour())
                .build();
    }

    @Override
    public boolean save(Facility object) {
        /*
        //Version without database persistence
        Facility facility = (Facility) object;
        if (validateSave(facility)) {
            facilityList.add(facility);
            return true;
        }
        return false;
         */
        ConnectionFactory cf = Context.getConnectionFactory();
        DatabaseConnection dbc = cf.getDatabaseConnection();
        Connection conn = dbc.getConnection();
        //
        try {
            conn.setAutoCommit(false); // indica o inicio da transacao, desativar autocommit
            //
            FacilityDB facilityDB = new FacilityDB();
            facilityDB.save(conn, object);
            //
            conn.commit(); // grava o que foi feito
            facilityList.add(object);
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
    public boolean delete(Facility object) {
        ConnectionFactory cf = Context.getConnectionFactory();
        DatabaseConnection dbc = cf.getDatabaseConnection();
        Connection conn = dbc.getConnection();
        //
        try {
            conn.setAutoCommit(false);
            //
            FacilityDB facilityDB = new FacilityDB();
            facilityDB.delete(conn, (Facility) object);
            //
            conn.commit();
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

    public List<Facility> facilityList() {
        return facilityList;
    }

    public List<KeyValueDTO> keyValueDTOList() {
        List<KeyValueDTO> dtoList = new ArrayList<>();
        for (Facility item : getAll()) {
            FacilityDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO(dto.id().toString(), dto.name()));
        }
        return dtoList;
    }

    @Override
    public List<Facility> getAll() {
        try {
            List<Facility> list = new ArrayList<>();
            String sqlCmd = "select * from Facility";
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
    public Facility buildFromResultSet(ResultSet resultSet) {
        try {
            Address address = new Address(
                    resultSet.getString("address"));
//                    resultSet.getString("address_postalcode"),
//                    resultSet.getString("address_cityname"));
//           Email email = new Email(resultSet.getString("email"));
            Phone phone = new Phone(resultSet.getString("phone"));
//            Phone fax = new Phone(resultSet.getString("fax"));
            Website website = new Website(resultSet.getString("website"));

            return new Facility.Builder()
                    .withId(resultSet.getInt("id"))
                    .withName(resultSet.getString("name"))
                    //.withAddress(address)
                    //.withEmail(email)
                    //.withPhone(phone)
                    //.withPhone(fax)
                    //.withWebsite(website)
                    //.withOpeningHour(resultSet.getDouble("openinghour"))
                    //.withClosingingHour(resultSet.getDouble("closinghour"))
                    //.withMaxVaccinesPerHour(resultSet.getInt("maxvaccinesperhour"))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}