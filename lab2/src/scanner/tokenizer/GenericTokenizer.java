package scanner.tokenizer;

import scanner.ScannerException;
import scanner.tokenizer.ITokenizer;
import scanner.utils.RegexCollection;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class GenericTokenizer implements ITokenizer {
    private String delimiters;

    public GenericTokenizer(String delimiters){
        this.delimiters = delimiters;
    }

    public List<String> tokenize(String input) throws ScannerException {
        return Arrays.asList(input.split(String.format(RegexCollection.WITH_DELIMITER, delimiters)));
    }
}
