package scanner.scannerResult;

import scanner.utils.SortedTable;
import scanner.domain.SymbolPair;

/**
 * Created by Sebi on 22-Oct-17.
 */
public class SymbolTable {
    private SortedTable table;

    private Integer identifier = 0;

    public SymbolTable(){
        table = new SortedTable();
    }

    public void insert(SymbolPair symbolPair){
        table.insert(symbolPair);
    }

    public Integer insert(String symbol){
        identifier++;
        table.insert(new SymbolPair(identifier, symbol));
        return identifier;
    }

    public Integer getIdentifier(String symbol){
        return table.getIdentifier(symbol);
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "table=" + table +
                '}';
    }
}
