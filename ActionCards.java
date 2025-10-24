public class ActionCards extends Card{

    public ActionCards(Colours colour, Values value){
        super(colour, value);
    }

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
