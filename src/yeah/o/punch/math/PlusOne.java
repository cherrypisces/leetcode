package yeah.o.punch.math;

public class PlusOne {
	
	public static int[] plusOne(int[] digits) {
		if (digits == null || digits.length==0) 
			return new int []{1};
		
		int len = digits.length;
		boolean added = true;
		for(int i=len-1; i>=0; i--) {
			digits[i] += 1;
			added = digits[i] > 9 ? true : false;
			digits[i] = added ? (digits[i] - 10) : digits[i]; 
			
			if(!added) break;
		}

		if (added) {
			int[] newDigits = new int[len+1];
			for(int i=len-1; i>=0; i--) {
				newDigits[i+1] = digits[i];
			}
			newDigits[0] = 1;
			return newDigits;
		} else {
			return digits;
		}
	}
	   
	public static void main(String[] args) {
		int[] n1 = {9,9,9};
		int[] res1 = plusOne(n1);
		for(int i=0; i<res1.length; i++) {
			System.out.print(res1[i]);;
		}
		System.out.println("\n --------------------------------- ");
		
		int[] n2 = {0,1,1};
		int[] res2 = plusOne(n2);
		for(int i=0; i<res2.length; i++) {
			System.out.print(res2[i]);;
		}
		System.out.println("\n --------------------------------- ");
		
		
		int[] n3 = {9};
		int[] res3 = plusOne(n3);
		for(int i=0; i<res3.length ; i++) {
			System.out.print(res3[i]);;
		}
		System.out.println("\n --------------------------------- ");

	}

}
