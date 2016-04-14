package automata.resyntax;

import java.util.Set;

public class OneOrMore extends RegExp {
    public final RegExp r;
    public OneOrMore(RegExp r) {
        this.r = r;
    }

    @Override
    public void addToGraph(Graph g, String start, String end, Set<Character> alphabet) {
        String oneStart = newQ();
        String oneEnd = newQ();
        String moreStart = newQ();
        String moreEnd = newQ();
        g.addEdge(start, EPSILON, oneStart);
        r.addToGraph(g, oneStart, oneEnd, alphabet);
        g.addEdge(oneEnd, EPSILON, moreStart);
        r.addToGraph(g, moreStart, moreEnd, alphabet);
        g.addEdge(moreEnd, EPSILON, moreStart); // loop back case
        g.addEdge(oneEnd, EPSILON, end); // only one case
        g.addEdge(moreEnd, EPSILON, end);
        if (DEBUG) {
            System.out.println(this.getClass());
        }
    }
}
