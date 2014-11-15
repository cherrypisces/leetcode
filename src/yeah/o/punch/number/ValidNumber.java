package yeah.o.punch.number;

public class ValidNumber {

	private static String trimed(String s){
		if (s==null)
			return null;
		
		int LEN = s.length();
		int startIndex=0, endIndex=LEN;
		// remove front " "
		for(int i=0; i<LEN; i++) {
			char c = s.charAt(i);
			if(c ==' ')
				continue;
			else {
				if(c=='-' || c=='+')
					startIndex=i+1;
				else
					startIndex=i;
				break;
			}
		}
		// remove " " in the end
		for(int i=LEN-1; i>=0; i--) {
			if(s.charAt(i)==' ')
				continue;
			else {
				endIndex=i+1;
				break;
			}
		}		
		return s.substring(startIndex, endIndex);
	}
	
	private static boolean invalidSign(String s) {
		if (s!=null && s.length()>0 && s.charAt(0)=='-')
			return true;
		return false;
	}
	
	private static boolean containsNonDigits(String s) {
		int LEN = s.length();
		for(int i=0; i<LEN; i++) {
			char c = s.charAt(i);			

			if(c=='+' || c=='-') {
				if (i-1>=0 && (s.charAt(i-1)=='e' || s.charAt(i-1)=='E')) {
					continue;
				} else {
					return true;
				}		
				
			} else if ((c!='e' && c!='E'&& c!='.') && (c<'0' || c>'9')) {
					System.out.println(c+"(((");
					return true;
			}
		}
		return false;
	}
	
	private static boolean containInvalidDots(String s) {
		if(s.equals("."))
			return true;
		
		int count=0;
		int LEN = s.length();
		for(int i=0; i<LEN; i++) {
			char c = s.charAt(i);
			if(c=='.')
				count++;
			
			if(count>1)
				return true;
		}
		
		return !(count<=1);
	}
	
	private static boolean invalidScientificNotation(String s) {
		int eCount=0;
		int LEN = s.length();
		boolean found = false;
		for(int i=0; i<LEN; i++) {
			char c = s.charAt(i);
			if (c=='.') {
				if (found == true)
					return true;
			}
			else if(c=='e' || c=='E') {
				found=true;
			
				eCount++;

				if(eCount>1)
					return true;
				
				if (i==0 || i==LEN-1)
					return true;
				else if (i-1>=0 && s.charAt(i-1)=='.' && (i-1==0 || 
						Long.parseLong(s.substring(0,i-1))==0)) {
					return true;
				}
				
				if(i+1<LEN && (s.charAt(i+1)=='+' || s.charAt(i+1)=='-')) {
					i++;
					if (i==LEN-1)
						return true;
				}
			}
		}
		
		return false;
	}
	
    public static boolean isNumber(String s) {
        String trimedStr = trimed(s);
        return !(
        		invalidSign(trimedStr) 			||
        		containsNonDigits(trimedStr)	||
        		containInvalidDots(trimedStr) 	||
        		invalidScientificNotation(trimedStr)
        	   );
    }
    
    public static void printResult(String s) {
		boolean r = isNumber(s);
		System.out.println(s + (r==true ? " is " : " is NOT ") + "number!");
    }
	
	public static void main(String[] args) {
		printResult("0");
		printResult(" 0.1 ");
		printResult("abc");
		printResult("1 a");
		printResult("2e10");
		printResult("e");
		printResult(".");
		printResult("0e");
		printResult(".e");
		printResult("6ee");
		printResult("-e.");
		printResult("--e");
		printResult("4078046578e3992");
		printResult("5e");
		printResult("1e.");
		printResult("3");
		printResult(".e1");
		printResult("+.8");
		System.out.println(containsNonDigits("005047e+6"));
		printResult("005047e+6");
		printResult("4e+");   // not
		printResult("81e+0"); // yes
	}

}
