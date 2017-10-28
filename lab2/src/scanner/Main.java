package scanner;

import scanner.scannerResult.ScannerResult;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class Main {
    public static void main(String[] args) {
        IScanner scanner = new Scanner();
        ScannerResult result;
        try {
            result = scanner.runScanner("E:\\Info\\anu3\\lb formale\\git\\FormalLanguagesAndCompilersLabs\\lab2\\src\\scanner\\resources\\program.txt");
            System.out.println(result);
        } catch (ScannerException e) {
            System.out.println(e.getMessage());
        }


    }
}
