package com.thonwelling.tests.models;

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
}
