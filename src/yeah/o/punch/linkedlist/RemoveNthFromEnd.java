package yeah.o.punch.linkedlist;

import yeah.o.punch.Utilities;

public class RemoveNthFromEnd {
	
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow=head, fast=head;
        
        for(int i=1; i<=n; i++) {
        	fast = fast.next;
        }
       
        if (fast == null) {
        	slow = slow.next;
        	head.next = null;
        	head = slow;
        	return head;
        }
        
        while(fast.next != null) {
        	slow = slow.next;
        	fast = fast.next;
        }
        
        slow.next = slow.next.next;
    	
    	return head;
    }
    
	public static void main(String[] args) {
		LinkedList l1 = new LinkedList();
		int num1 = Utilities.randomIntInRange(5, 10);
		for (int i=0; i<num1; i++) {
			int n = Utilities.randomIntInRange(0, 50);
			l1.appendToTail(n);
		}		
		System.out.println("One linked list is: ");
		System.out.print(l1);

		ListNode removel1 = removeNthFromEnd(l1.head,  num1);
		LinkedList.printFromNode(removel1);
	}

}
