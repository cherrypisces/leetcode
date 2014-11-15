package yeah.o.punch.number;

public class CountAndSay {
	
    public static String countAndSay(int n) {
        StringBuilder prevLine = new StringBuilder("1");

        for(int i=2; i<=n; i++) {
        	int len = prevLine.length();
            StringBuilder currLine = new StringBuilder();
            
        	int count = 1;
        	char currChar = prevLine.charAt(0);
        	
        	for(int k=1; k<len; k++) {
        		if (currChar==prevLine.charAt(k)) {
        			count++;
        		} else {
        			currLine.append(count);
        			currLine.append(currChar);
        			currChar = prevLine.charAt(k);
        			count=1;
        		}
        	}
        	currLine.append(count);
			currLine.append(currChar);
        	
        	prevLine = currLine;
        }
        
        return prevLine.toString();
    }
	
	
	public static void main(String[] args) {
		for(int i=1; i<10; i++) {
			String line = countAndSay(i);
			System.out.println(line);
		}
	}

}
