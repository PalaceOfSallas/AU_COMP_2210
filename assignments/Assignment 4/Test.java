public class Test<T> {
   public static class Node<T> {
    // Instance Variables
      private T element;
      private Node next;
      private Node prev;
    
    // Constructor #1
      public Node(T x) {   
         element = x;
      }
   
    // Constructor #2
      public Node(T x, Node y) {
         element = x;
         next = y;
      }
   
    // Constructor #3
      public Node(T x, Node y, Node z) {
         element = x;
         next = y;
         prev = z;
      }
   }

   public static void main(String[] args) {
      Node n = new Node("a");
      n = new Node("b", n);
      n.next = new Node("c", n.next);
      n = new Node("d", n.next);
      n.next.next = new Node("e", new Node("f"));  
      
         
   
   
   
   }
}