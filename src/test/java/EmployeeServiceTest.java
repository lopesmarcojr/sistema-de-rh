import db.DBException;
import model.entities.Employee;
import model.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {

    EmployeeService service = new EmployeeService();

    @Test
    public void insertThrowsExceptionWhenReceivesNull(){
        EmployeeService service = new EmployeeService();
        Employee employee = null;
        assertThrows(DBException.class, () -> service.insert(employee));
    }
}
