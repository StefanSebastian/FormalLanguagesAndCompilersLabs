package app;

import app.controller.Controller;
import app.finiteAutomata.FiniteAutomata;
import app.finiteAutomata.Transition;
import app.grammar.Grammar;
import app.grammar.Production;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class UI {
    private Controller controller;
    private Scanner reader;

    public UI(Controller controller){
        this.controller = controller;
        reader = new Scanner(System.in);
    }

    private void printMenu(){
        System.out.println("1.Read a grammar");
        System.out.println("2.Display grammars");
        System.out.println("3.Check regular");
        System.out.println("4.Read FA");
        System.out.println("5.Display FAs");
    }

    private List<Production> readProductions(Scanner reader) throws AppException {


        List<Production> productions = new LinkedList<>();

        String prod = reader.next();
        while (!prod.equals("end")){
            // split left side , right side
            String[] leftRight = prod.split("->");

            if (leftRight.length != 2){
                throw new AppException("Invalid format");
            }

            String leftSide = leftRight[0];

            // split in disjunctions
            String[] disjunctions = leftRight[1].split("\\|");
            List<List<String>> disjunctionsList = new LinkedList<>();
            for (String disjunction : disjunctions){
                // split every disjunction
                List<String> disjunctionList = Arrays.asList(disjunction.split(","));
                disjunctionsList.add(disjunctionList);
            }

            Production production = new Production(leftSide, disjunctionsList);
            productions.add(production);

            prod = reader.next();
        }
        return productions;
    }

    private void readGrammarFromKeyboard() throws AppException{
        System.out.println("Identifier : ");
        String identifier = reader.next();

        System.out.println("Nonterminals separated by commas ; e.g A,B,C");
        String nonterminals = reader.next();
        List<String> nonterminalsList = Arrays.asList(nonterminals.split(","));

        System.out.println("Terminals separated by commas ; e.g. a,b,c");
        String terminals = reader.next();
        List<String> terminalsList = Arrays.asList(terminals.split(","));

        System.out.println("Start symbol");
        String startSymbol = reader.next();

        System.out.println("Productions");
        System.out.println("Use this format : left->a,B,c|d,E,F|...");
        System.out.println("one production per line ; type 'end' when you're done");
        List<Production> productions = readProductions(reader);

        Grammar grammar = new Grammar(identifier, nonterminalsList, terminalsList, productions, startSymbol);
        controller.addGrammar(grammar);
    }

    private void readGrammarFromFile() throws AppException {
        System.out.println("Path : ");
        reader.nextLine();
        String path = reader.nextLine();

        File file = new File(path);
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new AppException(e.getMessage());
        }

        String identifier = fileReader.next();
        String nonterminals = fileReader.next();
        List<String> nonterminalsList = Arrays.asList(nonterminals.split(","));
        String terminals = fileReader.next();
        List<String> terminalsList = Arrays.asList(terminals.split(","));
        String startSymbol = fileReader.next();

        //productions
        List<Production> productions = readProductions(fileReader);

        Grammar grammar = new Grammar(identifier, nonterminalsList, terminalsList, productions, startSymbol);
        controller.addGrammar(grammar);

        fileReader.close();
    }

    private void readGrammar() throws AppException{
        System.out.println("1.Read from keyboard");
        System.out.println("2.Read from file");
        String opt = reader.next();


        if (opt.equals("1")) {
            readGrammarFromKeyboard();
        } else {
            readGrammarFromFile();
        }

    }

    private void displayGrammarDetailsMenu(String id) throws AppException{
        Grammar grammar = controller.getGrammarById(id);
        if (grammar == null){
            System.out.println("Invalid id");
            return;
        }

        while (true){
            System.out.println("1.terminals");
            System.out.println("2.nonterminals");
            System.out.println("3.start symbol");
            System.out.println("4.productions");
            System.out.println("5.back");

            String opt = reader.next();
            if (opt.equals("1")){
                System.out.println(grammar.getTerminals());
            } else if (opt.equals("2")){
                System.out.println(grammar.getNonterminals());
            } else if (opt.equals("3")){
                System.out.println(grammar.getStartSymbol());
            } else if (opt.equals("4")){
                System.out.println(grammar.getProductions());
            } else if (opt.equals("5")){
                break;
            }
        }
    }

    private void displayGrammars() throws AppException{

        for (Grammar grammar : controller.getGrammars().values()) {
            System.out.println(grammar.getId());
        }

        System.out.println("1.display grammar details");
        System.out.println("2.back");

        String opt = reader.next();
        if (opt.equals("1")){
            System.out.println("Grammar id : ");
            String id = reader.next();
            displayGrammarDetailsMenu(id);
        }

    }


    private void checkRegular() throws AppException{
        System.out.println("Grammar id: ");
        String id = reader.next();

        String res = controller.checkRegular(controller.getGrammarById(id));

        if (res.equals("OK")){
            System.out.println("Grammar is regular");
        } else {
            System.out.println(res);
        }
    }



    private List<Transition> readTransitions(Scanner reader) throws AppException {
        List<Transition> transitions = new LinkedList<>();

        String transition = reader.next();
        while (!transition.equals("end")){
            String[] values = transition.split(",");
            if (values.length != 3){
                throw new AppException("Invalid format");
            }
            transitions.add(new Transition(values[0], values[1], values[2]));

            transition = reader.next();
        }
        return transitions;
    }

    private void readFiniteAutomataFromFile() throws AppException {

        System.out.println("Path : ");
        reader.nextLine();
        String path = reader.nextLine();

        File file = new File(path);
        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new AppException(e.getMessage());
        }

        String identifier = fileReader.next();
        String alphabet = fileReader.next();
        List<String> alphabetList = Arrays.asList(alphabet.split(","));
        String states = fileReader.next();
        List<String> statesList = Arrays.asList(states.split(","));
        String initialState = fileReader.next();
        String finalStates = fileReader.next();
        List<String> finalStatesList = Arrays.asList(finalStates.split(","));

        List<Transition> transitions = readTransitions(fileReader);

        FiniteAutomata finiteAutomata = new FiniteAutomata(identifier, statesList, alphabetList, initialState, finalStatesList, transitions);
        controller.addFiniteAutomata(finiteAutomata);
    }

    private void readFiniteAutomataFromKeyboard() throws AppException {
        System.out.println("Identifier : ");
        String identifier = reader.next();

        System.out.println("Alphabet separated by commas : e.g. a,b,c");
        String alphabet = reader.next();
        List<String> alphabetList = Arrays.asList(alphabet.split(","));

        System.out.println("States separated by commas ; e.g. q1,q2,q3");
        String states = reader.next();
        List<String> statesList = Arrays.asList(states.split(","));

        System.out.println("Initial state : ");
        String initialState = reader.next();

        System.out.println("Final states separated by commas : ");
        String finalStates = reader.next();
        List<String> finalStatesList = Arrays.asList(finalStates.split(","));

        System.out.println("Transitions ; one on each line ");
        System.out.println("Use format state1,state2,value");
        System.out.println("Type 'end' after the last transition");

        List<Transition> transitions = readTransitions(reader);

        FiniteAutomata finiteAutomata = new FiniteAutomata(identifier, statesList, alphabetList, initialState, finalStatesList, transitions);
        controller.addFiniteAutomata(finiteAutomata);
    }

    private void readFiniteAutomata() throws AppException {
        System.out.println("1.Read from keyboard");
        System.out.println("2.Read from file");
        String opt = reader.next();


        if (opt.equals("1")) {
            readFiniteAutomataFromKeyboard();
        } else {
            readFiniteAutomataFromFile();
        }
    }


    private void displayFiniteAutomataDetailsMenu(String id) throws AppException {
        FiniteAutomata finiteAutomata = controller.getFiniteAutomataById(id);
        if (finiteAutomata == null){
            System.out.println("Invalid id");
            return;
        }

        while (true){
            System.out.println("1.states");
            System.out.println("2.alphabet");
            System.out.println("3.transitions");
            System.out.println("4.initial state");
            System.out.println("5.final states");
            System.out.println("6.back");

            String opt = reader.next();
            if (opt.equals("1")){
                System.out.println(finiteAutomata.getStates());
            } else if (opt.equals("2")){
                System.out.println(finiteAutomata.getAlphabet());
            } else if (opt.equals("3")){
                System.out.println(finiteAutomata.getTransitions());
            } else if (opt.equals("4")){
                System.out.println(finiteAutomata.getInitialState());
            } else if (opt.equals("5")){
                System.out.println(finiteAutomata.getFinalStates());
            } else if (opt.equals("6")){
                break;
            }
        }
    }

    private void displayFiniteAutomatas() throws AppException {
        for (FiniteAutomata finiteAutomata : controller.getFiniteAutomatas().values()) {
            System.out.println(finiteAutomata.getIdentifier());
        }

        System.out.println("1.display finite automata details");
        System.out.println("2.back");

        String opt = reader.next();
        if (opt.equals("1")){
            System.out.println("FA id : ");
            String id = reader.next();
            displayFiniteAutomataDetailsMenu(id);
        }
    }

    public void runMenu(){
        while(true){
            printMenu();

            try {

                String option = reader.next();
                if (option.equals("1")) {
                    readGrammar();
                } else if (option.equals("2")) {
                    displayGrammars();
                } else if (option.equals("3")) {
                    checkRegular();
                } else if (option.equals("4")) {
                    readFiniteAutomata();
                } else if (option.equals("5")) {
                    displayFiniteAutomatas();
                }

            } catch (AppException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
