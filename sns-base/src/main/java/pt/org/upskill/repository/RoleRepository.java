package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Role;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.dto.RoleDTO;

import java.util.ArrayList;
import java.util.List;


public class RoleRepository implements Persistable {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_SNSUSER = "SNSUSER";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";

    private List<Role> rolesList = new ArrayList<>();

    public Role createRole(DTO dto) {
        RoleDTO roleDTO = (RoleDTO) dto;
        return new Role(roleDTO.getId(), roleDTO.getName());
    }

    public Role getById(int id) {
        for (Role role : rolesList) {
            if (role.getId() == id) {
                return role;
            };
        }
        return null;
    }

    public boolean add(Role role) {
        try {
            this.rolesList.add(role);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Role getRoleByName(String name) {
        for (Role role : this.rolesList) {
            if (role.getName().equals(name)) {
                return role;
            };
        }
        return null;
    }

    private Boolean validateSave(Object object) {
        return true;
    }

    private Boolean validateDelete(Object object) {
        return true;
    }
    @Override
    public boolean save(Object object) {
        if (validateSave(object)) {
            rolesList.add((Role) object);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Object object) {
        if (validateDelete(object)) {
            rolesList.remove(object);
            return true;
        }return false;
    }

    public List<Role> getRoleList() {
        return rolesList;
    }

    public List<KeyValueDTO> keyValueDTOList() {
        //Instancia-se uma lista de KeyValueDTO
        List<KeyValueDTO> dtoList = new ArrayList<>();
        //Percorre a lista de VaccineTech...
        for (Role role : getRoleList()) {
            //Para cada vaccineTech presente na lista é feita a sua conversão para VaccineTechDTO
            RoleDTO dto = role.toDTO();
            //Adicionar à dtoList um novo objeto do tipo KeyValueDTO com os atributos do dto.
            dtoList.add(new KeyValueDTO(dto.getId().toString(), dto.getName()));
        }
        return dtoList;
    }
}
