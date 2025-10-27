import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.util.Map;

public class GameFlowTest{
  private GameFlow game;
  private Player player1, player2;
  @Before
  public void setUp() throws Exception{
    game = new GameFlow();
    player1 = new Player("John");
    player2 = new Player("Mark");

    Field playersField = GameFlow.class.getDeclaredField("player");
    playersField.setAccessible(true);
    List players = (List) playersField.get(game);
    players.clear();
    players.add(player1);
    players.add(player2);

    Field scoresField = GameFlow.class.getDeclaredField("finalScore");
    scoresField.setAccessible(true);
    Map scores = (Map) playersField.get(game);
    players.clear();
    scores.put(player1.getName(), 0);
    scores.add(player2.getName(), 0); 
    
    Field topCardField = GameFlow.class.getDeclaredField("topCard");
    topCardField.setAccessible(true);
    topCardField.set(game, new Card(Card.Colours.GREEN, Card.Values.THREE));
  }
  @Test
  public void testGetRandomCard(){
    for(int i = 0; i < 10; i++){
      Card card = GameFlow.GetRandomCard();
      assertNotNull(card);
      assertNotNull(card.getValue());
      if(card.getValue() !=Card.Values.WILD && card.getValuse() != Card.Values.WILD_DRAW_TWO){
        assertNotNull(card.getColour());
    }
    
  }
 }
  @Test
  public void testNewRoundDealsCards()
  throws Exception{
    player1.getPersonalDeck().clear();
    player2.getPersonalDeck().clear();
    int expected = 7;
    assertEquals(expected, player1.getPersonalDeck().size());
    assertEquals(expected, player2.getPersonalDeck().size());
    Feild topCardField = GameFlow.class.getDeclaredField("topCard");
    topCardField.setAccessible(true);
    Card top = (Card) topCardField.get(game);
    boolean notWild = top.getValue() != Card.Values.WILD &&
      top.getValue()  != Card.Values.WILD_DRAW_TWO;
    assertTrue(notWild);
  }
  @Test
  public void testDrawOneCard(){
    int old = player2.getPersonalDeck().size();
    game.drawOne(player2);
    assertEquals(old + 1, player2.getPersonalDeck().size());
  }
  @Test 
  public void testReverseDirection()
  throws Exception{
    game.reverse(player1);
    Field topCardField = GameFlow.class.getDeclaredField("topCard");
    topCardField.setAccessible(true);
    topCardField.set(game, new Card(Card.Colours.GREEN, Card.Values.THREE));
  }
  
}
