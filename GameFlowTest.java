/*** @author Iman Elabd
* @verion 1.2 October 25, 2025
*test for GameFlow
*/

import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Field;
import java.io.ByteArrayInputStream;
import java.util.Map;

public class GameFlowTest{
  private GameFlow game;
  private Player player1, player2;
  @Before
  public void setUp() throws Exception{
    game = new GameFlow();
    player1 = new Player("John");
    player2 = new Player("Mark");

    Field playersField = GameFlow.class.getDeclaredField("players");
    playersField.setAccessible(true);
    List players = (List) playersField.get(game);
    players.clear();
    players.add(player1);
    players.add(player2);

    Field scoresField = GameFlow.class.getDeclaredField("finalScores");
    scoresField.setAccessible(true);
    Map scores = (Map) scoresField.get(game);
    scores.clear();
    scores.put(player1.getName(), 0);
    scores.put(player2.getName(), 0); 
    
    Field topCardField = GameFlow.class.getDeclaredField("topCard");
    topCardField.setAccessible(true);
    topCardField.set(game, new Card(Card.Colours.GREEN, Card.Values.THREE));
  }
  @Test
  public void testGetRandomCard(){
     for (int i = 0; i < 10; i++) {
          Card card = GameFlow.GetRandomCard();
          assertNotNull(card);
          assertNotNull(card.getValue());
          if (card.getValue() != Card.Values.WILD &&
              card.getValue() != Card.Values.WILD_DRAW_TWO) {
              assertNotNull(card.getColour());
            }
        }
  }
      
  @Test
  public void testNewRoundDealsCards() throws Exception{
    player1.getPersonalDeck().clear();
    player2.getPersonalDeck().clear();
    game.newRound()
    int expected = 7;
    assertEquals(expected, player1.getPersonalDeck().size());
    assertEquals(expected, player2.getPersonalDeck().size());
    Field topCardField = GameFlow.class.getDeclaredField("topCard");
    topCardField.setAccessible(true);
    Card top = (Card) topCardField.get(game);
    boolean notWild = top.getValue() != Card.Values.WILD &&
      top.getValue()  != Card.Values.WILD_DRAW_TWO;
    assertTrue(notWild);
  }
  @Test
  public void testDrawOneCard(){
    int before = player2.getPersonalDeck().size();
    game.drawOne(player2);
    assertEquals(before + 1, player2.getPersonalDeck().size());
  }
  @Test 
  public void testReverseDirection() throws Exception{
    game.reverse(player1);
    Field directionField = GameFlow.class.getDeclaredField("direction");
    directionField.setAccessible(true);
    int direction = (int) directionField.get(game);
    assertEquals(-1,direction);
  }
   @Test 
  public void testSkipPlayer() throws Exception{
    game.skip(player2);
    Field skipField = GameFlow.class.getDeclaredField("playerSkipped");
    skipField.setAccessible(true);
    Player skipped = (Player) skipField.get(game);
    assertTrue(skipped == player2);
  }
   @Test 
  public void testWildCardColour() throws Exception{
    setScannerInput("RED\n");
    game.wild();
    Field topCardField = GameFlow.class.getDeclaredField("topCard");
    topCardField.setAccessible(true);
    Card top = (Card) topCardField.get(game);
    assertEquals(Card.Colours.RED, top.getColour());
  }

   @Test 
   public void testWildDrawAddsT() throws Exception {
    setScannerInput("YELLOW\n");
    int before = player2.getPersonalDeck().size();
    game.wildDrawTwo(player2);
    assertEquals(before + 2, player2.getPersonalDeck().size());
    Field skippedField = GameFlow.class.getDeclaredField("playerSkipped");
    skippedField.setAccessible(true);
    Player skip = (Player) skippedField.get(game);
    assertTrue(skip == player2);
    Field topCardField = GameFlow.class.getDeclaredField("topCard");
    topCardField.setAccessible(true);
    Card top = (Card) topCardField.get(game);
    assertEquals(Card.Colours.YELLOW, top.getColour());
  }
  @Test 
  public void testScoresCalculateSumExpected(){
    player2.getPersonalDeck().clear();
    player2.addCard(new Card(Card.Colours.RED,Card.Values.ONE));
    player2.addCard(new ActionCards(Card.Colours.YELLOW,Card.Values.SKIP));
    player2.addCard(new Card(null,Card.Values.WILD));
    int score = game.score(player1);
    assertEquals(71, score); 
  }
  private void setScannerInput(String text) throws Exception{
    Field fields = GameFlow.class.getDeclaredField("input");
    fields.setAccessible(true);
    fields.set(game, new Scanner(new ByteArrayInputStream(text.getBytes())));
  }   
}
