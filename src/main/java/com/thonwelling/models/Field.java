package com.thonwelling.models;

import java.util.ArrayList;
import java.util.List;

public class Field {
  private final int row;
  private final int column;
  private boolean open;
  private boolean mineField;
  private boolean marked;

  private List<Field> neighborFields = new ArrayList<>();

  public Field(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public boolean addNeighborField(Field neighborField) {

    boolean diferentRow = row != neighborField.row;
    boolean diferentColumn = column != neighborField.column;
    boolean diagonal = diferentRow && diferentColumn;

    int deltaRow = Math.abs(row - neighborField.row);
    int deltaColumn = Math.abs(column - neighborField.column);
    int deltaGeneral = deltaColumn + deltaRow;

    if (deltaGeneral == 1 && !diagonal){
      neighborFields.add(neighborField);
      return true;
    } else if (deltaGeneral == 2 && diagonal) {
      neighborFields.add(neighborField);
      return true;
    } else {
      return false;
    }
  }
}