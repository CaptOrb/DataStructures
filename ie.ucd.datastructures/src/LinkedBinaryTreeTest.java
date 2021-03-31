import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class LinkedBinaryTreeTest {

    @Test
    void testSize() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
        Position<Integer> root = bt.addRoot(1);
        assertEquals(1, bt.size());

        Position<Integer> l = bt.addLeft(root, 2);

        bt.remove(bt.root());
        assertEquals(1, bt.size());
    }

    @Test
    void testAddRecursive() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        bt.insert(25);
        bt.insert(31);
        bt.insert(58);
        bt.insert(36);
        bt.insert(42);
        bt.insert(90);
        bt.insert(62);
        bt.insert(75);

        bt.breadthfirst();
        System.out.println(bt.toBinaryTreeString());
        assertEquals(8, bt.size());
        assertEquals("[25, 31, 58, 36, 90, 42, 62, 75]", bt.breadthfirst().toString());

    }

    @Test
    void testAddRoot() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        assertEquals(c, bt.root().getElement(), "root not added correctly");
    }

    @Test
    void testParent(){

        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Position<Integer> root = bt.addRoot(12);

        Position<Integer> p1 = bt.addLeft(root, 25);
        assertEquals(bt.parent(p1), root);

        Position<Integer> p2 = bt.addRight(p1, 30);
        assertEquals(bt.parent(p2), p1);

    }

    @Test
    void testAddLeft() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addLeft(bt.root(), 1);
        assertEquals(1, bt.left(bt.root()).getElement());

        try {
            bt.addLeft(bt.root(), 1);

            fail("Already has left child - > should of thrown exception");
        } catch (IllegalArgumentException ex){}
    }

    @Test
    void testAddRight() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addRight(bt.root(), 1);
        assertEquals(1, bt.right(bt.root()).getElement());

        try {
            bt.addRight(bt.root(), 1);

            fail("Already has right child - > should of thrown exception");
        } catch (IllegalArgumentException ex){}
    }

    @Test
    void testRemove() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addRight(bt.root(), 1);
        Integer old = bt.remove(bt.right(bt.root()));
        assertEquals(old, 1);
        assertEquals(1, bt.size());

        try {
            LinkedBinaryTree<Integer> another = new LinkedBinaryTree<Integer>();

            Integer anInt = Integer.parseInt("0");
            another.addRoot(anInt);

            another.addLeft(bt.root(), 1);
            another.addRight(bt.root(), 1);

            fail("Should have thrown exception");

        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    void testToString() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.toString());
    }

    @Test
    void testCreateLevelOrder() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.toString());
    }

    @Test
    void testInorder() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.inorder().toString());
    }

    @Test
    void testDepth() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);

        assertEquals(0, bt.depth(bt.root()));
    }

    @Test
    void testHeight() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);

        assertEquals(3, bt.height(bt.root()));
    }

    @Test
    void set() {

        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Position<Integer> root = bt.addRoot(12);

        Position<Integer> p1 = bt.addLeft(root, 25);

        Position<Integer> p2 = bt.addRight(root, 31);

        bt.set(p1, 1);

        bt.breadthfirst();

        assertEquals("[12, 1, 31]", bt.breadthfirst().toString());

        try {
            Position<Integer> p3 = null;

            bt.set(p3, 1);

            fail("NOT A VALID POSITION, TEST SHOULD OF FAILED");
        } catch (IllegalArgumentException ex){}

    }

    @Test
    void testValidate() {

        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Position<Integer> root = bt.addRoot(12);

        Position<Integer> p1 = null;

        Position<Integer> p2 = bt.addRight(root, 44);

        try {
            bt.validate(p1);

            fail("Should of thrown exception");
        } catch (IllegalArgumentException ex){}

        assertEquals(bt.validate(p2), p2);


    }

    @Test
    void testLeafCount(){
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Position<Integer> root = bt.addRoot(12);

        Position<Integer> p1 = bt.addLeft(root, 3);

        Position<Integer> p2 = bt.addRight(root, 44);

        Position<Integer> p3 = bt.addRight(p1, 5);

        assertEquals(bt.getLeafCount(), 2);

    }

}
