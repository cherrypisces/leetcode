package yeah.o.punch.linkedlist;

import yeah.o.punch.Utilities;



class ListNode {	
	int val;
	ListNode next;
	
	ListNode(int x) {
		val = x;
		next = null;
	}
	
	public String toString() {
		if (next != null)
			return val + " --> ";
		else
			return val + " \n";
	}

}

class LinkedList {
	ListNode head;
	
	public void appendToTail(int d) {
		ListNode end = new ListNode(d);	
        appendNode(end);
	}
	
	public void appendNode(ListNode d) {
		if (d==null) return;		
		if (head == null) {
			head = d;
			return;
		}
		ListNode n = head;
		while(n.next != null) { n = n.next; }		
		n.next = d;
	}
	
	public static void printFromNode(ListNode d) {
		ListNode curr = d;
		while(curr != null) {
			System.out.print(curr);
			curr = curr.next;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		ListNode curr = head;
		while(curr != null) {
			sb.append(curr);
			curr = curr.next;
		}
		return sb.toString();
	}
	
}

public class LinkedListSort {
	
	/*
	 * http://oj.leetcode.com/problems/sort-list/
	 * 
	 * 	Sort a linked list in O(nlogn) time using constant space complexity.
	 *   
	 *  Similar to MergeSort 
	 * 
	 */	
	public static ListNode sortList(ListNode head) {
		if (head == null) {
			return null;
		} else if (head.next == null) {
			return head;
		}
		
		int count = 1;
		ListNode end = head;
		while(end != null) {
			end = end.next;
			count++;
		}
		
		int k = count / 2;
		end = head;
		while(k > 1) {
			end = end.next;
			k--;
		}
		
		ListNode leftEnd = end;
		ListNode rightStart = end.next;
		leftEnd.next = null;
		
		ListNode leftHead = sortList(head);
		ListNode rightHead = sortList(rightStart);
		ListNode newHead = merge(leftHead, rightHead);

		return newHead;
	}
	
	public static ListNode merge(ListNode h_left, ListNode h_right) {
		if (h_left==null && h_right==null)
			return null;
		else if (h_left==null) {
			return h_right;
		} else if (h_right==null) {
			return h_left;
		} 
		
		ListNode head = (h_left.val<=h_right.val) ? h_left : h_right;
		ListNode curr = head;
		
		ListNode left = (head == h_left) ? h_left.next : h_left;
		ListNode right = (head == h_right) ? h_right.next : h_right;
		while(left!=null && right!=null) {		
			if(left.val <= right.val) {
				curr.next = left;
				left = left.next;
			} else {
				curr.next = right;
				right = right.next;
			}
			
			curr = curr.next;
		}
		
		if (left!=null) {
			curr.next = left;
		} 
		
		if (right!=null) {
			curr.next = right;
		}
		
		return head;
	}

	
	/*
	 * http://oj.leetcode.com/problems/insertion-sort-list/
	 * Sort a linked list using insertion sort.
	 * 
	 */
	public static ListNode insertionSortList(ListNode head) {
		if (head == null) 
			return null;
		else if (head.next == null)
			return head;
		
		ListNode currNode = head.next;
		ListNode sortList = head;
		sortList.next = null;		
		
		while(currNode != null) {	
			ListNode next = currNode.next;
			currNode.next = null;
			
			ListNode p = sortList;
			ListNode pre = p;
			while(p!=null) {				
				if (p.val <= currNode.val) {
					pre = p;
					p = p.next;
				}	
				else {					
					if(p == sortList) {
						currNode.next = sortList;
						sortList = currNode;
					} else {
						pre.next = currNode;
						currNode.next = p;
					}
				
					break;
				}					
			}
			
			if (p == null) {
				pre.next = currNode;
			}

			currNode = next;
		}
		
		return sortList;
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
		
	//	ListNode head = insertionSortList(list.head);
	//	System.out.println("After insertion sort: ");
	//	LinkedList.printFromNode(head);
		
		ListNode head = sortList(list.head);
		System.out.println("After quick sort: ");
		LinkedList.printFromNode(head);
		
	}

}
