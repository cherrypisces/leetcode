package yeah.o.punch.others;

public class ReversePolishNotation2 {

	public static int evalRPN(String[] tokens) {	
		int[] stack = new int[tokens.length];
		int top = -1;

		for(int i=0; i<tokens.length; i++) {
			
			if(isNumber(tokens[i])) {
				stack[++top] = Integer.parseInt(tokens[i]);
				continue;
			}

			int right = stack[top--];
			int left = stack[top--];
			
			switch (tokens[i].charAt(0)) {
				case '+':stack[++top] = left + right; break;
				case '-':stack[++top] = left - right; break;
				case '*':stack[++top] = left * right; break;
				case '/':stack[++top] = left / right; break;
			}
		}
		
		return stack[top];
	}
	
	public static boolean isNumber(String s) {
		if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) 
			return false;
		else
			return true;
	}
	
	public static void main(String[] args) {
		
		String[] testcase1 = {"2","1","+","3","*"};
		String[] testcase2 = {"4","13","5","/","+"};
		String[] testcase3 = {"0", "3", "/"};
		
		System.out.println(evalRPN(testcase1));
		System.out.println(evalRPN(testcase2));
		System.out.println(evalRPN(testcase3));
		
		String tt="+";
		if (tt == testcase1[2]) {
			System.out.println("==: yes");
		}
		if (tt.equals("+")) {
			System.out.println("equal: yes");
		}
	}

}
