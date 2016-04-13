import automata.resyntax.Graph;

/**
 * Created by sdierauf on 4/12/16.
 */
public class DFA {

    public Graph<String, Character> graph;

    public DFA(Graph<String, Character> g) {
        this.graph = g;

    }

    // returns whether the input matches this DFA's graph
    public boolean match(String input) {
        return false;
    }
}
