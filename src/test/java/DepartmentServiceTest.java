import db.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Employee;
import model.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DepartmentServiceTest {

    Department department;
    DepartmentDao departmentDao;
    DepartmentService service;

    @BeforeEach
    void setUp(){
        departmentDao = mock(DepartmentDao.class);
        service = new DepartmentService(departmentDao);
    }

    private Department createValidDepartment(){
        Department department = new Department(1,"Test");
        return department;
    }

    @Test
    void findByIdShouldReturnDepartmentWhenIdIsValid(){
        Integer id = 1;
        Department department = createValidDepartment();
        when(departmentDao.findById(id)).thenReturn(department);
        Department result = service.findById(id);
        assertEquals(department,result);
        verify(departmentDao).findById(id);
    }

    @ParameterizedTest
    @NullSource
    void findByIdShouldThrowExceptionWhenIdIsNull(Integer id){
        DBException exception = assertThrows(DBException.class, () -> service.findById(id));
        String expectedMessage = "Id cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, -1})
    void findByIdShouldThrowExceptionWhenIdIsLessOrEqualToZero(Integer id){
        DBException exception = assertThrows(DBException.class, () -> service.findById(id));
        String expectedMessage = "Id should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void findAllShouldReturnListReturnedByDepartmentDao(){
        List<Department> departments = List.of(createValidDepartment());
        when(departmentDao.findAll()).thenReturn(departments);
        List<Department> result = service.findAll();
        assertEquals(departments, result);
        verify(departmentDao).findAll();
    }





}
