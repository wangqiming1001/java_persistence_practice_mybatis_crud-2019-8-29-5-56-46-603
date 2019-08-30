package tws.test;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@MybatisTest
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "employee");
    }

    @Test
    public void should_fetch_all_employees() {
        // given
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES('1','zhangsan', 21);");
        // when
        List<Employee> employeeList = employeeMapper.selectAll();
        // then
        assertEquals(1, employeeList.size());
    }
    
    @Test
    public void should_insert_the_given_employee() {
        // given
        Employee employee = new Employee("1","aaa","21");
        Employee employee1 = new Employee("2","bbb","11");
        // when
        employeeMapper.insertEmployee(employee);
        employeeMapper.insertEmployee(employee1);
        // then
        int numbers = JdbcTestUtils.countRowsInTable(jdbcTemplate, "employee");
        assertEquals(2, numbers);
    }
    
    
//    @Test
//    public void should_fetch_employee_by_id() {
//        // given
//        Employee employee = new Employee("1","aaa","21");
//        Employee employee1 = new Employee("2","aaa","21");
//        // when
//        employeeMapper.insertEmployee(employee);
//        employeeMapper.insertEmployee(employee1);
//        // then
//        int numbers = JdbcTestUtils.countRowsInTable(jdbcTemplate, "employee");
//        assertEquals(2, numbers);
//    }
    
    
}
