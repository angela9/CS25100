//import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;

class Pair<K, V>{
    /*
    The pair class is intended to store user id and associated score as a <key, value> pair
    */
    public K key;
    public V value;

    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }
}

public class HashTableChaining<K, V> {
    //constructor
    private int size;
    private int capacity;
    ArrayList<LinkedList<Pair>> hashTable;

    public HashTableChaining(int capacity){
        /* initialize hashtable and other variables */
        this.capacity = capacity;
        hashTable = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            hashTable.add(new LinkedList<>());
        }
    }

    public ArrayList<LinkedList<Pair>> getHashTable(){
        return hashTable;
    }

    public void insert(String key, int value){
        /* use hash() to determine the index of the useid, add to the linkedlist at that position of the hashtable */
            hashTable.get(hash(key)).addLast(new Pair(key,value));
            this.size++;
    }

    public void remove(String key){
        LinkedList<Pair> k = hashTable.get(hash(key));
        if (k == null) {
            return;
        }
        int i = 0;
        while (i < k.size()) {
            if (k.get(i).key.equals(key)) {
                k.remove(i);
            }
            i++;
        }
    }

    public boolean contains(String key) {
        LinkedList<Pair> k = hashTable.get(hash(key));
        if (k == null) {
            return false;
        }
        int i = 0;
        while (i < k.size()) {
            if (k.get(i).key.equals(key)) {
                return true;
            }
            i++;
        }
        return false;
    }

    public int getSize(){
        //return the total number of keys in the hashtable
        return this.size;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public int hash(String key){
        return  (key.hashCode() & 0x7fffffff) % getCapacity();
    }

    public int getValue(String key){
        return -1;
    }

}
