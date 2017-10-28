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
    // util class for reading files
    private FileReader fileReader;

    // map of codes for token types
    private Map<String, Integer> codes;

    // program internal form
    private ProgramInternalForm pif;

    // symbol table
    private SymbolTable symbolTable;

    /*
    Reads codes from codes.txt
     */
    private void readCodes(){
        String path = "E:\\Info\\anu3\\lb formale\\git\\FormalLanguagesAndCompilersLabs\\lab2\\src\\scanner\\tokenIdentifier\\codes.txt";
        List<String> lines = fileReader.getLines(path);
        for (String line : lines){
            String[] lineArr = line.split(" ");
            codes.put(lineArr[0], Integer.parseInt(lineArr[1]));
        }
    }

    /*
    Constructor, initialize codes
     */
    public TokenIdentifier(ProgramInternalForm pif, SymbolTable symbolTable){
        this.pif = pif;
        this.symbolTable = symbolTable;
        fileReader = new FileReader();
        codes = new HashMap<>();
        readCodes();
    }

    /*
    Returns the type of a token
     */
    private TokenType identify(String token) throws ScannerException{
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

    /*
    Identifies the token and saves it to pif or symbolTable
     */
    public void identifyAndSave(String token) throws ScannerException {
        // skip empty lines
        if (Objects.equals(token, "")){
            return;
        }

        TokenType type = identify(token);
        System.out.println("Token : " + token + " identified as " + type);
        if (type == TokenType.RESERVED_WORD || type == TokenType.SEPARATOR || type == TokenType.OPERATOR){
            PIFPair pair = new PIFPair(codes.get(token), -1);
            pair.setToken(token);
            pif.insert(pair);
        }
        if (type == TokenType.IDENTIFIER || type == TokenType.CONSTANT){
            Integer identifier = symbolTable.getIdentifier(token);
            if (identifier == null){
                identifier = symbolTable.insert(token);
            }
            PIFPair pair;
            if (type == TokenType.CONSTANT) {
                pair = new PIFPair(codes.get("constant"), identifier);
            } else {
                pair = new PIFPair(codes.get("identifier"), identifier);
            }
            pair.setToken(token);
            pif.insert(pair);
        }

    }

}
