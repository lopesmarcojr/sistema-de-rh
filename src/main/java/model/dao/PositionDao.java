package model.dao;

import main.java.model.entities.Position;

import java.util.List;

public interface PositionDao {

    void insert(Position position);
    void update(Position position);
    void deleteById(Integer id);
    Position findById(Integer id);
    List<Position> findAll();

}
