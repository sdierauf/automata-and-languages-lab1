package automata.resyntax;

public class Union extends RegExp {
    public final RegExp r1, r2;
    public Union(RegExp r1, RegExp r2) {
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public void addToGraph(Graph g, String start, String end) {

    }
}
