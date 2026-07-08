import db.DBException;
import model.entities.Employee;
import model.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    EmployeeService service;

    @BeforeEach
    void setUp(){
        service = new EmployeeService();
    }

    @Test
    public void insertThrowsExceptionWithCorrectMessageWhenEmployeeIsNull(){
        Employee employee = null;
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Employee cannot be null";
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void insertThrowsExceptionWithCorrectMessageWhenSalaryIsZero(){
        EmployeeService service = new EmployeeService();
        Employee employee = new Employee();
        employee.setName("Exemplo");
        employee.setSalary(0.0);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Employee salary should be greater than zero";
        assertEquals(expectedMessage,actualMessage);
    }
}
