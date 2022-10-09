import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Joshua Wainwright
 * @userid jwainwright3
 * @GTID 903584588
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * @throws IllegalArgumentException if data or any element in data is null
     * @param data the data to add to the tree
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Inputted data colleciton is null");
        }
        for (T value : data) {
            add(value);
        }
    }

    /**
     * Adds the data to the AVL. Start by adding it as a leaf like in a regular
     * BST and then rotate the tree as needed.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Inputted data was null");
        }
        root = addHelper(root, data);
    }

    /**
     * Recursive helper for add method
     * @param curr AVLNode that holds current node
     * @param data T data that is being added to AVL tree
     * @return node to replace curr
     */
    private AVLNode<T> addHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            size += 1;
            return new AVLNode<T>(data);
        }
        if (curr.getData().compareTo(data) < 0) {
            curr.setRight(addHelper(curr.getRight(), data));
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(addHelper(curr.getLeft(), data));
        } else {
            return curr;
        }
        curr.setBalanceFactor(calcBalanceFactor(curr));
        curr.setHeight(calcHeight(curr));
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() < 1) {
                return leftRotation(curr);
            }
            curr.setRight(rightRotation(curr.getRight()));
            return leftRotation(curr);
        }
        if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() > -1) {
                return rightRotation(curr);
            }
            curr.setLeft(leftRotation(curr.getLeft()));
            return rightRotation(curr);
        }
        return curr;
    }

    /**
     * Left rotation algorithm that rebalances a right heavy tree
     * @param a AVLNode that holds top node
     * @return AVLnode to replace A
     */
    private AVLNode<T> leftRotation(AVLNode<T> a) {
        AVLNode<T> b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        a.setHeight(calcHeight(a));
        a.setBalanceFactor(calcBalanceFactor(a));
        b.setHeight(calcHeight(b));
        b.setBalanceFactor(calcBalanceFactor(b));
        return b;
    }

    /**
     * Right rotation algorithm that rebalances a left heavy tree
     * @param c AVLNode that holds top node
     * @return AVLNode to replace C
     */
    private AVLNode<T> rightRotation(AVLNode<T> c) {
        AVLNode<T> b = c.getLeft();
        c.setLeft(b.getRight());
        b.setRight(c);
        c.setHeight(calcHeight(c));
        c.setBalanceFactor(calcBalanceFactor(c));
        b.setHeight(calcHeight(b));
        b.setBalanceFactor(calcBalanceFactor(b));
        return b;
    }

    /**
     * Helper method that calculates the balance factor of a node
     * @param curr AVLNode that holds current node
     * @return int balanceFactor of curr
     */
    private int calcBalanceFactor(AVLNode<T> curr) {
        int h1 = -1;
        int h2 = -1;
        if (curr.getLeft() != null) {
            h1 = curr.getLeft().getHeight();
        }
        if (curr.getRight() != null) {
            h2 = curr.getRight().getHeight();
        }
        return h1 - h2;
    }

    /**
     * Helper method that calculates height of a given node
     * @param curr AVLNode that holds current node
     * @return int height of curr
     */
    private int calcHeight(AVLNode<T> curr) {
        int h1 = -1;
        int h2 = -1;
        if (curr.getLeft() != null) {
            h1 = curr.getLeft().getHeight();
        }
        if (curr.getRight() != null) {
            h2 = curr.getRight().getHeight();
        }
        if (h1 > h2) {
            return 1 + h1;
        }
        return 1 + h2;
    }


    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the successor to replace the data,
     * not the predecessor. As a reminder, rotations can occur after removing
     * the successor node.
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Inputted data was null");
        }
        AVLNode<T> dummy = new AVLNode<T>(null);
        root = removeHelper(dummy, root, data);
        size -= 1;
        return dummy.getData();
    }

    /**
     * Recursive helper for remove method
     * @param dummy AVLNode that holds removed data that will later be returned
     * @param curr AVLNode that holds current node
     * @param data T data to be removed
     * @return AVLNode to replace curr
     */
    private AVLNode<T> removeHelper(AVLNode<T> dummy, AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Inputted data was not in tree");
        }
        if (curr.getData().compareTo(data) < 0) {
            curr.setRight(removeHelper(dummy, curr.getRight(), data));
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(removeHelper(dummy, curr.getLeft(), data));
        } else {
            if (curr.getLeft() == null) {
                dummy.setData(curr.getData());
                return curr.getRight();
            } else if (curr.getRight() == null) {
                dummy.setData(curr.getData());
                return curr.getLeft();
            } else {
                dummy.setData(curr.getData());
                curr.setData(findSuccesor(curr).getData());
                AVLNode<T> dummy1 = new AVLNode<T>(null);
                curr.setRight(removeHelper(dummy1, curr.getRight(), curr.getData()));
            }
        }
        curr.setBalanceFactor(calcBalanceFactor(curr));
        curr.setHeight(calcHeight(curr));
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() < 1) {
                return leftRotation(curr);
            }
            curr.setRight(rightRotation(curr.getRight()));
            return leftRotation(curr);
        }
        if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() > -1) {
                return rightRotation(curr);
            }
            curr.setLeft(leftRotation(curr.getLeft()));
            return rightRotation(curr);
        }
        return curr;
    }

    /**
     * Helper method to find the Successor of a given node
     * @param curr AVLNode that holds current node
     * @return Successor of curr
     */
    private AVLNode<T> findSuccesor(AVLNode<T> curr) {
        curr = curr.getRight();
        while (curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Inputted data was null");
        }
        return getHelper(root, data);
    }

    /**
     * Recursive helper method for get method
     * @param curr AVLNode that holds current node
     * @param data T data to be searched for
     * @return T data that was found in tree
     */
    private T getHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Inputted data was not found in tree");
        }
        if (curr.getData().compareTo(data) < 0) {
            return getHelper(curr.getRight(), data);
        } else if (curr.getData().compareTo(data) > 0) {
            return getHelper(curr.getLeft(), data);
        } else {
            return curr.getData();
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Inputted data was null");
        }
        return containsHelper(root, data);
    }

    /**
     * Recrusive helper method for contains method
     * @param curr AVLNode that holds current node
     * @param data T data to be searched for
     * @return boolean representing if data is found in tree
     */
    private boolean containsHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            return false;
        }
        if (curr.getData().compareTo(data) < 0) {
            return containsHelper(curr.getRight(), data);
        } else if (curr.getData().compareTo(data) > 0) {
            return containsHelper(curr.getLeft(), data);
        } else {
            return true;
        }
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     *
     * Your list should not duplicate data, and the data of a branch should be
     * listed in order going from the root to the leaf of that branch.
     *
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     *
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {
        LinkedList<T> deepest = new LinkedList<T>();
        return deepestHelper(root, deepest);

    }

    /**
     * Recursive helper for deepestBranches method
     * @param curr AVLNode that holds current node
     * @param deepest List that holds data from the deepest branches
     * @return completed List "deepest"
     */
    private List<T> deepestHelper(AVLNode<T> curr, List<T> deepest) {
        if (curr != null) {
            deepest.add(curr.getData());
            int h1 = -1;
            int h2 = -1;
            if (curr.getLeft() != null) {
                h1 = curr.getLeft().getHeight();
            }
            if (curr.getRight() != null) {
                h2 = curr.getRight().getHeight();
            }
            if (h1 > h2) {
                deepestHelper(curr.getLeft(), deepest);
            } else if (h2 > h1) {
                deepestHelper(curr.getRight(), deepest);
            } else {
                deepestHelper(curr.getLeft(), deepest);
                deepestHelper(curr.getRight(), deepest);
            }
        }
        return deepest;
    }

    /**
     * Returns a sorted list of data that are within the threshold bounds of
     * data1 and data2. That is, the data should be > data1 and < data2.
     *
     * Should run in worst case O(n), but this is heavily dependent on the
     * threshold data. You should not explore branches of the tree that do not
     * satisfy the threshold.
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * sortedInBetween(7, 14) returns [8, 9, 10, 13]
     * sortedInBetween(3, 8) returns [4, 5, 6, 7]
     * sortedInBetween(8, 8) returns []
     *
     * @throws java.lang.IllegalArgumentException if data1 or data2 are null
     * @param data1 the smaller data in the threshold
     * @param data2 the larger data in the threshold
     * or if data1 > data2
     * @return a sorted list of data that is > data1 and < data2
     */
    public List<T> sortedInBetween(T data1, T data2) {
        if (data1 == null) {
            throw new java.lang.IllegalArgumentException("The first inputted data was null");
        }
        if (data2 == null) {
            throw new java.lang.IllegalArgumentException("The second inputted data was null");
        }
        if (data1.compareTo(data2) > 0) {
            throw new java.lang.IllegalArgumentException("Range of numbers must be an ascending order");
        }
        LinkedList<T> sorted = new LinkedList<T>();
        return sortedHelper(root, sorted, data1, data2);
    }

    /**
     * Recursive helper for sortedInBetween method
     * @param curr AVLNode that holds current node
     * @param sorted List that holds data where data1 < data < data2
     * @param data1 T of lower bound
     * @param data2 T of upper bound
     * @return completed List "sorted"
     */
    private List<T> sortedHelper(AVLNode<T> curr, List<T> sorted, T data1, T data2) {
        if (curr != null) {
            if (curr.getData().compareTo(data1) > 0 && curr.getData().compareTo(data2) < 0) {
                sortedHelper(curr.getLeft(), sorted, data1, data2);
                sorted.add(curr.getData());
                sortedHelper(curr.getRight(), sorted, data1, data2);
            }
            if (curr.getData().compareTo(data1) <= 0) {
                sortedHelper(curr.getRight(), sorted, data1, data2);
            }
            if (curr.getData().compareTo(data2) >= 0) {
                sortedHelper(curr.getLeft(), sorted, data1, data2);
            }
        }
        return sorted;
    }

    /**
     * Clears the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Returns the size of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the AVL tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * Returns the root of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the AVL tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}