package com.thonwelling.models;

import com.thonwelling.exceptions.ExplosionException;

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

  public void toggleMarkup() {
    if(!open) {
      marked = !marked;
    }
  }

  public boolean open(){
    if (!open && !marked) {
      open = true;
      if (mineField) {
        throw new ExplosionException();
      }
      if (safeNeighborhood()) {
        neighborFields.forEach(Field::open);
      }
      return true;
    } else {
      return false;
    }
  }

  boolean safeNeighborhood(){
    return neighborFields.stream().noneMatch(neighbors -> neighbors.mineField);
  }

  public boolean isMarked(){
    return marked;
  }

  public boolean mineField() {
    mineField = true;
    return false;
  }

  public boolean isMined() {
    return mineField;
  }
  public boolean isOpen() {
    return open;
  }
  public boolean isClosed() {
    return !isOpen();
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public boolean goalAchieved() {
    boolean sortedOut = !mineField && open;
    boolean unarmed   = mineField && marked;
    return sortedOut || unarmed;
  }

  public long minesInTheNeighborhood (){
    return neighborFields.stream().filter(value -> value.mineField).count();
  }

  public void resetGame(){
    open      = false;
    mineField = false;
    marked    = false;
  }
  
  public String toString(){
    if(marked){
      return "X";
    } else if (open && mineField) {
      return "*";
    } else if (open && minesInTheNeighborhood() > 0) {
      return Long.toString(minesInTheNeighborhood());
    } else if (open) {
      return " ";
    } else {
      return "?";
    }
  }
}