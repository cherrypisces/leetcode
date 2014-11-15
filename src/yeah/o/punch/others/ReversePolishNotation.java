package yeah.o.punch.others;

/*
 * http://oj.leetcode.com/problems/evaluate-reverse-polish-notation/
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * 		["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * 		["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 */


public class ReversePolishNotation {

	 public static int evalRPN(String[] tokens) {
		
			int[] stack = new int[tokens.length];
			int top = -1;
		   	for(int i=0; i<tokens.length; i++) {
		   		
				if (isNumber(tokens[i])) {
					stack[++top] = Integer.parseInt(tokens[i]);
					continue;
				}	
				
				int right = stack[top--];
				int  left = stack[top--];
				
				char operator = tokens[i].charAt(0);
				switch(operator) {
					case '+': stack[++top] = left + right; break;
					case '-': stack[++top] = left - right; break;
					case '*': stack[++top] = left * right; break;
					case '/': stack[++top] = left / right; break;
					default: break;
				}

			}
			
		   	return stack[top];
	 }


	 public static boolean isNumber(String s) {
		 if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
			 return false;
		 else
			 return true;
	 }

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] testcase1 = {"2", "1", "+", "3", "*"};
		String[] testcase2 = {"4", "13", "5", "/", "+"};
		String[] testcase3 = {"0", "3", "/"};
		
		System.out.println(evalRPN(testcase1));
		System.out.println(evalRPN(testcase2));
		System.out.println(evalRPN(testcase3));		
	}

}
