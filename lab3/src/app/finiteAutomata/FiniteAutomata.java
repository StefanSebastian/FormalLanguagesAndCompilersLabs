package app.finiteAutomata;

import java.util.List;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class FiniteAutomata {
    private String identifier;
    private List<String> states;
    private List<String> alphabet;
    private String initialState;
    private List<String> finalStates;
    private List<Transition> transitions;

    public FiniteAutomata(String identifier, List<String> states, List<String> alphabet, String initialState, List<String> finalStates, List<Transition> transitions) {
        this.identifier = identifier;
        this.states = states;
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(List<String> finalStates) {
        this.finalStates = finalStates;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    @Override
    public String toString() {
        return "FiniteAutomata{" +
                "identifier='" + identifier + '\'' +
                ", states=" + states +
                ", alphabet=" + alphabet +
                ", initialState='" + initialState + '\'' +
                ", finalStates=" + finalStates +
                ", transitions=" + transitions +
                '}';
    }
}


