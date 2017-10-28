package scanner;

import scanner.scannerResult.ProgramInternalForm;
import scanner.scannerResult.ScannerResult;
import scanner.scannerResult.SymbolTable;
import scanner.tokenIdentifier.TokenIdentifier;
import scanner.tokenizer.LexicalScannerTokenizer;
import scanner.utils.FileReader;

import java.util.List;

/**
 * Created by Sebi on 22-Oct-17.
 */
public class Scanner implements IScanner {
    private FileReader fileReader;
    private LexicalScannerTokenizer tokenizer;
    private ProgramInternalForm pif;
    private SymbolTable symbolTable;
    private TokenIdentifier tokenIdentifier;

    public Scanner(){
        fileReader = new FileReader();
        tokenizer = new LexicalScannerTokenizer();
        pif = new ProgramInternalForm();
        symbolTable = new SymbolTable();
        tokenIdentifier = new TokenIdentifier(pif, symbolTable);
    }

    @Override
    public ScannerResult runScanner(String filePath) throws ScannerException {
        Integer lineNr = 0;
        for (String line : fileReader.getLines(filePath)){
            List<String> lineTokens = tokenizer.tokenize(line);
            lineNr++;

            try {
                for (String token : lineTokens) {
                    tokenIdentifier.identifyAndSave(token);
                }
            } catch (ScannerException e){
                throw new ScannerException(e.getMessage() + " on line " + lineNr);
            }
        }

        return new ScannerResult(pif, symbolTable);
    }
}
