package scanner.utils;

import scanner.domain.SymbolPair;

/**
 * Created by Sebi on 17-Oct-17.
 */
public class SortedTable {
    // the array
    private SymbolPair[] table;
    // size in memory
    private int sizeInMemory;
    // elements in array
    private int size;

    public SortedTable(){
        size = 0;
        sizeInMemory = 1;
        table = new SymbolPair[sizeInMemory];
    }

    /*
    The number of elements in array
     */
    public int getSize() {
        return size;
    }

    /*
    Doubles the allocated memory
     */
    private void expand(){
        sizeInMemory = sizeInMemory * 2;
        SymbolPair[] newTable = new SymbolPair[sizeInMemory];
        System.arraycopy(table, 0, newTable, 0, size);
        table = newTable;
    }

    /*
    Returns the position at which the given element should be inserted
     */
    private int findPosition(SymbolPair element){
        int i = 0;
        while (i < size && element.compareTo(table[i]) > 0){
            i++;
        }
        return i;
    }

    /*
    Moves all elements, starting from the given position, one position to the right
     */
    private void moveElements(int position){
        for (int i = size; i > position; i--){
            table[i] = table[i - 1];
        }
    }

    /*
    Inserts an element in our table
     */
    public void insert(SymbolPair element){
        if (size == sizeInMemory){
            expand();
        }

        if (size == 0){
            table[0] = element;
            size += 1;
            return;
        }

        int position = findPosition(element);
        moveElements(position);
        table[position] = element;
        size += 1;
    }

    private SymbolPair binarySearch(int start, int end, String symbol){
        if (start > end){
            return null;
        }
        int mid = (start + end) / 2;

        if (table[mid].getSymbol().equals(symbol)){
            return table[mid];
        }

        if (symbol.compareTo(table[mid].getSymbol()) < 0){
            return binarySearch(start, mid - 1, symbol);
        } else {
            return binarySearch(mid + 1, end, symbol);
        }
    }

    public Integer getIdentifier(String symbol){
        SymbolPair pair = binarySearch(0, size - 1, symbol);
        return pair == null ? null : pair.getIdentifier();
    }

    @Override
    public String toString(){
        String res = "";
        for (int i = 0; i < size; i++){
            res += table[i];
            res += "\n";
        }
        return res;
    }
}
