import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GameFlow {
    private List<Player> players = new ArrayList<>();
    private Player playerSkipped = null;
    Scanner input = new Scanner(System.in);
    Card topCard;


    public static Card GetRandomCard(){             //modified it to handle action cards
        Random rand = new Random();

        //String[] colours= {"Yellow", "Red", "Blue", "Green"};

        Card.Values[] values = Card.Values.values();
        Card.Values value = values[rand.nextInt(values.length)];

        Card.Colours colour = null;

        if (value != Card.Values.WILD && value != Card.Values.WILD_DRAW_TWO) {
            Card.Colours[] colours = Card.Colours.values();
            colour = colours[rand.nextInt(colours.length)];
        }

        if(value == Card.Values.DRAW_ONE ||value == Card.Values.REVERSE || value == Card.Values.SKIP || value == Card.Values.WILD || value == Card.Values.WILD_DRAW_TWO ) {
            return new ActionCards(colour, value);
        }

        //int value = rand.nextInt(9)+1;
        return new Card(colour, value);

    }

    public void drawOne(Player nextPlayer) {
        Card drawnCard = GetRandomCard();
        nextPlayer.addCard(drawnCard);
        System.out.println(nextPlayer.getName() + " has to draw a card: " + drawnCard);
    }

    public void reverse(Player currPlayer){
        Collections.reverse(players);
        System.out.println(currPlayer.getName() + " has reversed the order");

        int currPlayerNum = players.indexOf(currPlayer);  //help properly assign next player
        if(currPlayerNum < players.size() - 1) {
            Player x = players.remove(currPlayerNum);
            players.add(x);
        }
    }

    public void skip(Player nextPlayer) {
        System.out.println(nextPlayer.getName() + " misses their turn");
        playerSkipped = nextPlayer;
    }


    public void wild(){
        boolean valid = false;
        while(!valid) {
            System.out.println("Enter the colour that will be played next (RED, YELLOW, GREEN, BLUE): ");
            String colour = input.next().toUpperCase();

            if(colour.equals("RED") || colour.equals("YELLOW") || colour.equals("GREEN") || colour.equals("BLUE")){
                Card.Colours newColour = Card.Colours.valueOf(colour);
                topCard.setColour(newColour);
                System.out.println(colour + " has been chosen for the next card");
                valid = true;
            }

            else{
                System.out.println("Invalid colour, choose again");
            }
        }
    }

    public void wildDrawTwo(Player nextPlayer) {
        wild();
        Card drawnCard1 = GetRandomCard();
        Card drawnCard2 = GetRandomCard();
        nextPlayer.addCard(drawnCard1);
        nextPlayer.addCard(drawnCard2);

        System.out.println(nextPlayer.getName() + " has to draw 2 cards: " + drawnCard1 + ", " + drawnCard2);

        skip(nextPlayer);
    }


    public void play() {
        int numPlayers;

        do {
            System.out.print("Enter the number of players (2-4): ");
            while (!input.hasNextInt()) {
                System.out.print("Invalid input. Please try again: ");
                input.next();
            }
            numPlayers = input.nextInt();
        } while (numPlayers < 2 || numPlayers > 4);


        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter a name for player " + i + ": ");
            String name = input.next();
            players.add(new Player(name));
        }

        topCard = GetRandomCard();
        for (Player player : players) {
            for(int i = 0; i < 7;i++){
                player.addCard(GetRandomCard());
            }
        }


        boolean next = false;
        boolean win = false;
        while (!win) {
            while(!next){
                for (Player player : players) {

                    if (playerSkipped != null && player == playerSkipped) { //helps skip player without messing with reverse method
                        playerSkipped = null;
                        continue;
                    }

                    System.out.println("------ " + player.getName() + "s'turn ------");
                    System.out.println("Top card: " + topCard.toString());
                    System.out.println("Your cards: ");

                    int i = 1;
                    for(Card card: player.getPersonalDeck()){
                        System.out.println(i + ". " + card.toString());
                        i++;
                    }

                    System.out.println("Enter a card index to play a card, or 0 to draw a card");

                    int cardIndex = -1;

                    while(!input.hasNextInt()){
                        System.out.print("Invalid input. Please try again: ");
                        input.next();
                    }

                    cardIndex = input.nextInt();

                    while(cardIndex < 0 || cardIndex > player.getPersonalDeck().size()){
                        System.out.print("Invalid input. Please try again: ");
                        cardIndex = input.nextInt();
                    }

                    if (cardIndex == 0){
                        player.getPersonalDeck().add(GetRandomCard());
                        next = true;

                    }
                    else if(cardIndex >= 1 && cardIndex <= player.getPersonalDeck().size()){

                        Card chosen = player.getPersonalDeck().get(cardIndex - 1);
                        boolean sameColour = false;

                        if (chosen.getColour() != null && topCard.getColour() != null) {
                            sameColour = chosen.getColour().equals(topCard.getColour());
                        } else if (chosen.getValue() == Card.Values.WILD || chosen.getValue() == Card.Values.WILD_DRAW_TWO) {
                            // Wild cards can be played on anything
                            sameColour = true;
                        }


                        //boolean sameColour = chosen.getColour().equals(topCard.getColour());

                        //boolean sameColour = chosen.getColour().equals(topCard.getColour());
                        boolean sameValue  = chosen.getValue() == topCard.getValue();

                        if (sameColour || sameValue){
                            if(chosen instanceof ActionCards actions) {

                                int currPlayerNum = (players.indexOf(player)) % players.size();
                                Player currPlayer = players.get(currPlayerNum) ;

                                int nextPlayerNum = (players.indexOf(player) + 1) % players.size();
                                Player nextPlayer = players.get(nextPlayerNum) ;

                                actions.processActionCard(this, nextPlayer, currPlayer);
                            }

                            //topCard.setColour(player.getPersonalDeck().get(cardIndex - 1).getColour());
                            //topCard.setValue(player.getPersonalDeck().get(cardIndex - 1).getValue());
                            //player.getPersonalDeck().remove(cardIndex - 1);

                            Card playedCard = player.getPersonalDeck().remove(cardIndex - 1);
                            topCard.setValue(playedCard.getValue());
                            if (playedCard.getColour() != null) {
                                topCard.setColour(playedCard.getColour());
                            }

                            if(player.getPersonalDeck().isEmpty()){
                                System.out.println("Player " + player.getName() + " won!");
                                win = true;
                                return;
                            }
                            next = true;
                        }
                        else {
                            System.out.println("Placing that card is not a valid move. Try again. ");
                        }
                    }
                    else{
                        System.out.println("Invalid input. Please try again: ");
                    }
                }
            }
            next=false;
        }
    }

    public static void main(String[] args) {
        GameFlow game = new GameFlow();
        game.play();
    }
}