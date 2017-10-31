package scanner.tokenizer;

import scanner.ScannerException;
import scanner.utils.RegexCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class SeparatorTokenizer implements ITokenizer {
    private String getSeparators(){
        return " |;|\\(|\\)|\\{|\\}|\\[|\\]";
    }

    public List<String> tokenize(String input) throws ScannerException {
        return Arrays.asList(input.split(String.format(RegexCollection.WITH_DELIMITER, getSeparators())));
    }
}
