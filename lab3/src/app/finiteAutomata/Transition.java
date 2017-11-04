package app.finiteAutomata;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class Transition {
    private String state1;
    private String state2;
    private String value;

    public Transition(String state1, String state2, String value) {
        this.state1 = state1;
        this.state2 = state2;
        this.value = value;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "From " + state1 + " to " + state2 + " with " + value;
    }
}
