package model.service;

import model.dao.DaoFactory;
import model.dao.PositionDao;

public class PositionService {

    public PositionDao positionDao = DaoFactory.createPositionDao();
}
