package pt.org.upskill.controller;

import pt.org.upskill.domain.Employee;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.repository.EmployeeRepository;

public class EmployeeController {
    EmployeeRepository employeeRepository = new EmployeeRepository();

    private Employee employee;
    public void createEmployee(DTO dto) throws Exception {
        employee = employeeRepository.createEmployee(dto);
    }

    public boolean save() {
        return employeeRepository.save(employee);
    }
}

