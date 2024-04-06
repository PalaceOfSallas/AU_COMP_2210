public class test {

   public static void main(String[] args) {

      test client = new test();
      client.quiz();
   }

   private class Node {
      private Object element;
      private Node next;

      public Node(Object e) {
         element = e;
      }

      public Node(Object e, Node n) {
         element = e;
         next = n;
      }
   }

   public void quiz() {
      Node n = new Node(1);
      n.next = new Node(2, new Node(3));
      n = new Node(4, n);
      n.next.next = new Node(5);
      n.next = new Node(6, n.next);
   
   
   
   
   
   }
}