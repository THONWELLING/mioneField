package com.thonwelling;

import com.thonwelling.models.Table;
import com.thonwelling.view.TableConsole;


public class Main {
  public static void main(String[] args) {

    System.out.println("CAMPO MINADO!");
    Table table = new Table(6, 6, 6);
    new TableConsole(table);

  }
}