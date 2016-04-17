package automata.resyntax;

import java.util.Set;

public class Closure extends RegExp {
    public final RegExp r;
    public Closure(RegExp r) {
        this.r = r;
    }

    @Override
    public void addToGraph(Graph g, String start, String end, Set<Character> alphabet) {
        String closureStart = newQ();
        String closureEnd = newQ();
        g.addEdge(start, RegExp.EPSILON, closureStart);
        // turns ()* into (()?)+
        ZeroOrOne inner = new ZeroOrOne(this.r);
        OneOrMore outer = new OneOrMore(inner);
        outer.addToGraph(g, closureStart, closureEnd, alphabet);
        g.addEdge(closureEnd, RegExp.EPSILON, end);
        if (DEBUG) {
            System.out.println(this.getClass());
        }
    }
}
