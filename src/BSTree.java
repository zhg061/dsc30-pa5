/*
 * NAME: Zhaoyi Guo
 * PID: A15180402
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.ArrayList;

/**
 * this class implements a generic binary search tree along with the iterator,
 * which can achieve O(logn) find and insert
 * @param <T>
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    private int nelems;
    private BSTNode root;

    /**
     * Inner class of BSTree that implement nodes in the tree
     */
    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * constructor for BSTNode when the dataList is given
         * @param left
         * @param right
         * @param dataList
         * @param key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            //Constructor that initializes the BSTNode instance variables.
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * constructors that creates an empty dataList
         * @param left
         * @param right
         * @param key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            //Constructor that initializes BSTNode variables.
            this.left = left;
            this.right = right;
            this.key = key;
            this.dataList = new LinkedList<>();
        }

        /**
         * get the key fo the node
         * @return key
         */
        public T getKey() {
            //Returns the key
            return key;
        }

        /**
         * return the left node of the current node
         * @return left
         */
        public BSTNode getLeft() {
            //Getter for the left child of the node
            return left;
        }

        /**
         * return the right node of the current node
         * @return right
         */
        public BSTNode getRight() {
            //Getter for the right child of the node
            return right;
        }

        /**
         * get the dataList of the node
         * @return dataList
         */
        public LinkedList<T> getDataList() {
            //Getter for the linked list of the node
            return dataList;
        }

        /**
         * set the left to a new left node
         * @param newleft
         */
        public void setleft(BSTNode newleft) {
            //Setter for left pointer of the node
            left = newleft;
        }

        /**
         * set the right to a new right node
         * @param newright
         */
        public void setright(BSTNode newright) {
            //Setter for right pointer of the node
            right = newright;
        }

        /**
         * set the dataList to a new dataList
         * @param newData
         */
        public void setDataList(LinkedList<T> newData) {
            //Setter for the linked list of the node
            dataList = newData;
        }

        /**
         * add new data to the dataList
         * @param data
         */
        public void addNewInfo(T data) {
            //Append data to the end of the existing LinkedList of the node
            dataList.add(data);
        }

        /**
         * remove the data from the dataList
         * @param data
         * @return true if the dataList contains the data, false otherwise
         */
        public boolean removeInfo(T data) {
            //Remove info data from the LinkedList of the node and return true.
            //If the LinkedList does not contain the element, return false
            return dataList.remove(data);
        }
    }

    /**
     * constructor for the BSTree
     */
    public BSTree() {
        //A 0-arg constructor that initializes root to null and nelems to 0
        nelems = 0;
        root = null;
    }

    /**
     * get the root of the tree
     * @return root
     */
    public BSTNode getRoot() {
        //Returns the root of BSTree.
        //Returns null if the tree is empty
        if (root != null) {
            return root;
        }
        return null;
    }

    /**
     * get the size of the tree
     * @return nelems
     */
    public int getSize() {
        //Returns number of elements in the tree
        return nelems;
    }

    /**
     * add a node to the tree but still keep the tree balanced
     * @param key
     * @return true if the key is added, false otherwise
     */
    public boolean insert(T key) {
        //Inserts a key into the BST. Since our BST does not allow duplicates,
        // return false if the key is already presented in the BST.
        // Returns true if the insertion is successful.
        //Throws NullPointerException if key is null.
        if (key == null) {
            throw new NullPointerException();
        }
        if (findKey(key)) {
            return false;
        }
        if (root == null) {
            root = new BSTNode(null, null, key);
            root.left = null;
            root.right = null;
        }
        else {
            BSTNode curr = root;
            while (curr != null) {
                if (key.compareTo(curr.getKey()) < 0) {
                    if (curr.left == null) {
                        curr.setleft(new BSTNode(null, null, key));
                        curr = null;
                    }
                    else {
                        curr = curr.left;
                    }
                }
                else {
                    if (curr.right == null) {
                        curr.setright(new BSTNode(null, null, key));
                        curr = null;
                    }
                    else {
                        curr = curr.right;
                    }
                }
            }
        }
        nelems++;
        return true;
    }

    /**
     * try to find the key in the BSTree
     * @param key
     * @return true if the key is in the tree, false otherwise
     */
    public boolean findKey(T key) {
        //Return true if the ‘key’ is found in the tree, false otherwise.
        //Throw NullPointerException if key is null
        if (key == null) {
            throw new NullPointerException();
        }
        BSTNode curr = root;
        while (curr != null) {
            if (curr.key.compareTo(key) == 0) {
                return true;
            }
            if (curr.key.compareTo(key) < 0) {
                curr = curr.right;
            }
            else {
                curr = curr.left;
            }
        }
        return false;
    }

    /**
     * insert the data to the dataList that links to the key node
     * @param key
     * @param data
     */
    public void insertData(T key, T data) {
        //Inserts data into the LinkedList of the node whose key is key.
        //Throw NullPointerException if key or ‘data’ is null
        //Throw IllegalArgumentException if ‘key’ is not found in the BST
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode curr = root;
        while (curr != null) {
            if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
            }
            else if (key.compareTo(curr.key) == 0) {
                curr.getDataList().add(data);
                break;
            }
            else {
                curr = curr.right;
            }
        }
    }

    /**
     * try to find the dataList of the given key
     * @param key
     * @return dataList of the current node
     */
    public LinkedList<T> findDataList(T key) {
        //Return the LinkedList of the node with key value key
        //Throw NullPointerException if key is null
        //Throw IllegalArgumentException if ‘key’ is not found in the BST
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode curr = root;
        while (curr != null) {
            if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
            } else if (key.compareTo(curr.key) == 0) {
                return curr.dataList;
            } else {
                curr = curr.right;
            }
        }
        return curr.dataList;
    }

    /**
     * find the height of the BSTree
     * @return findHeightHelper(root)
     */
    public int findHeight() {
        //Returns the height of the tree,
        // which is defined to be the number of “edges”
        // on the longest downward path from the root to any leaf.
        //By convention, height of an empty tree is -1
        // and the height of a tree with only one node is 0.
        return findHeightHelper(root);
    }

    /**
     * helper method for findHeight
     * @param node
     * @return
     */
    private int findHeightHelper(BSTNode node) {
        //helper method for findHeight
        //recursion call to the findHeightHelper until it reaches null
        // return the total count of recursion based on the maximum height
        if (node == null) {
            return -1;
        }
        int leftHeight = findHeightHelper(node.getLeft());
        int rightHeight = findHeightHelper(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * find the number of leaf in the tree
     * @return leafCountHelper(root)
     */
    public int leafCount() {
        //Returns the number of leaf nodes in the tree.
        // Leaves are nodes with no children.
        return leafCountHelper(root);
    }

    /**
     * helper method for leafCount
     * @param node
     * @return total count of leaves
     */
    private int leafCountHelper(BSTNode node) {
        int leftCount = 0;
        int rightCount = 0;
        // if the root have no children, return 1, the base case
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        // recursion call to get the total count of leaves on the left
        if (node.getLeft()!= null) {
            leftCount = leafCountHelper(node.getLeft());
        }
        // recursion call to ger the total count of leaves on the right
        if (node.getRight() != null) {
            rightCount = leafCountHelper(node.getRight());
        }

        return leftCount + rightCount;
    }

    //******************ITERATOR STARTS HERE!!!********************************

    /**
     * an Iterator for the BST. The iterator help to traverse the tree in strict order.
     */
    public class BSTree_Iterator implements Iterator<T> {
        Stack<BSTNode> nodeStack;

        /**
         * constructor of the class that creates a stack with the leftPath of the root
         */
        public BSTree_Iterator() {
            //Constructor that initializes the Stack with the leftPath of the root
            nodeStack = new Stack<>();
            BSTNode curr = root;
            while (curr != null) {
                nodeStack.push(curr);
                curr = curr.left;
            }
        }

        /**
         * check if the stack has a next node
         * @return true if the stack has next, false otherwise
         */
        public boolean hasNext() {
            //Returns false if the Stack is empty i.e. no more nodes left to iterate, true otherwise
            return !nodeStack.isEmpty();
        }

        /**
         * return the next item in the BST
         * @return the next key from the stack
         */
        public T next() {
            //Returns the next item in the BST.
            // Throws NoSuchElementException if there is no next item
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            BSTNode pop = nodeStack.pop();
            T result = pop.getKey();
            BSTNode curr = pop.right;
            while (curr != null) {
                nodeStack.push(curr);
                curr = curr.left;
            }
            return result;
        }
    }

    /**
     * this method from BSTree is used for calling Iterator
     * @return a BSTree_Iterator()
     */
    public Iterator<T> iterator() {
        //return BSTree_Iterator from the class BSTree
        return new BSTree_Iterator();
    }

    /**
     * return the overlapping nodes of two iterators in an arrayList
     * @param iter1
     * @param iter2
     * @return the overlapped elements in an array list
     */
    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2){
        //Implement a method that returns the
        // intersection of two BSTrees (Elements that can be found in both BSTs).
        // The method takes two iterators as parameter
        // and returns an ArrayList containing the intersection data of the two trees
        ArrayList<T> result = new ArrayList<>();
        ArrayList<T> iter1List = new ArrayList<>();
        ArrayList<T> iter2List = new ArrayList<>();
        while (iter1.hasNext()) {
            iter1List.add(iter1.next());
        }
        while (iter2.hasNext()) {
            iter2List.add(iter2.next());
        }
        if (iter1List.size() > iter2List.size()) {
            for (int i = 0; i < iter1List.size(); i++) {
                if (iter2List.contains(iter1List.get(i))) {
                    result.add(iter1List.get(i));
                }
            }
        }
        else {
            for (int i = 0; i < iter2List.size(); i++) {
                if (iter1List.contains(iter2List.get(i))) {
                    result.add(iter2List.get(i));
                }
            }
        }
        return result;
    }

    /**
     * count the number of integers at certain levels
     * @param level
     * @return interger of number of nodes
     */
    public int levelCount(int level) {
        //Implement a method that counts the number of nodes at a given level.
        // The method takes a level as a parameter and returns the count of nodes at that level.
        if (level > findHeight()) {
            return -1;
        }

        return levelCountHelper(root, 0, level);
    }

    /**
     * count the number of nodes at a certain level
     * heper method for levelCount
     * @param cur
     * @param curLevel
     * @param tarLevel
     * @return number of nodes
     */
    public int levelCountHelper(BSTNode cur, int curLevel, int tarLevel)
    {
        //base case when the current level is null, greater than or equal
        // to the tarLevel
        if(cur == null)
            return 0;
        if(curLevel > tarLevel)
            return 0;
        if(curLevel == tarLevel)
            return 1;
        // separate recursion call of left and right
        return levelCountHelper(cur.left, curLevel + 1, tarLevel) +
                levelCountHelper(cur.right, curLevel + 1, tarLevel);
    }

    /**
     *
     * @param data
     * @return true if the deletion is successful, false otherwise
     */

    public boolean remove(T data)
    {
        if(!findKey(data))
            return false;
        root = removeHelper(root, data);
        return true;
    }

    /**
     * helper method for remove
     * A recursive function to insert a new key in BST
     * @param root
     * @param data
     * @return the deleted node
     */
    private BSTNode removeHelper(BSTNode root, T data)
    {
        /* Base Case: If the tree is empty */
        if (root == null)  return null;

        /* Otherwise, recur down the tree */
        if (data.compareTo(root.key) < 0)
            root.left = removeHelper(root.left, data);
        else if (data.compareTo(root.key) > 0)
            root.right = removeHelper(root.right, data);

            // if key is same as root's key, then This is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if (root.left == null && root.right != null) {
                return root.right;
            }
            else if (root.right == null && root.left != null) {
                return root.left;
            }
            else if (root.right == null && root.left == null) {
                return null;
            }
            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            else
            {
                BSTNode minRight = root.right;
                BSTNode prev = root;
                while(minRight.left != null) {
                    prev = minRight;
                    minRight = minRight.left;
                }
                prev.left = minRight.right;
                minRight.right = root.right;
                minRight.left = root.left;
                return minRight;
            }
        }
        return root;
    }



}
