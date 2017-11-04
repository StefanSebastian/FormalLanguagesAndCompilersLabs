package app;

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

    public Controller(){
        grammars = new HashMap<>();
    }

    private void validateGrammar(Grammar grammar) throws AppException{
        if (grammars.containsKey(grammar.getId())){
            throw new AppException("This grammar is already saved");
        }

        if (!grammar.getNonterminals().contains(grammar.getStartSymbol())){
            throw new AppException("The start symbol must be a nonterminal");
        }

        for (Production production : grammar.getProductions()){
            if (!grammar.getNonterminals().contains(production.getLeftSide())){
                throw new AppException("Nonterminal " + production.getLeftSide() + " from production " + production + " is not a nonterminal for this grammar");
            }
            for (List<String> disj : production.getRightSide()){
                for (String elem : disj){
                    if (!grammar.getNonterminals().contains(elem) && !grammar.getTerminals().contains(elem)){
                        throw new AppException(elem + " from production " + production + " is neither a terminal nor a nonterminal for this grammar");
                    }
                }
            }
        }
    }

    public void addGrammar(Grammar grammar) throws AppException {
        validateGrammar(grammar);
        grammars.put(grammar.getId(), grammar);
    }

    public Map<String, Grammar> getGrammars() throws AppException {
        return grammars;
    }

    public Grammar getById(String id) throws AppException{
        return grammars.get(id);
    }

}
