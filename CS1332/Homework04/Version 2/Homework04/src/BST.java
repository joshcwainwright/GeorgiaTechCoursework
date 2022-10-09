import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Collection;

/**
 * Your implementation of a BST.
 *
 * @author Joshua Wainwright
 * @version 2.0
 * @userid jwainwright3
 * @GTID 903584588
 *
 * Collaborators: no collaborators
 *
 * Resources: vistool, ppt slides
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     * <p>
     * This constructor should initialize an empty BST.
     * <p>
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     * <p>
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     * <p>
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        for (T dataItem : data) {
            add(dataItem);
        }
    }

    /**
     * Adds the data to the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * The data becomes a leaf in the tree.
     * <p>
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        root = addHelper(data, root);
    }

    /**
     * Rescursive helper method for add
     * @param data T data to be added (passed in from add method)
     * @param node BSTNode that holds current node
     * @return node being assigned to original node
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> node) {
        if (node == null) {
            size += 1;
            return new BSTNode<T>(data);
        }
        if (data.compareTo(node.getData()) == -1) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) == 1) {
            node.setRight(addHelper(data, node.getRight()));
        }
        return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     * <p>
     * This must be done recursively.
     * <p>
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     * <p>
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        if (root != null && data.compareTo(root.getData()) == 0) {
            T r = root.getData();
            size -= 1;
            root = recover(data, root);
            return r;
        }
        return removeHelper(data, root);
    }

    /**
     * Recursive helper method for remove
     * @param data T data that is being removed (passed in from remove method)
     * @param node BSTNode that holds current node
     * @return T data that was removed
     */
    private T removeHelper(T data, BSTNode<T> node) {
        if (node != null) {
            if (data.compareTo(node.getData()) == -1 && node.getLeft() != null) {
                if (data.compareTo(node.getLeft().getData()) == 0) {
                    T r = node.getLeft().getData();
                    node.setLeft(recover(node.getLeft().getData(), node.getLeft()));
                    size -= 1;
                    return r;
                } else {
                    return removeHelper(data, node.getLeft());
                }

            } else if (data.compareTo(node.getData()) == 1 && node.getRight() != null) {
                if (data.compareTo(node.getRight().getData()) == 0) {
                    T r = node.getRight().getData();
                    node.setRight(recover(node.getRight().getData(), node.getRight()));
                    size -= 1;
                    return r;
                } else {
                    return removeHelper(data, node.getRight());
                }
            }
        }
        throw new java.util.NoSuchElementException("data was not found in tree");
    }

    /**
     * Recursive helper method for remove Helper method that rebuilds a BST tree after a removal
     * @param data T data to be removed
     * @param node BSTNode that holds current node
     * @return appropriate BSTNode replacement after a removal
     */
    private BSTNode<T> recover(T data, BSTNode<T> node) {
        if (node != null) {
            if (data.compareTo(node.getData()) == 0) {
                if (node.getLeft() == null && node.getRight() == null) {
                    return null;
                } else if (node.getLeft() == null) {
                    return node.getRight();
                } else if (node.getRight() == null) {
                    return node.getLeft();
                } else {
                    node.setData(findNext(node.getRight()).getData());
                    node.setRight(recover(node.getData(), node.getRight()));
                }
            } else if (data.compareTo(node.getData()) == -1) {
                node.setLeft(recover(data, node.getLeft()));
            } else if (data.compareTo(node.getData()) == 1) {
                node.setRight(recover(data, node.getRight()));
            }
        }
        return node;
    }



    /**
     * Helper method that finds the successor of a given node
     * put in the right child of value
     * @param node BSTNode that holds the right child of node being removed in remove method
     * @return BSTNode successor node
     */
    private BSTNode<T> findNext(BSTNode<T> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return findNext(node.getLeft());
    }


    /**
     * Returns the data from the tree matching the given parameter.
     * <p>
     * This must be done recursively.
     * <p>
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        return getHelper(data, root);

    }

    /**
     * Recursive helper method of get
     * @param data T data that is being searched for (passed in from get method)
     * @param node BSTNode that holds current node
     * @return T data being retrieved
     */
    private T getHelper(T data, BSTNode<T> node) {
        if (node != null) {
            if (data.compareTo(node.getData()) == 0) {
                return node.getData();
            }
            if (data.compareTo(node.getData()) == -1) {
                return getHelper(data, node.getLeft());
            } else if (data.compareTo(node.getData()) == 1) {
                return getHelper(data, node.getRight());
            }
        }
        throw new java.util.NoSuchElementException("data is not in tree");
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("data cannot be null");
        }
        return containsHelper(data, root);
    }

    /**
     * Resursive helper method for contains
     * @param data T data that is being searched for
     * @param node BSTNode that holds the current node
     * @return boolean that is true if data is in the BST
     */
    private boolean containsHelper(T data, BSTNode<T> node) {
        if (node != null) {
            if (data.compareTo(node.getData()) == 0) {
                return true;
            }
            if (data.compareTo(node.getData()) == -1) {
                return containsHelper(data, node.getLeft());
            } else if (data.compareTo(node.getData()) == 1) {
                return containsHelper(data, node.getRight());
            }
        }
        return false;
    }

    /**
     * Generate a pre-order traversal of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> pre = new LinkedList<T>();
        preorderHelper(root, pre);
        return pre;
    }

    /**
     * Recursive helper method for preorder method
     * @param pre List that records traversed data
     * @param node BSTNode that holds current node
     */
    private void preorderHelper(BSTNode<T> node, List<T> pre) {
        if (node != null) {
            pre.add(node.getData());
            preorderHelper(node.getLeft(), pre);
            preorderHelper(node.getRight(), pre);
        }
        return;
    }

    /**
     * Generate an in-order traversal of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> in = new LinkedList<T>();
        inorderHelper(root, in);
        return in;
    }

    /**
     * Recursive helper method for inorder method
     * @param node BSTNode that holds current node
     * @param in List that records traversed data
     */
    private void inorderHelper(BSTNode<T> node, List<T> in) {
        if (node != null) {
            inorderHelper(node.getLeft(), in);
            in.add(node.getData());
            inorderHelper(node.getRight(), in);
        }
        return;
    }

    /**
     * Generate a post-order traversal of the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> post = new LinkedList<T>();
        postorderHelper(root, post);
        return post;
    }

    /**
     * @param post list that records traversed data
     * @param node BSTNode that holds current node
     */
    private void postorderHelper(BSTNode<T> node, List<T> post) {
        if (node != null) {
            postorderHelper(node.getLeft(), post);
            postorderHelper(node.getRight(), post);
            post.add(node.getData());
        }
        return;
    }


    /**
     * Generate a level-order traversal of the tree.
     * <p>
     * This does not need to be done recursively.
     * <p>
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     * <p>
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> level = new LinkedList<T>();
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> curr = (queue.remove());
            if (curr != null) {
                level.add(curr.getData());
                queue.add(curr.getLeft());
                queue.add(curr.getRight());
            }
        }
        return level;
    }






    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        }
        return heightHelper(root);
    }

    /**
     * Recursive helper method for height method
     * @param node BSTNode that is used to find height
     * @return height of node
     */
    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }
        return max(heightHelper(node.getLeft()) + 1, heightHelper(node.getRight()) + 1);
    }

    /**
     * method that returns the max value of two integers
     * @param a int 1
     * @param b int 2
     * @return int with max value
     */
    private int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k).
     *
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     * @throws java.lang.IllegalArgumentException if k > n, the number of data
     *                                            in the BST
     */
    public List<T> kLargest(int k) {
        if (k > size) {
            throw new java.lang.IllegalArgumentException("only able to retrieve the k largest data if k < size");
        }
        List<T> large = new LinkedList<T>();
        kLargestHelper(root, large, k);
        return large;

    }

    /**
     * Resursive helper method for KLargest
     * @param node BSTNode that holds current node
     * @param large List the records the largest data
     * @param k integer of how many of largest data should be added to list
     */
    private void kLargestHelper(BSTNode<T> node, List<T> large, int k) {
        if (node != null) {
            kLargestHelper(node.getRight(), large, k);
            if (large.size() < k) {
                large.add(0, node.getData());
            }
            kLargestHelper(node.getLeft(), large, k);
        }
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
