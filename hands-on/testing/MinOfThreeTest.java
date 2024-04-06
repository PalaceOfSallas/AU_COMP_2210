import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {

   @Test public void min1Test1() {
      int a = 1;
      int b = 2;
      int c = 3;
      int expected = 1;
      int actual = MinOfThree.min1(a, b, c);
      assertEquals(expected, actual);
   }
   
   @Test public void min1Test2() {
      int a = 2;
      int b = 1;
      int c = 3;
      int expected = 1;
      int actual = MinOfThree.min1(a, b, c);
      assertEquals(expected, actual);
   }
   
   @Test public void min1Test3() {
      int a = 2;
      int b = 3;
      int c = 1;
      int expected = 1;
      int actual = MinOfThree.min1(a, b, c);
      assertEquals(expected, actual);
   }
   
   @Test public void min1Test4() {
      int a = 2;
      int b = 2;
      int c = 3;
      int expected = 2;
      int actual = MinOfThree.min1(a, b, c);
      assertEquals(expected, actual);
   }

   
   @Test public void min2Test1() {
      int a = 1;
      int b = 2;
      int c = 3;
      int expected = 1;
      int actual = MinOfThree.min2(a, b, c);
      assertEquals(expected, actual);
   }
   
   @Test public void min2Test2() {
      int a = 2;
      int b = 1;
      int c = 3;
      int expected = 1;
      int actual = MinOfThree.min2(a, b, c);
      assertEquals(expected, actual);
   }
   
   @Test public void min2Test3() {
      int a = 2;
      int b = 3;
      int c = 1;
      int expected = 1;
      int actual = MinOfThree.min2(a, b, c);
      assertEquals(expected, actual);
   }



}
