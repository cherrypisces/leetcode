package yeah.o.punch.others;

import java.util.HashMap;

public class MaxPointsOnLine2 {

	public static int maxPoints(Point[] points) {		 
	      int max = 0;
	      double slope;
	      
	      for(int i=0; i<points.length; i++) {
		      HashMap<Double, Integer> map = new HashMap<Double, Integer>();
	    	  int tmpMax = 1;
	    	  int overlapPoints = 0;    	  
	    	  for(int j=0; j<points.length; j++) {
	    		  
	    		  if (i == j) continue;
	    		  
	    		  Point pi = points[i];
	    		  Point pj = points[j];
	    		  if(pi.x == pj.x) {	    			  
	    			  if(pi.y == pj.y) {
	    				  overlapPoints++;
	    				  continue;
	    			  }
	    			  
	    			  slope = Double.MAX_VALUE;
	    		  } else if (pi.y == pj.y) {
	    			  slope = 0.0;
	    		  } else {
	    			  slope = 1.0 * (pj.y - pi.y) / (pj.x - pi.x);
	    		  }
	    		  
	    		  if (map.containsKey(slope)) {
	    			  map.put(slope, map.get(slope)+1);
	    		  } else {
	    			  map.put(slope, 2);
	    		  }
	    		  
	    		  if (map.get(slope) > tmpMax) {
	    			  tmpMax = map.get(slope);
	    		  }
	    	  }
	    	  
	    	  if(tmpMax + overlapPoints > max) {
	    		  max = tmpMax + overlapPoints;
	    	  }
	      }
	      
	      return max;
	}
	
	public static void main(String[] args) {
		int pNum1 = 9;
		Point[] pt1 = {	new Point(84,250), new Point(0, 0), new Point(1, 0),
						new Point(0, -70), new Point(0, -70), new Point(1, -1),
						new Point(21, 10), new Point(42, 90), new Point(-42, -230)  };
		
		System.out.println("[Test Case 1] Points generated are: ");
		for(int i=0; i<pNum1; i++) {
			System.out.print(pt1[i]);		
		}
		
		System.out.print("\nMaximum Points on the same line are: ");
		System.out.println(maxPoints(pt1));

		Point[] pt2 = { new Point(0,0) };
		System.out.println(maxPoints(pt2));
		
		Point[] pt3 = {new Point(0,0), new Point(1,0) };
		System.out.println(maxPoints(pt3));
	}

}
