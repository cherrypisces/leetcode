package yeah.o.punch.str;

public class strStr {
	
    public static String strStr(String haystack, String needle) {
        int index = haystack.indexOf(needle);
        if (index == -1)
        	return null;
        else
        	return haystack.substring(index);
    }
    
	public static void main(String[] args) {
		String haystack = "1234adfsdf";
		String needle = "adf";
		String res = strStr(haystack, needle);
		System.out.println(res);
	}

}
