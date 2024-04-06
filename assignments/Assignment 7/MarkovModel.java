import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Your Name (you@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-04-17
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;

   // add other fields as you need them ...
   private String firstKgram;

   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      int a = 0;
      int b = 0;
      
      firstKgram = sourceText.substring(0, K);
      
      while (a + K <= sourceText.length()) {
         String c = sourceText.substring(a, a + K);
         String d = "";
         if (!model.containsKey(c)) {
            int e = K;
            while (b + e < sourceText.length()) {
               String f = sourceText.substring(b, b + e);
               if (b + K >= sourceText.length()) {
                  d += '\u0000';
               }
               if (c.equals(f)) {
                  d += sourceText.substring(b + e, b + e + 1);
               }
               b++;  
            }
            model.put(c, d);   
         }
         b = 0;
         a++; 
      } 
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return firstKgram;
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      int size = model.size();
      int a = 0;
      
      Random r = new Random();
      int i = r.nextInt(size);
      
      for (String x : model.keySet()) {
         if (i == a) {
            return x;
         }
         a++;
      }
      return null;
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      Random r = new Random();
      String a = "";
      int b = 0;
      
      for(String x: model.keySet()) {
         if (x.equals(kgram)) {
            a = model.get(kgram);
            int c = a.length();
            if (c > 0) {
               b = r.nextInt(c) + 1;
            }
         }
      }
      int d = b - 1;
      if (!a.equals("")) {
         return a.charAt(d);
      }
      return '\u0000';
   }
   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
