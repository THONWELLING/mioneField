package com.thonwelling.view;

import com.thonwelling.exceptions.ExitException;
import com.thonwelling.exceptions.ExplosionException;
import com.thonwelling.models.Table;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TableConsole {
    private Table table;
    private Scanner input = new Scanner(System.in);

    public TableConsole(Table table) { 
        this.table = table;
        executeGame();
    
    }

    private void executeGame() {
        try{
            boolean vContinue = true;
            while (vContinue) {
                gameFlow();
                System.out.println("Do You Want Another Match? (S/n) ");
                String vAnswer = input.nextLine();
                if ("n".equalsIgnoreCase(vAnswer)) {
                    vContinue = false;
                } else {
                    table.resetGame();

                }
            }

        } catch (ExitException exit) {
            System.out.println("You Are Living The Game.");
        } finally {
            input.close();
        }

    }

    private void gameFlow() {
        try {
            while (!table.goalAchieved()) {
                System.out.println(table);
                String userValue = getValueEnteredByUser("Type The Value To (X, Y): ");

                Iterator<Integer> vRowAndColumn = Arrays.stream(userValue.split(","))
                        .map(vElement -> Integer.parseInt(vElement.trim())).iterator();

                userValue = getValueEnteredByUser("Type 1 To Open Or 2 To (Mark/unMark): ");
                if ("1".equals(userValue)){
                    table.openField(vRowAndColumn.next(), vRowAndColumn.next());
                } else if ("2".equals(userValue)) {
                    table.changeMarcation(vRowAndColumn.next(), vRowAndColumn.next());
                }
            }
            System.out.println("You Did It.");
        } catch (ExplosionException explosion) {
            System.out.println("You Stepped On A Landmine. You Lose.");
        }
    }

    private String getValueEnteredByUser (String text) {
        System.out.print(text);
        String enteredValue = input.nextLine();
        if ("exit".equalsIgnoreCase(enteredValue)) {
            throw new ExitException();
        }
        return enteredValue;
    }

}
