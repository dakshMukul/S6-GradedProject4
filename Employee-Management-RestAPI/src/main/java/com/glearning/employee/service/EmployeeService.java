package com.glearning.employee.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.glearning.employee.entity.Employee;
import com.glearning.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	public Set<Employee> fetchAllEmployee() {
		return new HashSet<>(this.employeeRepository.findAll());
	}

	public Employee fetchEmployeeById(long empId) {
		return this.employeeRepository.findById(empId).orElseThrow();
	}

	public void deleteEmployeeById(long empId) {
		this.employeeRepository.deleteById(empId);
	}
}
