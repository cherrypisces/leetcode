package yeah.o.punch.linkedlist;

import java.util.HashMap;

public class LRUCache {

	class Cache {
		int key, value;
		Cache pre, next;
		
		public Cache(int k, int v) {
			key = k;
			value = v;
			pre = next = null;
		}
	}
	
	int capacity = 0;
	
	Cache head = null;
	Cache tail = null;
	
	HashMap<Integer, Cache> hashMap = new HashMap<Integer, Cache>();
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
	}
	
	public int get(int key) {
		if (head == null || !hashMap.containsKey(key))
			return -1;
 
		Cache cache = hashMap.get(key);
		moveToHead(cache);
		return cache.value;
	}

	private void moveToHead(Cache cache) {
		if (cache == head) {
			return;
		} else {
			if (tail == cache) {
				tail = tail.pre;
				tail.next = null;
			} else {						
				cache.next.pre = cache.pre;
				cache.pre.next = cache.next;
			}
			head.pre = cache;
			cache.pre = null;
			cache.next = head;
			head = cache;
		}
	}
	
	private void add(int k, int v) {
		Cache cache = new Cache(k, v);
		head.pre = cache;
		cache.next = head;
		head = cache;
		hashMap.put(k, cache);
	}
	
	public void set(int key, int value) {		
		if(head == null) {
			head = new Cache(key, value);
			tail = head;
			hashMap.put(key, head);
			return;
		}
		
		if (hashMap.containsKey(key)) {
			get(key);
			Cache curr = hashMap.get(key);
			curr.value = value;
		} else if (hashMap.size() < capacity) {
			add(key, value);
		} else {
			if (tail != head) {
				hashMap.remove(tail.key);
				tail = tail.pre;
				tail.next = null;
				add(key, value);
			} else {
				hashMap.clear();
				head = tail = new Cache(key, value);
				hashMap.put(key, head);
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();	
		Cache cache = head;
		while(cache != tail.next) {
			sb.append("<").append(cache.key)
			  .append(", ").append(cache.value).append("> -> ");		
			cache = cache.next;
		}
		sb.append("(END)\n");
		
		cache = tail;
		while(cache != head.pre) {
			sb.append("<").append(cache.key).append(", ")
			  .append(cache.value).append(">").append(" -> ");		
			cache = cache.pre;
		}
		sb.append("(END)");
		return sb.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		int CAPACITY 	= 	Utilities.randomIntInRange(10, 20);
		int ops 		=  	Utilities.randomIntInRange(20, 25);
		
		System.out.println("CAPACITY = " + CAPACITY + "\n");
		LRUCache cache = new LRUCache(CAPACITY);
		
		for(int i=0; i<ops; i++) {
			int key 	=	Utilities.randomIntInRange(10,CAPACITY);
			int value	=	Utilities.randomIntInRange(40,70);
			
			int getOrSet = 	Utilities.randomIntInRange(0,1);
			if(getOrSet == 0) {   // get
				System.out.println("[GET] Key=" + key + ", value=" + cache.get(key));
				System.out.println(cache);
				System.out.println("-----------------------------------------------");
			} else {			  // set
				System.out.println("[SET] key=" + key + ", value=" + value);
				cache.set(key, value);
				System.out.println(cache);
				System.out.println("-----------------------------------------------");
			}
		} */
		// test case 1
	/*	LRUCache cache = new LRUCache(1);
		cache.set(2, 1);
		System.out.println("[SET (2,1)] ");
		System.out.println(cache);
		System.out.println("------------------------------------------------------------------------");
		int v = cache.get(2);
		System.out.println("[GET (2)]: " + v);
		System.out.println(cache);
		System.out.println("------------------------------------------------------------------------");
		cache.set(3, 2);
		System.out.println("[SET (3,2)] ");
		System.out.println(cache);
		System.out.println("------------------------------------------------------------------------");
		v= cache.get(2);
		System.out.println("[GET (2)]: " + v);
		System.out.println(cache);
		System.out.println("------------------------------------------------------------------------");
		v= cache.get(3);
		System.out.println("[GET (3)]: " + v);
		System.out.println(cache);
		System.out.println("------------------------------------------------------------------------");
		*/
		
		LRUCache cache = new LRUCache(3);
		cache.set(1, 1);
		System.out.println("[SET (1,1)] ");
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		cache.set(2, 2);
		System.out.println("[SET (2,2)] ");
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		cache.set(3, 3);
		System.out.println("[SET (3,3)] ");
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		cache.set(4, 4);
		System.out.println("[SET (4,4)] ");
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println(cache);
		System.out.println("------------------------------------------------------------------------");			
		int v = cache.get(4);
		System.out.println("[GET (4)]: " + v);
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		v = cache.get(3);
		System.out.println("[GET (3)]: " + v);
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		v = cache.get(2);
		System.out.println("[GET (2)]: " + v);
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		v = cache.get(1);
		System.out.println("[GET (1)]: " + v);
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		cache.set(5, 5);
		System.out.println("[SET (5,5)] ");
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");			
		v = cache.get(1);
		System.out.println("[GET (1)]: " + v);
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		v= cache.get(2);
		System.out.println("[GET (2)]: " + v);
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		v= cache.get(3);
		System.out.println("[GET (3)]: " + v);
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		v= cache.get(4);
		System.out.println("[GET (4)]: " + v);
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		v= cache.get(5);
		System.out.println("[GET (5)]: " + v);
		System.out.println(cache);
		System.out.println("head is : " + cache.head.value);
		System.out.println("tail is : " + cache.tail.value);
		System.out.println("------------------------------------------------------------------------");
		
		
	}

	/*
	Output:	[4,3,2,-1,-1,2,-1,4,5]
	Expected:	[4,3,2,-1,-1,2,3,-1,5]
	*/
}
