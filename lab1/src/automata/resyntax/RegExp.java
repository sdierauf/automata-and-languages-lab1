package automata.resyntax;



public abstract class RegExp {
    public static final Character EPSILON = 'ë';
    public static int counter = 0;
    public abstract void addToGraph(Graph g, String start, String end);
    public String newQ() {
        counter++;
        return "q"+counter;
    }
}