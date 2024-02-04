package pt.org.upskill.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

public interface PersistableRepo <P, TId, TBId> {
        boolean save(P object);

        boolean delete(P object);

        P getById(TId id);

        P getByBusinessId(TBId businessId);

        List<P> getAll();

        P buildFromResultSet(ResultSet resultSet);

        }