package com.thonwelling.models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Table {

private int rowsQuantity;
private int columnsQuantity;
  private int minesQuantity;
  private final List<Field> fields = new ArrayList<>();

  public Table(int rowsQuantity, int columnsQuantity, int minesQuantity) {
    this.rowsQuantity = rowsQuantity;
    this.columnsQuantity = columnsQuantity;
    this.minesQuantity = minesQuantity;

    generateFields();
    associteNeighbors();
    sortMines();
  }
  private void generateFields() {
    for (int row = 0; row < rowsQuantity; row++) {
      for (int column = 0; column < columnsQuantity ; column++) {
        fields.add(new Field(row, column));
      }
    };
  }
  private void associteNeighbors() {
    for (Field field1 : fields ) {
      for (Field field2 : fields ) {
        field1.addNeighborField(field2);
      }
    }
  }
  private void sortMines() {
    long armedMines = 0;
    Predicate<Field> minade = field -> field.isMined();
    do {
      armedMines = fields.stream().filter(minade).count();
      int randomic = (int) (Math.random() * fields.size());

      fields.get(randomic).mineField();
    } while (armedMines < minesQuantity);
  }

  public boolean goalAchieved() {
    return fields.stream().allMatch(field -> field.goalAchieved());
  }

  public void resetGame() {
    fields.stream().forEach(field -> field.resetGame());
    sortMines();
  }

}
