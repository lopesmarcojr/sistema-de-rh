import db.DBException;
import model.entities.Department;
import model.entities.Employee;
import model.entities.Position;
import model.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    EmployeeService service;
    Employee employee;

    @BeforeEach
    void setUp(){
        service = new EmployeeService();
    }

    private Employee createValidEmployee(){
        Department department = new Department(1,"Teste");
        Position position = new Position(1,"Teste");
        Date date = new Date(2026,07,10);
        Employee employee = new Employee(1, "Teste", 0.1, date, department, position);
        return employee;
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
    public void insertThrowsExceptionWithCorrectMessageWhenNameIsNull(){
        employee = createValidEmployee();
        employee.setName(null);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee name cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    public void insertThrowsExceptionWithCorrectMessageWhenNameIsEmpty(){
        employee = createValidEmployee();
        employee.setName("");
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee name cannot be empty";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    public void insertThrowsExceptionWithCorrectMessageWhenSalaryIsnUll(){
        employee = createValidEmployee();
        employee.setSalary(null);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee salary cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
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
