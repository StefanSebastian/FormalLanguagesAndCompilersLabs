package scanner.utils;

/**
 * Created by Sebi on 27-Oct-17.
 */
public class RegexCollection {
    public static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
    public static final String RESERVED_WORD = "int|char|read|write|if|else|while|begin|end";
    public static final String OPERATOR = "\\+|-|\\*|/|%|<|>|=|<=|>=|==|!=";
    public static final String SEPARATOR = " |;|\\(|\\)|\\{|\\}|\\[|\\]";
    public static final String IDENTIFIER = "(_|[a-zA-Z])[a-zA-Z0-9_]*";
}
