import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SplayTreeMap<K, V> extends TreeMap<K, V> {

    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public SplayTreeMap() {
        super();
    }

    /**
     * Constructs an empty map using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the map
     */
    public SplayTreeMap(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Utility used to rebalance after a map operation.
     */
    private void splay(Position<Entry<K, V>> p) {
        while (!isRoot(p)) {

            Position<Entry<K, V>> parent = parent(p);
            Position<Entry<K, V>> gParent = parent(parent);

            if (gParent == null) {
                // zig
                rotate(p);
            } else if ((parent == left(gParent)) == (p == left(parent))) {
                // zig zig
                rotate(parent);
                rotate(p);
            } else {
                // zig zag
                rotate(p);
                rotate(p);
            }
        }
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a node access.
     *
     * @param p
     */
    //@Override
    protected void rebalanceAccess(Position<Entry<K, V>> p) {
        if (isExternal(p)) {
            p = parent(p);
        }
        if (p != null) {
            splay(p);

        }
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after an insertion.
     *
     * @param p
     */
    //@Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        splay(p);
    }

    /**
     * Overrides the TreeMap rebalancing hook that is called after a deletion.
     *
     * @param p
     */
    //@Override
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        if (!isRoot(p))
            splay(parent(p));
    }

    public String toString() {
        return tree.toString();
    }

    public String toBinaryTreeString() {
        return tree.toBinaryTreeString();
    }

/*    public static void main(String[] args) {

        Random rnd = new Random();

        SplayTreeMap<Integer, Integer> map = new SplayTreeMap<>();

        List<Integer> keys = rnd.ints(1, 100).limit(40).distinct().boxed().collect(Collectors.toList());

        keys.forEach(x -> map.put(x, x));

        System.out.println(map.toBinaryTreeString());

        for (int i = 0; i < 10; ++i) {
            map.get(keys.get(i));
            System.out.println(map.toBinaryTreeString());
        }

    }*/

    public static void main(String[] args) {
        SplayTreeMap<Integer, Integer> treeMap = new SplayTreeMap<Integer, Integer>();

        //Integer[] arr = new Integer[]{44, 17, 88, 8, 32, 65, 97, 28, 54, 82, 93, 21,
        //        29, 76, 80};
        Integer[] arr = new Integer[]{8, 3, 10, 4, 11, 6, 12, 5, 7, 16, 13, 17, 14};
        // Integer[] arr = new Integer[]{1, 3, 2, 4};
        List<Integer> intList = Arrays.asList(arr);
        //Collections.shuffle(intList);
        //Collections.sort(intList, Collections.reverseOrder());
        //Collections.reverse(intList);

        intList.forEach(x -> {
            treeMap.put(x, x);

        });


        System.out.println(treeMap.toBinaryTreeString());
        System.out.println("Value at key 12 is " + treeMap.get(12));

        System.out.println("\nRemoving 11");
        treeMap.remove(11);
        System.out.println(treeMap.toBinaryTreeString());


        treeMap.put(50, 50);
        System.out.println("Added 50 \n" + treeMap.toBinaryTreeString());

    }
}
