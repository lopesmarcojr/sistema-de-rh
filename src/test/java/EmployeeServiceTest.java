import db.DBException;
import model.entities.Employee;
import model.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    EmployeeService service = new EmployeeService();

    @Test
    public void insertThrowsExceptionWithCorrectMessageWhenEmployeeIsNull(){
        EmployeeService service = new EmployeeService();
        Employee employee = null;
        DBException exception = assertThrows(DBException.class, () -> service.insert(employee));
        String actualMessage = exception.getMessage();
        String expectedMessage = "Employee cannot be null";
        assertEquals(expectedMessage,actualMessage);
    }
}
