package model.dao;

import model.entities.Position;

import java.util.List;

public interface PositionDao {

    void insert(Position position);
    void update(Position position);
    void deleteById(Integer id);
    Position findById(Integer id);
    List<Position> findAll();

}
