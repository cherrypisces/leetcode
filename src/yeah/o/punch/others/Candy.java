package yeah.o.punch.others;

import java.util.Arrays;

public class Candy {	
	
	public static int candy2(int[] ratings) {
	    if(ratings.length < 2) return ratings.length;
	    int[] leftTrav = new int[ratings.length];
	    int[] rightTrav = new int[ratings.length];
	    Arrays.fill(leftTrav, 1);
	    Arrays.fill(rightTrav, 1);
	
	    for(int i=0; i<ratings.length - 1; i++) {
	        int j = (ratings.length - 1) - i;
	        if(ratings[i+1] > ratings[i]) leftTrav[i+1] = leftTrav[i] + 1;
	        if(ratings[j-1] > ratings[j]) rightTrav[j-1] = rightTrav[j] + 1;
	    }
	    int total = 0;
	    for(int i=0; i<ratings.length; i++) total += Math.max(leftTrav[i], rightTrav[i]);
	    return total;
	}
	
	// equal comprehension
    public static int candy(int[] ratings) {
    	if (ratings == null ||  ratings.length == 0) 
    		return 0;
    	else if (ratings.length == 1)
    		return 1;

    	int nCandiesCnt = 0;
    	int[] candy = new int[ratings.length];
    	Arrays.fill(candy, -1);
    	
    	int i = 1;
    	int latest = 1;
    	while(i < ratings.length) {
    		
    		if(i<ratings.length && ratings[i-1]<=ratings[i]) {
	    		while(i<ratings.length && ratings[i-1]<=ratings[i]) {
	    			
					candy[i-1] = latest;
					nCandiesCnt += latest;
	    			
	    			if (ratings[i-1]<ratings[i]) {
	    				latest++;
	    			}
	    			
	    			i++;
	    		}
	    		
				candy[i-1] = latest;
				nCandiesCnt += latest;
				
	    		if(i==ratings.length) break;
    		}
			
    		while(i<ratings.length && ratings[i-1]>=ratings[i]) {
    			latest = 1;
    			i++;
    		}
    		
    	}
  		
    	i = ratings.length-2;
    	latest = 1;
    	while(i >= 0) {
    		
    		if(i>=0 && ratings[i]>=ratings[i+1]) {
    			
	    		while(i>=0 && ratings[i]>=ratings[i+1]) {
	    			
	    			if(candy[i+1]==-1) {
						nCandiesCnt += latest;
	    				candy[i+1] = latest;
	    			} else if (candy[i+1]>=latest) {
	    				latest = candy[i+1]; 
	    			} else {
						nCandiesCnt += (latest-candy[i+1]);
	    				candy[i+1] = latest;
	    			}
	    								
	    			if (ratings[i]>ratings[i+1]) {
	    				latest++;
	    			} 
	    			
	    			i--;
	    		}
	    		
    			if(candy[i+1]==-1) {
					nCandiesCnt += latest;
    				candy[i+1] = latest;
    			} else if (candy[i+1]>=latest) {
    				latest = candy[i+1]; 
    			} else {
					nCandiesCnt += (latest-candy[i+1]);
    				candy[i+1] = latest;
    			}
	    		
	    		if(i<0) break;
    		}
    		
    		while(i>=0 && ratings[i]<=ratings[i+1]) {
    			latest = 1;
    			i--;
    		}
    	}
    	
    	return nCandiesCnt;
    }
    
    // Do as requirement
    public static int candy3(int[] ratings) {
    	if (ratings==null)
    		return 0;
    	else if (ratings.length < 2)
    		return ratings.length;
    	
    	int sum = 0;
    	int latest = 1;
    	for(int i=1; i<ratings.length;i++) {
    		
    		if (ratings[i-1] < ratings[i]) {
    			sum += latest;
    			latest++;
    		} else if (ratings[i-1] == ratings[i]) {
    			sum += latest;
    			latest = 1;
    		} else {  // ratings[i-1] > ratings[i]
    			
    			int nDecrePath = 1;
    			while(i<ratings.length && ratings[i-1] > ratings[i]) {
    				nDecrePath++;
    				i++;
    			}
    			i--;
    			
    			if(latest < nDecrePath) {
    				sum += nDecrePath;
    			} else {
    				sum += latest;
    			}
				sum += nDecrePath*(nDecrePath-1)/2-1;
    			latest = 1;
    		}
    	}
    	
    	sum += latest;
    	
    	return sum;
    }
	
	public static void main(String[] args) {		
		int[] r0 = new int[]{0};
		System.out.println("candy:" + candy(r0));
		System.out.println("candy2:" +candy2(r0));
		System.out.println("candy3:" +candy3(r0));
		System.out.println();
		
		int[] r1 = new int[]{2,3,1};
		System.out.println("candy:" + candy(r1));
		System.out.println("candy2:" +candy2(r1));
		System.out.println("candy3:" +candy3(r1));
		System.out.println();

		int[] r2 = new int[]{3,2,1,1,4,3,3};
		System.out.println("candy:" + candy(r2));
		System.out.println("candy2:" +candy2(r2));
		System.out.println("candy3:" +candy3(r2));
		System.out.println();
		
		int[] r3 = new int[]{4,3,2,1};
		System.out.println("candy:" + candy(r3));
		System.out.println("candy2:" +candy2(r3));
		System.out.println("candy3:" +candy3(r3));
		System.out.println();
		
		int[] r4 = new int[]{1,2,3,4};
		System.out.println("candy:" + candy(r4));
		System.out.println("candy2:" +candy2(r4));
		System.out.println("candy3:" +candy3(r4));
		System.out.println();
		
		int[] r5 = new int[]{4,4,2,1,1,3,5,5};
		System.out.println("candy:" + candy(r5));
		System.out.println("candy2:" +candy2(r5));
		System.out.println("candy3:" +candy3(r5));
		System.out.println();
		
		int[] r6 = new int[]{1,3,5,9,8,7,6,3,2};
		System.out.println("candy:" + candy(r6));
		System.out.println("candy2:" +candy2(r6));
		System.out.println("candy3:" +candy3(r6));
		System.out.println();
		
		int[] r7 = new int[]{1,2,2};
		System.out.println("candy:" + candy(r7));
		System.out.println("candy2:" +candy2(r7));
		System.out.println("candy3:" +candy3(r7));
		System.out.println();
		
		int[] r8 = new int[] {58,21,72,77,48,9,38,71,68,77,82,47,25,94,89,54,26,54,54,99,64,71,76,63,81,82,60,64,29,51,87,87,72,12,16,20,21,54,43,41,83,77,41,61,72,82,15,50,36,69,49,53,92,77,16,73,12,28,37,41,79,25,80,3,37,48,23,10,55,19,51,38,96,92,99,68,75,14,18,63,35,19,68,28,49,36,53,61,64,91,2,43,68,34,46,57,82,22,67,89};
		System.out.println("candy:" + candy(r8));
		System.out.println("candy2:" +candy2(r8));
		System.out.println("candy3:" +candy3(r8));
		System.out.println();
		
		int[] r9 = new int[]{2,2,1};
		System.out.println("candy:" + candy(r9));
		System.out.println("candy2:" +candy2(r9));
		System.out.println("candy3:" +candy3(r9));
	}
}
