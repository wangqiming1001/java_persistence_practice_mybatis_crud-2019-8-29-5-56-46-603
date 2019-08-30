package tws.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tws.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> selectAll();
    
    List<Employee> selectAllEmployees(@Param(value = "offSet") Integer offSet,@Param(value = "pageSize") Integer pageSize);
    
    void insertEmployee(@Param(value = "employee") Employee employee);

    List<Employee> fetchEmployeeById(@Param(value = "id") String id);
    
    void updateEmployeeById(@Param(value = "employee") Employee employee);

	void deleteEmployeeById(@Param(value = "id") String id);

	List<Employee> selectByName(@Param(value = "name") String name);
}
