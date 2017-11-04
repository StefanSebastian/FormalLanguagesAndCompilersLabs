package app.grammar;

import java.util.List;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class Grammar {
    private String id;
    private List<String> nonterminals;
    private List<String> terminas;
    private List<Production> productions;
    private String startSymbol;

    public Grammar(String id, List<String> nonterminals, List<String> terminas, List<Production> productions, String startSymbol) {
        this.id = id;
        this.nonterminals = nonterminals;
        this.terminas = terminas;
        this.productions = productions;
        this.startSymbol = startSymbol;
    }

    public List<String> getNonterminals() {
        return nonterminals;
    }

    public void setNonterminals(List<String> nonterminals) {
        this.nonterminals = nonterminals;
    }

    public List<String> getTerminas() {
        return terminas;
    }

    public void setTerminas(List<String> terminas) {
        this.terminas = terminas;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    public String getStartSymbol() {
        return startSymbol;
    }

    public void setStartSymbol(String startSymbol) {
        this.startSymbol = startSymbol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Grammar{" +
                "id='" + id + '\'' +
                ", nonterminals=" + nonterminals +
                ", terminas=" + terminas +
                ", productions=" + productions +
                ", startSymbol='" + startSymbol + '\'' +
                '}';
    }
}
