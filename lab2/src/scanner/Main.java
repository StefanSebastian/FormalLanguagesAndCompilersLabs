package scanner;

import scanner.scannerResult.ScannerResult;
import scanner.utils.FileWriter;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class Main {
    private static final String input = "E:\\Info\\anu3\\lb formale\\git\\FormalLanguagesAndCompilersLabs\\lab2\\src\\scanner\\resources\\program.txt";
    private static final String output = "E:\\Info\\anu3\\lb formale\\git\\FormalLanguagesAndCompilersLabs\\lab2\\src\\scanner\\resources\\sequenceOutput.txt";

    public static void main(String[] args) {
        IScanner scanner = new Scanner();
        ScannerResult result;
        try {
            result = scanner.runScanner(input);
            System.out.println(result);

            new FileWriter().saveSequence(output, result.getProgramInternalForm());
        } catch (ScannerException e) {
            System.out.println(e.getMessage());
        }


    }
}
