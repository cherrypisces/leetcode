package yeah.o.punch.others;

public class MostWaterContainer {
	
    public static int maxArea(int[] height) {
    	if (height.length <= 1)
    		return 0;
    	
    	int lowPos 	= 0;
    	int highPos = height.length-1;
    	int max = -1;
    	
    	while(lowPos < highPos) {
    		int area = (highPos-lowPos) * Math.min(height[lowPos], height[highPos]);
    		if (area > max) {
    			max = area;
    		}
    		if (height[lowPos] < height[highPos]) {
    			lowPos++;
    		} else {
    			highPos--;
    		}
    	}
    	
    	return max;
    }
    
	public static void main(String[] args) {
		

	}

}
