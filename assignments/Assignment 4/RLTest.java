import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class RLTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      RL rl = new RL();
      rl.add(1);
      rl.add(2);
      // rl.add(3);
      // rl.add(4);
      // rl.add(5);
      // rl.add(6);
      rl.remove();
      rl.remove();
      rl.add(1);
      rl.add(2);
      
      int x = rl.size();
      Assert.assertEquals(2, x);
      
      boolean y = rl.isEmpty();
      Assert.assertEquals(false, y);
      
      // T test1 = rl.sample();
      // T test2 = r2.remove();
      
      
   }
}
