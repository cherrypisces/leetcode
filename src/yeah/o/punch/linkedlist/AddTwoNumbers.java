package yeah.o.punch.linkedlist;

public class AddTwoNumbers {
	
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {    	
    	if (l1==null)
    		return l2;
    	else if(l2==null)
    		return l1;
    	
    	ListNode first 	= l1;
    	ListNode second = l2;
    	
    	ListNode prev = null;
    	
    	int carry = 0;
    	while(first!= null && second!=null) {
    		int val = first.val + second.val + carry;    		
    		carry = (val >= 10) ? 1 : 0;    		
    		first.val 	= val % 10;
    		
    		prev 	= first;
    		
    		first = first.next;
    		second = second.next;
    	}
    	

    	if (first == null) {
    		prev.next = second;
    		
    		while(second!=null) {
    			int val = second.val + carry;  		
        		carry = (val >= 10) ? 1 : 0;
        		second.val = val%10;

        		prev = second;
        		second = second.next;
    		}
    		
    		if (carry > 0) {
    			ListNode node = new ListNode(carry);
    			prev.next = node;
    			node.next = null;
    		}
    		
    	} else if (second == null) {
    		while(first!=null) {
    			int val = first.val + carry;  		
        		carry = (val >= 10) ? 1 : 0;
        		first.val = val%10;

        		prev 	= first;
        		first = first.next;
    		}
    		
    		if (carry > 0) {
    			ListNode node = new ListNode(carry);
    			prev.next = node;
    			node.next = null;
    		}
    	}
    	
    	return l1;    	
    }

	public static void main(String[] args) {
		LinkedList l1 = new LinkedList();
		l1.appendToTail(2);
		l1.appendToTail(4);
		l1.appendToTail(3);
		
		LinkedList l2 = new LinkedList();
		l2.appendToTail(5);
		l2.appendToTail(6);
		l2.appendToTail(4);
		
		ListNode res1 = addTwoNumbers(l1.head, l2.head);		
		LinkedList.printFromNode(res1);
		
		
		LinkedList l3 = new LinkedList();
		l3.appendToTail(5);
		
		LinkedList l4 = new LinkedList();
		l4.appendToTail(5);
		
		ListNode res2 = addTwoNumbers(l3.head, l4.head);		
		LinkedList.printFromNode(res2);
	}
}
