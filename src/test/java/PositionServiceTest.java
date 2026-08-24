import db.DBException;
import model.dao.PositionDao;
import model.entities.Department;
import model.entities.Position;
import model.service.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    @Test
    void insertShouldCallPositionDaoWhenPositionIsValid(){
        position = createValidPosition();
        service.insert(position);
        verify(positionDao).insert(position);
    }

    @ParameterizedTest
    @CsvSource(value = {"null, Position cannot be null"}, nullValues = "null")
    void insertShouldThrowExceptionWhenPositionIsNull(Position position, String expectedMessage){
        DBException exception = assertThrows(DBException.class, () -> service.insert(position));
        assertEquals(expectedMessage,exception.getMessage());
        verify(positionDao, never()).insert(position);
    }

    @ParameterizedTest
    @CsvSource(value = {"null, Position name cannot be null","'', Position name cannot be empty"}, nullValues = "null")
    void insertShouldThrowExceptionWhenPositionNameIsNullOrEmpty(String name, String expectedMessage){
        position = createValidPosition();
        position.setName(name);
        DBException exception = assertThrows(DBException.class, () -> service.insert(position));
        assertEquals(expectedMessage, exception.getMessage());
        verify(positionDao, never()).insert(position);
    }

    @Test
    void updateShouldCallPositionDaoWhenPositionIsValid(){
        position = createValidPosition();
        service.update(position);
        verify(positionDao).update(position);
    }

    @ParameterizedTest
    @CsvSource(value = {"null, Position cannot be null"}, nullValues = "null")
    void updateShouldThrowExceptionWhenPositionIsNull(Position position, String expectedMessage){
        DBException exception = assertThrows(DBException.class, () -> service.update(position));
        assertEquals(expectedMessage, exception.getMessage());
        verify(positionDao, never()).update(position);
    }

    @ParameterizedTest
    @CsvSource(value = {"null, Position name cannot be null","'', Position name cannot be empty"}, nullValues = "null")
    void updateShouldThrowExceptionWhenPositionNameIsNullOrEmpty(String name, String expetecMessage){
        position = createValidPosition();
        position.setName(name);
        DBException exception = assertThrows(DBException.class, () -> service.update(position));
        assertEquals(expetecMessage, exception.getMessage());
        verify(positionDao, never()).update(position);
    }

    @Test
    void findByIdShouldCallPositionDaoWhenIdIsValid(){
        Integer id = 1;
        service.findById(id);
        verify(positionDao).findById(id);
    }

    @ParameterizedTest
    @NullSource
    void findByIdShouldThrowExceptionWhenIdIsNull(Integer id){
        DBException exception = assertThrows(DBException.class, () -> service.findById(id));
        String expectedMessage = "Id cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
        verify(positionDao, never()).findById(id);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void findByIdShouldThrowExceptionWhenIdIsLessOrEqualToZero(Integer id) {
        DBException exception = assertThrows(DBException.class, () -> service.findById(id));
        String expectedMessage = "Id should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
        verify(positionDao, never()).findById(id);
    }

    @Test
    void findAllShouldReturnListReturnedByPositionDao(){
        List<Position> positions = List.of(createValidPosition());
        when(positionDao.findAll()).thenReturn(positions);
        List<Position> result = service.findAll();
        assertEquals(positions, result);
        verify(positionDao).findAll();
    }

    @Test
    void findAllShouldReturnEmptyListWhenDaoReturnsEmptyList(){
        List<Position> positions = Collections.emptyList();
        when(positionDao.findAll()).thenReturn(positions);
        List<Position> result = service.findAll();
        assertEquals(positions, result);
        verify(positionDao).findAll();
    }

    @Test
    void countPositionShouldReturnCountReturnedByDao(){
        Integer countPositions = 1;
        when(positionDao.countPosition()).thenReturn(countPositions);
        Integer result = service.countPosition();
        assertEquals(countPositions, result);
        verify(positionDao).countPosition();
    }

    @Test
    void countPositionShouldReturnZeroWhenDaoReturnZero(){
        Integer countPositions = 0;
        when(positionDao.countPosition()).thenReturn(countPositions);
        Integer result = service.countPosition();
        assertEquals(countPositions, result);
        verify(positionDao).countPosition();
    }

    @Test
    void findPageShouldReturnListReturnedByDao(){
        List<Position> positions = List.of(createValidPosition());
        Integer page = 1;
        Integer pageSize = 5;
        when(positionDao.findPage(page, pageSize)).thenReturn(positions);
        List<Position> result = service.findPage(page, pageSize);
        assertEquals(positions, result);
        verify(positionDao).findPage(page, pageSize);
    }

    @Test
    void findPageShouldReturnEmptyListWhenDaoReturnsEmptyList(){
        List<Position> positions = Collections.emptyList();
        Integer page = 1;
        Integer pageSize = 5;
        when(positionDao.findPage(page, pageSize)).thenReturn(positions);
        List<Position> result = service.findPage(page, pageSize);
        assertEquals(positions, result);
        verify(positionDao).findPage(page, pageSize);
    }

    @Test
    void findPageShouldThrowExceptionWhenPageNumberIsNull(){
        Integer page = null;
        Integer pageSize = 5;
        DBException exception = assertThrows(DBException.class, () -> service.findPage(page, pageSize));
        String expectedMessage = "Page number cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
        verify(positionDao, never()).findPage(anyInt(), anyInt());
    }

    @Test
    void findPageShouldThrowExceptionWhenPageNumberIsLessOrEqualToZero(){
        Integer page = 0;
        Integer pageSize = 5;
        DBException exception = assertThrows(DBException.class, () -> service.findPage(page, pageSize));
        String expectedMessage = "Page number should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
        verify(positionDao, never()).findPage(anyInt(),anyInt());
    }

    @Test
    void findPageShouldThrowExceptionWhenPageSizeIsNull(){
        Integer page = 1;
        Integer pageSize = null;
        DBException exception = assertThrows(DBException.class, () -> service.findPage(page, pageSize));
        String expectedMessage = "Page size cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
        verify(positionDao, never()).findPage(anyInt(), anyInt());
    }

    @Test
    void findPageShouldThrowExceptionWhenPageSizeIsEqualOrLessThanZero(){
        Integer page = 1;
        Integer pageSize = 0;
        DBException exception = assertThrows(DBException.class, () -> service.findPage(page, pageSize));
        String expectedMessage = "Page size should be greater than zero";
        assertEquals(expectedMessage, exception.getMessage());
        verify(positionDao, never()).findPage(anyInt(),anyInt());
    }


}
