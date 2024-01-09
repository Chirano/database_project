package pt.org.upskill.repository;

public interface Persistable {
    boolean save(Object object);
    boolean delete(Object object);
}
