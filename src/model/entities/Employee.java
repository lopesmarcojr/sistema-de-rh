package model.entities;

import java.util.Date;
import java.util.Objects;

public class Employee {

    private Integer id;
    private String name;
    private Date hireDate;
    private Integer DepartmentId;
    private Integer PositionId;

    public Employee(){

    }

    public Employee(Integer id, String name, Date hireDate, Integer departmentId, Integer positionId) {
        this.id = id;
        this.name = name;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        DepartmentId = departmentId;
    }

    public Integer getPositionId() {
        return PositionId;
    }

    public void setPositionId(Integer positionId) {
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
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hireDate=" + hireDate +
                ", DepartmentId=" + DepartmentId +
                ", PositionId=" + PositionId +
                '}';
    }
}
