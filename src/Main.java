import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.PositionDao;
import model.entities.Department;
import model.entities.Position;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    PositionDao positionDao = DaoFactory.createPositionDao();
    Position position = new Position();
    position.setName("Auxiliar de Recursos");
    position.setId(1);
    positionDao.update(position);
    System.out.println("Cargo atualizado com sucesso!");

    }

