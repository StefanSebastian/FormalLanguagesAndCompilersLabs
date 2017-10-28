package scanner.tokenIdentifier;

import scanner.ScannerException;
import scanner.domain.PIFPair;
import scanner.scannerResult.ProgramInternalForm;
import scanner.scannerResult.SymbolTable;
import scanner.utils.FileReader;
import scanner.utils.RegexCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class TokenIdentifier {
    /*
    Returns the type of a token
     */
    public TokenType identify(String token) throws ScannerException{
        if (token.matches(RegexCollection.RESERVED_WORD)){
            return TokenType.RESERVED_WORD;
        }
        if (token.matches(RegexCollection.OPERATOR)){
            return TokenType.OPERATOR;
        }
        if (token.matches(RegexCollection.SEPARATOR)){
            return TokenType.SEPARATOR;
        }
        if (token.matches(RegexCollection.IDENTIFIER) && token.matches(RegexCollection.LENGTH_250)){
            return TokenType.IDENTIFIER;
        }
        if (token.matches(RegexCollection.NUMERIC_CONSTANT)){
            return TokenType.CONSTANT;
        }
        if (token.matches(RegexCollection.CHAR_CONSTANT)){
            return TokenType.CONSTANT;
        }
        if (token.matches(RegexCollection.STRING_CONSTANT)){
            return TokenType.CONSTANT;
        }

        throw new ScannerException("Invalid token : " + token);
    }
}
