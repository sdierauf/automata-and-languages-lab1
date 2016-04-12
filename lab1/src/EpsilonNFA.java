import automata.resyntax.Closure;
import automata.resyntax.RegExp;
import automata.resyntax.*;

/**
 * Created by sdierauf on 4/11/16.
 */
public class EpsilonNFA {
    public Graph<String, Character> graph;


    public EpsilonNFA() {
        graph = new Graph<>();
    }

    public void buildFromRegexTree(RegExp t) {
        System.out.println(t.getClass());
        t.addToGraph(graph);
    }


}
