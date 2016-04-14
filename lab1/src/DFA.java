import automata.resyntax.Graph;
import automata.resyntax.Edge;
import automata.resyntax.RegExp;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sdierauf on 4/12/16.
 */
public class DFA {

    public Graph<String, Character> graph;
    public Set<String> finalStates;
    public String start = "START";
    public Set<Character> alphabet;

    public DFA(Graph<String, Character> g) {
        this.graph = g;
        this.finalStates = g.finalStates;
        this.alphabet = new HashSet<>();
//        determineAlphabet();
    }

    public void determineAlphabet() {
        for (String node : graph.listNodes()) {
            for (Edge<String, Character> edge : graph.getEdges(node)) {
                alphabet.add(edge.getData());
            }
        }
    }

    // returns whether the input matches this DFA's graph
    public boolean match(String input) {
        String curNode = start;
        for (int i = 0; i < input.length(); i++) {
            curNode = graph.hasChildWithEdgeLabel(curNode, input.charAt(i));
            if (curNode == null) {
                return false;
            }
        }
        return finalStates.contains(curNode);
    }

    public String nonTerminalMatch(String input) {
        String curNode = start;
        for (int i = 0; i < input.length(); i++) {
            if (curNode == null) {
                return null;
            }
            if (finalStates.contains(curNode)) {
                return input.substring(0, i);
            }
            curNode = graph.hasChildWithEdgeLabel(curNode, input.charAt(i));
        }
        if (finalStates.contains(curNode)) {
            return input;
        } else {
            return null;
        }
    }
}
