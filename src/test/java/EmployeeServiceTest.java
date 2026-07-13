import db.DBException;
import model.entities.Department;
import model.entities.Employee;
import model.entities.Position;
import model.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.sql.Date;
import java.time.LocalDate;

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
        LocalDate dataLocal = LocalDate.of(2026,07,10);
        Date date = Date.valueOf(dataLocal);
        Employee employee = new Employee(1, "Teste", 0.1, date, department, position);
        return employee;
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenEmployeeIsNull(){
        Employee employee = null;
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Employee cannot be null";
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenNameIsNull(){
        employee = createValidEmployee();
        employee.setName(null);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee name cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenNameIsEmpty(){
        employee = createValidEmployee();
        employee.setName("");
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee name cannot be empty";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenSalaryIsNull(){
        employee = createValidEmployee();
        employee.setSalary(null);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee salary cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenSalaryIsZero(){
        employee = createValidEmployee();
        employee.setSalary(0.0);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Employee salary should be greater than zero";
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenHireDateIsNull(){
        employee = createValidEmployee();
        employee.setHireDate(null);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee hire date cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenDepartmentIsNull(){
        employee = createValidEmployee();
        employee.setDepartment(null);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee department cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenDepartmentIdIsNull(){
        employee = createValidEmployee();
        Department department = new Department(null, "Teste");
        employee.setDepartment(department);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee department id cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenDepartmentIdIsZero(){
        employee = createValidEmployee();
        Department department = new Department(0, "Teste");
        employee.setDepartment(department);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee department id should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenPositionIsNull(){
        employee = createValidEmployee();
        employee.setPosition(null);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee position cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenPositionIdIsNull(){
        employee = createValidEmployee();
        Position position = new Position(null, "Teste");
        employee.setPosition(position);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee position id cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void insertThrowsExceptionWithCorrectMessageWhenPositionIdIsZero(){
        employee = createValidEmployee();
        Position position = new Position(0, "Teste");
        employee.setPosition(position);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String expectedMessage = "Employee position id should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
