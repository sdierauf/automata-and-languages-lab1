package automata.resyntax;

public class Union extends RegExp {
    public final RegExp r1, r2;
    public Union(RegExp r1, RegExp r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void addToGraph(Graph g, String start, String end) {
        String branchAStart = newQ();
        String branchAEnd = newQ();
        String branchBStart = newQ();
        String branchBEnd = newQ();
        g.addEdge(start, EPSILON, branchAStart);
        g.addEdge(start, EPSILON, branchBStart);
        r1.addToGraph(g, branchAStart, branchAEnd);
        r2.addToGraph(g, branchBStart, branchBEnd);
        g.addEdge(branchBEnd, EPSILON, end);
        g.addEdge(branchAEnd, EPSILON, end);
    }
}
