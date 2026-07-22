import db.DBException;
import model.dao.EmployeeDao;
import model.entities.Department;
import model.entities.Employee;
import model.entities.Position;
import model.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    EmployeeDao employeeDao;

    EmployeeService service;
    Employee employee;

    @BeforeEach
    void setUp(){
         employeeDao = mock(EmployeeDao.class);
         service = new EmployeeService(employeeDao);
    }

    private Employee createValidEmployee(){
        Department department = new Department(1,"Teste");
        Position position = new Position(1,"Teste");
        LocalDate dataLocal = LocalDate.of(2026,07,10);
        Date date = Date.valueOf(dataLocal);
        Employee employee = new Employee(1, "Teste", 0.1, date, department, position);
        return employee;
    }

    @ParameterizedTest
    @NullSource
    void insertThrowsExceptionWithCorrectMessageWhenEmployeeIsNull(Employee employee){
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Employee cannot be null";
        assertEquals(expectedMessage,actualMessage);
    }

    @ParameterizedTest
    @CsvSource(value = {"null, Employee name cannot be null", "'',Employee name cannot be empty"}, nullValues = "null")
    void insertThrowsExceptionWithCorrectMessageWhenNameIsNullOrEmpty(String name, String expectedMessage){
        employee = createValidEmployee();
        employee.setName(name);
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
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

    @Test
    void insertShouldCallEmployeeDaoInsertWhenEmployeeIsValid(){
        employee = createValidEmployee();
        service.insert(employee);
        verify(employeeDao).insert(employee);
    }

    @ParameterizedTest
    @NullSource
    void updateThrowsExceptionWithCorrectMessageWhenEmployeeIsNull(Employee employee){
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Employee cannot be null";
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenNameIsNull(){
        employee = createValidEmployee();
        employee.setName(null);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee name cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenNameIsEmpty(){
        employee = createValidEmployee();
        employee.setName("");
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee name cannot be empty";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenSalaryIsNull(){
        employee = createValidEmployee();
        employee.setSalary(null);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee salary cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenSalaryIsZero(){
        employee = createValidEmployee();
        employee.setSalary(0.0);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Employee salary should be greater than zero";
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenHireDateIsNull(){
        employee = createValidEmployee();
        employee.setHireDate(null);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee hire date cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenDepartmentIsNull(){
        employee = createValidEmployee();
        employee.setDepartment(null);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee department cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenDepartmentIdIsNull(){
        employee = createValidEmployee();
        Department department = new Department(null, "Teste");
        employee.setDepartment(department);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee department id cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenDepartmentIdIsZero(){
        employee = createValidEmployee();
        Department department = new Department(0, "Teste");
        employee.setDepartment(department);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee department id should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenPositionIsNull(){
        employee = createValidEmployee();
        employee.setPosition(null);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee position cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenPositionIdIsNull(){
        employee = createValidEmployee();
        Position position = new Position(null, "Teste");
        employee.setPosition(position);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee position id cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenPositionIdIsZero(){
        employee = createValidEmployee();
        Position position = new Position(0, "Teste");
        employee.setPosition(position);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee position id should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void updateThrowsExceptionWithCorrectMessageWhenEmployeeIdIsNull(){
        employee = createValidEmployee();
        Integer id = null;
        employee.setId(id);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee id cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void updateThrowsExceptionWithCorrectMessageWhenEmployeeIdIsZeroOrNegative(Integer id){
        employee = createValidEmployee();
        employee.setId(id);
        DBException exception = assertThrows(DBException.class, () -> service.update(employee));
        String expectedMessage = "Employee id should be greater than zero";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void updateShouldCallEmployeeDaoWhenEmployeeIsValid(){
        employee = createValidEmployee();
        service.update(employee);
        verify(employeeDao).update(employee);
    }

    @ParameterizedTest
    @NullSource
    void findByIdThrowsExceptionWithCorrectMessageWhenEmployeeIdIsNull(Integer id){
        DBException exception = assertThrows(DBException.class, () -> service.findById(id));
        String expectedMessage = "Employee id cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void findByIdThrowsExceptionWithCorrectMessageWhenEmployeeIdIsZeroOrNegative(Integer id){
        DBException exception = assertThrows(DBException.class, () -> service.findById(id));
        String expectedMessage = "Employee id should be greater than zero";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void findByIdShouldCallEmployeeDaoFindByIdWhenIdIsValid(){
        Integer id = 1;
        service.findById(id);
        verify(employeeDao).findById(id);
    }
    @Test
    void findByIdShouldReturnEmployeeReturnedByDao(){
        Integer id = 1;
        employee = createValidEmployee();
        when(employeeDao.findById(id)).thenReturn(employee);
        Employee result = service.findById(id);
        assertEquals(employee,result);
        verify(employeeDao).findById(id);
    }

    @Test
    void findByIdShouldPropagateExceptionWhenDaoThrowsException(){
        Integer id = 1;
        when(employeeDao.findById(id)).thenThrow(new DBException("Database error"));
        DBException exception = assertThrows(DBException.class, ()-> service.findById(id));
        String expectedMessage = "Database error";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao).findById(id);
    }

    @ParameterizedTest
    @NullSource
    void deleteThrowsExceptionWithCorrectMessageWhenEmployeeIdIsNull(Integer id){
        DBException exception = assertThrows(DBException.class, () -> service.deleteById(id));
        String expectedMessage = "Employee id cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void deleteThrowsExceptionWithCorrectMessageWhenEmployeeIdIsZeroOrNegative(Integer id){
        DBException exception = assertThrows(DBException.class, () -> service.deleteById(id));
        String expectedMessage = "Employee id should be greater than zero";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void deleteByIdShouldCallEmployeeDaoWhenIdIsValid(){
        Integer id = 1;
        service.deleteById(id);
        verify(employeeDao).deleteById(id);
    }

    @Test
    void findAllShouldReturnListReturnedByEmployeeDao(){
        List<Employee> employees = List.of(createValidEmployee());
        when(employeeDao.findAll()).thenReturn(employees);
        List<Employee> result = service.findAll();
        assertEquals(employees,result);
        verify(employeeDao).findAll();
    }

    @Test
    void findAllShouldReturnEmptyListWhenDaoReturnsEmptyList(){
        List<Employee> employees = Collections.emptyList();
        when(employeeDao.findAll()).thenReturn(employees);
        List<Employee> result = service.findAll();
        assertEquals(employees,result);
        verify(employeeDao).findAll();
    }

}
