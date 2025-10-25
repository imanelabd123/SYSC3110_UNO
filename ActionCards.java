/**
* Handles special effects of UNO action cards such as, Skip, Reverse, Draw, and Wild cards.
*
* @author Amreen Shahid
* @version 1.0 October 24, 2025
*/

public class ActionCards extends Card{
    /**
    * Constructs ActionCard with specified colour and value.
    *
    * @param colour the colour of the card.
    * @param value the value of the card.
    */
    public ActionCards(Colours colour, Values value){
        super(colour, value);
    }
    /**
    * Processes the action card effect based on the card's value.
    *
    * @param game The GameFlow instance to execute game actions.
    * @param nextPlaver player who's next in turn order.
    * @param currPlaver player who played the action card.
    */
    public void processActionCard(GameFlow game, Player nextPlayer, Player currPlayer) {
        if(getValue() == Values.DRAW_ONE) {
            game.drawOne(nextPlayer);
        }
        else if(getValue() == Values.REVERSE) {
            game.reverse(currPlayer);
        }

        else if(getValue() == Values.SKIP) {
            game.skip(nextPlayer);
        }

        else if (getValue() == Values.WILD) {
            game.wild();
        }

        else if(getValue() == Values.WILD_DRAW_TWO) {
            game.wildDrawTwo(nextPlayer);
        }
    }

}
