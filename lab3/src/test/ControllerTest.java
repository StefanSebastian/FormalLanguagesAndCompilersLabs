package test;

import app.controller.Controller;
import app.controller.GrammarValidator;
import app.grammar.Grammar;
import app.grammar.Production;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class ControllerTest {
    @org.junit.Test
    public void checkRegular() throws Exception {
        List<Production> productionList = Arrays.asList(
                new Production("S", Arrays.asList(Arrays.asList("a"), Arrays.asList("a","A"))),
                new Production("A", Arrays.asList(Arrays.asList("a")))
        );

        Grammar grammar = new Grammar("1", Arrays.asList("S","A"), Arrays.asList("a","b"), productionList, "S");

        GrammarValidator validator = new GrammarValidator();
        validator.validateGrammar(grammar);

        Controller controller = new Controller();
        String res = controller.checkRegular(grammar);
        System.out.println(res);
    }

}