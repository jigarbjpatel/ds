import java.util.*;
public class LRUCache {
    public static void main(String[] args){
        LRUCache c = new LRUCache(2);
        c.set(2,1);        
        c.set(2,2);
        System.out.println(c.get(2));
        c.set(1,1);
        c.set(4,1);
        System.out.println(c.get(2));             
    }


    class DLL{
        class Node{
            public Integer key;
            public Integer value;
            public Node prev,next;
            public Node(Integer key, Integer val){
                this.key = key;
                this.value = val;
                prev = next = null;
            }
        }
        private Node head,tail;
        public DLL(){
            head = null;
            tail = null;
        }        
        public Node add(Integer key, Integer val){
            Node n = new Node(key,val);
            if(tail == null){
                //List is empty
                head = tail = n;
            }else{
                n.prev = tail;
                tail.next = n;
                tail = n;
            }
            return n;
        }

        public Node addFirst(Integer key, Integer val){
           Node n = new Node(key,val);
            if(head == null){
                //List is empty
                head = tail = n;
            }else{
                head.prev = n;
                n.next = head;
                head = n;                
            }
            return n;
        }
        public Node removeFirst(){            
            Node n = null;
            if(head != null){
                n = head;
                if(head.next != null){
                    head.next.prev = null;                    
                    head = head.next;                   
                }else{
                    //Head and tail are same and this was the only element in queue
                    head = null;
                    tail = null;
                }                
            }
            return n;
        }
        public Node removeLast(){            
            Node n = null;
            if(tail != null){
                n = tail;
                if(tail.prev != null){
                    tail.prev.next = null;                    
                    tail = tail.prev;
                }else{
                    //Head and tail are same and this was the only element in queue
                    head = null;
                    tail = null;
                }                
            }
            return n;
        }
        public void remove(Node n){
            if(n.prev != null && n.next != null){
                n.prev.next = n.next;
                n.next.prev = n.prev;
            }else if(n.next != null){
                n.next.prev = null;
                head = n.next;
            }else if(n.prev != null){
                n.prev.next = null;
                tail = n.prev;
            }else{
                head = tail = null;
            }
        }
    }    
    HashMap<Integer,DLL.Node> cache;
    DLL queue;
    int capacity;
    public LRUCache(int capacity) {
        cache = new HashMap<Integer,DLL.Node>();
        queue = new DLL();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        DLL.Node n = cache.get(key);        
        if(n != null){            
            queue.remove(n);
            cache.remove(n.key);
            queue.addFirst(n.key, n.value);
            cache.put(n.key,queue.addFirst(n.key, n.value));
            return n.value;
        }
        else
            return -1;
    }
    
    public void set(int key, int value) {
        if(cache.get(key) != null){
            //Update existing node - shft the node to front of queue
            DLL.Node n = cache.get(key);
            n.value = value;
            queue.remove(n);
            cache.remove(n.key);
            
            cache.put(n.key,queue.addFirst(n.key, n.value));
        }else{  
            if(cache.size() == this.capacity){
                //Remove the LRU node
                DLL.Node n = queue.removeLast();
                if(n != null){
                    cache.remove(n.key);                    
                } 
            }
            //Add new node to begining
            DLL.Node n = queue.addFirst(key, value);
            cache.put(key, n);         
            //System.out.println(cache.keySet());
        }
    }
}