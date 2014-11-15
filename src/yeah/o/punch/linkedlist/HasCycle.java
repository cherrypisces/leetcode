package yeah.o.punch.linkedlist;

import yeah.o.punch.Utilities;


class LinkedListWithTail {
	ListNode head;
	ListNode tail;
	
	public void appendToTail(int d) {
		ListNode end = new ListNode(d);	
        appendNode(end);
	}
	
	public void appendNode(ListNode d) {
		if (d==null) return;		
		if (head == null) {
			head = d;
			tail = head;
			return;
		}
		ListNode n = head;
		while(n.next != null) { n = n.next; }		
		n.next = d;
		tail = d;
	}
	
	public void printFromNode(ListNode d) {
		ListNode curr = d;
		while(curr != tail) {
			System.out.print(curr);
			curr = curr.next;
		}
		System.out.print(tail);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ListNode curr = head;
		while(curr != tail) {
			sb.append(curr);
			curr = curr.next;
		}
		sb.append("(tail)"+curr);
		return sb.toString();
	}
	
}


public class HasCycle {

	/**
	 * If the list has a circle, like below
	 * 
	 * [A]-->[B]-->[C]-->[D]-->[E]-->[F]
	 * 				      |		      |
	 *                   [L]<--[H]<--[G]
	 * 
	 * assume A->D		: x steps
	 * assume D->G->D	: y steps
	 * 
	 * let quickPtr move forward 2 steps a time
	 * let slowPtr  move forward 1 step a time
	 * 
	 * i)   if x==y, then slowPtr and quickPtr will meet at D
	 * ii)  if x<y,	 then quickPtr will meet slowPtr at (y-x) in the clockwise direction
	 * iii) if x>y,  then quickPtr will meet slowPtr at x%y in the counter clockwise direction
	 * 
	 */
	public static boolean hasCycle(ListNode head) {
		if(head == null)
			return false;
		
		ListNode quick = head;
		ListNode slow = head;
		
		while(quick != null) {
			slow = slow.next;
			
			quick = quick.next;
			if(quick != null) {
				quick = quick.next;
				if(quick == slow)
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * after quick meet slow, put quick at head, and move 1 step a time
	 * then quick and slow will just meet at where circle starts
	 * 
	 * @param head
	 * @return
	 */
    public static ListNode detectCycle(ListNode head) {
		if(head == null)
			return null;
		
		ListNode quick = head;
		ListNode slow = head;
		
		while(quick != null) {
			slow = slow.next;
			quick = quick.next;
			
			if(quick != null) {
				quick = quick.next;
				if(quick == slow) {
					
					quick = head;
					while(quick != slow) {
						quick = quick.next;
						slow = slow.next;
					}
					return quick;
				}
			}
		}
		
    	return null;
    }
	
	
	public static void main(String[] args) {		
		LinkedListWithTail list = new LinkedListWithTail();
		int num = Utilities.randomIntInRange(5, 10);
		for (int i=0; i<num; i++) {
			int n = Utilities.randomIntInRange(0, 50);
			list.appendToTail(n);
		}		
		System.out.println("The linked list is: ");
		System.out.print(list);
		
		if(hasCycle(list.head)) {
			System.out.println("Has Cycle!");
		} else {
			System.out.println("No Cycle!");
		}
		
		list.tail.next = list.head.next.next.next;
		if(hasCycle(list.head)) {
			System.out.println("Has Cycle!");
		} else {
			System.out.println("No Cycle!");
		}
		
		ListNode entryCirclePoint = detectCycle(list.head);
		if(entryCirclePoint != null) {
			System.out.println("Circle starts from: " + entryCirclePoint);
		}
	}

}
