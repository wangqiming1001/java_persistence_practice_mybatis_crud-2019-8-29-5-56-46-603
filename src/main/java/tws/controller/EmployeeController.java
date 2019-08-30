package tws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tws.dto.EmployeeDto;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;
import tws.service.EmployeeService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService EmployeeService;

    //fetch employee list
    @GetMapping("")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeMapper.selectAll());
    }
    
    //create a new employee
    @PostMapping("/insert")
    public ResponseEntity<Employee> insertEmployee(@RequestBody Employee employee) {
    	String id = UUID.randomUUID().toString();
    	employee.setId(id);
    	employeeMapper.insertEmployee(employee);
        return ResponseEntity.created(URI.create(URI.create("/employees")+employee.getId())).build();
    }
    
    //fetch one employee
    @GetMapping("/{id}")
    public ResponseEntity<List<Employee>> fetchEmployeeById(@PathVariable String id) {
    	return ResponseEntity.ok(employeeMapper.fetchEmployeeById(id));
    }
    
  //fetch one employee
    @GetMapping("/{id}")
    public ResponseEntity<List<EmployeeDto>> fetchEmployeeDtoById(@PathVariable String id) {
    	List<EmployeeDto>  employeeDtoList = EmployeeService.fetchEmployeeDtoById(id);
    	return ResponseEntity.ok(employeeDtoList);
    }
    
    
    //update a specific employee
    @PutMapping
    public ResponseEntity<List<Employee>> updateEmployeeById(@RequestBody Employee employee) {
    	employeeMapper.updateEmployeeById(employee);
    	List<Employee> employeeList= employeeMapper.selectAll();
    	return ResponseEntity.ok(employeeList);
    }
    
    
    //delete a specific employee
  	@DeleteMapping("/{id}")
  	public ResponseEntity<List<Employee>> deleteEmployeeById(@PathVariable String id){
  		employeeMapper.deleteEmployeeById(id);
  		List<Employee> employeeList= employeeMapper.selectAll();
    	return ResponseEntity.ok(employeeList);
  	}
  	
  	
    
}
