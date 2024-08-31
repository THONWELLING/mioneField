package com.thonwelling;

import com.thonwelling.models.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Table table;
    @BeforeEach
    void setUp() {
        table = new Table(7, 7, 7);
    }

    @Test
    void testOpenField() {
        table.openField(3, 3);
        assertTrue(table.isFieldOpened(3, 3), "Field At (3, 3) Shoud Be Open.");
    }
    @Test
    void testChangeMarcation() {
        table.changeMarcation(4, 4);
        table.changeMarcation(4, 5);
        assertTrue(table.isFieldMarked(4, 4), "Field At (4, 4) Shoould Be Marked.");
        assertTrue(table.isFieldMarked(4, 5), "Field At (4, 4) Shoould Be Marked.");
    }

    @Test
    void testToString() {
        table.openField(3, 3);
        table.changeMarcation(4, 4);
        table.changeMarcation(4, 5);

        String tableExpected = " ? ? ? ? ? ? ? \n" +
                               " ? ? ? ? ? ? ? \n" +
                               " ? ? ? ? ? ? ? \n" +
                               " ? ? ? 0 ? ? ? \n" +
                               " ? ? ? ? x x ? \n" +
                               " ? ? ? ? ? ? ? \n" +
                               " ? ? ? ? ? ? ? \n";
        assertEquals(tableExpected, table.toString());
    }
}