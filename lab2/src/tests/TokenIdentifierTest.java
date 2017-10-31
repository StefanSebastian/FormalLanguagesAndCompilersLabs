package tests;

import org.junit.Test;
import scanner.ScannerException;
import scanner.scannerResult.ProgramInternalForm;
import scanner.scannerResult.SymbolTable;
import scanner.tokenIdentifier.TokenIdentifier;
import scanner.tokenIdentifier.TokenType;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 28-Oct-17.
 */
public class TokenIdentifierTest {
    private TokenIdentifier tokenIdentifier = new TokenIdentifier();

    @Test
    public void identifyConstants() throws Exception {
        TokenType type = tokenIdentifier.identify("12");
        System.out.println(type);
        assertTrue(type == TokenType.CONSTANT);

        type = tokenIdentifier.identify("-1233");
        System.out.println(type);
        assertTrue(type == TokenType.CONSTANT);

        type = tokenIdentifier.identify("\"1233\"");
        System.out.println(type);
        assertTrue(type == TokenType.CONSTANT);

        type = tokenIdentifier.identify("'1'");
        System.out.println(type);
        assertTrue(type == TokenType.CONSTANT);

        try {
            type = tokenIdentifier.identify("'12'");
            System.out.println(type);
            assertTrue(false);
        } catch (ScannerException e){
            assertTrue(true);
        }

        try {
            type = tokenIdentifier.identify("\"1233 sds\""); // no spaces allowed
            System.out.println(type);
            assertTrue(false);
        } catch (ScannerException e){
            assertTrue(true);
        }

    }


    @Test
    public void identifiers() throws Exception {
        TokenType type = tokenIdentifier.identify("a");
        System.out.println(type);
        assertTrue(type == TokenType.IDENTIFIER);

        type = tokenIdentifier.identify("_a");
        System.out.println(type);
        assertTrue(type == TokenType.IDENTIFIER);

        type = tokenIdentifier.identify("_a22");
        System.out.println(type);
        assertTrue(type == TokenType.IDENTIFIER);

        type = tokenIdentifier.identify("_");
        System.out.println(type);
        assertTrue(type == TokenType.IDENTIFIER);

        type = tokenIdentifier.identify("_2_");
        System.out.println(type);
        assertTrue(type == TokenType.IDENTIFIER);

        try {
            String s = "";
            for (int i = 0; i < 260; i++){
                s += "a";
            }
            type = tokenIdentifier.identify(s); // too long
            System.out.println(type);
            assertTrue(false);
        } catch (ScannerException e){
            assertTrue(true);
        }
    }

    @Test
    public void operators() throws Exception {
        TokenType type = tokenIdentifier.identify("<");
        System.out.println(type);
        assertTrue(type == TokenType.OPERATOR);

        type = tokenIdentifier.identify("+");
        System.out.println(type);
        assertTrue(type == TokenType.OPERATOR);

        type = tokenIdentifier.identify("<=");
        System.out.println(type);
        assertTrue(type == TokenType.OPERATOR);
    }

    @Test
    public void separators() throws Exception {
        TokenType type = tokenIdentifier.identify(" ");
        System.out.println(type);
        assertTrue(type == TokenType.SEPARATOR);

        type = tokenIdentifier.identify("(");
        System.out.println(type);
        assertTrue(type == TokenType.SEPARATOR);

        type = tokenIdentifier.identify("}");
        System.out.println(type);
        assertTrue(type == TokenType.SEPARATOR);
    }

    @Test
    public void reservedWords() throws Exception {
        TokenType type = tokenIdentifier.identify("int");
        System.out.println(type);
        assertTrue(type == TokenType.RESERVED_WORD);

        type = tokenIdentifier.identify("char");
        System.out.println(type);
        assertTrue(type == TokenType.RESERVED_WORD);

        type = tokenIdentifier.identify("begin");
        System.out.println(type);
        assertTrue(type == TokenType.RESERVED_WORD);

    }
}