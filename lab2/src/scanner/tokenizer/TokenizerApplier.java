package scanner.tokenizer;

import scanner.ScannerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class TokenizerApplier {
    /*
    Given a list of inputs and a tokenizer
    applies the tokenizer and concatenates the results
     */
    private List<String> applyTokenizer(List<String> inputs, ITokenizer tokenizer) throws ScannerException{
        List<String> tokens = new ArrayList<>();

        for (String input : inputs){
            tokens.addAll(tokenizer.tokenize(input));
        }

        return tokens;
    }

    /*
    Applies a series of tokenizers for an input and concatenates the results
     */
    private List<String> applyTokenizers(String input, List<ITokenizer> tokenizers) throws ScannerException {
        List<String> tokens = new ArrayList<>();

        tokens.add(input);
        for (ITokenizer tokenizer : tokenizers){
            tokens = applyTokenizer(tokens, tokenizer);
        }

        return tokens;
    }

    public List<String> tokenize(String input, List<ITokenizer> tokenizers) throws ScannerException{
        return applyTokenizers(input, tokenizers);
    }
}
