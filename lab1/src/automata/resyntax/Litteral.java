package automata.resyntax;

import java.util.Set;

public class Litteral extends RegExp {
    public final Character c;
    public Litteral(Character c) {
        this.c = c;
    }

    @Override
    public void addToGraph(Graph g, String start, String end, Set<Character> alphabet) {
        // assume start and end are already in the graph
        String literalStart = newQ();
        String literalEnd = newQ();
        g.addEdge(start, RegExp.EPSILON, literalStart);
        g.addEdge(literalStart, c, literalEnd);
        g.addEdge(literalEnd, RegExp.EPSILON, end);
//        g.addFinalState(end);
        if (DEBUG) {
            System.out.println(this.getClass());
        }
    }
}
