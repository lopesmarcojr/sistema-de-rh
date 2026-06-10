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

    /*System.out.println("Teste de inserção de novo empregado");
    EmployeeDao employeeDao = DaoFactory.createEmploeeyDao();
    Employee employee = new Employee();
    employee.setName("Erika dos Santos");
    employee.setSalary(5000.0);
    employee.setHireDate(new Date());
    employee.setDepartmentId(2);
    employee.setPositionId(2);
    employeeDao.insert(employee);
    System.out.println("Empregado inserido com sucesso!");*/

    EmployeeDao employeeDao = DaoFactory.createEmploeeyDao();
    /*Employee employee = employeeDao.findById(1);
    employee.setSalary(3500.0);
    employeeDao.update(employee);*/

    System.out.println("Teste do método findAll");
    List<Employee> emps = employeeDao.findAll();
    for(Employee e : emps){
        System.out.println(e);
    }


    }

