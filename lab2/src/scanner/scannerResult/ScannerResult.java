package scanner.scannerResult;

/**
 * Created by Sebi on 22-Oct-17.
 */

/*
Result of scanner execution
PIF + SymbolTable
 */
public class ScannerResult {
    private ProgramInternalForm programInternalForm;
    private SymbolTable symbolTable;

    public ScannerResult(ProgramInternalForm programInternalForm, SymbolTable symbolTable) {
        this.programInternalForm = programInternalForm;
        this.symbolTable = symbolTable;
    }

    public ProgramInternalForm getProgramInternalForm() {
        return programInternalForm;
    }

    public void setProgramInternalForm(ProgramInternalForm programInternalForm) {
        this.programInternalForm = programInternalForm;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }
}
