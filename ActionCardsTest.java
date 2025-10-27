/*** @author Iman Elabd
* @verion 1.2 October 25, 2025
*/

import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.io.ByteArrayInputStream;
import java.util.Map;

public class ActionCardsTest{
  private GameFlow game;
  private Player currentPlayer, nextPlayer;
  
  @Before
  public void setUp() throws Exception{
    game = new GameFlow();
    currentPlayer = new Player("John");
    nextPlayer = new Player("Mark");
    Field playersField = GameFlow.class.getDeclaredField("players");
    playersField.setAccessible(true);
    List players = (List) playersField.get(game);
    players.clear();
    players.add(currentPlayer);
    players.add(nextPlayer);
    Field topCardField = GameFlow.class.getDeclaredField("topCard");
    topCardField.setAccessible(true);
    topCardField.set(game,new Card(Card.Colours.BLUE,Card.Values.FOUR))
  }
}
