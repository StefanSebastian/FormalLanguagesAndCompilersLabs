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
        size = 1;
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
    }

    /*
    Returns the position at which the given element should be inserted
     */
    private int findPosition(SymbolPair element){
        int i = 0;
        while (element.compareTo(table[i]) > 0){
            i++;
        }
        return i - 1;
    }

    /*
    Moves all elements, starting from the given position, one position to the right
     */
    private void moveElements(int position){
        for (int i = size + 1; i > position; i--){
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

        int position = findPosition(element);
        moveElements(position);
        table[position] = element;
        size += 1;
    }

    private SymbolPair binarySearch(int start, int end, String symbol){
        if (start >= end){
            return table[start];
        }
        int mid = (start + end) / 2;
        
    }

    public Integer getIdentifier(String symbol){
        SymbolPair pair = binarySearch(0, size, symbol);
        return pair.getIdentifier();
    }

}
