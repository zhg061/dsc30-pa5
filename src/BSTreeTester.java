/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;
import java.util.Iterator;

/**
 * class that tests BSTree class
 */
public class BSTreeTester {
    BSTree<Integer> test1;
    BSTree<Integer> test2;
    BSTree<Integer> test3;
    BSTree<String> test4;
    BSTree<String> test5;
    BSTree<String> test6;


    @org.junit.Before
    public void setUp() throws Exception {
        test1 = new BSTree<>();
        test2 = new BSTree<>();
        test3 = new BSTree<>();
        test4 = new BSTree<>();
        test5 = new BSTree<>();
        test6 = new BSTree<>();
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void getRoot() {
        assertEquals(null, test1.getRoot());
        test1.insert(100);
        assertEquals(new Integer(100), test1.getRoot().getKey());
        test1.insert(10);
        assertEquals(new Integer(100), test1.getRoot().getKey());
    }

    @org.junit.Test
    public void getSize() {
        assertEquals(0, test1.getSize());
        test1.insert(100);
        assertEquals(1, test1.getSize());
        test1.insert(1000);
        test1.insert(10);
        test1.insert(1);
        assertEquals(4, test1.getSize());

    }

    @org.junit.Test
    public void insert() {
        test1.insert(9);
        assertEquals(new Integer(9), test1.getRoot().getKey());
        assertEquals(1, test1.getSize());
        test1.insert(1000);
        test1.insert(10);
        test1.insert(1);
        assertEquals(new Integer(1000), test1.getRoot().getRight().getKey());
        assertEquals(new Integer(1), test1.getRoot().getLeft().getKey());
        assertEquals(new Integer(10), test1.getRoot().getRight().getLeft().getKey());
        assertEquals(4, test1.getSize());

    }

    @org.junit.Test
    public void findKey() {
        test1.insert(9);
        assertTrue(test1.findKey(9));
        test1.insert(19);
        assertTrue(test1.findKey(19));
        assertFalse(test1.findKey(119));
    }

    @org.junit.Test
    public void insertData() {
        test1.insert(9);
        test1.insertData(9, 10);
        assertEquals(new Integer(10), test1.findDataList(9).get(0));
        test1.insertData(9, 100);
        assertEquals(new Integer(100), test1.findDataList(9).get(1));
        test1.insert(19);
        test1.insertData(19, 100);
        assertEquals(new Integer(100), test1.findDataList(19).get(0));
    }

    @org.junit.Test
    public void findDataList() {
        test1.insert(9);
        test1.insert(8);
        test1.insertData(9, 10);
        test1.insertData(9, 17);
        test1.insertData(9, 117);
        assertEquals(new Integer(117), test1.findDataList(9).get(2));
        assertEquals(new Integer(17), test1.findDataList(9).get(1));
        assertEquals(new Integer(10), test1.findDataList(9).get(0));
    }

    @org.junit.Test
    public void findHeight() {
        test1.insert(9);
        test1.insert(8);
        test1.insert(7);
        test1.insert(6);
        test1.insert(5);
        test1.insert(4);
        assertEquals(5, test1.findHeight());
        test2.insert(9);
        test2.insert(10);
        test2.insert(7);
        test2.insert(100);
        test2.insert(80);
        assertEquals(3, test2.findHeight());
        assertEquals(-1, test3.findHeight());
        test3.insert(80);
        assertEquals(0, test3.findHeight());
    }

    @org.junit.Test
    public void leafCount() {
        test1.insert(9);
        test1.insert(8);
        test1.insert(7);
        test1.insert(6);
        test1.insert(5);
        test1.insert(4);
        assertEquals(1, test1.leafCount());
        test2.insert(9);
        test2.insert(10);
        test2.insert(7);
        test2.insert(100);
        test2.insert(80);
        assertEquals(2, test2.leafCount());
        test3.insert(9);
        assertEquals(1, test3.leafCount());
    }

    @org.junit.Test
    public void iterator() {
        test2.insert(9);
        test2.insert(10);
        test2.insert(7);
        test2.insert(100);
        test2.insert(80);
        Iterator<Integer> iter = test2.iterator();
        assertTrue(iter.hasNext());
        assertEquals(new Integer(7), iter.next());
        assertTrue(iter.hasNext());
        assertEquals(new Integer(9), iter.next());
        assertEquals(new Integer(10), iter.next());
        assertEquals(new Integer(80), iter.next());
        assertEquals(new Integer(100), iter.next());

    }

    @Test
    public void intersection() {
        test2.insert(9);
        test2.insert(10);
        test2.insert(7);
        test2.insert(100);
        test2.insert(80);
        Iterator<Integer> iter = test2.iterator();
        test1.insert(9);
        test1.insert(80);
        test1.insert(7);
        test1.insert(6);
        test1.insert(5);
        test1.insert(4);
        Iterator<Integer> iter1 = test1.iterator();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(7);
        temp.add(9);
        temp.add(80);
        assertEquals(temp, test1.intersection(iter, iter1));
        test3.insert(19);
        test3.insert(80);
        test3.insert(17);
        test3.insert(6);
        test3.insert(15);
        test3.insert(4);
        Iterator<Integer> iter3 = test3.iterator();
        iter1 = test1.iterator();
        ArrayList<Integer> temp1 = new ArrayList<Integer>();
        temp1.add(4);
        temp1.add(6);
        temp1.add(80);
        assertEquals(temp1, test1.intersection(iter3, iter1));
    }

    @Test
    public void levelCount() {
        test2.insert(9);
        test2.insert(10);
        test2.insert(7);
        test2.insert(100);
        test2.insert(80);
        assertEquals(1, test2.levelCount(0));
        assertEquals(2, test2.levelCount(1));
        assertEquals(1, test2.levelCount(2));
        assertEquals(1, test2.levelCount(3));
        test1.insert(9);
        test1.insert(8);
        test1.insert(11);
        test1.insert(7);
        test1.insert(15);
        test1.insert(10);
        assertEquals(1, test1.levelCount(0));
        assertEquals(2, test1.levelCount(1));
        assertEquals(3, test1.levelCount(2));
        assertEquals(-1, test1.levelCount(3));
    }

    @Test
    public void remove() {
        test1.insert(9);
        test1.insert(8);
        test1.insert(11);
        test1.insert(7);
        test1.insert(15);
        test1.insert(10);
        assertTrue(test1.remove(9));
        assertTrue(test1.remove(15));
        assertTrue(test1.remove(7));
        assertTrue(test1.remove(8));
        assertFalse(test1.remove(99));
        assertTrue(test1.remove(10));
        assertFalse(test1.remove(10));
    }
}