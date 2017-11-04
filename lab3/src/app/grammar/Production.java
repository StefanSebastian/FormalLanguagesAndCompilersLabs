package app.grammar;

import java.util.List;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class Production {
    private String leftSide;

    // first list contains elements separated by '|'
    // each inner list is a list of terminals + nonterminals
    private List<List<String>> rightSide;

    public Production(String leftSide, List<List<String>> rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    @Override
    public String toString() {
        String prod = leftSide + " -> ";
        for (int i = 0; i < rightSide.size(); i++){
            List<String> disj = rightSide.get(i);
            for (String elem : disj){
                prod += elem;
            }
            if (i < rightSide.size() - 1){
                prod += "|";
            }
        }
        return prod;
    }

    public String getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(String leftSide) {
        this.leftSide = leftSide;
    }

    public List<List<String>> getRightSide() {
        return rightSide;
    }

    public void setRightSide(List<List<String>> rightSide) {
        this.rightSide = rightSide;
    }
}
