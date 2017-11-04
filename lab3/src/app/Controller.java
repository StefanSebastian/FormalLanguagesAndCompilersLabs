package app;

import app.grammar.Grammar;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class Controller {
    private Map<String, Grammar> grammars;

    public Controller(){
        grammars = new HashMap<>();
    }

    public void addGrammar(Grammar grammar) throws AppException {
        grammars.put(grammar.getId(), grammar);
    }

    public Map<String, Grammar> getGrammars() throws AppException {
        return grammars;
    }

}
