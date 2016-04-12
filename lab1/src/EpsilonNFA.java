import automata.resyntax.Closure;
import automata.resyntax.Edge;
import automata.resyntax.RegExp;
import automata.resyntax.*;

import java.util.*;

/**
 * Created by sdierauf on 4/11/16.
 */
public class EpsilonNFA {
    public Graph<String, Character> graph;

    // Name of nodes
    public String start = "START";
    public String end = "END"; // probably wont be used


    public EpsilonNFA() {
        graph = new Graph<>();
    }

    public void buildFromRegexTree(RegExp t) {
        System.out.println(t.getClass());
        t.addToGraph(graph, start, end);
        graph.printGraph();
    }

    public Graph<String, Character> toDFAGraph() {
        int counter = 0;
        // Copy the graph, will mutate it so w/e
        Graph<String, Character> ret = new Graph<>();
        // Map of new s states to sets of q states;
        Set<String> visited = new HashSet<String>();
        Map<Set<String>, String> setToName = new HashMap<Set<String>, String>();


        // starting at START, follow all edges with epsilon
        Set<String> starterStates = new HashSet<>();
        starterStates.add(start);
        Queue<Set<String>> todo = new LinkedList<>();
        Set<String> starterClosed = epsilonClose(starterStates);
        setToName.put(starterClosed, "START");

        todo.add(starterClosed);

        while(!todo.isEmpty()) {
            Set<String> states = todo.remove();
            if (!visited.contains(setToName.get(states))) {
                String DFAStateGroupName = setToName.get(states);
                Map<Character, Set<String>> STrans = new HashMap<>();
                ret.addNode(DFAStateGroupName);
                visited.add(DFAStateGroupName);
                for (String s : states) {
                    for (Edge<String, Character> e : graph.getEdges(s)) {
                        if (e.getData() != RegExp.EPSILON) {
                            Set<String> toState;
                            if (STrans.containsKey(e.getData())) {
                                toState = STrans.get(e.getData());
                            } else {
                                toState = new HashSet<>();
                                STrans.put(e.getData(), toState);
                            }
                            toState.add(e.getDestination());
                        }
                    }
                }
                System.out.print("STrans: ");
                System.out.println(STrans);
                HashMap<Character, Set<String>> STransClosed = new HashMap<>();
                for (Character key: STrans.keySet()) {
                    Set<String> closed = epsilonClose(STrans.get(key));
                    System.out.print("closed: ");
                    System.out.println(closed);
                    STransClosed.put(key, closed);
                    todo.add(closed);
                    counter++;
                    String DFAResultNode = "s" + counter;
                    setToName.put(closed, DFAResultNode);
                    ret.addEdge(DFAStateGroupName, key, DFAResultNode);
                }
            }
        }

        return ret;

    }

    public Set<String> epsilonClose(Set<String> states) {

        Queue<String> todo = new LinkedList<>();
        Set<String> ret = new HashSet<>();
        ret.addAll(states);
        todo.addAll(states);
        while(!todo.isEmpty()) {
            String state = todo.remove();
            for (Edge<String, Character> edge: graph.getEdges(state)) {
                if (edge.getData() == RegExp.EPSILON
                        && !ret.contains(edge.getDestination())) {
                    ret.add(edge.getDestination());
                    todo.add(edge.getDestination());
                }
            }
        }

        return ret;
    }


}
