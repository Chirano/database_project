package pt.org.upskill.repository;

import pt.org.upskill.db.VaccineTypeDB;
import pt.org.upskill.domain.VaccineTech;
import pt.org.upskill.domain.VaccineType;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.dto.VaccineTypeDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pt.org.upskill.repository.JdbcRepository.conn;

public class VaccineTypeRepository extends JdbcRepository implements PersistableRepo <VaccineType, String, String>{
    public VaccineType createVaccineType(DTO dto) {
        VaccineTypeDTO vaccineTypeDTO = (VaccineTypeDTO) dto;
        VaccineTechRepository vaccineTechRepository = Repositories.getInstance().vaccineTechRepository();
        return new VaccineType(vaccineTypeDTO.code(), vaccineTypeDTO.shortDescription(), vaccineTechRepository.getById(vaccineTypeDTO.vaccineTechDTO().id()));
    }

    @Override
    public boolean save(VaccineType object) {
        try {
            VaccineTypeDB vaccineTypeDB = new VaccineTypeDB();
            vaccineTypeDB.save(conn, object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(VaccineType object) {
        try {
            VaccineTypeDB vaccineTypeDB = new VaccineTypeDB();
            vaccineTypeDB.delete(conn, object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public VaccineType getById(String id) {
        return new VaccineTypeDB().getById(conn, id);
    }

    @Override
    public VaccineType getByBusinessId(String businessId) {
        return new VaccineTypeDB().getByBusinessId(conn, businessId);
    }

    @Override
    public List<VaccineType> getAll() {
        try {
            List<VaccineType> list = new ArrayList<>();
            String sqlCmd = "select * from VaccineType order by 1";
            try (PreparedStatement ps = conn.prepareStatement(sqlCmd)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(buildFromResultSet(rs));
                }
                return list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VaccineTypeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public VaccineType buildFromResultSet(ResultSet resultSet) {
        try {
            VaccineTechRepository vaccineTechRepository = new VaccineTechRepository();
            VaccineTech vaccineTech = vaccineTechRepository.getById(resultSet.getInt("vaccinetechid"));
            return new VaccineType(resultSet.getString("code")
                    , resultSet.getString("shortdescription")
                    , vaccineTech);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<KeyValueDTO> keyValueDTOList() {
        List<KeyValueDTO> dtoList = new ArrayList<>();
        for (VaccineType item : getAll()) {
            VaccineTypeDTO dto = item.toDTO();
            dtoList.add(new KeyValueDTO(dto.code(), dto.shortDescription()));
        }
        return dtoList;
    }
}
