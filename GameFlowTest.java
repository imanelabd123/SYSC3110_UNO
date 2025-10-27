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

    Field playersField = GameFlow.class.getDeclaredField("Player");
    playersField.setAccessible(true);
    List players = (List) playersField.get(game);
    players.clear();
    players.add(player1);
    players.add(player2);

    Field scoresField = GameFlow.class.getDeclaredField("Final Score");
    scoresField.setAccessible(true);
    Map scores = (Map) playersField.get(game);
    players.clear();
    scores.put(player1.getName(), 0);
    scores.add(player2.getName(), 0);

    
    
  }
  
}
