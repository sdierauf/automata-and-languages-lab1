package automata.resyntax;

import java.util.Set;

public class Union extends RegExp {
    public final RegExp r1, r2;
    public Union(RegExp r1, RegExp r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void addToGraph(Graph g, String start, String end, Set<Character> alphabet) {
        String branchAStart = newQ();
        String branchAEnd = newQ();
        String branchBStart = newQ();
        String branchBEnd = newQ();
        g.addEdge(start, EPSILON, branchAStart);
        g.addEdge(start, EPSILON, branchBStart);
        r1.addToGraph(g, branchAStart, branchAEnd, alphabet);
        r2.addToGraph(g, branchBStart, branchBEnd, alphabet);
        g.addEdge(branchBEnd, EPSILON, end);
        g.addEdge(branchAEnd, EPSILON, end);
        if (DEBUG) {
            System.out.println(this.getClass());
        }
    }
}
