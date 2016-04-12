package automata.resyntax;

public class ZeroOrOne extends RegExp {
    public final RegExp r;
    public ZeroOrOne(RegExp r) {
        this.r = r;
    }

    @Override
    public void addToGraph(Graph g, String start, String end) {
        String zeroOrOneStart = newQ();
        String zeroOrOneEnd = newQ();
        g.addEdge(start, EPSILON, zeroOrOneStart);
        g.addEdge(zeroOrOneStart, EPSILON, zeroOrOneEnd); //zero case
        g.addEdge(zeroOrOneEnd, EPSILON, end);
        r.addToGraph(g, zeroOrOneStart, zeroOrOneEnd); // one case
    }
}
