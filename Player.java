
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> personalDeck = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getPersonalDeck() {
        return personalDeck;
    }

    public void addCard(Card c) {
        personalDeck.add(c);
    }

    @Override
    public String toString() {
        return name;
    }
}
