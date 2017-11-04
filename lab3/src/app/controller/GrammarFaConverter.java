package app.controller;

import app.Constants;
import app.finiteAutomata.FiniteAutomata;
import app.finiteAutomata.Transition;
import app.grammar.Grammar;
import app.grammar.Production;

import java.util.*;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class GrammarFaConverter {

    /*
    assumes grammar is regular
     */
    public FiniteAutomata convert(Grammar grammar){
        Map<String, String> correspondingStates = new HashMap<>();
        List<String> states = new LinkedList<>();
        List<String> finalStates = new LinkedList<>();

        // states = nonterm + 1
        int i = 0;
        for (String nonterm : grammar.getNonterminals()){
            correspondingStates.put(nonterm, "q" + i);
            states.add("q" + i);
            i++;
        }
        states.add("q" + i);
        finalStates.add("q" + i);

        //set initial state
        String initialState = correspondingStates.get(grammar.getStartSymbol());

        List<Transition> transitions = new LinkedList<>();

        boolean firstGoesToEpsilon = false;


        for (Production production : grammar.getProductions()){
            String leftSide = production.getLeftSide();

            for (List<String> disj : production.getRightSide()){
                //  A -> aB => A -> B with a
                if (disj.size() == 2){
                    String stateFirst = correspondingStates.get(leftSide);
                    String stateSecond = correspondingStates.get(disj.get(1));
                    String value = disj.get(0);

                    transitions.add(new Transition(stateFirst, stateSecond, value));
                }

                // A -> b ; transition to final
                if (disj.size() == 1 && !disj.get(0).equals(Constants.epsilon)){
                    String stateFirst = correspondingStates.get(leftSide);
                    String stateSecond = finalStates.get(0);
                    String value = disj.get(0);

                    transitions.add(new Transition(stateFirst, stateSecond, value));

                }
            }


            // S -> epsilon
            if (production.getLeftSide().equals(grammar.getStartSymbol())){
                for (List<String> disj : production.getRightSide()){
                    if (disj.size() == 1 && disj.get(0).equals(Constants.epsilon)){
                        firstGoesToEpsilon = true;
                    }
                }
            }
        }

        if (firstGoesToEpsilon){
            finalStates.add(initialState);
        }


        return new FiniteAutomata(grammar.getId() + "FA", states, grammar.getTerminals(), initialState, finalStates, transitions);
    }
}
