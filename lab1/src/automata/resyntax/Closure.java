package automata.resyntax;

public class Closure extends RegExp {
    public final RegExp r;
    public Closure(RegExp r) {
        this.r = r;
    }

    @Override
    public void addToGraph(Graph g, String start, String end) {
        String closureStart = newQ();
        String closureEnd = newQ();
        g.addEdge(start, RegExp.EPSILON, closureStart);
        r.addToGraph(g, closureStart, closureEnd);
        g.addEdge(closureEnd, RegExp.EPSILON, end);
        if (DEBUG) {
            System.out.println(this.getClass());
        }
    }
}
