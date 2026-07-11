package model.entities;

import java.util.Date;
import java.util.Objects;

public class Employee {

    private Integer id;
    private String name;
    private Double salary;
    private Date hireDate;
    private Department department;
    private Position position;

    public Employee(){

    }

    public Employee(Integer id, String name, Double salary, Date hireDate, Department department, Position position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
        this.department = department;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return name +  ", data da contratação: " + hireDate + ", departamento: " + department +
                ", cargo: " + position + ", salário: " + salary;
    }
}
