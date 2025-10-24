public class Card {

    //private String colour;
    //private int value;

    public enum Colours {RED, YELLOW, GREEN, BLUE};
    public enum Values {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
        DRAW_ONE, REVERSE, SKIP, WILD, WILD_DRAW_TWO};

    private Colours colour;
    private Values value;

    public Card(Colours colour, Values value) {
        this.colour = colour;
        this.value = value;
    }

    public Colours getColour() {
        return colour;
    }

    public Values getValue() {
        return value;
    }


    public void setColour(Colours colour) {
        this.colour = colour;
    }

    public void setValue(Values value) {
        this.value = value;
    }


    @Override
    public String toString() {
        if(colour == null) {
            return value.toString();
        }
        return colour + " " + value;
    }

}
