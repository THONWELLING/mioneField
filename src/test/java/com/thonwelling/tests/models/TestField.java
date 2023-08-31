package com.thonwelling.tests.models;

import com.thonwelling.exceptions.ExplosionException;
import com.thonwelling.models.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestField {
  private Field field ;
  @BeforeEach
  void startField() {
    field = new Field(3, 3);
  }

  @Test
  void neighborTestDisntanceOnePositionRight() {
    Field neighbor = new Field(3, 2);
    boolean result = field.addNeighborField(neighbor);
    assertTrue(result);
  }
  @Test
  void neighborTestDisntanceOnePositionLeft() {
    Field neighbor = new Field(3, 4);
    boolean result = field.addNeighborField(neighbor);
    assertTrue(result);
  }
  @Test
  void neighborTestDisntanceOnePositionTop() {
    Field neighbor = new Field(2, 3);
    boolean result = field.addNeighborField(neighbor);
    assertTrue(result);
  }
  @Test
  void neighborTestDisntanceOnePositionDown() {
    Field neighbor = new Field(4, 3);
    boolean result = field.addNeighborField(neighbor);
    assertTrue(result);
  }
  @Test
  void neighborTestDisntanceTwo() {
    Field neighbor = new Field(2, 2);
    boolean result = field.addNeighborField(neighbor);
    assertTrue(result);
  }
  @Test
  void notNeighborTest() {
    Field neighbor = new Field(1, 1);
    boolean result = field.addNeighborField(neighbor);
    assertFalse(result);
  }

  @Test
  void defaultTestValueOfTheMarkedAttribute() {
    assertFalse(field.isMarked());
  }

  @Test
  void testToggleMarkup() {
    field.toggleMarkup();
    assertTrue(field.isMarked());
  }

  @Test
  void testToggleMarkupTwiceCalled(){
    field.toggleMarkup();
    field.toggleMarkup();
    assertFalse(field.isMarked());
  }

  @Test
  void testOpenUnminedAndUnmarked() {
    assertTrue(field.open());
  }
  @Test
  void testOpenUnminedButMarked() {
    field.toggleMarkup();
    assertFalse(field.open());
  }
  @Test
  void testOpenMinedAndMarked() {
    field.toggleMarkup();
    field.mineField();
    assertFalse(field.open());
  }
  @Test
  void testOpenMinedAndUnMarked() {
    field.mineField();
   assertThrows(ExplosionException.class, () -> {
     field.open();
   });
  }
  @Test
  void testOpenWithNeigbors() {
    Field field11 = new Field(1, 1);
    Field field22 = new Field(2, 2);

    field22.addNeighborField(field11);
    field.addNeighborField(field22);

    field.open();
    assertTrue(field22.isOpen() && field11.isOpen());
  }
  @Test
  void testOpenWithNeigbors2() {
    Field field11 = new Field(1, 1);
    Field field12 = new Field(1, 1);
    field12.mineField();

    Field field22 = new Field(2, 2);
    field22.addNeighborField(field11);
    field22.addNeighborField(field12);

    field.addNeighborField(field22);
    field.open();
    assertTrue(field22.isOpen() && field11.isClosed());
  }

  @Test
  public void goalAchieved() {
    Field field = new Field(0, 0);
    field.mineField();
    assertFalse(field.goalAchieved());
    field.isMarked();
    assertFalse(field.goalAchieved());
  }

  @Test
  void minesInTheNeighborhood() {
    Field field1 = new Field(0, 0);
    Field field2 = new Field(1, 0);
    Field field3 = new Field(0, 1);

    field1.addNeighborField(field2);
    field1.addNeighborField(field3);
    field2.mineField();

    assertEquals(1, field1.minesInTheNeighborhood());
  }

  @Test
  void resetGame() {
    Field field = new Field(1, 1);
    field.mineField();
    field.isOpen();

    assertFalse(field.isOpen());
    assertFalse(field.mineField());

    field.resetGame();
    assertFalse(field.isOpen());
    assertFalse(field.mineField());
  }

  @Test
  void testToString() {
  }
}
