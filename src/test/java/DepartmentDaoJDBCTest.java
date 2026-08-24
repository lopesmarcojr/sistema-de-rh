import db.DB;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DepartmentDaoJDBCTest {

    Department department;
    DepartmentDao departmentDao;

    @BeforeEach
    void setUp(){
        departmentDao = new DepartmentDaoJDBC(DB.getConnection());
    }

    @AfterEach
    void cleanUp(){
        if(department != null){
            departmentDao.deleteById(department.getId());
        }
    }

    @Test
    void findByIdShouldReturnDepartmentWhenIsValid(){
        department = createValidDepartment();
        departmentDao.insert(department);
        Integer id = department.getId();
        String name = department.getName();
        Department result = departmentDao.findById(id);
        assertEquals(id, result.getId());
        assertEquals(name, result.getName());
    }

    @Test
    void findByIdShouldReturnNullWhenDepartmentIdDoesNotExist(){
        Integer id = 99999;
        Department result = departmentDao.findById(id);
        assertNull(result);
    }

    private Department createValidDepartment(){
        return new Department(null, "Test Department");
    }
}


