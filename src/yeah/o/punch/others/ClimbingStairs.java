package yeah.o.punch.others;

public class ClimbingStairs {
	
    public static int climbStairs(int n) {
        int[] s={0,1};
        
        if (n<2)
        	return s[n];
        
        int sn = 2;
        for(int t=3; t<=n; t++) {
        	s[0] = s[1];
        	s[1] = sn;
        	sn = s[1]+s[0];
        }
        
        return sn;
    }
	
	public static void main(String[] args) {
		for(int i=0; i<46; i++) {
			int res = climbStairs(i);
			System.out.println("For " + i + " steps, " + res + " ways can climb to the top");
		}
	}

}
