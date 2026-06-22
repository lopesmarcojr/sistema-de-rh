package model.service;

import db.DB;
import db.DBException;
import model.dao.DaoFactory;
import model.dao.PositionDao;
import model.entities.Employee;
import model.entities.Position;

import java.util.List;
import java.util.function.DoublePredicate;

public class PositionService {

    private PositionDao positionDao = DaoFactory.createPositionDao();

    public void insert(Position position){
        if(position == null){
            throw new DBException("Position cannot be null");
        }
        if(position.getName() == null){
            throw new DBException("Position name cannot be null");
        }
        if(position.getName().trim().isEmpty()){
            throw new DBException("Position name cannot be empty");
        }
        positionDao.insert(position);
    }

    public void update(Position position){
        if(position == null){
            throw new DBException("Position cannot be null");
        }
        if(position.getId() == null){
            throw new DBException("Position id cannot be null");
        }
        if(position.getId() <= 0){
            throw new DBException("Position id should be greater than zero");
        }
        if(position.getName() == null){
            throw new DBException("Position name cannot be null");
        }
        if(position.getName().trim().isEmpty()){
            throw new DBException("Position name cannot be empty");
        }
        positionDao.update(position);
    }

    public Position findById(Integer id){
        if(id == null){
            throw new DBException("Id cannot be null");
        }
        if(id <= 0){
            throw new DBException("Id should be greater than zero");
        }
        return positionDao.findById(id);
    }

    public void deleteById(Integer id){
        if(id == null){
            throw new DBException("Id cannot be null");
        }
        if(id <= 0){
            throw new DBException("Id should be greater than zero");
        }
    }

    public List<Position > findAll(){
        return positionDao.findAll();
    }
}
