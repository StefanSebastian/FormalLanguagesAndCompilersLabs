package app.controller;

import app.AppException;
import app.Constants;
import app.grammar.Grammar;
import app.grammar.Production;

import java.util.List;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class GrammarValidator {
    public void validateGrammar(Grammar grammar) throws AppException {
        if (!grammar.getNonterminals().contains(grammar.getStartSymbol())){
            throw new AppException("The start symbol must be a nonterminal");
        }

        for (Production production : grammar.getProductions()){
            if (!grammar.getNonterminals().contains(production.getLeftSide())){
                throw new AppException("Nonterminal " + production.getLeftSide() + " from production " + production + " is not a nonterminal for this grammar");
            }
            for (List<String> disj : production.getRightSide()){
                for (String elem : disj){
                    if (!grammar.getNonterminals().contains(elem) && !grammar.getTerminals().contains(elem) && !elem.equals(Constants.epsilon)){
                        throw new AppException(elem + " from production " + production + " is neither a terminal nor a nonterminal for this grammar");
                    }
                }
            }
        }
    }
}
