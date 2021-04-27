import java.util.ArrayList;
import java.util.Random;

/*
 * Map implementation using hash table with separate chaining.
 */

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    /**
     * Creates a hash table with capacity 11 and prime factor 109345121.
     */
    public ChainHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ChainHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }


    public static void main(String[] args) {
        //HashMap<Integer, String> chainHashMap = new HashMap<Integer, String>();
        ChainHashMap<Integer, String> chainHashMap = new ChainHashMap<Integer, String>();

        System.out.println(chainHashMap.hashValue(1) + chainHashMap.hashValue(10));

        chainHashMap.put(1, "One");
        chainHashMap.put(10, "Ten");
        chainHashMap.put(11, "Eleven");
        chainHashMap.put(20, "Twenty");

        int n = 17 * 10;

        System.out.println("ChainHashMap contains:  " + chainHashMap);

        chainHashMap.remove(11);
        System.out.println("ChainHashMap contains:  " + chainHashMap);

        for (int i = 0; i < n; ++i) {
            chainHashMap.put(i, "test");
        }
        System.out.println("ChainHashMap contains: " + chainHashMap);

        System.out.println("Value at position 0: " + chainHashMap.get(0));
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = new UnsortedTableMap[this.capacity];
    }

    /**
     * Returns value associated with key k in bucket with hash value h. If no such
     * entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    @Override
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        return bucket == null ? null : bucket.get(k);
    }

    /**
     * Associates key k with value v in bucket with hash value h, returning the
     * previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K, V> bucket = table[h];

        if (bucket == null) {
            bucket = new UnsortedTableMap<K, V>();

            table[h] = bucket;

        }
        return bucket.put(k, v);
    }

    /**
     * Removes entry having key k from bucket with hash value h, returning the
     * previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    @Override
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];

        if (bucket == null) {
            return null;
        }

        int oldSize = bucket.size();
        V removed = bucket.remove(k);

        n -= (oldSize - bucket.size());

        return removed;
    }

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {

        /*
		for each element in (UnsortedTableMap []) table
			for each element in bucket:
				print element
		*/
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (Entry<K, V> k : table[i].entrySet()) {
                    buffer.add(k);
                }
            }
        }
        return buffer;
    }

    public String toString() {
        return entrySet().toString();
    }
}
