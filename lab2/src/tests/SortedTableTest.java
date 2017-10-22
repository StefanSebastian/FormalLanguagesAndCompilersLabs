package tests;

import scanner.utils.SortedTable;
import scanner.domain.SymbolPair;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 22-Oct-17.
 */
public class SortedTableTest {
    @org.junit.Test
    public void getSize() throws Exception {
        SortedTable table = new SortedTable();
        assertTrue(table.getSize() == 0);
        SymbolPair pair = new SymbolPair(1, "a");
        table.insert(pair);
        assertTrue(table.getSize() == 1);
        SymbolPair pair1 = new SymbolPair(2, "b");
        table.insert(pair1);
        assertTrue(table.getSize() == 2);
    }

    @org.junit.Test
    public void insert() throws Exception {
        SortedTable sortedTable = new SortedTable();

        SymbolPair pair = new SymbolPair(1, "ab");
        SymbolPair pair1 = new SymbolPair(2, "bb");

        sortedTable.insert(pair1);
        sortedTable.insert(pair);

        assertTrue(sortedTable.getSize() == 2);

        SymbolPair pair3 = new SymbolPair(3, "cb");

        sortedTable.insert(pair3);

        assertTrue(sortedTable.getSize() == 3);

        SymbolPair pair4 = new SymbolPair(4, "a");
        sortedTable.insert(pair4);

        assertTrue(sortedTable.getSize() == 4);
    }

    @org.junit.Test
    public void getIdentifier() throws Exception {
        SortedTable sortedTable = new SortedTable();

        SymbolPair pair = new SymbolPair(1, "aba");
        SymbolPair pair1 = new SymbolPair(2, "bba");
        SymbolPair pair3 = new SymbolPair(3, "cba");
        SymbolPair pair4 = new SymbolPair(4, "a");
        SymbolPair pair5 = new SymbolPair(5, "baa");

        sortedTable.insert(pair1);
        sortedTable.insert(pair);
        sortedTable.insert(pair3);
        sortedTable.insert(pair4);
        sortedTable.insert(pair5);

        assertTrue(sortedTable.getSize() == 5);

        assertTrue(sortedTable.getIdentifier("aba") == 1);
        assertTrue(sortedTable.getIdentifier("bba") == 2);
        assertTrue(sortedTable.getIdentifier("cba") == 3);
        assertTrue(sortedTable.getIdentifier("baa") == 5);
        assertTrue(sortedTable.getIdentifier("a") == 4);

    }

}