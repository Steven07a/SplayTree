/*
 * 
insertion tests.

fred-0
(fred-1)joanie-0
((fred-2)joanie-1)lynn-0
(((fred-3)joanie-2)lynn-1)peter-0
(fred-1)helen-0((joanie-2)lynn-1(peter-2))
(((((((fred-7)helen-6)hope-5)ian-4)joanie-3)john-2)kelly-1((lynn-3)miki-2))nichole-0(peter-1(tom-2))


search tests.
Found.
(((((((fred-7)helen-6)hope-5)ian-4)joanie-3)john-2)kelly-1((lynn-3)miki-2))nichole-0(peter-1(tom-2))
Found.
((fred-2)helen-1)hope-0(((ian-3(joanie-4))john-2(kelly-3((lynn-5)miki-4)))nichole-1(peter-2(tom-3)))
Found.
(((fred-3)helen-2)hope-1((ian-3(joanie-4))john-2(kelly-3((lynn-5)miki-4))))nichole-0(peter-1(tom-2))
Not in the tree.
((((fred-4)helen-3)hope-2(ian-3(joanie-4)))john-1(kelly-2))lynn-0((miki-2)nichole-1(peter-2(tom-3)))
Not in the tree.
((((fred-4)helen-3)hope-2)ian-1)joanie-0(john-1((kelly-3)lynn-2((miki-4)nichole-3(peter-4(tom-5)))))
Not in the tree.
(((fred-3)helen-2)hope-1)ian-0(joanie-1(john-2((kelly-4)lynn-3((miki-5)nichole-4(peter-5(tom-6))))))

*/


public class Test0 {

	public static void main(String[] args) {
		SplayBSTXtra t = new SplayBSTXtra();
		System.out.println("insertion tests.");
		System.out.println(t);
		t.insert("fred");
		System.out.println(t);
		t.insert("apple");
		System.out.println(t);
		System.out.println(t.leafCt());
		t.insert("joanie");
		System.out.println(t);
		System.out.println("leaf count: " + t.leafCt());
		System.out.println("tree height " + t.height());
		// System.out.println(t);
		t.insert("peter");
		System.out.println(t);
		System.out.println("leaf count: " + t.leafCt());
		System.out.println("tree height " + t.height());
		// System.out.println(t);
		// t.insert("helen");
		// System.out.println(t);
		// t.insert("joanie"); t.insert("hope"); t.insert("ian"); t.insert("tom");
		// t.insert("miki"); t.insert("john"); t.insert("kelly"); t.insert("nichole");
		// System.out.println(t);
		// System.out.println();
		// System.out.println();
		// System.out.println("search tests.");
		// if (t.search("nichole") == null)
		// 	System.out.println("Not in the tree.");
		// else
		// 	System.out.println("Found.");
		// System.out.println(t);
		// if (t.search("hope") == null)
		// 	System.out.println("Not in the tree.");
		// else
		// 	System.out.println("Found.");
		// System.out.println(t);
		// if (t.search("nichole") == null)
		// 	System.out.println("Not in the tree.");
		// else
		// 	System.out.println("Found.");
		// System.out.println(t);
		// if (t.search("larry") == null)
		// 	System.out.println("Not in the tree.");
		// else
		// 	System.out.println("Found.");
		// System.out.println(t);
		// if (t.search("jack") == null)
		// 	System.out.println("Not in the tree.");
		// else
		// 	System.out.println("Found.");
		// System.out.println(t);
		// if (t.search("jack") == null)
		// 	System.out.println("Not in the tree.");
		// else
		// 	System.out.println("Found.");
		// System.out.println(t);
	}
}

class SplayBSTXtra extends SplayBST {

	public SplayBSTXtra() {
		super();
	}

	public StringNode getRoot() {
		return root;
	}

	// for output purposes -- override Object version
	public String toString() {
		return toString(root, 0);
	}

	private static String toString(StringNode l, int x) {
		String s = "";
		if (l == null)
			; // nothing to output
		else {
			if (l.getLeft() != null) // don't output empty subtrees
				s = '(' + toString(l.getLeft(), x + 1) + ')';
			s = s + l.getString() + "-" + x;
			if (l.getRight() != null) // don't output empty subtrees
				s = s + '(' + toString(l.getRight(), x + 1) + ')';
		}
		return s;
	}

}