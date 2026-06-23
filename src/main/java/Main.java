import main.java.model.entities.Employee;
import model.service.EmployeeService;

import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {

   /* SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    System.out.println("Teste de inserção de novo empregado");*/
        EmployeeService employeeService = new EmployeeService();
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

        System.out.println("Teste por filtros");
        List<Employee> emps = employeeService.findPageByFilters("Informatica",null,null,1,5);
        for(Employee e : emps){
            System.out.println(e);
        }
    }
}
