package yeah.o.punch.linkedlist;

import yeah.o.punch.Utilities;

public class MergeTwoSortedLists {

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = null;

		if (l1 == null || l2 == null) {
			if (l1 == null)
				head = l2;
			else if (l2 == null)
				head = l1;

			return head;
		}

		if (l1.val < l2.val) {
			head = l1;
			l1 = l1.next;
		} else {
			head = l2;
			l2 = l2.next;
		}
		ListNode curr = head;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}
			curr = curr.next;
		}
		
		if (l1 != null) {
			curr.next = l1;
		}
		if (l2 != null) {
			curr.next = l2;
		}

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
		ListNode sort1 = SortLinkedList.sortList(l1.head);
		System.out.println("After sorting: ");
		LinkedList.printFromNode(sort1);
		System.out.println();
		LinkedList l2 = new LinkedList();
		int num2 = Utilities.randomIntInRange(5, 10);
		for (int i=0; i<num2; i++) {
			int n = Utilities.randomIntInRange(0, 50);
			l2.appendToTail(n);
		}		
		System.out.println("Another linked list is: ");
		System.out.print(l2);
		ListNode sort2 = SortLinkedList.sortList(l2.head);
		System.out.println("After sorting: ");
		LinkedList.printFromNode(sort2);
		System.out.println();
		
		ListNode merged = mergeTwoLists(sort1, sort2);
		System.out.println("After merging : ");
		LinkedList.printFromNode(merged);	
	}

}
