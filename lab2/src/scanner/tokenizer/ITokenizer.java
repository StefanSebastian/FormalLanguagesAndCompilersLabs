package scanner.tokenizer;

import scanner.ScannerException;

import java.util.List;

/**
 * Created by Sebi on 27-Oct-17.
 */
public interface ITokenizer {
    List<String> tokenize(String input) throws ScannerException;
}
