package Avella;

// Java program to demonstrate insert operation
// in binary search tree with parent pointer
class GfG {

    static class Node
    {
        int key;
        Node left, right, parent;
    }

    // A utility function to create a new BST Node
    static Node newNode(int item)
    {
        Node temp = new Node();
        temp.key = item;
        temp.left = null;
        temp.right = null;
        temp.parent = null;
        return temp;
    }

    // A utility function to do inorder traversal of BST
    static void inorder(Node root)
    {
        if (root != null)
        {
            inorder(root.left);
            System.out.print("Node : "+ root.key + " , ");
            if (root.parent == null)
                System.out.println("Parent : NULL");
            else
                System.out.println("Parent : " + root.parent.key);
            inorder(root.right);
        }
    }

    /* A utility function to insert a new Node with
    given key in BST */
    static Node insert(Node node, int key)
    {
        /* If the tree is empty, return a new Node */
        if (node == null) return newNode(key);

        /* Otherwise, recur down the tree */
        if (key < node.key)
        {
            Node lchild = insert(node.left, key);
            node.left = lchild;

            // Set parent of root of left subtree
            lchild.parent = node;
        }
        else if (key > node.key)
        {
            Node rchild = insert(node.right, key);
            node.right = rchild;

            // Set parent of root of right subtree
            rchild.parent = node;
        }

        /* return the (unchanged) Node pointer */
        return node;
    }

    // Driver Program to test above functions
    public static void main(String[] args)
    {
	/* Let us create following BST
			50
		/	 \
		30	 70
		/ \ / \
	20 40 60 80 */
//        Node root = null;
//        root = insert(root, 50);
//        insert(root, 30);
//        insert(root, 20);
//        insert(root, 40);
//        insert(root, 70);
//        insert(root, 60);
//        insert(root, 80);
//
//
//
//        // print iNoder traversal of the BST
//        inorder(root);

        Node ok = new Node();
        Node beyonce = new Node();
        Node taylorSwift = new Node();

        Node temp = insert(ok,15);
        System.out.println(temp.key);
        inorder(ok);
    }










    static Node insertNode(Node root, int key)
    {
        // Create a new Node containing
        // the new element
        Node newnode = newNode(key);

        // Pointer to start traversing from root and
        // traverses downward path to search
        // where the new node to be inserted
        Node x = root;

        // Pointer y maintains the trailing
        // pointer of x
        Node y = null;

        while (x != null) {
            y = x;
            if (key < x.key)
                x = x.left;
            else
                x = x.right;
        }

        // If the root is null i.e the tree is empty
        // The new node is the root node
        if (y == null)
            y = newnode;

            // If the new key is less then the leaf node key
            // Assign the new node to be its left child
        else if (key < y.key)
            y.left = newnode;

            // else assign the new node its right child
        else
            y.right = newnode;

        // Returns the pointer where the
        // new node is inserted
        return y;
    }

}

