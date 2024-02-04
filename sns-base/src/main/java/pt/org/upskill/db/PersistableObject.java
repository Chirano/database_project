package pt.org.upskill.db;

import pt.org.upskill.domain.VaccineType;

import java.sql.Connection;

public interface PersistableObject<P, TId, TBid> {
    public boolean save(Connection connection, P object);
    public boolean delete(Connection connection, P object);
    public P getById(Connection connection, TId id);
    public P getByBusinessId(Connection connection, TBid id);

}
