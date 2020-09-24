/*
 * Author: Steven Herrera
 * Class: CS 282 
 * Meeting Time: M,W 3:30 - 4:45pm
 * Assignment #1
 * Project: MagicSquare
 * Purpose: To test three different algorithims which are meant to find a Magic Square 
 *          which is a square where all rows columns and diagnols add up to the same number.
 * 
 * Date turned in: 9/1/20
 * Notes: 
 * need to check situations like lr rotation and rl rotation and search.
 */

class StringNode {
    private String word;
    private StringNode left, right;

    // The only constructor you will need
    public StringNode(String w) {
        word = w;
    }
    public String getString() {
        return word;
    }
    public StringNode getLeft() {
        return left;
    }
    public void setLeft(StringNode pt) {
        left = pt;
    }
    public StringNode getRight() {
        return right;
    }
    public void setRight(StringNode pt) {
        right = pt;
    }
} // StringNode

// So that a String can change. There is nothing you need to add
// to this class
class WrapString {
// Yes, I am allowing (and encouraging) direct access to the String
    public String string;
    public WrapString(String str) {
        this.string = str;  
    }
}

class SplayBST {
    
    // member variable pointing to the root of the splay tree
    // It really should be private but I need access to it for the test program 
    StringNode root;
    
    // default constructor
    public SplayBST() { 
        root = null;
    }

    // copy constructor   
    // Be sure to make a copy of the entire tree   
    // Do not make two pointers point to the same tree
    public SplayBST(SplayBST t) {

    }

    // like last time
    public static String myName() {
        return "Steven Herrera";
    }

    // This is the driver method. You should also check for and perform
    // a final zig here
    // You will also have to write the 2-parameter recursive insert method
    public void insert(String s) {
        root = insert(root,s);
    }

    private StringNode insert(StringNode rt, String s) {
        // base case
        if(rt == null) {
            rt = new StringNode(s);
            rt.setLeft(null);
            rt.setRight(null);
        } else {
            if(s.compareTo(rt.getString()) < 0) {
                rt.setLeft(insert(rt.getLeft(), s));
            } else if (s.compareTo(rt.getString()) > 0) {
                rt.setRight(insert(rt.getRight(), s));
            }
        }
        rt = splay(rt, s);
        return rt;
    }

    // if s is not in the tree, splay the last node visited
    // final zig, if needed, is done here
    // Return null if the string is not found 
    public StringNode search(String s) {
        WrapString temp = new WrapString(s);
        return search(temp, root);
    }

    // recursive search method
    // if str not in the tree strbacktracks with value of last node visited
    public StringNode search(WrapString str, StringNode t) {
        // base case
        if(root == null) {
            return null;
        } else {
            if(str.string.compareTo(t.getString()) < 0) {
                t = search(str, t.getLeft());
            } else if (str.string.compareTo(t.getString()) > 0) {
                t = search(str,t.getRight());
            }
            return t;
        }
    }
    public static StringNode rotateLeft(StringNode t) {
        StringNode tempNode = t.getRight();
        t.setRight(tempNode.getLeft());
        tempNode.setLeft(t);
        return tempNode;
    }
    public static StringNode rotateRight(StringNode t) {
        StringNode tempNode = t.getLeft();
        t.setLeft(tempNode.getRight());
        tempNode.setRight(t);
        return tempNode;
    }
    
    // // How many leaves in the splay tree?
    public int leafCt() {
        return leafCt(root);
    }
    private int leafCt(StringNode root) {
        if(root != null) {
            if(root.getLeft() == null && root.getRight() == null) {
                return 1;
            } else {
                return (leafCt(root.getLeft()) + leafCt(root.getRight()));
            }
        }
        return 0;
    }

    // What is the height the splay tree?
    public int height() {
        return height(root);
    }
    private int height(StringNode rt) {
        int h = 0 , leftHeight = 0, rightHeight = 0;
        //base case
        if(rt == null) {
            h = 0;
        } else {
            if(rt.getLeft() == null && rt.getRight() == null) {
                h = 0;
            } else {
                leftHeight = height(rt.getLeft());
                rightHeight = height(rt.getRight());
                if(leftHeight == rightHeight) {
                    h = 1 + leftHeight;
                } else if (leftHeight > rightHeight) {
                    h = 1 + leftHeight;
                } else {
                    h = 1 + rightHeight;
                }
            }
        }

        return h;
    }

    private StringNode splay(StringNode rt, String item) {
        if(rt != null) {
            if(isGrandparent(rt, item)) {
                String rotations = getRotations(rt, item);
                if (rotations.compareTo("ll") == 0) {
                    rt = rotateRight(rt);
                    rt = rotateRight(rt);
                } else if (rotations.compareTo("rr") == 0) {
                    rt = rotateLeft(rt);
                    rt = rotateLeft(rt);
                } else if (rotations.compareTo("rl") == 0) {
                    rt.setRight(rotateRight(rt.getRight()));
                    rt = rotateLeft(rt);
                } else if (rotations.compareTo("lr") == 0) {
                    rt.setLeft(rotateLeft(rt.getLeft()));
                    rt = rotateRight(rt);
                }
            //only runs in the event that we are at the root and the item we need to splay is our child
            } else if(root == rt) {
                if(rt.getLeft() != null && rt.getLeft().getString() == item) {
                    rt = rotateRight(rt);
                } else if(rt.getRight() != null && rt.getRight().getString() == item) {
                    rt = rotateLeft(rt);
                }
            }
        }
        return rt;
    }

    private boolean isGrandparent(StringNode rt, String item) {
        boolean grandparent = false;
        if(nodeDistance(rt, item) == 2) {
            grandparent = true;
        } 
        return grandparent;
    }

    private int nodeDistance(StringNode rt, String item) {
        int distance = 0;
        if(rt != null) {
            if(rt.getString() != item) {
                //int num = rt.getString().compareTo(item); 
                if(rt.getString().compareTo(item) > 0) {
                    distance = 1 + nodeDistance(rt.getLeft(), item);
                } else {
                    distance = 1 + nodeDistance(rt.getRight(), item);
                }
            }
        }
        return distance;
    }

    private String getRotations(StringNode rt, String item) {
        String rotate = "";
        if(rt != null) {
            if(rt.getString() != item) {
                if(rt.getString().compareTo(item) > 0) {
                    rotate = "l" + getRotations(rt.getLeft(), item);
                } else {
                    rotate = "r" + getRotations(rt.getRight(), item);
                }
            }
        }
        return rotate;
    }

    // // How many nodes have exactly 1 non-null children
    // public int stickCt() {

    // }
}