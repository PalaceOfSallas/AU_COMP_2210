import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.FileNotFoundException;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  YOUR NAME (you@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
     
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) throws FileNotFoundException {
         File file =	new File(filename);
		   Scanner scan =	new Scanner(file);
		   int N	= scan.nextInt();
		   points =	new Point[N];
		   for (int	i = 0; i	< N; i++) {
		   	points[i] =	new Point(scan.nextInt(), scan.nextInt());
		   }
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      
      double s1, s2, s3;
      
      for (int i = 0; i < points.length(); i++) {
         for (int j = 1; j < points.length(); j++) {
            for (int k = 2; k < points.length(); k++) {
               for (int l = 3; l < points.length(); l++) {
                  s1 = points[i].slopeTo(points[j]);
                  s2 = points[i].slopeTo(points[k]);
                  s3 = points[i].slopeTo(points[l]);
                  if (s1 == s2 && s1 == s3) {
                     //lines.add(new Line(new TreeSet<Point>(){points[i], points[j], points[k], points[l]}));
                  }
               }
            }
         }
      } 
         
         
         
         
      
      return lines;
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      return lines;
   }
   
}
