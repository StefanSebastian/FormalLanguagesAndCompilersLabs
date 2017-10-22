package scanner.scannerResult;

import scanner.utils.SortedTable;
import scanner.domain.SymbolPair;

/**
 * Created by Sebi on 22-Oct-17.
 */
public class SymbolTable {
    private SortedTable table;

    public SymbolTable(){
        table = new SortedTable();
    }

    // maybe string symbol - do a query then add with a generated id
    public void insert(SymbolPair symbolPair){
        table.insert(symbolPair);
    }

    public Integer getIdentifier(String symbol){
        return table.getIdentifier(symbol);
    }
}
