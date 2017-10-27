package scanner.tokenizer;

import scanner.ScannerException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class LexicalScannerTokenizer implements ITokenizer {
    private boolean isInteger(String nr){
        try {
            Integer.parseInt(nr);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private String getPrecedent(List<String> tokens, int pos){
        while (pos > 0 && tokens.get(pos).equals(" ")){
            pos--;
        }
        return tokens.get(pos);
    }

    private void correctNegativeNumberConstants(List<String> tokens){

        for (int i = 1; i < tokens.size() - 1; i++){
            // if current is - and is followed by an int
            if (tokens.get(i).equals("-") && isInteger(tokens.get(i + 1))){
                // if the precedent is an operator or separator
                String precedent = getPrecedent(tokens, i - 1);
                if (Constants.operators.contains(precedent)
                        || precedent.equals("(")){
                    // save as constant
                    tokens.set(i, tokens.get(i) + tokens.get(i + 1));
                    tokens.remove(i + 1);
                }
            }
        }
    }


    @Override
    public List<String> tokenize(String input) throws ScannerException {
        TokenizerApplier tokenizerApplier = new TokenizerApplier();
        List<String> tokens = tokenizerApplier.tokenize(input, Arrays.asList(new SeparatorTokenizer(), new OperatorTokenizer()));
        correctNegativeNumberConstants(tokens);
        return tokens;
    }
}
