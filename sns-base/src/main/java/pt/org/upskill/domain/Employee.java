package pt.org.upskill.domain;

import pt.org.upskill.auth.Email;
import pt.org.upskill.dto.BrandDTO;
import pt.org.upskill.dto.DTOable;
import pt.org.upskill.dto.EmployeeDTO;

public class Employee implements DTOable<EmployeeDTO> {
    private Integer id;
    private final Email email;
    private String name;
    private Role role;
    private Phone phoneNumber;

    public Employee(Integer id, Email email, String name, Role role, Phone phoneNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }
    public Employee(Email email, String name, Role role, Phone phoneNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }
    public Integer getId() {return this.id;  }
    public Email getEmail() {
        return this.email;
    }
    public String getName() {
        return this.name;
    }
    public Role getRole() {
        return this.role;
    }
    public Phone getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setId(Integer id) { this.id = id;}
    public void setName(String name) {
        if(name == null ||  name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name.");
        }
        this.name = name;
    }
    public void setRole(Role role) {
        if(role == null) {
            throw new IllegalArgumentException("Invalid role.");
        }
        this.role = role;
    }
    public void setPhoneNumber(Phone phoneNumber) {
        if(phoneNumber == null) {
            throw new IllegalArgumentException("Invalid phone number.");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return this.id.equals(employee.id)
                && email.equals(employee.email)
                && this.name.equals(employee.name)
                && this.phoneNumber.equals(employee.phoneNumber)
                && this.role.equals(employee.role);
    }

    public boolean hasEmail(String email) {
        return this.email.address().equals(email);
    }

    @Override
    public EmployeeDTO toDTO() {
        EmployeeDTO.Builder builder = new EmployeeDTO.Builder();
        if (getId() != null) {
            builder.withId(getId());
        }
        if (getName() != null) {
            builder.withName(getName());
        }
        if (getEmail() != null) {
            builder.withEmailDTO(getEmail().toDTO());
        }
        if (getPhoneNumber() != null) {
            builder.withPhoneDTO(getPhoneNumber().toDTO());
        }
        if (getRole() != null) {
            builder.withRoleDTO(getRole().toDTO());
        }
        return builder.build();
    }
}