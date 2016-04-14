package automata.resyntax;


import java.util.Set;

public abstract class RegExp {
    public static final Character EPSILON = 'ë';
    public static final Character DOT = '.';
    public static int counter = 0;
    public static boolean DEBUG = false;
    public abstract void addToGraph(Graph g, String start, String end, Set<Character> alphabet);
    public String newQ() {
        counter++;
        return "q"+counter;
    }
}
