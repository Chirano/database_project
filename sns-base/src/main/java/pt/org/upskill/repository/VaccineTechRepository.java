package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.db.VaccineTechDB;
import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.dto.VaccineTechDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VaccineTechRepository extends JdbcRepository implements PersistableRepo<VaccineTech, Integer, String> {

    public VaccineTech createVaccineTech(DTO dto) {
        VaccineTechDTO vaccineTechDTO = (VaccineTechDTO) dto;
        return new VaccineTech(vaccineTechDTO.id(), vaccineTechDTO.name(), vaccineTechDTO.description());
    }

    public List<KeyValueDTO> keyValueDTOList() {
        List<KeyValueDTO> dtoList = new ArrayList<>();
        for (VaccineTech item : getAll()) {
            VaccineTechDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO(dto.id().toString(), dto.name()));
        }
        return dtoList;
    }

    @Override
    public boolean save(VaccineTech object) {
        try {
            VaccineTechDB vaccineTechDB = new VaccineTechDB();
            vaccineTechDB.save(conn, object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(VaccineTech object) {
        try {
            VaccineTechDB vaccineTechDB = new VaccineTechDB();
            vaccineTechDB.delete(conn, object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public VaccineTech getById(Integer id) {
        return new VaccineTechDB().getById(conn, id);
    }

    @Override
    public VaccineTech getByBusinessId(String businessId) {
        return new VaccineTechDB().getByBusinessId(conn, businessId);
    }

    @Override
    public List<VaccineTech> getAll() {
        try {
            List<VaccineTech> list = new ArrayList<>();
            String sqlCmd = "select * from VaccineTech";
            try (PreparedStatement ps = conn.prepareStatement(sqlCmd)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(buildFromResultSet(rs));
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VaccineTechRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public VaccineTech buildFromResultSet(ResultSet resultSet) {
        try {
            return new VaccineTech(resultSet.getInt("id")
                    , resultSet.getString("name")
                    , resultSet.getString("description"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}