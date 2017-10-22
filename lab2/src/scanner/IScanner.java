package scanner;

import scanner.scannerResult.ScannerResult;

/**
 * Created by Sebi on 22-Oct-17.
 */

/*
Main interface of the lexical scanner program
Input path of the file to be scanned
Output Program Internal Form + Symbol Table
 */
public interface IScanner {
    ScannerResult runScanner(String filePath) throws ScannerException;
}
