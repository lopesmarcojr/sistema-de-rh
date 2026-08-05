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

    @Test
    void countEmployeesShouldReturnEmployeeCountReturnedByDao(){
        Integer employees = 1;
        when(employeeDao.countEmployees()).thenReturn(employees);
        Integer result = service.countEmployees();
        assertEquals(employees,result);
        verify(employeeDao).countEmployees();
    }

    @Test
    void countEmployeesShouldReturnZeroWhenDaoReturnsZero(){
        Integer employees = 0;
        when(employeeDao.countEmployees()).thenReturn(employees);
        Integer result = service.countEmployees();
        assertEquals(employees,result);
        verify(employeeDao).countEmployees();
    }

    @Test
    void findPageShouldReturnEmployeesReturnedByDao(){
        List<Employee> employees = List.of(createValidEmployee());
        Integer page = 1;
        Integer pageSize = 5;
        when(employeeDao.findPage(page, pageSize)).thenReturn(employees);
        List<Employee> result = service.findPage(page, pageSize);
        assertEquals(employees,result);
        verify(employeeDao).findPage(page, pageSize);
    }

    @Test
    void findPageShoudlThrowExceptionWhenPageIsNull(){
        Integer page = null;
        int pageSize = 5;
        DBException exception = assertThrows(DBException.class, () -> service.findPage(page, pageSize));
        String expectedMessage = "Page number cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPage(anyInt(), anyInt());
    }

    @Test
    void findPageShouldThrowExceptionWhenPageSizeIsNull(){
        int page = 1;
        Integer pageSize = null;
        DBException exception = assertThrows(DBException.class, () -> service.findPage(page, pageSize));
        String expectedMessage = "Page size cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPage(anyInt(), anyInt());
    }

    @Test
    void findPageShouldThrowExceptionWhenPageIsLessThanOrEqualToZero(){
        int page = 0;
        int pageSize = 5;
        DBException exception = assertThrows(DBException.class, () -> service.findPage(page, pageSize));
        String expectedMessage = "Page number should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPage(anyInt(), anyInt());
        
    }

    @Test
    void findPageShouldThrowExceptionWhenPageSizeIsLessThanOrEqualToZero(){
        int page = 1;
        int pageSize = 0;
        DBException exception = assertThrows(DBException.class, () -> service.findPage(page, pageSize));
        String expectedMessage = "Page size should be greater than zero";
        assertEquals(expectedMessage,exception.getMessage());
        verify(employeeDao, never()).findPage(anyInt(), anyInt());
    }

    @Test
    void findByFiltersShouldReturnEmployeesReturnedByDao(){
        List<Employee> employees = List.of(createValidEmployee());
        String department = "TI";
        String position = null;
        Double salary = null;
        when(employeeDao.findByFilters(department, position, salary)).thenReturn(employees);
        List<Employee> result = service.findByFilters(department, position, salary);
        assertEquals(employees, result);
        verify(employeeDao).findByFilters(department, position, salary);
    }

    @Test
    void findByFiltersShouldReturnEmptyListWhenDaoReturnsEmptyList(){
        List<Employee> employees = Collections.emptyList();
        String department = "a";
        String position = "a";
        Double salary = 0.1;
        when(employeeDao.findByFilters(department, position, salary)).thenReturn(employees);
        List<Employee> result = service.findByFilters(department, position, salary);
        assertEquals(employees,result);
        verify(employeeDao).findByFilters(department, position, salary);
    }

    @Test
    void findByFiltersShouldThrowExceptionWhenAllFiltersAreNull(){
        String department = null;
        String position = null;
        Double salary = null;
        DBException exception = assertThrows(DBException.class, () -> service.findByFilters(department, position, salary));
        String expectedMessage = "At least one of the parameters should be valid";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findByFilters(department, position, salary);
    }

    @Test
    void findByFiltersShouldThrowExceptionWhenDepartmentIsEmpty(){
        String department = "";
        String position = "a";
        Double salary = 0.1;
        DBException exception = assertThrows(DBException.class, () -> service.findByFilters(department, position, salary));
        String expectedMessage = "Department cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findByFilters(department, position, salary);
    }

    @Test
    void findByFiltersShouldThrowExceptionWhenPositionIsEmpty(){
        String department = "a";
        String position = "";
        Double salary = 0.1;
        DBException exception = assertThrows(DBException.class, () -> service.findByFilters(department, position, salary));
        String expectedMessage = "Position cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findByFilters(department, position, salary);
    }

    @Test
    void findByFiltersShouldThrowExceptionWhenSalaryIsLessThanOrEqualToZero(){
        String department = "a";
        String position = "a";
        Double salary = 0.0;
        DBException exception = assertThrows(DBException.class, () -> service.findByFilters(department, position, salary));
        String expectedMessage = "Salary should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findByFilters(department, position, salary);
    }

    @Test
    void countEmployeeByFiltersShouldReturnEmployeeByEmployeeDao(){
        String department = "a";
        String position = "a";
        Double salary = 0.1;
        Integer employeeCount = 5;
        when(employeeDao.countEmployeeByFilters(department, position, salary)).thenReturn(employeeCount);
        Integer result = service.countEmployeeByFilters(department, position, salary);
        assertEquals(employeeCount,result);
        verify(employeeDao).countEmployeeByFilters(department, position,salary);
    }

    @Test
    void countEmployeeByFiltersShouldReturnZeroWhenDaoReturnsZero(){
        String department = "a";
        String position = "a";
        Double salary = 0.1;
        int empCount = 0;
        when(employeeDao.countEmployeeByFilters(department, position, salary)).thenReturn(empCount);
        Integer result = service.countEmployeeByFilters(department, position, salary);
        assertEquals(empCount, result);
        verify(employeeDao).countEmployeeByFilters(department, position, salary);
    }

    @Test
    void countEmployeeByFiltersShouldThrowExceptionWhenDepartmentIsEmpty(){
        String department = "";
        String position = "a";
        Double salary = 0.1;
        DBException exception = assertThrows(DBException.class, () -> service.countEmployeeByFilters(department, position, salary));
        String expectedMessage = "Department cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).countEmployeeByFilters(any(), any(), any());
    }

    @Test
    void countEmployeeByFiltersShouldThrowExceptionWhenPositionIsEmpty(){
        String department = "a";
        String position = "";
        Double salary = 0.1;
        DBException exception = assertThrows(DBException.class, () -> service.countEmployeeByFilters(department, position, salary));
        String expectedMessage = "Position cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).countEmployeeByFilters(any(),any(),any());
    }

    @Test
    void countEmployeeByFiltersShouldThrowExceptionWhenSalaryIsLessOrEqualToZero(){
        String department = "a";
        String position = "a";
        Double salary = 0.0;
        DBException exception = assertThrows(DBException.class, () -> service.countEmployeeByFilters(department, position, salary));
        String expectedMessage = "Salary should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).countEmployeeByFilters(any(),any(),any());
    }

    @Test
    void countEmployeeByFiltersShouldThrowExceptionWhenAllParametersAreNull(){
        String department = null;
        String position = null;
        Double salary = null;
        DBException exception = assertThrows(DBException.class, () -> service.countEmployeeByFilters(department, position, salary));
        String expectedMessage = "At least one of the parameters should be valid";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).countEmployeeByFilters(any(), any(), any());
    }

    @Test
    void findPageByFiltersShouldReturnEmployeesReturnedByDao(){
        List<Employee> employees = List.of(createValidEmployee());
        String department = "a";
        String position = "a";
        Double salary = 0.1;
        Integer page = 1;
        Integer pageSize = 5;
        when(employeeDao.findPageByFilters(department, position, salary, page, pageSize)).thenReturn(employees);
        List<Employee> result = service.findPageByFilters(department, position, salary, page, pageSize);
        assertEquals(employees, result);
        verify(employeeDao).findPageByFilters(department, position, salary, page, pageSize);
    }

    @Test
    void findPageByFiltersShouldReturnEmptyListWhenDaoReturnsEmptyList(){
        List<Employee> employee = Collections.emptyList();
        String department = "a";
        String position = "a";
        Double salary = 0.1;
        Integer page = 1;
        Integer pageSize = 5;
        when(employeeDao.findPageByFilters(department, position, salary, page, pageSize)).thenReturn(employee);
        List<Employee> result = service.findPageByFilters(department, position, salary, page, pageSize);
        assertEquals(employee, result);
        verify(employeeDao).findPageByFilters(department, position, salary, page, pageSize);
    }

    @Test
    void findPageByFiltersShouldThrowExceptionWhenAllParametersAreNull(){
        String department = null;
        String position = null;
        Double salary = null;
        Integer page = 1;
        Integer pageSize = 5;
        DBException exception = assertThrows(DBException.class, () -> service.findPageByFilters(department, position, salary, page, pageSize));
        String expectedMessage = "At least one of the parameters should be valid";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPageByFilters(department, position, salary,page,pageSize);
    }

    @Test
    void findBageByFiltersShouldThrowExceptionWhenDepartmentIsEmpty(){
        String department = "";
        String position = "a";
        Double salary = 0.1;
        Integer page = 1;
        Integer pageSize = 5;
        DBException exception = assertThrows(DBException.class, () -> service.findPageByFilters(department, position, salary, page, pageSize));
        String expectedMessage = "Department cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPageByFilters(department, position, salary, page, pageSize);
    }

    @Test
    void findPageByFiltersShouldThrowExceptionWhenPositionIsEmpty(){
        String department = "a";
        String position = "";
        Double salary = 0.1;
        Integer page = 1;
        Integer pageSize = 1;
        DBException exception = assertThrows(DBException.class, () -> service.findPageByFilters(department, position, salary, page, pageSize));
        String expectedMessage = "Position cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPageByFilters(department, position, salary, page, pageSize);
    }

    @Test
    void findPageByFiltersShouldThrowExceptionWhenSalaryIsLessOrEqualToZero(){
        String department = "a";
        String position = "a";
        Double salary = 0.0;
        Integer page = 1;
        Integer pageSize = 5;
        DBException exception = assertThrows(DBException.class, () -> service.findPageByFilters(department, position, salary, page, pageSize));
        String expectedMessage = "Salary should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPageByFilters(department, position, salary, page, pageSize);
    }

    @Test
    void findPageByFiltersShouldThrowExceptionWhenPageNumberAreNull(){
        String department = "a";
        String position = "a";
        Double salary = 0.1;
        Integer page = null;
        Integer pageSize = 5;
        DBException exception = assertThrows(DBException.class, () -> service.findPageByFilters(department, position, salary, page, pageSize));
        String expectedMessage = "Page number cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPageByFilters(department, position, salary, page, pageSize);
    }

    @Test
    void findPageByFiltersShouldThrowExceptionWhenPageNumberAreLessOrEqualToZero(){
        String department = "a";
        String position = "a";
        Double salary = 0.1;
        Integer page = 0;
        Integer pageSize = 5;
        DBException exception = assertThrows(DBException.class, () -> service.findPageByFilters(department, position, salary, page, pageSize));
        String expectedMessage = "Page number should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPageByFilters(department, position, salary, page, pageSize);
    }

    @Test
    void findPageByFiltersShouldThrowExceptionWhenPageSizeAreNull(){
        String department = "a";
        String position = "a";
        Double salary = 0.1;
        Integer page = 1;
        Integer pageSize = null;
        DBException exception = assertThrows(DBException.class, () -> service.findPageByFilters(department, position, salary, page, pageSize));
        String expectedMessage = "Page size cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPageByFilters(department, position, salary, page, pageSize);
    }

    @Test
    void findPageByFiltersShouldThrowExceptionWhenPageSizeAreLessOrEqualToZero(){
        String department = "a";
        String position = "a";
        Double salary = 0.1;
        Integer page = 1;
        Integer pageSize = 0;
        DBException exception = assertThrows(DBException.class, () -> service.findPageByFilters(department, position, salary, page, pageSize));
        String expectedMessage = "Page size should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeDao, never()).findPageByFilters(department, position, salary, page, pageSize);
    }
}
