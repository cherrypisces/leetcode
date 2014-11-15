package yeah.o.punch.linkedlist;

import yeah.o.punch.Utilities;

/*
 * 
 * http://oj.leetcode.com/problems/swap-nodes-in-pairs/
 * 		Given a linked list, swap every two adjacent nodes and return its head.
 * 		For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 		Your algorithm should use only constant space. 
 * 		You may not modify the values in the list, only nodes itself can be changed.	
 * 
 */

public class SwapNodesInPairs {

	public static ListNode swapPairs(ListNode head) {
		if (head==null || head.next==null)
			return head;		
		
		ListNode p = head;
		ListNode q = head.next;
		head = q;
		ListNode prev = null;
		
		while(q != null) {
			p.next = q.next;
			q.next = p;
			if (prev !=null) {
				prev.next = q;
			}
			
			if (p != head) {
				prev = p;
			}

			if(p.next==null)
				break;
			
			p = p.next;
			q = p.next;
		}
		
		return head;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList();
		int num = Utilities.randomIntInRange(5, 10);
		for (int i=0; i<num; i++) {
			int n = Utilities.randomIntInRange(0, 50);
			list.appendToTail(n);
		}
		System.out.println("The linked list is: ");
		System.out.println(list);
		
		ListNode head = swapPairs(list.head);
		System.out.println("After swap in pairs: ");
		LinkedList.printFromNode(head);

	}

}
