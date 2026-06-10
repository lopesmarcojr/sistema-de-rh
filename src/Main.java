import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.EmployeeDao;
import model.dao.PositionDao;
import model.entities.Department;
import model.entities.Employee;
import model.entities.Position;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    System.out.println("Teste de inserção de novo empregado");
    EmployeeDao employeeDao = DaoFactory.createEmploeeyDao();
    Employee employee = new Employee();
    employee.setName("Marco Aurelio");
    employee.setSalary(3.500);
    employee.setHireDate(new Date());
    employee.setDepartmentId(5);
    employee.setPositionId(1);
    employeeDao.insert(employee);
    System.out.println("Novo empregado inserido com sucesso!");


    }

