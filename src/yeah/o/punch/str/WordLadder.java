package yeah.o.punch.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	private static boolean isTransformable(String s1, String s2) {
		if(s1 == null && s2 == null)
			return false;
		
		if (s1.length() != s2.length())
			return false;
		
		int diffs = 0;
		int len = s1.length();
		for(int i=0; i<len; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				diffs ++;
				if (diffs > 1)
					return false;
			}
		}
		
		return diffs == 1;
	}
	
	/*
	 * Adjacent Matrix(V^2): TLE 
	 * 
	 */
    public static int ladderLength_matrix(String start, String end, Set<String> dict) {
    	
    	int dictSize = dict.size();
    	final int MAX = Integer.MAX_VALUE;
    	
    	// init graph
    	int[][] res = new int[2+dictSize][2+dictSize];
    	for(int i=0; i<res.length; i++) {
    		Arrays.fill(res[i], MAX);
    	}
    	
    	ArrayList<String> dictList = new ArrayList<String>();
    	dictList.add(start);
    	dictList.addAll(dict);
    	dictList.add(end);

    	for(int i=0; i<2+dictSize; i++) {
    		res[i][i] = 0;    		
    		for(int j=i+1; j<2+dictSize; j++) {		
        		if(isTransformable(dictList.get(i), dictList.get(j))) {
        			res[i][j] = 1;
        			res[j][i] = 1;        			
        		}
    		}
    	}
  	
    	// shortest path : Floyd, O(V^3)
    	// this could get shortest path between any pair of points
    	for(int i=0; i<dictSize+2; i++) {
    		for (int j=0; j<dictSize+2; j++) {

    			if (i==j || res[i][j]==1) continue;
    			
    			for(int k=0; k<dictSize+2; k++) {
    				
	    			if (res[i][k] != MAX && res[k][j] != MAX) {
	    				
	    				if (res[i][k] + res[k][j] < res[i][j]) {
	    					res[i][j] = res[j][i] = res[i][k] + res[k][j];
	    				}	
	    			}
    			}
    		}
    	}

    	return res[0][dictSize+1]==MAX ? 0 : res[0][dictSize+1]+1;
    }
    
	/*
	 * Adjacency List (V+E)
	 * Still TLE
	 */
    public static int ladderLength_adjlist(String start, String end, Set<String> dict) {
    	
    	int dictSize = dict.size();
    	final int MAX = Integer.MAX_VALUE;
	   	
    	ArrayList<String> dictList = new ArrayList<String>();
    	dictList.add(start);
    	dictList.addAll(dict);
    	dictList.add(end);
    	    	
    	ArrayList<LinkedList<Integer>> graph = new ArrayList<LinkedList<Integer>>();
    	for(int i=0; i<2+dictSize; i++) {
    		graph.add(new LinkedList<Integer>());
    	}
    	
    	// init graph
    	// Time Limit Exceed
    	for(int i=0; i<1+dictSize; i++) {    				
    		for(int j=i+1; j<2+dictSize; j++) {  		
        		if(isTransformable(dictList.get(i), dictList.get(j))) {
        			graph.get(i).add(j);
        			graph.get(j).add(i);
        		}
    		}
    	} 
    /*	
    	int LEN = start.length();
    	for(int s=0; s<2+dictSize; s++) {
    		String str = dictList.get(s);
    		
    		for(int k=0; k<LEN;k++) {
    			String left = str.substring(0,k);
    			String right = str.substring(k+1);
    			
    			for(char c='a'; c<='z'; c++) {
    				String newStr = left+c+right;
    				if (dictList.contains(newStr)) {
    					int index = dictList.indexOf(newStr);
    					graph.get(s).add(index);
    					graph.get(index).add(s);
    				}
    			}
    			
    		}    		
    	} */
 
    	// =================== shortest path : DP  (BFS)
    	Queue<Integer> queue = new LinkedList<Integer>();
    	boolean[] visited = new boolean[2+dictSize];
    	
    	queue.offer(0);
    	visited[0] = true;

      	int[] d = new int[2+dictSize];
    	Arrays.fill(d, MAX);
    	d[0] = 1;
    	
    	while(!queue.isEmpty()) {
    		int curr = queue.poll();
    		
    		LinkedList<Integer> edges = graph.get(curr);
    		for(int neighbor : edges) {
    			if (!visited[neighbor]) {
    				visited[neighbor] = true;
    				queue.offer(neighbor);
    			}
    			
    			if (d[curr] + 1 < d[neighbor]) {
    				d[neighbor] = d[curr] + 1;
    			}
    		}
    	}    	
 
    	return d[1+dictSize]==MAX ? 0 : d[1+dictSize];
    }
    
    
	 public static int ladderLength_dijkstra(String start, String end, Set<String> dict) {
	    	
	    	int dictSize = dict.size();
	    	final int MAX = Integer.MAX_VALUE;
		   	
	    	ArrayList<String> dictList = new ArrayList<String>();
	    	dictList.add(start);
	    	dictList.addAll(dict);
	    	dictList.add(end);
	    	    	
	    	ArrayList<LinkedList<Integer>> graph = new ArrayList<LinkedList<Integer>>();
	    	for(int i=0; i<2+dictSize; i++) {
	    		graph.add(new LinkedList<Integer>());
	    	}
	    	
	    	// init graph
	    	for(int i=0; i<1+dictSize; i++) {    				
	    		for(int j=i+1; j<2+dictSize; j++) {  		
	        		if(isTransformable(dictList.get(i), dictList.get(j))) {
	        			graph.get(i).add(j);
	        			graph.get(j).add(i);
	        		}
	    		}
	    	}
	    	
	    	// algo starts ....
	    	HashSet<Integer> unvisited = new HashSet<Integer>();
	    	for(int i=0; i<2+dictSize; i++)
	    		unvisited.add(i);
	    	
	      	int[] d = new int[2+dictSize];
	    	Arrays.fill(d, MAX);
	    	
	    	d[0] = 1;
	    	int curr = 0;
	    	while(!unvisited.isEmpty()) {
	    		LinkedList<Integer> edges = graph.get(curr); 
	    		for(int neighbor : edges) {
	    			if(d[curr] + 1 < d[neighbor]) {
	    				d[neighbor] = d[curr] + 1;
	    			}
	    		}
	    		
	    		unvisited.remove(curr);
	    		
	    		int min = MAX;
	    		for(int i : unvisited) {
	    			if (d[i] < min) {
	    				min = d[i];
	    				curr = i;
	    			}
	    		}
	    	}
	    	
	    	return  d[1+dictSize]==MAX ? 0 : d[1+dictSize];
	 }
	 
    
    /**
     * improve with hints here:
     * https://oj.leetcode.com/discuss/7348/time-limit-exceeded-bfs
     * 
     * Using isTransformable seems to be slower than changing every position from 'a' to 'z'.
     */
    
    public static int ladderLength_bfs(String start, String end, Set<String> dict) {
    	final String SEPERATOR = "#";
    	final int LEN = start.length();
    	
    	int step = 1;
    	
    	Set<String> visited = new HashSet<String>();
    	
    	Queue<String> queue = new LinkedList<String>();
    	queue.offer(start);
    	visited.add(start);
    	queue.offer(SEPERATOR);
    	
    	while(!queue.isEmpty()) {
    		String curr = queue.poll();
    		
    		if (curr == SEPERATOR) {
    			step++;

    			if (queue.isEmpty()) {
    				return 0;
    			} else {
    				queue.offer(SEPERATOR);
    				continue;
    			}
    		}
    		
    		for(int i=0; i<LEN; i++) {
    			String left	= i>0 ? curr.substring(0,i) : "";
    			String right= i<LEN-1 ? curr.substring(i+1) : "";
    			
	    		for(char c='a'; c<='z'; c++) {
	    			if (curr.charAt(i) == c)
	    				continue;
	    			
	    			String newStr = left+c+right;
	    			
	    			if (end.equals(newStr)) {
	    				return step+1;
	    			}
	    			
	    			if (!visited.contains(newStr) && dict.contains(newStr)) {
	    				queue.offer(newStr);
	    				visited.add(newStr);
	    			}
	    		}
    		}
    		
    	}	
    	
    	return 0;
    }
    
    // from MIT algorithm video DP session : memorized DP
    public static int ladderLength_matrix_p2p(String start, String end, Set<String> dict) {
    	// ----- BEGIN: same with ladderLength_matrix ----- 
    	int dictSize = dict.size();
    	final int MAX = Integer.MAX_VALUE;
    	
    	// init graph
    	int[][] res = new int[2+dictSize][2+dictSize];
    	for(int i=0; i<res.length; i++) {
    		Arrays.fill(res[i], MAX);
    	}
    	
    	ArrayList<String> dictList = new ArrayList<String>();
    	dictList.add(start);
    	dictList.addAll(dict);
    	dictList.add(end);

    	for(int i=0; i<2+dictSize; i++) {
    		res[i][i] = 0;    		
    		for(int j=i+1; j<2+dictSize; j++) {		
        		if(isTransformable(dictList.get(i), dictList.get(j))) {
        			res[i][j] = 1;
        			res[j][i] = 1;        			
        		}
    		}
    	}
    	// ------ STOP: same with ladderLength_matrix ------ 
    	
    	// d[k]: shortest path from 0 to k, so what we need to get is d[end]
    	int[] d = new int[2+dictSize];
    	Arrays.fill(d, MAX);
    	d[0] = 0;
    	
    	Queue<Integer> queue = new LinkedList<Integer>();
    	queue.offer(0);
    	
    	boolean[] visited = new boolean[2+dictSize];
    	visited[0] = true;    	
    	
    	while(!queue.isEmpty()) {
    		int curr = queue.poll();
    		
    		for(int i=0; i<2+dictSize; i++) {
    			if (res[curr][i]==MAX) 
    				continue;
    		
    			if (!visited[i]) {
    				visited[i] = true;
    				queue.offer(i);
    			}
    			
    			int c = d[curr] + res[curr][i];
    			if (c < d[i]) {
    				d[i] = c;
    			}
    		}
    	}
    		
    	return d[1+dictSize]==MAX ? 0 : d[1+dictSize]+1;
    }
	
    
	public static void main(String[] args) {
		
		System.out.println("TEST CASE 1:");
		String start1 = "hit";
		String end1 = "cog";
		String[] dictValues1 = new String[]{"hot", "dot", "dog", "lot", "log"};
		Set<String> dict1 = new HashSet<String>(Arrays.asList(dictValues1));
		int res1_1 = ladderLength_matrix(start1, end1, dict1);
		System.out.println(res1_1);
		int res1_2 = ladderLength_adjlist(start1, end1, dict1);
		System.out.println("\n"+ res1_2);
		int res1_5 = ladderLength_dijkstra(start1, end1, dict1);
		System.out.println("\n"+ res1_5);
		int res1_3 = ladderLength_bfs(start1, end1, dict1);
		System.out.println("\n"+ res1_3);
		int res1_4 = ladderLength_matrix_p2p(start1, end1, dict1);
		System.out.println("\n"+ res1_4);
		
		System.out.println("============================================================");
		
		System.out.println("TEST CASE 2:");
		String start2 = "nape";
		String end2	  = "mild";
		String[] dictValues2 = new String[]{"mild","dose","ends","dine","jars","prow","soap","guns","hops","cray","hove","ella","hour","lens","jive","wiry","earl","mara","part","flue","putt","rory","bull","york","ruts","lily","vamp","bask","peer","boat","dens","lyre","jets","wide","rile","boos","down","path","onyx","mows","toke","soto","dork","nape","mans","loin","jots","male","sits","minn","sale","pets","hugo","woke","suds","rugs","vole","warp","mite","pews","lips","pals","nigh","sulk","vice","clod","iowa","gibe","shad","carl","huns","coot","sera","mils","rose","orly","ford","void","time","eloy","risk","veep","reps","dolt","hens","tray","melt","rung","rich","saga","lust","yews","rode","many","cods","rape","last","tile","nosy","take","nope","toni","bank","jock","jody","diss","nips","bake","lima","wore","kins","cult","hart","wuss","tale","sing","lake","bogy","wigs","kari","magi","bass","pent","tost","fops","bags","duns","will","tart","drug","gale","mold","disk","spay","hows","naps","puss","gina","kara","zorn","boll","cams","boas","rave","sets","lego","hays","judy","chap","live","bahs","ohio","nibs","cuts","pups","data","kate","rump","hews","mary","stow","fang","bolt","rues","mesh","mice","rise","rant","dune","jell","laws","jove","bode","sung","nils","vila","mode","hued","cell","fies","swat","wags","nate","wist","honk","goth","told","oise","wail","tels","sore","hunk","mate","luke","tore","bond","bast","vows","ripe","fond","benz","firs","zeds","wary","baas","wins","pair","tags","cost","woes","buns","lend","bops","code","eddy","siva","oops","toed","bale","hutu","jolt","rife","darn","tape","bold","cope","cake","wisp","vats","wave","hems","bill","cord","pert","type","kroc","ucla","albs","yoko","silt","pock","drub","puny","fads","mull","pray","mole","talc","east","slay","jamb","mill","dung","jack","lynx","nome","leos","lade","sana","tike","cali","toge","pled","mile","mass","leon","sloe","lube","kans","cory","burs","race","toss","mild","tops","maze","city","sadr","bays","poet","volt","laze","gold","zuni","shea","gags","fist","ping","pope","cora","yaks","cosy","foci","plan","colo","hume","yowl","craw","pied","toga","lobs","love","lode","duds","bled","juts","gabs","fink","rock","pant","wipe","pele","suez","nina","ring","okra","warm","lyle","gape","bead","lead","jane","oink","ware","zibo","inns","mope","hang","made","fobs","gamy","fort","peak","gill","dino","dina","tier"};		
		Set<String> dict2 = new HashSet<String>(Arrays.asList(dictValues2));
		int res2_1 = ladderLength_matrix(start2, end2, dict2);
		System.out.println(res2_1);
		int res2_2 = ladderLength_adjlist(start2, end2, dict2);
		System.out.println("\n"+ res2_2);
		int res2_5 = ladderLength_dijkstra(start2, end2, dict2);
		System.out.println("\n"+ res2_5);
		int res2_3 = ladderLength_bfs(start2, end2, dict2);
		System.out.println("\n"+ res2_3);
		int res2_4 = ladderLength_matrix_p2p(start2, end2, dict2);
		System.out.println("\n"+ res2_4);
		
		System.out.println("============================================================");
		
		System.out.println("TEST CASE 3:");
		String start3 = "cet";
		String end3	  = "ism";		
		String[] dictValues3 = new String[]{"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
		Set<String> dict3 = new HashSet<String>(Arrays.asList(dictValues3));
		int res3_1 = ladderLength_matrix(start3, end3, dict3);
		System.out.println(res3_1);
		int res3_2 = ladderLength_adjlist(start3, end3, dict3);
		System.out.println("\n"+ res3_2);
		//int res3_5 = ladderLength_dijkstra(start3, end3, dict3);
		//System.out.println("\n"+ res3_5);
		int res3_3 = ladderLength_bfs(start3, end3, dict3);
		System.out.println("\n"+ res3_3);
		int res3_4 = ladderLength_matrix_p2p(start3, end3, dict3);
		System.out.println("\n"+ res3_4);
		
		System.out.println("============================================================");
		
	}

}
