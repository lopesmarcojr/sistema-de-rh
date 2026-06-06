import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
    Department department = new Department();
    department.setName("Informatica");
    departmentDao.insert(department);
    System.out.println("Departamento inserido com sucesso!");
    }

