package scanner.tokenizer;

import scanner.ScannerException;
import scanner.utils.RegexCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class OperatorTokenizer implements ITokenizer {
    private List<String> doubleCharOps;

    public OperatorTokenizer(){
        doubleCharOps = Arrays.asList(">=", "==", "<=", "!=");
    }

    public String getDoubleOperators(){
        return "(>=|<=|==|!=)";
    }

    public String getSingleCharOperators(){
        return "(>|<|=|\\+|-|\\*|/|%)";
    }

    @Override
    public List<String> tokenize(String input) throws ScannerException {
        List<String> tokens = new ArrayList<>();

        // apply tokenizer on operators of 2 chars : >=, <=, !=, ==
        Tokenizer doubleCharOpTokenizer = new Tokenizer(getDoubleOperators());
        List<String> firstSplit = doubleCharOpTokenizer.tokenize(input);

        // for each token of the previous operation (that is not an operator) apply a tokenizer on single-char operator
        Tokenizer singleCharOpTokenizer = new Tokenizer(getSingleCharOperators());
        for (String toSplit : firstSplit){
            // ignore if operator
            if (doubleCharOps.contains(toSplit)){
                tokens.add(toSplit);
            } else {
                tokens.addAll(singleCharOpTokenizer.tokenize(toSplit));
            }
        }
        return tokens;
    }
}
