package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

public interface Persistable {
    boolean save(Object object);
    boolean delete(Object object);
}
