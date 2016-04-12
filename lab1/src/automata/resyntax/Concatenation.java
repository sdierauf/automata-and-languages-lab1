package automata.resyntax;

public class Concatenation extends RegExp {
    public final RegExp r1, r2;
    public Concatenation(RegExp r1, RegExp r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void addToGraph(Graph g, String start, String end) {
        String frontEpsilonBridge = newQ();
        String midEpsilonBridge = newQ();
        String midEpsilonBridge2 = newQ();
        String endEpsilonBridge = newQ();
        g.addEdge(start, EPSILON, frontEpsilonBridge);
        r1.addToGraph(g, frontEpsilonBridge, midEpsilonBridge);
        g.addEdge(midEpsilonBridge, EPSILON, midEpsilonBridge2);
        r2.addToGraph(g, midEpsilonBridge2, endEpsilonBridge);
        g.addEdge(endEpsilonBridge, EPSILON, end);

    }
}
