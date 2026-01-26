public class Route {
    private String start;
    private String end;

    public Route(String start, String end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return start + " -> " + end;
    }
}
