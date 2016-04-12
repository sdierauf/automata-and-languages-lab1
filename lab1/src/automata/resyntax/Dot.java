package automata.resyntax;

public class Dot extends RegExp {
    @Override
    public void addToGraph(Graph g, String start, String end) {
        String literalStart = newQ();
        String literalEnd = newQ();
        g.addEdge(start, RegExp.EPSILON, literalStart);
        g.addEdge(literalStart, DOT, literalEnd);
        g.addEdge(literalEnd, RegExp.EPSILON, end);
    }
}
