package model.service;

import db.DBException;
import model.entities.Position;
import model.dao.DaoFactory;
import model.dao.PositionDao;

import java.util.List;

public class PositionService {

    private PositionDao positionDao;

    public PositionService(PositionDao positionDao){
        this.positionDao = positionDao;
    }

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

    public Integer countPosition(){
        return positionDao.countPosition();
    }

    public List<Position> findPage(int page, int pageSize){
        validatePagination(page, pageSize);
        return positionDao.findPage(page, pageSize);
    }

    public void validatePagination(Integer page, Integer pageSize){
        if(page == null){
            throw new DBException("Page number cannot be null");
        }
        if(page <= 0){
            throw new DBException("Page number should be greater than zero");
        }
        if(pageSize == null){
            throw new DBException("Page size cannot be null");
        }
        if(pageSize <= 0){
            throw new DBException("Page size should be greater than zero");
        }
    }
}
