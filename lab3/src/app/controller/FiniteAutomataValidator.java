package app.controller;

import app.AppException;
import app.Constants;
import app.finiteAutomata.FiniteAutomata;
import app.finiteAutomata.Transition;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class FiniteAutomataValidator {
    public void validateFiniteAutomata(FiniteAutomata finiteAutomata) throws AppException {
        if (!finiteAutomata.getStates().contains(finiteAutomata.getInitialState())){
            throw new AppException("Initial state must be a valid state");
        }
        for (String finalState : finiteAutomata.getFinalStates()){
            if (!finiteAutomata.getStates().contains(finalState)){
                throw new AppException("Final state " + finalState + " must be a valid state");
            }
        }
        for (Transition transition : finiteAutomata.getTransitions()){
            if (!finiteAutomata.getStates().contains(transition.getState1())){
                throw new AppException("Invalid transition " + transition + " first state is not valid");
            }
            if (!finiteAutomata.getStates().contains(transition.getState2())){
                throw new AppException("Invalid transition " + transition + " second state is not valid");
            }
            if (!finiteAutomata.getAlphabet().contains(transition.getValue())){
                throw new AppException("Invalid value for " + transition);
            }
        }
    }
}
