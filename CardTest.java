/*** @author Iman Elabd
* @verion 1.2 October 25, 2025
* test cases for CardTest
*/

import org.junit.*;
import static org.junit.Assert.*;


 /***
  this is test  for the card creation
  */
public class CardTest{
  @Test
  public void testCardCreation(){
    Card card = new Card(Card.Colours.RED, Card.Values.FIVE);
    assertEquals(Card.Colours.RED, card.getColour());
    assertEquals(Card.Values.FIVE, card.getValue());}

  /***
  this is test for the card created for setColour() and setValue
  */

  @Test
  public void testCardSetters(){
    Card card = new Card(Card.Colours.YELLOW, Card.Values.TWO);
    card.setColour(Card.Colours.GREEN);
    card.setValue(Card.Values.SEVEN);
    assertEquals(Card.Colours.GREEN, card.getColour());
    assertEquals(Card.Values.SEVEN, card.getValue());
  }
  /***
  this is test for toString() for both colour and value 
  */
  @Test
  public void testCardOutputColour(){
    Card card = new Card(Card.Colours.RED, Card.Values.FIVE);
    assertEquals("RED FIVE", card.toString());}
  /***
  this is test for toString() for value. 
  */
  @Test
   public void testCardOutputWild(){
    Card card = new Card(null, Card.Values.WILD);
    assertEquals("WILD", card.toString());}

}

