import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2019-03-29
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   /////////////////////////////////////////////////////////////////////////////
   // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
   // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
   // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
   // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
   // table with chaining).
   /////////////////////////////////////////////////////////////////////////////
   private HashSet<String> lexicon;

   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         //////////////////////////////////////
         // INSTANTIATE lexicon OBJECT HERE  //
         //////////////////////////////////////
         lexicon = new HashSet<String>();
         
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            lexicon.add(str.toLowerCase());
            
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////
   
  /**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */
   public int getHammingDistance(String str1, String str2) {
      if (str1.length() != str2.length()) {
         return -1;
      }
      
      str1 = str1.toLowerCase();
      str2 = str2.toLowerCase();
      int length = str1.length();
      int hd = 0;
      
      for (int i = 0; i < length; i++) {
         if (str1.charAt(i) != str2.charAt(i)) {
            hd++;
         }
      }
      
      return hd;
   }


  /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */
   public List<String> getMinLadder(String start, String end) {
      // Lists to store ladder
      List<String> minLadder = new ArrayList<String>();
      List<String> words = new ArrayList<String>();
      
      // Making words lowercase
      start = start.toLowerCase();
      end = end.toLowerCase();
      
      // Returning empty list if words are different lengths
      if (getHammingDistance(start, end) == -1) {
         return minLadder;  
      }
      
      // Checking if words are valid
      if (isWord(start) && isWord(end)) {
         // If words are the same returns it
         if (start.equals(end)) {
            minLadder.add(start);
            return minLadder;
         }
         // If two different, valid words bfs runs
         else {
            words = bfs(start, end);
         }
      }
      // Returns empty list if words aren't valid
      else {
         return minLadder;
      }
      
      // If list is empty no ladder so empty list is returned
      if (words.isEmpty()) {
         return minLadder;
      }
      // Otherwise moves words into minLadder
      else {
         for (int i = words.size() - 1; i >= 0; i--) {
            minLadder.add(words.get(i));
         }
      }  
      
      return minLadder;
   }
   
   /**
    * Breadth-First Search class
    */
   private List<String> bfs(String x, String y) {
      Deque<Node> queue = new ArrayDeque<Node>();
      List<String> words2 = new ArrayList<String>();
      HashSet<String> processed = new HashSet<String>();
      List<String> neighbors;
      Node last = new Node(y, null);
      String current;
      boolean exit = false;
      
      processed.add(x);
      queue.addLast(new Node(x, null));
      
      while (!queue.isEmpty()) {
         Node n = queue.removeFirst();
         current = n.word;
         neighbors = getNeighbors(current);
         for (String z : neighbors) {
            if (!processed.contains(z)) {
               queue.addLast(new Node(z, n));
               processed.add(z);
               if (z.equals(y)) {
                  last.next = n;
                  exit = true;
               }
            }
            if (exit) {
               break;
            }
         }
         if (exit) {
            break;
         }
      }
      
      if (last.next == null) {
         return words2;
      }
      
      Node m = last;
      while (m != null) {
         words2.add(m.word);
         m = m.next;
      }
      
      return words2;
   }
    
   /**
    * Node class for getMinLadder
    */
   private class Node {
      // Instance Variables
      private String word;
      private Node next;
      
      // Construtor #1
      public Node(String x) {
         word = x;
      }
      
      // Constructor #2
      public Node(String x, Node y) {
         word = x;
         next = y;
      }
    
   }


   /**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
   public List<String> getNeighbors(String word) {
      List<String> neighbors = new ArrayList<String>();
      word = word.toLowerCase();
      int length = word.length();
      char x, y;
      String possible;
     
      for (int i = 0; i < length; i++) {
         x = word.charAt(i);
         y = word.charAt(i);
         y++;
         if (y > 'z') {
            y = 'a';
            while (y != x) {
               possible = cc(word, y, i);
               if (isWord(possible)) {
                  neighbors.add(possible);
               }
               y++;
            }
         }
         else {
            while (y != x) {
               possible = cc(word, y, i);
               if (isWord(possible)) {
                  neighbors.add(possible);
               }
               y++;
               if (y == 'z') {
                  possible = cc(word, y, i);
                  if (isWord(possible)) {
                     neighbors.add(possible);
                  }
                  y = 'a';
               }
            }
         }
      }
     
      return neighbors;
   }
   
   /**
    * Creating word to test neighbors.
    */
   private String cc(String word, char c, int index) {
      String x;
      x = (word.substring(0, index)) + c + (word.substring(index + 1));
      return x;
   }


   /**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
      return lexicon.size();
   }


   /**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
   public boolean isWord(String str) {
      return lexicon.contains(str.toLowerCase());
   }


   /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
   public boolean isWordLadder(List<String> sequence) {
      if (sequence.isEmpty()) {
         return false;
      }
      
      for (String word : sequence) {
         if (!isWord(word)) {
            return false;
         }  
      }
      
      if (sequence.size() == 1) {
         return true;
      }
      
      Iterator<String> iterator = sequence.iterator();
      String x = iterator.next();
      String y = iterator.next();
      int hd = getHammingDistance(x, y);
      if (hd != 1) {
         return false;
      }
      
      while (iterator.hasNext()) {
         x = y;
         y = iterator.next();
         hd = getHammingDistance(x, y);
         if (hd != 1) {
            return false;
         }  
      }
      
      return true;
   }
}

