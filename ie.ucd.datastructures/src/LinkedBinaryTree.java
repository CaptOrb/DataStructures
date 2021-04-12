import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        //  root = new Node<E>(e, parent, left, right);
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {

        Node<E> node = validate(p);

        return node.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {

        if (!isEmpty()) {
            throw new IllegalStateException("Root already exists");
        }
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }


    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {

        Node<E> parent = validate(p);

        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("P already has left child");
        }

        Node<E> n = createNode(e, parent, null, null);

        parent.setLeft(n);

        size++;

        return n;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {

        Node<E> parent = validate(p);

        if (parent.getRight() != null) {
            throw new IllegalArgumentException("P already has left child");
        }

        Node<E> n = createNode(e, parent, null, null);

        parent.setRight(n);

        size++;

        return n;
    }

    int getLeafCount() {
        return getExternalNode(root);
    }

    int getExternalNode(Node<E> node) {
        // base case, root node is null
        if (node == null)
            return 0;
        // base case 2: root node is not null but it has no leaf nodes
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        else
            //
            return getExternalNode(node.getLeft()) + getExternalNode(node.getRight());
    }


    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);

        E temp = node.getElement();

        node.setElement(e);

        return temp;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);

        if (isInternal(p))
            throw new IllegalArgumentException("p must be a leaf");

        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            // attach t1 as left subtree of node
            t1.root.setParent(node);
            node.setLeft(t1.root);

            t1.root = null;
            t1.size = 0;
        }

        if (!t2.isEmpty()) {
            // attach t2 as right subtree of node
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    public void insert(E e) {
        root = addRecursive(root, e);
        ++size;
    }

    public Node<E> addRecursive(Node<E> p, E e) {

        if (p == null) {
            return createNode(e, null, null, null);
        }

        if ((Integer) e < (Integer) p.getElement()) {
            p.left = addRecursive(p.left, e);
        }

        if ((Integer) e > (Integer) p.getElement()) {
            p.right = addRecursive(p.right, e);
        }

        return p;
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);

        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("P cannot have two children");
        }

        Node<E> child;

        if (node.getLeft() != null) {
            child = node.getLeft();
        } else {
            child = node.getRight();
        }

        if (child != null) {
            child.setParent(node.getParent());// child’s grandparent becomes its parent
        }
        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();

            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        size--;

        //removing node from memory
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);

        return temp;
    }

    public void createLevelOrder(ArrayList<E> l) {
        root = createLevelOrderHelper(l, root, 0);
    }

    private Node<E> createLevelOrderHelper(ArrayList<E> l, Node<E> p, int i) {

        if (i < l.size()) {
            Node<E> n = createNode(l.get(i), p, null, null);

            n.left = createLevelOrderHelper(l, n.left, 2 * i + 1);
            n.right = createLevelOrderHelper(l, n.right, 2 * i + 2);

            return n;
        }
        return p;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] a, Node<E> p, int i) {

        if (i < a.length) {
            Node<E> n = createNode(a[i], p, null, null);

            n.left = createLevelOrderHelper(a, n.left, 2 * i + 1);
            n.right = createLevelOrderHelper(a, n.right, 2 * i + 2);

            ++size;

            return n;
        }
        return p;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("[");
        for (Position<E> position : positions()) {
            s.append(position.getElement()).append(", ");
        }

        if (s.length() > 2)
            s.delete(s.length() - 2, s.length());

        s.append("]");
        return s.toString();
    }


    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return String.valueOf(element);
        }
    }

    public boolean isSymmetric(Node<E> nodeA, Node<E> nodeB) {

        if (nodeA == null && nodeB == null) {
            return true;
        }

        if (nodeA != null && nodeB != null) {

            return isSymmetric(nodeA.getLeft(), nodeB.getRight()) && isSymmetric(nodeA.getRight(), nodeB.getLeft());
        }
        return false;
    }

    public boolean isSymmetric(Node<E> root) {
        return isSymmetric(root.getLeft(), root.getRight());
    }

    public boolean isTreeSymmetric() {
        return isSymmetric(root);
    }

   public Node<E> mirror() {
        return mirrorHelper(root);
    }

    public Node<E> mirrorHelper(Node<E> nodeA) {

        if(nodeA == null){
            return null;
        }

        Node<E> temp = nodeA.getLeft();

        nodeA.setLeft(nodeA.getRight());

        nodeA.setRight(temp);

        mirrorHelper(nodeA.getLeft());
        mirrorHelper(nodeA.getRight());

        return nodeA;
    }

    public Node<Integer> LCA(Node<Integer> root, int a, int b) {
        if (root == null) {
            return null;
        }
        if (root.getElement() == a || root.getElement() == b) {
            return root;
        }

        Node<Integer> left = LCA(root.left, a, b);
        Node<Integer> right = LCA(root.right, a, b);


        if (left != null && right != null) {
            return root;
        }

        if(left != null){
            return left;
        }
        return right;
    }

    public  int treeDistance(Node<Integer> root, int node1, int node2) {
        if (root == null) return -1;
        Node<Integer> lca = LCA(root, node1, node2);

        return dist(lca, node1) + dist(lca, node2);
    }
    //gets distance between two points
    private static int dist(Node src, int dest) {
        if ((int)src.getElement() == dest) return 0;
        Node node = src.left;
        if ((int)src.getElement() < dest) {
            node = src.right;
        }
        return 1 + dist(node, dest);
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        // Direct construction of Tree
        Position<Integer> root = bt.addRoot(12);

        bt.insert(25);
        bt.insert(31);
        bt.insert(58);
        bt.insert(36);
        bt.insert(42);
        bt.insert(90);
        bt.insert(62);
        bt.insert(75);

        System.out.println("root " + root);

        // Can you write a level order constructor?
        // Level order construction
        Integer[] arr = {12, 25, 31, 58, 36, 42, 90};
        bt.createLevelOrder(arr);

        System.out.println("bt inorder: " + bt.size() + " " + bt.inorder());
        System.out.println("bt preorder: " + bt.size() + " " + bt.preorder());
        System.out.println("bt postorder: " + bt.size() + " " + bt.postorder());

        System.out.println("bt height: " + bt.height(bt.root()));

        System.out.println(bt.getLeafCount() + " external nodes");

        System.out.println(bt.toString());

        System.out.println();

        System.out.println(bt.toBinaryTreeString());

        System.out.println(bt.isTreeSymmetric());

        Node<Integer> i = bt.mirror();

        System.out.println(i);

        System.out.println(bt.toBinaryTreeString());

        System.out.println("j " + bt.left(bt.root));

        System.out.println(bt.left(bt.root.getLeft()));

        System.out.println(bt.right(bt.root.getRight()));

       // System.out.println("LCA " + bt.treeDistance(bt.root,  bt.left(bt.root.getLeft()).getElement(),  bt.right(bt.root.getRight()).getElement()));


        /*Position<Integer> p1 = bt.addLeft(root, 25);

        System.out.println("p1: " + p1);

        System.out.println("root " + root);

        Position<Integer> p2 = bt.addRight(root, 31);

        Position<Integer> p3 = bt.addLeft(p1, 58);

        bt.addRight(p1, 36);

        Position<Integer> p5 = bt.addLeft(p2, 42);

        bt.addRight(p2, 90);

        Position<Integer> p4 = bt.addLeft(p3, 62);

        bt.addRight(p3, 75);*/

        //System.out.println("root " + root);

        //  System.out.println("ext node count " + bt.left(bt.root()).getElement());

        // System.out.println(bt.toString());
    }
}
