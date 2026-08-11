import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Employee;
import model.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DepartmentServiceTest {

    Department department;
    DepartmentDao departmentDao;
    DepartmentService departmentService;

    @BeforeEach
    void setUp(){
        departmentDao = mock(DepartmentDao.class);
        departmentService = new DepartmentService(departmentDao);
    }

    private Department createValidDepartment(){
        Department department = new Department(1,"Test");
        return department;
    }

    @Test
    void findByIdShouldReturnDepartmentWhenIdIsValid(){
        Integer id = 1;
        departmentService.findById(id);
        verify(departmentDao).findById(id);
    }
}
