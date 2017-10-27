package scanner.tokenizer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class Constants {
    public static final List<String> separators = Arrays.asList(" ", "(", ")", "{", "}", ";");
    public static final List<String> operators =
            Arrays.asList("+", "-", "/", "%", "*", "<", ">", "=", "==", "!=", "<=", ">=");

}
