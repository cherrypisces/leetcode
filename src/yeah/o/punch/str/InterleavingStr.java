package yeah.o.punch.str;

/**
 * 
 * http://oj.leetcode.com/problems/interleaving-string/
 * 
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * 
 * For example,  Given:
 * 		s1 = "aabcc", 	s2 = "dbbca",
 * 		When s3 = "aadbbcbcac", return true.
 * 		When s3 = "aadbbbaccc", return false.
 * 
 * http://oj.leetcode.com/discuss/1553/there-trick-here-are-supposed-convert-recursion-to-for-loop
 * 	
 * 	 	res[i][j] indicates whether s3.substr(0, i+j) is interleaving of 
 * 				 s1.substr(0,i) and s2.substr(0,j)
 * 
 * @author CherryPisces
 *
 */

public class InterleavingStr {
	
	public static boolean isInterleave(String s1, String s2, String s3) {
		
		int len1 = s1.length();
		int len2 = s2.length();
		int len3 = s3.length();
		
		if(len3 != len1+len2)
			return false;
		
		boolean[][] res = new boolean[len1+1][len2+1];
		res[0][0] = true;
		for(int i=1;i<=len1;i++) {
			res[i][0] = (s3.substring(0, i).equals(s1.substring(0, i))) ;
		}
		for(int i=1;i<=len2;i++) {
			res[0][i] = (s3.substring(0, i).equals(s2.substring(0, i)));
		}
		
		for(int i=1; i<=len1; i++) 
			for(int j=1; j<=len2; j++) {
				res[i][j] = ( s3.charAt(i-1+j)==s1.charAt(i-1) && res[i-1][j] ) || 
							( s3.charAt(i+j-1)==s2.charAt(j-1) && res[i][j-1] );
			}
		
		return res[len1][len2];
    }
	
	public static void printTestCase(String s1, String s2, String s3) {
		System.out.println("------------------------------------------------------");
		boolean res = isInterleave(s1, s2, s3);
		System.out.print(s3);
		System.out.print(res ? " IS " : " IS NOT ");
		System.out.println("interleaving of " + s1 + " and " + s2);
		System.out.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "aabcc", s2="dbbca", s3="aadbbcbcac";
		printTestCase(s1, s2, s3);
	
		s3="aadbbbaccc";
		printTestCase(s1, s2, s3);
		
	}

}
