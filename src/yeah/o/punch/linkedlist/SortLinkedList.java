package yeah.o.punch.linkedlist;

import yeah.o.punch.Utilities;

public class SortLinkedList {
	
	/**
	 * 
	 * https://oj.leetcode.com/problems/sort-list/
	 * 
	 * Sort a linked list in O(nlogn) time using constant space complexity.
	 *
	 */
	public static ListNode sortList(ListNode head) {
		if(head == null) return null;
		else if(head.next == null) return head;
		
		int count = 0;
		ListNode p = head;
		while(p != null) {
			count ++;			
			p = p.next;
		}
		
		int k = 0;
		p = head;
		while(k < count/2-1) {
			p = p.next;
			k++;
		}
		
		ListNode left = head;
		ListNode right = p.next;
		p.next = null;
		
		ListNode n_left = sortList(left);
		ListNode n_right = sortList(right);
		ListNode n_head = mergeSort(n_left, n_right);
		
		return n_head;
	}
	
	
	public static ListNode mergeSort(ListNode left, ListNode right) {					
		if(left == null) return right;
		else if(right == null) return left;
		
		ListNode n_head = null;
		ListNode n_tail = null;
		ListNode curr = null;
		while(left != null && right != null) {
			
			if(left.val <= right.val) {
				curr = left;
				left = left.next;
			} else {	
				curr = right;
				right = right.next;
			}
			
			curr.next = null;
			
			if(n_head == null) {
				n_head = curr;
				n_tail = curr;
			} else {
				n_tail.next = curr;
				n_tail = curr;
			}
		}
		
		if (left == null)
			n_tail.next = right;
		
		if (right == null)
			n_tail.next = left;
		
		return n_head;
	}
	
	//////////////////////////////////////////////////////////////
	
	public static ListNode insertionSortList(ListNode head) {
		if(head == null) return null;
		else if(head.next == null) return head;

		ListNode curr = null;
		ListNode n_head = null;
		
		do {
			// get one from original list
			curr = head;
			head = head.next;
			curr.next = null;
			
			if(n_head == null) {
				n_head = curr;
			} else {				
				ListNode p = n_head;
				ListNode q = p;
				while(p != null) {
					if (p.val > curr.val)
						break;
					q = p;
					p = p.next;
				}
				if (p == null) { 			// end
					q.next = curr;
				} else if (p == n_head) {  	// head
					curr.next = p;
					n_head = curr;
				} else {					// mid
					curr.next = p;
					q.next = curr;
				}
			}
			
		} while(head != null);
		
		return n_head;
	}

	//////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		
		LinkedList list1 = new LinkedList();
		int num = Utilities.randomIntInRange(5, 10);
		for (int i=0; i<num; i++) {
			int n = Utilities.randomIntInRange(0, 50);
			list1.appendToTail(n);
		}
		
		System.out.println("The linked list is: ");
		System.out.println(list1);
		
		ListNode head = insertionSortList(list1.head);
		System.out.println("After insert sort: ");
		LinkedList.printFromNode(head);
		
		///////////////////////////////////////////////////////
		System.out.println("\n///////////////////////////////////////////////////////\n");
		
		LinkedList list2= new LinkedList();
		num = Utilities.randomIntInRange(5, 10);
		for (int i=0; i<num; i++) {
			int n = Utilities.randomIntInRange(0, 50);
			list2.appendToTail(n);
		}
		
		System.out.println("The linked list is: ");
		System.out.println(list2);
		
		ListNode head2 = sortList(list2.head);
		System.out.println("After merge sort: ");
		LinkedList.printFromNode(head2);
	}

}
