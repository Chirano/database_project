package pt.org.upskill.controller;

import pt.org.upskill.domain.Role;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.Repositories;
import pt.org.upskill.repository.RoleRepository;

import java.util.List;

public class RoleController implements UIable {
    RoleRepository roleRepository = Repositories.getInstance().roleRepository();

    private Role role;


    public List<Role> getRoleList() {
        return roleRepository.getRoleList();
    }

    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    @Override
    public void register(DTO dto) throws Exception {
        this.role = roleRepository.createRole(dto);
    }

    @Override
    public boolean save() {
        return roleRepository.save(role);
    }

    @Override
    public List<KeyValueDTO> keyValueDTOList() {
        return roleRepository.keyValueDTOList();
    }

}
