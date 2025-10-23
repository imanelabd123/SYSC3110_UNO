import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class GameFlow {



    public static Card GetRandomCard(){
        String[] colours= {"Yellow", "Red", "Blue", "Green"};
        Random rand = new Random();
        String colour = colours[rand.nextInt(colours.length)];
        int value = rand.nextInt(9)+1;
        Card card = new Card(colour, value);
        return card;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numPlayers;


        do {
            System.out.print("Enter the number of players (2-4): ");
            while (!input.hasNextInt()) {
                System.out.print("Invalid input. Please try again: ");
                input.next();
            }
            numPlayers = input.nextInt();
        } while (numPlayers < 2 || numPlayers > 4);


        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter a name for player " + i + ": ");
            String name = input.next();
            players.add(new Player(name));
        }

        Card topCard = GetRandomCard();
        for (Player player : players) {
            for(int i = 1; i <= 1;i++){
                player.addCard(GetRandomCard());
            }
        }





        boolean next = false;
        boolean win = false;
        while (!win) {
            while(!next){
                for (Player player : players) {
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
                        boolean sameColour = chosen.getColour().equals(topCard.getColour());
                        boolean sameValue  = chosen.getValue() == topCard.getValue();

                        if (sameColour || sameValue){
                            topCard.setColour(player.getPersonalDeck().get(cardIndex - 1).getColour());
                            topCard.setValue(player.getPersonalDeck().get(cardIndex - 1).getValue());
                            player.getPersonalDeck().remove(cardIndex - 1);
                            if(player.getPersonalDeck().size() == 0){
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

        }









