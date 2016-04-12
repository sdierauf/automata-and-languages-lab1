package automata.resyntax;

public class OneOrMore extends RegExp {
    public final RegExp r;
    public OneOrMore(RegExp r) {
        this.r = r;
    }

    @Override
    public void addToGraph(Graph g, String start, String end) {

    }
}
