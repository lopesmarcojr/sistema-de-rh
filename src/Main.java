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
void main() throws ParseException {

   /* SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    System.out.println("Teste de inserção de novo empregado");*/
    EmployeeDao employeeDao = DaoFactory.createEmploeeyDao();
    /*Employee employee = new Employee();
    employee.setName("Roseli Teixeia Rosa");
    employee.setSalary(6581.4);
    String data = "17/04/2019";
    Date date = sdf.parse(data);
    employee.setHireDate(date);
    employee.setDepartmentId(5);
    employee.setPositionId(3);
    employeeDao.insert(employee);
    System.out.println("Empregado inserido com sucesso!");*/


    /*System.out.println("Teste de paginação");
    List<Employee> employees = employeeDao.findPage(1,5);
    for(Employee e : employees){
        System.out.println(e);
    }*/

    System.out.println("Teste contando por filtros");
    Integer emps = employeeDao.countEmployeeByFilters(null, null, 5000.0);
    System.out.println(emps);
}

