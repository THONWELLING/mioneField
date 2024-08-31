package com.thonwelling.models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Table {

  private int rowsQuantity;
  private int columnsQuantity;
  private int minesQuantity;
  private final List<Field> fields = new ArrayList<>();

  public Table(int pRowsQuantity, int pColumnsQuantity, int pMinesQuantity) {
    this.rowsQuantity    = pRowsQuantity;
    this.columnsQuantity = pColumnsQuantity;
    this.minesQuantity   = pMinesQuantity;

    generateFields();
    associteNeighbors();
    sortMines();
  }

  public void openField(int pRow, int pColumn){
    fields.parallelStream()
            .filter(field -> field.getRow() == pRow && field.getColumn() ==pColumn)
            .findFirst()
            .ifPresent(field -> field.open());
  }
  public void changeMarcation(int pRow, int pColumn){
    fields.parallelStream()
            .filter(field -> field.getRow() == pRow && field.getColumn() ==pColumn)
            .findFirst()
            .ifPresent(field -> field.toggleMarkup());
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

  public boolean isFieldMarked (int pRow, int pColumn) {
    return fields.parallelStream()
            .filter(field -> field.getRow() == pRow && field.getColumn() == pColumn)
            .findFirst()
            .map(Field::isMarked)
            .orElse(false);
  }
  public boolean isFieldOpened (int pRow, int pColumn) {
    return fields.parallelStream()
            .filter(field -> field.getRow() == pRow && field.getColumn() == pColumn)
            .findFirst()
            .map(Field::isMarked)
            .orElse(false);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    int i = 0;
    for (int r = 0; r < rowsQuantity; r++) {
      for (int c = 0; c < columnsQuantity; c++) {
        stringBuilder.append(" ");
        stringBuilder.append(fields.get(i));
        stringBuilder.append(" ");
        i++;

      }
      stringBuilder.append("\n");

    }
    return stringBuilder.toString();
  }
}
