/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * class that tests BinarySearchArray class
 */
public class BinarySearchArrayTester {
    BinarySearchArray<Integer> test1;
    BinarySearchArray<Integer> test2;
    BinarySearchArray<Integer> test3;
    BinarySearchArray<String> test4;
    BinarySearchArray<String> test5;
    BinarySearchArray<String> test6;

    @org.junit.Before
    public void setUp() {
        test1 = new BinarySearchArray<>();
        test2 = new BinarySearchArray<>();
        test3 = new BinarySearchArray<>();
        test4 = new BinarySearchArray<>();
        test5 = new BinarySearchArray<>();
        test6 = new BinarySearchArray<>();
    }

    @Test
    public void insert() {
        assertEquals(0, test1.getSize());
        test1.insert(6);
        assertEquals(1, test1.getSize());
        assertFalse(test1.insert(6));
        test1.insert(16);
        test1.insert(116);
        test1.insert(1116);
        assertEquals(4, test1.getSize());
//        assertEquals(3, test1.binarySearch(1116));
//        i changed binarysearch to private, and when i submit it , i chaged to public, does it affact grading
        test1.insert(1);
//        assertEquals(0, test1.binarySearch(1));
//        assertEquals(1, test1.binarySearch(6));
//        assertEquals(2, test1.binarySearch(16));
//        assertEquals(3, test1.binarySearch(116));
//        assertEquals(4, test1.binarySearch(1116));
        test1.insert(106);
//        assertEquals(3, test1.binarySearch(106));


    }

    @Test
    public void find() {
        assertEquals(null, test1.find(9));
        test1.insert(16);
        test1.insert(116);
        test1.insert(1116);
        assertEquals(new Integer(16), test1.find(16));
        assertEquals(new Integer(1116), test1.find(1116));
        assertEquals(null, test1.find(99));


    }

    @Test
    public void getSize() {
        assertEquals(0, test1.getSize());
        test1.insert(16);
        test1.insert(116);
        test1.insert(1116);
        assertEquals(3, test1.getSize());
        test1.insert(16);
        assertEquals(3, test1.getSize());

    }

    @Test
    public void printArray() {
        test1.insert(6);
        test1.insert(10006);
        test1.insert(116);
        test1.insert(106);
        test1.printArray();

    }
}