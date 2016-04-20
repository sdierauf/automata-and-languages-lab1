import automata.resyntax.Graph;
import automata.resyntax.Edge;
import automata.resyntax.RegExp;

import java.util.*;

/**
 * Created by sdierauf on 4/12/16.
 */
public class DFA {

    public Graph<String, Character> graph;
    public Set<String> finalStates;
    public String start;
    public Set<Character> alphabet;

    public DFA(Graph<String, Character> g, Set<Character> alphabet, String startNode) {
        this.graph = g;
        this.finalStates = g.finalStates;
        this.alphabet = alphabet;
        this.start = startNode;
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

    public boolean logicalXOR(boolean x, boolean y) {
        return ( ( x || y ) && ! ( x && y ) );
    }

    public DFA takeQuotient() {
        int numStates = graph.listNodes().size();
        String[] states = graph.listNodes().toArray(new String[numStates]);
        String[][] table = new String[numStates][numStates];
        print2dArray(table);
//        System.out.println(Arrays.toString(table));
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {

                if (logicalXOR(finalStates.contains(states[i]), finalStates.contains(states[j]))) {
                    table[i][j] = RegExp.EPSILON + "";
                }
            }
        }
        print2dArray(table);
        boolean dirty = true;
        while (dirty) {
            dirty = false;
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    String diff = table[i][j];
                    if (diff != null) {
                        continue;
                    }
                    String x = states[i];
                    String y = states[j];
                    Set<String> visited = new TreeSet<>();
                    for (Character c : this.alphabet) {
                        // do transition
                        String a = graph.hasChildWithEdgeLabel(x, c);
                        String b = graph.hasChildWithEdgeLabel(y, c);
                        if (a != null && b != null && !visited.contains(a + b)) {
                            // check if that pair is in the table
                            int ia = indexOf(states, a);
                            int ib = indexOf(states, b);
                            if (table[ia][ib] != null) {
//                                System.out.println(x + y + a + b);
                                table[i][j] = table[ia][ib] + c;
                                dirty = true;
                            }
                        }
                    }
                }
            }
//            print2dArray(table);
//            System.out.println(Arrays.toString(states));
//            System.out.println(this.alphabet);
//            System.out.println();
        }
        Graph<String, Character> retGraph = new Graph<>();
        Map<String, TreeSet<String>> state2set = new HashMap<String, TreeSet<String>>();
        for (String state: states) {
            TreeSet<String> group = new TreeSet<>();
            group.add(state);
            state2set.put(state, group);
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (table[i][j] == null) {
                    state2set.get(states[i]).add(states[j]);
                    state2set.get(states[j]).add(states[i]);
                }
            }
        }
//        System.out.println(state2set);
        String startNode = "START";


        for (Set<String> nodeName : state2set.values()) {
            retGraph.addNode(nodeName.toString());
            if (nodeName.contains(this.start)) {
                startNode = nodeName.toString();
            }
            for (String individual: nodeName) {
                if (this.finalStates.contains(individual)) {
                   retGraph.finalStates.add(nodeName.toString());
                }
                for (Edge<String, Character> e: graph.getEdges(individual)) {
                    retGraph.addEdge(nodeName.toString(), e.getData(), state2set.get(e.getDestination()).toString());
                }

            }
        }
        retGraph.printGraph();

        DFA quotientDfa = new DFA(retGraph, this.alphabet, startNode);
        return quotientDfa;
    }

    public int indexOf(String[] arr, String t) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public void print2dArray(String[][] table) {

//        for (int i = 1; i < table.length; i++) {
//            System.out.println(Arrays.toString(table[i]));
//        }
//        System.out.println();
    }
}
