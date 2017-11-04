package app.controller;

import app.AppException;
import app.Constants;
import app.finiteAutomata.FiniteAutomata;
import app.grammar.Grammar;
import app.grammar.Production;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class Controller {
    private Map<String, Grammar> grammars;
    private GrammarValidator grammarValidator;

    private Map<String, FiniteAutomata> finiteAutomatas;
    private FiniteAutomataValidator finiteAutomataValidator;

    private GrammarFaConverter converter;

    public Controller(){
        grammars = new HashMap<>();
        grammarValidator = new GrammarValidator();

        finiteAutomatas = new HashMap<>();
        finiteAutomataValidator = new FiniteAutomataValidator();

        converter = new GrammarFaConverter();
    }

    public void addFiniteAutomata(FiniteAutomata finiteAutomata) throws AppException {
        finiteAutomataValidator.validateFiniteAutomata(finiteAutomata);
        if (finiteAutomatas.containsKey(finiteAutomata.getIdentifier())){
            throw new AppException("This id is already set");
        }
        finiteAutomatas.put(finiteAutomata.getIdentifier(), finiteAutomata);
    }

    public void addGrammar(Grammar grammar) throws AppException {
        grammarValidator.validateGrammar(grammar);
        if (grammars.containsKey(grammar.getId())){
            throw new AppException("This id is already set");
        }
        grammars.put(grammar.getId(), grammar);
    }

    public Map<String, FiniteAutomata> getFiniteAutomatas() throws AppException {
        return finiteAutomatas;
    }

    public Map<String, Grammar> getGrammars() throws AppException {
        return grammars;
    }

    public FiniteAutomata getFiniteAutomataById(String id) throws AppException {
        return finiteAutomatas.get(id);
    }

    public Grammar getGrammarById(String id) throws AppException{
        return grammars.get(id);
    }

    public String checkRegular(Grammar grammar) throws AppException {
        if (grammar == null){
            throw new AppException("Invalid grammar");
        }

        String res = "";

        boolean startGoesToEpsilon = false;

        for (Production production : grammar.getProductions()){
            // production for start symbol
            if (production.getLeftSide().equals(grammar.getStartSymbol())){
                // iterate all productions for start
                for (List<String> disj : production.getRightSide()){
                    // iterate all elements in production
                    for (String elem : disj){
                        // check if epsilon
                        if (elem.equals(Constants.epsilon)){
                            startGoesToEpsilon = true;
                        }
                    }
                }
            }
        }


        for (Production production : grammar.getProductions()){
            for (List<String> disj : production.getRightSide()){
                if (disj.size() > 2){
                    res += "Invalid size at " + disj + " for production " + production;
                }
                if (disj.size() == 2){
                    String first = disj.get(0);
                    String second = disj.get(1);

                    if (!grammar.getTerminals().contains(first)){
                        res += "First element should be a terminal at" + disj + " for production " + production;
                    }
                    if (!grammar.getNonterminals().contains(second)){
                        res += "Second element should be a nonterminal at " + disj + " for production " + production;
                    }
                }
                if (disj.size() == 1){
                    if (!grammar.getTerminals().contains(disj.get(0))){
                        // not a terminal
                        if (!(production.getLeftSide().equals(grammar.getStartSymbol()) &&
                                disj.get(0).equals(Constants.epsilon))){
                            // not epsilon for start symbol
                            res += "Element should be a terminal at " + disj + " for production " + production;
                        }
                    }
                }
                if (startGoesToEpsilon){
                    for (String elem : disj){
                        if (elem.equals(grammar.getStartSymbol())){
                            res += "Start symbol cant appear in the right side of any production";
                        }
                    }
                }
            }
        }

        return res.length() == 0 ? "OK" : res;
    }

    public FiniteAutomata convertRegularGrammarToFiniteAutomata(Grammar grammar) throws AppException {
        if (!checkRegular(grammar).equals("OK")){
            throw new AppException("Grammar should be regular!");
        }

        FiniteAutomata finiteAutomata = converter.convert(grammar);
        addFiniteAutomata(finiteAutomata);
        return finiteAutomata;
    }

}
