/*** @author Iman Elabd
* @verion 1.2 October 25, 2025
This is the JUnit testings for ActionCards that is in UNO cards project. 
It checks if action cards work by those test cases.

*/

// imports 
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.io.ByteArrayInputStream;
import java.util.Map;

// setting up currentPlayer and nextPlayer
public class ActionCardsTest{
  private GameFlow game;
  private Player currentPlayer, nextPlayer;
  
  @Before
  /**sets the top card for starting, this will run before each test cases, it create game and the two players John and Mark
  * we called GameFlow its used for creating players.  
  */
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
    // topCard in teh GameFlow is used here and assigned to topCardField
    Field topCardField = GameFlow.class.getDeclaredField("topCard");
    topCardField.setAccessible(true);
    topCardField.set(game,new Card(Card.Colours.BLUE,Card.Values.FOUR));
  }
  /** Test for Draw One card.
  * If card is played then next player should draw a new card. with the assigned before action. 
  */
  @Test
  public void testDrawOneCard(){
    int before = nextPlayer.getPersonalDeck().size();
    ActionCards action = new ActionCards(Card.Colours.RED,Card.Values.DRAW_ONE);
    action.processActionCard(game, nextPlayer, currentPlayer);
    assertEquals(before + 1, nextPlayer.getPersonalDeck().size());
  }
  /** Test for Reverse card.
  */
  @Test 
  public void testReverseDirection() throws Exception{
    ActionCards action = new ActionCards(Card.Colours.GREEN, Card.Values.REVERSE);
    action.processActionCard(game, nextPlayer,currentPlayer);
    Field directionField = GameFlow.class.getDeclaredField("direction");
    directionField.setAccessible(true);
    int direction = (int) directionField.get(game);
    assertEquals(-1,direction);
  }

  /** Private and is not a Test, simulation for wild cards 
  * 
  */
   
  private void setScannerInput(String text)throws Exception{
    Field fields = GameFlow.class.getDeclaredField("input");
    fields.setAccessible(true);
    fields.set(game, new Scanner(new ByteArrayInputStream(text.getBytes())));
    
  }
  
}
