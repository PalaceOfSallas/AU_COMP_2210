import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Game Class
 */
public class WSG implements WordSearchGame {
 /**
  * Instance Variables
  */
   private TreeSet<String> lexicon = new TreeSet<String>();
   private boolean called = false;
   private String[][] board = new String[][] {
      {"E", "E", "C", "A"},
      {"A", "L", "E", "P"},
      {"H", "N", "B", "O"},
      {"Q", "T", "T", "Y"}
   };
   private int boardSize = 4;
   private SortedSet<String> words = new TreeSet<String>();
   private List<Integer> boardNumbers = new ArrayList<Integer>();
   
  /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      
      File f = new File(fileName);
      Scanner s = null;
      
      try {
         s = new Scanner(f);
         while(s.hasNext()) {
            lexicon.add(s.next().toUpperCase());
         }
      }
      catch (FileNotFoundException e) {
         throw new IllegalArgumentException();
      }
       
      s.close();   
      called = true;
   }
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) {
         throw new IllegalArgumentException();
      }
      
      double n = (double)letterArray.length;
      n = Math.sqrt(n);
      
      if (n != Math.round(n)) {
         throw new IllegalArgumentException();
      }
      
      int m = (int)n;
      
      board = new String[m][m];
      for (int i = 0; i < m; i++) {
         for (int j = 0; j < m; j++) {
            board[i][j] = letterArray[i * m + j];
         }
      }
      
      boardSize = m;
      words = new TreeSet<String>();
      boardNumbers = new ArrayList<Integer>();
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
   public String getBoard() {
   
      return null;
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      
      if (!called) {
         throw new IllegalStateException();
      }
      
      boolean[][] visited = new boolean[boardSize][boardSize];
      String s = "";
      
      for (int i = 0; i < boardSize; i++) {
         for (int j = 0; j < boardSize; j++) {
            getWords(i, j, minimumWordLength, s, visited);
         }
      }
      
      
      
      return words;
   }
   
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */ 
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException();
      }
      
      if (!called) {
         throw new IllegalStateException();
      }
      
      int score = 0;
      
      for (String x : words) {
         if (x.length() >= minimumWordLength && isValidWord(x) && isOnBoard2(x, minimumWordLength)) {
            score += ((x.length() - minimumWordLength) + 1); 
         }
      }
      
      return score;      
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      if (!called) {
         throw new IllegalStateException();
      }
      
      wordToCheck = wordToCheck.toUpperCase();
   
      if (lexicon.contains(wordToCheck)) {
         return true;
      }
      else {
         return false;
      }
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      if (!called) {
         throw new IllegalStateException();
      }
      
      prefixToCheck = prefixToCheck.toUpperCase();
      
      String s = lexicon.ceiling(prefixToCheck);
      
      if (s.startsWith(prefixToCheck) || s.equals(prefixToCheck)) {
         return true;
      }
      else {
         return false;
      }
      
      
     
      
      
      
   }
      
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException();
      }
      
      if (!called) {
         throw new IllegalStateException();
      }
      
      boolean[][] visited = new boolean[boardSize][boardSize];
      String s = "";
      
      for (int i = 0; i < boardSize; i++) {
         for (int j = 0; j < boardSize; j++) {
            findWord(i, j, wordToCheck, visited, s);
         }
      }
      
      return boardNumbers;
   }
   
  /**
   * Private class for getting words
   */
   private void getWords(int i, int j, int mwl, String s, boolean[][] v) {
      v[i][j] = true;
      
      s = (s + board[i][j]);
      
      if (isValidWord(s) && s.length() >= mwl) {
         words.add(s);
      }
      
      if (isValidPrefix(s)) {
         for (int x = i - 1; x <= i + 1 && x < boardSize; x++) {
            for (int y = j - 1; y <= j + 1 && y < boardSize; y++) {
               if (x >= 0 && y >= 0 && !v[x][y]) {
                  getWords(x, y, mwl, s, v);
               }
            }
         }
      } 
   
      s = s.substring(0, s.length() - 1);
      v[i][j] = false;
   }
   
  /**
   * Finding specific word
   */
   private void findWord(int i, int j, String wtc, boolean[][] v, String s) {
      v[i][j] = true;
      
      s = (s + board[i][j]);
      
      if (s.equals(wtc)) {
         for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
               if (v[x][y] == true) {
                  boardNumbers.add(x * boardSize + y);
               }
            }
         }
      }
        
      if (wtc.startsWith(s)) {
         for (int r = i - 1; r <= i + 1 && r < boardSize; r++) {
            for (int c = j - 1; c <= j + 1 && c < boardSize; c++) {
               if (r >= 0 && c >= 0 && !v[r][c]) {
                  findWord(r, c, wtc, v, s);
               }
            }
         }
      }
        
      s = s.substring(0, s.length() - 1);
      v[i][j] = false;
   }
   
   /**
    * Returns boolean if a string is on board
    */
   private boolean isOnBoard2(String w, int mwl) {
      getAllValidWords(mwl);
      if (words.contains(w)) {
         return true;
      }
      else {
         return false;
      }   
   
   
   }
}

 
 
 
 
 
 
 
 
 
 
 
 
 
