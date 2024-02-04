package pt.org.upskill.domain;

import pt.org.upskill.dto.DTOable;
import pt.org.upskill.dto.RoleDTO;

public class Role implements DTOable<RoleDTO> {
    private Integer id;
    private String name;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public Integer getId() { return this.id; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name.");
        }
        this.name = name;
    }

    public boolean equals(Object otherObject) {
        if (otherObject == null || otherObject.getClass() != this.getClass()) {
            return false;
        }

        if (this == otherObject) {
            return true;
        }

        Role otherRole = (Role) otherObject;
        return this.id.equals(otherRole.id)
                && this.name.equals(otherRole.name);
    }

    @Override
    public RoleDTO toDTO() {
        RoleDTO.Builder builder = new RoleDTO.Builder();
        if (getId() != null) {
            builder.withId(getId());
        }
        if (getName() != null) {
            builder.withName(getName());
        }

        return builder.build();
    }
}
