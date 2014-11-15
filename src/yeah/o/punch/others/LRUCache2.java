package yeah.o.punch.others;

import java.util.HashMap;


public class LRUCache2 {
	
	class CacheNode {
		int key, value;
		CacheNode pre, next;
		
		public CacheNode(int k, int v) {
			key = k;
			value = v;
			pre = next = null;
		}
	}
	
	int capacity = 0;
	
	CacheNode head = null;
	CacheNode tail = null;
	
	HashMap<Integer, CacheNode> map = new HashMap<Integer, CacheNode>();
	
    public LRUCache2(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(head == null || !map.containsKey(key)) 
        	return -1;
        
        CacheNode cache = map.get(key);
        moveToHead(cache);        
        return cache.value;
    }
    
    public void set(int key, int value) {
    	
        if(map.size()<this.capacity && !map.containsKey(key)) {
        	addToHead(key, value);
        } else if (map.containsKey(key)) {
        	get(key);
        	head.value = value;
        } else {  // map.size() == capacity && !map.contains(key)
        	if(this.capacity == 0) return;
        	
        	int k = tail.key;        	
        	if (tail != head) {
	        	tail = tail.pre;
	        	tail.next = null;
        	} else {
        		tail = head = null;
        	}
        	map.remove(k);
        	addToHead(key, value);
        }
    }
	
	private void moveToHead(CacheNode cache) {
		if(head == cache) return;
		
		if(tail == cache) {
			tail = cache.pre;
			tail.next = null;
		} else {
			cache.pre.next = cache.next;
			cache.next.pre = cache.pre;
		}

		cache.pre = null;
		cache.next = head;
		head.pre = cache;
		head = cache;
	}
     
	private void addToHead(int key , int value) {
		CacheNode cache = new CacheNode(key, value);
    	map.put(key, cache);
		
		if (head == null){
			head = cache;
			tail = cache;
		} else {
			cache.next = head;
			head.pre = cache;
			head = cache;
		}
	}
    
	
	public String toString() {
		StringBuilder sb = new StringBuilder();	
		CacheNode cache = head;
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
	
	
	public static void main(String[] args) {
		
		LRUCache2 cache = new LRUCache2(3);
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

}
