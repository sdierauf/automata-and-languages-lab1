package automata.resyntax;


import java.util.Set;

public class Dot extends RegExp {
    @Override
    public void addToGraph(Graph g, String start, String end, Set<Character> alphabet) {
        String literalStart = newQ();
        String literalEnd = newQ();
        g.addEdge(start, RegExp.EPSILON, literalStart);
        for (char c: alphabet) {
            g.addEdge(literalStart, c, literalEnd);
        }
        g.addEdge(literalEnd, RegExp.EPSILON, end);
        if (DEBUG) {
            System.out.println(this.getClass());
        }
    }
}
