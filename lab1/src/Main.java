
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
        Scanner sysInput = new Scanner(System.in);
        System.out.print("yolo");

        // get regex
        // build with REParser
        // convert into Graph
        String testRegex = "(d)+";
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
}
