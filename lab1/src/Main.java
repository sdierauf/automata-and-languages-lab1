
import java.io.*;
import java.util.*;
import automata.REParser;
import automata.*;
import automata.resyntax.Graph;
import automata.resyntax.RegExp;

/**
 * Created by sdierauf on 4/11/16.
 */
public class Main {

    public static void main(String[] args) {
        System.out.print("yolo");

        // get regex
        // build with REParser
        // convert into Graph
        String testRegex = "d*";

        RegExp t = null;
        try {
            t = REParser.parse(testRegex);
            System.out.println(t);
        } catch (Exception e) {
            System.out.println("there was an error");
        }
        if (t == null) {
            return;
        }
        EpsilonNFA e = new EpsilonNFA();
        e.buildFromRegexTree(t);
        Graph<String, Character> newg = e.toDFAGraph();
        newg.printGraph();
        DFA dfa = new DFA(newg);
        checkMatch(testRegex, "abc");
        checkMatch(testRegex, "ac");
        checkMatch(testRegex, "");
        checkMatch(testRegex, "d");
        checkMatch(testRegex, "dddddddd");
        checkMatch(testRegex, "abcdddabcabcacdddddddabd");

        loopForInput();

    }

    public static void checkMatch(String regex, String input) {
        RegExp t = null;
        try {
            t = REParser.parse(regex);
//            System.out.println(t);
        } catch (Exception e) {
            System.out.println("there was an error");
        }
        if (t == null) {
            return;
        }
        EpsilonNFA e = new EpsilonNFA();
        e.buildFromRegexTree(t);
        DFA dfa = new DFA(e.toDFAGraph());
        System.out.println(regex + " MATCHES " + input + ": " + dfa.match(input));
    }

    public static void loopForInput() {
        System.out.print("Name of file: ");
        Scanner sysInput = new Scanner(System.in);
//        String filename = sysInput.nextLine();
        String filename = "testcases/testcase1.txt";
        File file = new File(filename);
        System.out.println();
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (Exception e) {
            System.out.println("file did not exist!");
            loopForInput();
        }
        if (fileScanner == null) {
            return;
        }
        String alphabet = fileScanner.nextLine();
        String regex = fileScanner.nextLine();
        String substringRegex = "((.?)+)"+regex;
        RegExp t = null;
        try {
            t = REParser.parse(substringRegex);
        } catch (Exception e) {
            System.out.println("there was an error parsing the regex");
            loopForInput();
        }
        if (t == null) {
            return;
        }
        EpsilonNFA nfa = new EpsilonNFA();
        nfa.buildFromRegexTree(t);
        DFA dfa = new DFA(nfa.toDFAGraph());
        dfa.graph.printGraph();
//        System.out.println("Matching ");
        while (fileScanner.hasNextLine()) {
            String input = fileScanner.nextLine();
            boolean matches = dfa.nonTerminalMatch(input);
            if (matches) {
                System.out.println(input + "\tPASS");
            } else {
                System.out.println(input + "\tFAIL");
            }
        }
    }
}
