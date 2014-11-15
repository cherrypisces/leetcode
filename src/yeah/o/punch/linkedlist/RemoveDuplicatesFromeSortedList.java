package yeah.o.punch.linkedlist;

import yeah.o.punch.Utilities;

public class RemoveDuplicatesFromeSortedList {
	
    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        
        ListNode first = head;
        ListNode second = first.next;
        
        while(second != null) {
        	
        	if(first.val == second.val) {
        		first.next = second.next;
        		ListNode toDelete = second;
        		second = second.next;
        		toDelete.next = null;
        	} else {
        		first = second;
        		second = second.next;
        	}        	
        }	
        
        return head;
    }
    
    public static LinkedList generateSortedList() {
    	LinkedList list = new LinkedList();
    	
		int times = Utilities.randomIntInRange(5, 10);
		int start = 1;
		
		for(int time=1; time<=times; time++) {
			int n = Utilities.randomIntInRange(start, 50);
			int duplicates = Utilities.randomIntInRange(1, 10);
			for(int repeat=1; repeat<=duplicates; repeat++) {
				list.appendToTail(n);
			}
			start = n;
		}
		
		System.out.println("The linked list is: ");
		System.out.print(list);
		
		return list;
    }
    
	public static void main(String[] args) {
		
		int numOfTestCases = Utilities.randomIntInRange(3, 10);
		
		for(int i=1; i<numOfTestCases; i++) {
			System.out.println("[TEST CASE " + i + "]");
			LinkedList list = generateSortedList();
			ListNode res = deleteDuplicates(list.head);
			System.out.println("After removing duplicates: ");
			LinkedList.printFromNode(res);
			System.out.println();
		}
	}

}
