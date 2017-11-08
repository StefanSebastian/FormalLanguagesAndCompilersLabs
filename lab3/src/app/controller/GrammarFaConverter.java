package app.controller;

import app.AppException;
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

    public Grammar convert(FiniteAutomata finiteAutomata) throws AppException {
        List<String> nonterminals = new LinkedList<>();
        List<String> terminals = new LinkedList<>();
        Map<String, Production> productions = new HashMap<>(); // productions maped by left side
        Map<String, String> stateNonterminalCorresp = new HashMap<>();

        // terminals match the alphabet
        terminals = finiteAutomata.getAlphabet();

        // nonterminals
        // all states ;         alternative only outgoing states
        Set<String> statesOutgoing = new HashSet<>(finiteAutomata.getStates());

        String startSymbol = "S";
        nonterminals.add(startSymbol);
        stateNonterminalCorresp.put(finiteAutomata.getInitialState(), startSymbol);

        int i = 1;
        for (String state : statesOutgoing){
            if (!state.equals(finiteAutomata.getInitialState())) {
                nonterminals.add("N" + i);
                stateNonterminalCorresp.put(state, "N" + i);
                i++;
            }
        }

        // in initial state is final and there is an incoming transition
        boolean initialFinalAndIncoming = false;
        if (finiteAutomata.getFinalStates().contains(finiteAutomata.getInitialState())){
            boolean incoming = false;
            for (Transition transition : finiteAutomata.getTransitions()){
                if (transition.getState2().equals(finiteAutomata.getInitialState())){
                    incoming = true;
                }
            }

            if (incoming){
                // add the extra nonterminal
                stateNonterminalCorresp.put(finiteAutomata.getInitialState(), "N0");
                nonterminals.add("N0");

                // treat the starting non terminal separately
                Production production = new Production(startSymbol, new LinkedList<>());
                List<List<String>> righSide = production.getRightSide();
                righSide.add(Arrays.asList(Constants.epsilon));
                productions.put(startSymbol, production);

                initialFinalAndIncoming = true;
            } else {
                Production production = new Production(startSymbol, new LinkedList<>());
                List<List<String>> righSide = production.getRightSide();
                righSide.add(Arrays.asList(Constants.epsilon));
                productions.put(startSymbol, production);
            }
        }

        // iterate through transition
        for (Transition transition : finiteAutomata.getTransitions()){
            // a production of form A -> b
            if (finiteAutomata.getFinalStates().contains(transition.getState2())){
                String nonterm1 = stateNonterminalCorresp.get(transition.getState1());

                Production production = productions.get(nonterm1);
                if (production == null){
                    production = new Production(nonterm1, new LinkedList<>());
                    productions.put(nonterm1, production);
                }

                List<List<String>> rightSide = production.getRightSide();
                rightSide.add(Arrays.asList(transition.getValue()));

                if (transition.getState1().equals(finiteAutomata.getInitialState()) &&
                        initialFinalAndIncoming){
                    production = productions.get("S");
                    rightSide = production.getRightSide();
                    rightSide.add(Arrays.asList(transition.getValue()));
                }
            }
            // production of form A -> aB ; alternative only between outgoing states
            String nonterm1 = stateNonterminalCorresp.get(transition.getState1());
            String nonterm2 = stateNonterminalCorresp.get(transition.getState2());

            Production production = productions.get(nonterm1);
            if (production == null){
                production = new Production(nonterm1, new LinkedList<>());
                productions.put(nonterm1, production);
            }

            List<List<String>> rightSide = production.getRightSide();
            rightSide.add(Arrays.asList(transition.getValue(), nonterm2));

            if (transition.getState1().equals(finiteAutomata.getInitialState()) &&
                    initialFinalAndIncoming){
                production = productions.get("S");
                rightSide = production.getRightSide();
                rightSide.add(Arrays.asList(transition.getValue(), nonterm2));
            }

        }

        String id = finiteAutomata.getIdentifier() + "G";
        List<Production> productionList = new LinkedList<>(productions.values());
        return new Grammar(id, nonterminals, terminals, productionList, startSymbol);
    }
}
