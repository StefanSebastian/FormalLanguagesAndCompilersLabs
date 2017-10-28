package scanner;

import scanner.domain.PIFPair;
import scanner.scannerResult.ProgramInternalForm;
import scanner.scannerResult.ScannerResult;
import scanner.scannerResult.SymbolTable;
import scanner.tokenIdentifier.TokenIdentifier;
import scanner.tokenIdentifier.TokenType;
import scanner.tokenizer.LexicalScannerTokenizer;
import scanner.utils.FileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Sebi on 22-Oct-17.
 */
public class Scanner implements IScanner {
    private FileReader fileReader;
    private LexicalScannerTokenizer tokenizer;
    private ProgramInternalForm pif;
    private SymbolTable symbolTable;
    private TokenIdentifier tokenIdentifier;

    // map of codes for token types
    private Map<String, Integer> codes;

    public Scanner(){
        fileReader = new FileReader();
        tokenizer = new LexicalScannerTokenizer();
        pif = new ProgramInternalForm();
        symbolTable = new SymbolTable();
        tokenIdentifier = new TokenIdentifier();

        codes = new HashMap<>();
        readCodes();
    }


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
    Identifies the token and saves it to pif or symbolTable
     */
    public void identifyAndSave(String token) throws ScannerException {
        // skip empty lines
        if (Objects.equals(token, "")){
            return;
        }

        TokenType type = tokenIdentifier.identify(token);
        System.out.println("Token : " + token + " identified as " + type);
        if (type == TokenType.RESERVED_WORD || type == TokenType.SEPARATOR || type == TokenType.OPERATOR){
            PIFPair pair = new PIFPair(token.equals(" ") ? codes.get("space") : codes.get(token), -1);
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

    @Override
    public ScannerResult runScanner(String filePath) throws ScannerException {
        Integer lineNr = 0;
        for (String line : fileReader.getLines(filePath)){
            List<String> lineTokens = tokenizer.tokenize(line);
            lineNr++;

            try {
                for (String token : lineTokens) {
                    identifyAndSave(token);
                }
            } catch (ScannerException e){
                throw new ScannerException(e.getMessage() + " on line " + lineNr);
            }
        }

        return new ScannerResult(pif, symbolTable);
    }
}
