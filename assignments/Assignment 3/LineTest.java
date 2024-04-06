import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class LineTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      Point p1 = new Point(0, 0);
      Point p2 = new Point(1, 1);
      Point p3 = new Point(2, 2);
      Point p4 = new Point(3, 3);
      Point p5 = new Point(4, 4);
      Point p6 = new Point(2, 1);
      SortedSet<Point> xyz = new TreeSet<Point>();
      xyz.add(p1);
      xyz.add(p2);
      xyz.add(p3);
      xyz.add(p4);
      xyz.add(p5);
      xyz.add(p6);
      Line l = new Line(xyz);
     
      
      
   }
}
