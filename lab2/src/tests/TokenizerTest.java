package tests;

import org.junit.Test;
import scanner.tokenizer.LexicalScannerTokenizer;
import scanner.tokenizer.SeparatorTokenizer;
import scanner.tokenizer.TokenizerApplier;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class TokenizerTest {
    @Test
    public void spaceDetection() throws Exception {
        SeparatorTokenizer tokenizer = new SeparatorTokenizer();
        List<String> tokens = tokenizer.tokenize("a b c");
        System.out.println(tokens);

        assertTrue(tokens.size() == 5);

        tokens = tokenizer.tokenize("a  b c");
        System.out.println(tokens);
        assertTrue(tokens.size() == 6);

        tokens = tokenizer.tokenize("a  b");
        assertTrue(tokens.size() == 4);
        System.out.println(tokens);
    }

    @Test
    public void spaceAndSemicolon() throws Exception {
        SeparatorTokenizer tokenizer = new SeparatorTokenizer();
        List<String> tokens = tokenizer.tokenize("a b; c");
        System.out.println(tokens);

        assertTrue(tokens.size() == 6);

        tokens = tokenizer.tokenize(" ; aa;");
        assertTrue(tokens.size() == 5);
        System.out.println(tokens);
    }

    @Test
    public void withMultipleSeparators() throws Exception {
        SeparatorTokenizer tokenizer = new SeparatorTokenizer();
        List<String> tokens = tokenizer.tokenize("(a + b)");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);
    }

    @Test
    public void simpleAttrib() throws Exception {
        SeparatorTokenizer tokenizer = new SeparatorTokenizer();

        List<String> tokens = tokenizer.tokenize("int a = 3;");
        System.out.println(tokens);
        assertTrue(tokens.size() == 8);

        tokens = tokenizer.tokenize("int b = a + 1;");
        System.out.println(tokens);
        assertTrue(tokens.size() == 12);

    }

    @Test
    public void applyTokenizers() throws Exception {
        LexicalScannerTokenizer tokenizer = new LexicalScannerTokenizer();

        List<String> tokens = tokenizer.tokenize("int a = 3;");
        System.out.println(tokens);
        assertTrue(tokens.size() == 8);
    }

    @Test
    public void separatorsAndSingleCharOperators() throws Exception {
        LexicalScannerTokenizer tokenizer = new LexicalScannerTokenizer();

        List<String> tokens = tokenizer.tokenize("int a = b+3 - c+2;");
        System.out.println(tokens);
        assertTrue(tokens.size() == 16);
    }

    @Test
    public void doubleCharOperators() throws Exception {
        LexicalScannerTokenizer tokenizer = new LexicalScannerTokenizer();

        List<String> tokens = tokenizer.tokenize("int a = a>=b");
        System.out.println(tokens);
        assertTrue(tokens.size() == 9);

        tokens = tokenizer.tokenize("a>=b>c<=d");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);
    }

    @Test
    public void operatorTokenize() throws Exception{
        LexicalScannerTokenizer tokenizer = new LexicalScannerTokenizer();

        List<String> tokens = tokenizer.tokenize("int a=a+b");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);

        tokens = tokenizer.tokenize("int c=(a+1)>=2");
        System.out.println(tokens);
        assertTrue(tokens.size() == 11);

        tokens = tokenizer.tokenize("if(a>2){");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);

        tokens = tokenizer.tokenize("int a=-2");
        System.out.println(tokens);
        assertTrue(tokens.size() == 5);
    }

    @Test
    public void testNegativeConstants() throws Exception {
        LexicalScannerTokenizer tokenizer = new LexicalScannerTokenizer();
        List<String> tokens;

        System.out.println("int a=-2");
        tokens = tokenizer.tokenize("int a=-2");
        System.out.println(tokens);
        assertTrue(tokens.size() == 5);

        System.out.println("int a=2-2");
        tokens = tokenizer.tokenize("int a=2-2");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);

        System.out.println("int a=b-2");
        tokens = tokenizer.tokenize("int a=b-2");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);

        System.out.println("int a=-2+3");
        tokens = tokenizer.tokenize("int a=-2+3");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);

        System.out.println("if(-2<=3){");
        tokens = tokenizer.tokenize("if(-2<=3){");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);

        System.out.println("(a+b)-3");
        tokens = tokenizer.tokenize("(a+b)-3");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);

        System.out.println("(a+b) -3");
        tokens = tokenizer.tokenize("(a+b) -3");
        System.out.println(tokens);
        assertTrue(tokens.size() == 8);

        System.out.println("(a+b-3)");
        tokens = tokenizer.tokenize("(a+b-3)");
        System.out.println(tokens);
        assertTrue(tokens.size() == 7);

        System.out.println("(a+b)- 3");
        tokens = tokenizer.tokenize("(a+b)- 3");
        System.out.println(tokens);
        assertTrue(tokens.size() == 8);

        System.out.println("if( -3 < 2)");
        tokens = tokenizer.tokenize("if( -3 < 2)");
        System.out.println(tokens);
        assertTrue(tokens.size() == 9);

        System.out.println("2 +-3");
        tokens = tokenizer.tokenize("2 +-3");
        System.out.println(tokens);
        assertTrue(tokens.size() == 4);

    }

    @Test
    public void expressionsTest() throws Exception {
        LexicalScannerTokenizer tokenizer = new LexicalScannerTokenizer();
        List<String> tokens;

        tokens = tokenizer.tokenize("int a=((a+b)-3)");
        System.out.println(tokens);
        assertTrue(tokens.size() == 13);
    }

}