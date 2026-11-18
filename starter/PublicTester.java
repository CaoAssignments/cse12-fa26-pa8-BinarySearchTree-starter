import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PublicTester {
    MyBST<Integer, Integer> treeOne;
    MyBST<Integer, Integer> treeTwo;

    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> rootOne =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
                new MyBST.MyBSTNode<>(2, 1, rootOne);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 1, rootOne);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, six);

        this.treeOne = new MyBST<>();
        this.treeOne.root = rootOne;
        rootOne.setLeft(two);
        rootOne.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        treeOne.size = 6;

        MyBST.MyBSTNode<Integer, Integer> rootTwo =
                new MyBST.MyBSTNode<>(10, 1, null);
        MyBST.MyBSTNode<Integer, Integer> eight =
                new MyBST.MyBSTNode<>(8, 1, rootTwo);
        MyBST.MyBSTNode<Integer, Integer> twelve =
                new MyBST.MyBSTNode<>(12, 1, rootTwo);
        MyBST.MyBSTNode<Integer, Integer> nine =
                new MyBST.MyBSTNode<>(9, 30, eight);
        MyBST.MyBSTNode<Integer, Integer> eleven =
                new MyBST.MyBSTNode<>(11, 50, twelve);

        this.treeTwo = new MyBST<>();
        this.treeTwo.root = rootTwo;
        rootTwo.setLeft(eight);
        rootTwo.setRight(twelve);
        eight.setRight(nine);
        twelve.setLeft(eleven);
        treeTwo.size = 5;
    }
    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, Integer> treeOneRoot = treeOne.root;
        assertSame(treeOneRoot.getRight().getLeft(), treeOneRoot.successor());
    }

    @Test
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = treeOne.root;
        treeOne.insert(10, 1);
        assertEquals(root.getRight().getRight().getKey().intValue(), 10);
        assertSame(root.getRight(), root.getRight().getRight().getParent());
        assertEquals(7, treeOne.size);
    }

    @Test
    public void testSearch() {
        assertEquals(treeOne.search(3).intValue(), 30);
        assertNull(treeOne.search(10));
    }

    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root = treeOne.root;
        assertEquals(treeOne.remove(3).intValue(), 30);
        assertNull(root.getLeft().getRight());
        assertEquals(treeOne.remove(6).intValue(), 1);
        assertEquals(root.getRight().getKey().intValue(), 5);
        assertEquals(4, treeOne.size);
    }

    @Test
    public void testLowerBound() {
        MyBST.MyBSTNode<Integer, Integer> root = treeOne.root;
        assertSame(root.getLeft().getRight(), treeOne.lowerBound(3));
        assertSame(root.getRight().getLeft(), treeOne.lowerBound(5));
        assertSame(root.getLeft().getLeft(), treeOne.lowerBound(0));
        assertSame(root, treeOne.lowerBound(4));
        assertNull(treeOne.lowerBound(7));
    }

    @Test
    public void testCopy() {
        MyBST<Integer, Integer> actualRes = treeTwo.copy();
        MyBST.MyBSTNode<Integer, Integer> rootOrig = treeTwo.root;
        MyBST.MyBSTNode<Integer, Integer> rootCopy = actualRes.root;

        assertNotNull(rootCopy);
        assertEquals(5, actualRes.size);
        // Compare left/right pointers and node values
        assertEquals(rootOrig.getKey().intValue(),
                rootCopy.getKey().intValue());
        assertEquals(rootOrig.getLeft().getKey().intValue(),
                rootCopy.getLeft().getKey().intValue());
        assertEquals(rootOrig.getLeft().getRight().getKey().intValue(),
                rootCopy.getLeft().getRight().getKey().intValue());
        assertEquals(rootOrig.getRight().getKey().intValue(),
                rootCopy.getRight().getKey().intValue());
        assertEquals(rootOrig.getRight().getLeft().getKey().intValue(),
                rootCopy.getRight().getLeft().getKey().intValue());
        // Compare parent pointers and node values
        assertEquals(rootOrig.getLeft().getRight().getParent().getParent().
                getKey().intValue(), rootCopy.getLeft().getRight().getParent().
                getParent().getKey().intValue());
        assertEquals(rootOrig.getRight().getLeft().getParent().getParent().
                getKey().intValue(), rootCopy.getRight().getLeft().getParent().
                getParent().getKey().intValue());
    }

}

