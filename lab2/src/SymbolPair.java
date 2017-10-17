/**
 * Created by Sebi on 17-Oct-17.
 */
public class SymbolPair implements Comparable<SymbolPair>{

    private Integer identifier;
    private String symbol;

    public SymbolPair(Integer identifier, String symbol) {
        this.identifier = identifier;
        this.symbol = symbol;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public int compareTo(SymbolPair o) {
        return symbol.compareTo(o.getSymbol());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SymbolPair symbol1 = (SymbolPair) o;

        return symbol != null ? symbol.equals(symbol1.symbol) : symbol1.symbol == null;
    }
}
