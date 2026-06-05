package model.dao;

public interface PositionDao {

    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDao()
    }
}
