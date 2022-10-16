public enum Shape {
    TILES("Tiles") ,
    HEARTS("Hearts") ,
    SPADES("Spades"),
    CLOVERS("Clovers");

    private final String name;
    Shape(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
