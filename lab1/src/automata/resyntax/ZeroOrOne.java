package automata.resyntax;

public class ZeroOrOne extends RegExp {
    public final RegExp r;
    public ZeroOrOne(RegExp r) {
        this.r = r;
    }

    @Override
    public void addToGraph(Graph g, String start, String end) {

    }
}