package yeah.o.punch.linkedlist;

import yeah.o.punch.Utilities;

public class ReorderList {
	
	public static int count(ListNode head) {
		int count = 0;
		
		ListNode curr = head;
		while(curr != null) {
			count++;
			curr = curr.next;
		}
		
		return count;
	}
	
	
	public static ListNode splitIntoHalf(ListNode head) {
		if(head == null || head.next == null)
			return null;
		
		int num = count(head);
		int c = 0;
		
		ListNode curr = head;
		ListNode tmp = null;
		while(c < (num+1)/2) {
			c++;
			
			if(c == (num+1)/2) {
				tmp = curr;
			}
			
			curr = curr.next;
			
			if(c == (num+1)/2) {
				tmp.next = null;
			}
		}
		
		return curr;
	}
	
	public static ListNode reverseList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		
		ListNode p = head;
		ListNode q = head.next;
		p.next = null;
		ListNode n = q.next;
		
		while(n != null) {
			q.next = p;
			p = q;
			q = n;
			n = n.next;
		}		
		q.next = p;
		
		return q;
	}
	
	public static ListNode mergeLists(ListNode longer, ListNode shorter) {
		if(longer == null)
			return null;
		else if (shorter == null)
			return longer;
		
		ListNode p1 = longer;
		ListNode p2 = longer.next;
		
		ListNode q1 = shorter;
		ListNode q2 = shorter.next;
		
		while((p2!=null) && (q2!=null)) {
			p1.next = q1;
			q1.next = p2;
			
			q1 = q2; 
			q2 = q2.next;
			
			p1 = p2;
			p2 = p2.next;
		}
		p1.next = q1;
		q1.next = p2;
		
		return longer;
	}
	
	public static void reorderList(ListNode head) {
		ListNode mid = splitIntoHalf(head);
		if(mid != null) {
			ListNode reverseHead = reverseList(mid);
			head = mergeLists(head, reverseHead);
		}
    }	

	private static ListNode reorder2(ListNode head, int length) {
	    ListNode tail = head;
	    if (length > 2) {
	        tail = reorder2(head.next, length - 2);
	    } else if (length == 2) {
	        return head.next;
	    } else {
	        return head;
	    }

	    ListNode t = tail.next;
	    tail.next = t.next;
	    t.next = head.next;
	    head.next = t;
	    return tail;   
	}
	
	public static void main(String[] args) {
		
		LinkedList list1 = new LinkedList();
		int num = Utilities.randomIntInRange(5, 10);
		for (int i=0; i<num; i++) {
			int n = Utilities.randomIntInRange(0, 50);
			list1.appendToTail(n);
		}

		System.out.println("The linked list is [" + count(list1.head) + "]: ");
		System.out.println(list1);
/*		
		ListNode mid = splitIntoHalf(list1.head);		
		System.out.println(list1);
		
		ListNode reverseHead = reverseList(mid);
		LinkedList secondHalf = new LinkedList();
		secondHalf.head = reverseHead;
		System.out.println(secondHalf);
		
		ListNode mergeHead = mergeLists(list1.head, reverseHead);
		LinkedList merged = new LinkedList();
		merged.head  = mergeHead;
		System.out.println(merged);
		*/
		
		reorderList(list1.head);
		System.out.println(list1);
		
		////////////////////////////////////////////////
		
		LinkedList list2 = new LinkedList();
		int num2 = Utilities.randomIntInRange(5, 10);
		for (int i=0; i<num2; i++) {
			int n = Utilities.randomIntInRange(0, 50);
			list2.appendToTail(n);
		}
		
		System.out.println("The linked list is [" + count(list2.head) + "]: ");
		System.out.println(list2);
		
		reorder2(list2.head, count(list2.head));
		System.out.println("After reorder: \n" + list2);
		
	}

}
