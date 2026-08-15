import model.dao.PositionDao;
import model.entities.Position;
import model.service.PositionService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class PositionServiceTest {

    Position position;
    PositionDao positionDao;
    PositionService service;

    @BeforeEach
    void setUp(){
        positionDao = mock(PositionDao.class);
        service = new PositionService(positionDao);
    }

    private Position createValidPosition(){
        Position position = new Position(1,"Test");
        return position;
    }
}
