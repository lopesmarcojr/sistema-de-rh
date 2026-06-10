package model.entities;

import java.util.Date;
import java.util.Objects;

public class Employee {

    private Integer id;
    private String name;
    private Double salary;
    private Date hireDate;
    private Department DepartmentId;
    private Position PositionId;

    public Employee(){

    }

    public Employee(Integer id, String name, Double salary, Date hireDate, Department departmentId, Position positionId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
        DepartmentId = departmentId;
        PositionId = positionId;
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

    public Department getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Department departmentId) {
        DepartmentId = departmentId;
    }

    public Position getPositionId() {
        return PositionId;
    }

    public void setPositionId(Position positionId) {
        PositionId = positionId;
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
        return name +  ", data da contratação: " + hireDate + ", departamento: " + DepartmentId +
                ", cargo: " + PositionId;
    }
}
