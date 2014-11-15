package yeah.o.punch.others;

import java.util.HashMap;


/*
 * http://oj.leetcode.com/problems/max-points-on-a-line/
 * 
 * Given n points on a 2D plane, find the maximum number of points 
 * that lie on the same straight line.
 * 
 */


class Point {
	int x;
	int y;
	Point() { x = 0; y = 0;}
	Point(int a, int b) { x = a; y = b; }
	
	public String toString() {
		return "(" + x + ", " + y + ") ";
	}
}

/*
 * 
 *  y = kx + b
 *  
 */

public class MaxPointsOnLine {

	public static int maxPoints(Point[] points) {		
		int max = 0;
		double slope;
		
		for(int i=0; i < points.length; i++) {			
			Point p1 = points[i];
			int overlapPoints = 0;				
			HashMap<Double, Integer> hashMap = new HashMap<Double, Integer>();
			int tmpMax = 1;
			
			for (int j=i+1; j < points.length; j++) {
				Point p2 = points[j];
				
				if (p1.x == p2.x) {					
					if (p1.y == p2.y) {  	// overlapping points
						overlapPoints++;
						continue;
					}					
					slope = Double.MAX_VALUE;
				} else if (p1.y == p2.y) {
					slope = 0.0;			// jdk bug: hashCode(0.0) != hashCode(-0.0)
				} else {
					slope = 1.0*(p2.y - p1.y)/(p2.x-p1.x);
				}
				
				if (hashMap.containsKey(slope)) {
					hashMap.put(slope, 1+hashMap.get(slope));
				} else {
					hashMap.put(slope, 2);					
				}
				
				if (hashMap.get(slope) > tmpMax)
					tmpMax = hashMap.get(slope);		
			}
			
			if (tmpMax + overlapPoints > max)
				max = tmpMax + overlapPoints ;
			
		}		
		return max;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	
		int pNum = Utilities.randomIntInRange(10, 20);
		Point[] pt = new Point[pNum];
		for(int i=0; i<pNum; i++) {
			int x = Utilities.randomIntInRange(0, 200);
			int y = Utilities.randomIntInRange(0, 200);
			pt[i] = new Point(x, y);
		} */
		
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
	
		int pNum2 = 3;
		Point[] pt2 = {	new Point(2,3), new Point(3, 3), new Point(-5, 3) };
		
		System.out.println("\n[Test Case 2] Points generated are: ");
		for(int i=0; i<pNum2; i++) {
			System.out.print(pt2[i]);		
		}
		
		System.out.print("\nMaximum Points on the same line are: ");
		System.out.println(maxPoints(pt2));	

}

	
	

}
