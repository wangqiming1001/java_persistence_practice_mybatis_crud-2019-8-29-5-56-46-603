package tws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import tws.dto.EmployeeDto;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	
	  public List<EmployeeDto> fetchEmployeeDtoById(String id) {
	    	List<Employee> EmployeeList = employeeMapper.fetchEmployeeById(id);
	    	EmployeeDto employeeDto = new EmployeeDto();
	    	employeeDto.setId(id);
	    	employeeDto.setName(EmployeeList.get(0).getName());;
	    	employeeDto.setAge(EmployeeList.get(0).getAge());
	    	employeeDto.setDescript("这是描述");
	    	List<EmployeeDto>  employeeDtoList = new ArrayList<EmployeeDto>();
	    	employeeDtoList.add(employeeDto);
	    	return employeeDtoList;
	  }
}
