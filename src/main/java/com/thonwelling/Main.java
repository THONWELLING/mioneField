package com.thonwelling;

import com.thonwelling.models.Table;

public class Main {
  public static void main(String[] args) {

    System.out.println("Hello world!");
    Table table = new Table(6, 6, 6);

    table.openField(3, 3);
    table.changeMarcation(4,4);
    table.changeMarcation(4,5);
    System.out.println(table);
  }
}