/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */
import java.util.ArrayList;

/**
 * This class creates a binary search array uses binary search
 * to find and insert the element so that the array remains sorted.
 * @param <T>
 */
public class BinarySearchArray<T extends Comparable<? super T>> {

    private ArrayList<T> sortedArray;

    /**
     * constructor of the class that create an empty ArrayList
     */
    public BinarySearchArray() {
        sortedArray = new ArrayList<>();
    }

    /**
     * constructors of the class that create an ArrayList
     * @param sortedArray
     */
    public BinarySearchArray(ArrayList<T> sortedArray) {
        this.sortedArray = sortedArray;
    }

    /**
     * This method will perform a binary search on the sortedArray.
     * If the element can be found in the array, this method should return the
     * index of that element. Otherwise, if the element is not in the array, this
     * method should still return the index where the given element should be inserted to.
     *
     * @param element the given element to find
     * @return the index of that element. Otherwise, if the element is not in the array, this
     *         method should still return the index where the given element should be inserted to.
     */
    public int binarySearch(T element) {
        int start = 0, end = sortedArray.size(), mid = 0;
        while (start < end) {
            mid = (end + start) / 2;
            int cmp = sortedArray.get(mid).compareTo(element);
            if (cmp == 0) {
                start = end = mid;
            }
            else if (cmp < 0) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return start;
    }

    /**
     * method that inserts the element in the array while the array remains sorted
     * @param element
     * @return true if the insertion is successful, false otherwise
     */
    public boolean insert(T element) {
        
        // Use the binarySearch helper method to help you insert a
        // given element. Returns true if insertion is successful or false otherwise.
        //Since our binary search array does not allow duplicates,
        // then if the given element is already present in our sortedArray,
        // this method should return false immediately.
        //If the given element is not present in the sortedArray,
        // you should use the helper method to find the correct
        // position to insert the element, then return true.
        int i = binarySearch(element);
        if (i < sortedArray.size() && sortedArray.get(i).compareTo(element) == 0) {
            return false;
        }
        else {
            sortedArray.add(i, element);
        }
        return true;
    }

    /**
     * find the founded element in the arrayList
     * @param element
     * @return the founded element or null
     */
    public T find(T element) {
        
        // Use the binarySearch helper method to help you
        // find a given element. Returns the founded element
        // if the element to find is present in sortedArray
        // or null otherwise.
        // (Hint: use index returned by binarySearch to
        // check if the element at that index is the one you want to find)
        int i = binarySearch(element);
        if (i < sortedArray.size() && sortedArray.get(i).compareTo(element) == 0) {
            return sortedArray.get(i);
        }
        return null;
    }

    /**
     * return the size of the sortedArray
     * @return sortedArray.size()
     */
    public int getSize() {
        return sortedArray.size();
    }

    /**
     * print the array of the sortedArray
     */
    public void printArray() {
        System.out.println(sortedArray.toString());
    }

}

