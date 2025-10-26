import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;


public class PlayerTest{
  
  @Test
  public void testPlayerCreation(){
    Player player = new Player("Iman");
    assertEquals("Iman", player.getName());
    assertEquals("Iman", player.toString());}

   
  @Test
  public void testAddCardToDeckPlayer(){
    Player player = new Player("Amreen");
    assertTrue(player.getPersonalDeck().isEmpty());
  }

  @Test
  public void testNotEmptyDeck(){
    Player player = new Player("Marc");
    Card card = new Card(Card.Colours.RED, Card.Values.THREE);
    player.addCard(card);
    assertFalse(player.getPersonalDeck().isEmpty()); 
    List<Card> deck = player.getPersonalDeck();
    assertEquals(1, deck.size());}
  
}
