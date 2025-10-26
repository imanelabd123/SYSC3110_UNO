import org.junit.*;
import static org.junit.Assert.*;

public class CardTest{
  
  @Test
  public void testCardCreation(){
    Card card = new Card(Card.Colours.RED, Card.Values.FIVE);
    assertEquals(Card.Colours.RED, card.getColour());
    assertEquals(Card.Colours.FIVE, card.getValue());}

   
  @Test
  public void testCardSetters(){
    Card card = new Card(Card.Colours.YELLOW, Card.Values.TWO);
    card.setColour(Card.Colours.GREEN);
    card.setValue(Card.Values.SEVEN)
    assertEquals(Card.Colours.RED, card.getColour());
    assertEquals(Card.Colours.SEVEN, card.getValue());}
}
