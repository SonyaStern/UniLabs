package partlyEnumerationMethod;

public class BinaryTree {

  private Node root = new Node(0);

//  public BinaryTree() {
//    this.root = null;
//  }

  public static void main(String[] args) {
    BinaryTree bt = new BinaryTree();
    bt.add(2); //Root
    bt.add(4); //Right
    bt.add(4); //Right Left
//    bt.add(2); //Left
//    bt.add(2); //Left Left
//    bt.add(1); //Left Left Left
  }

  public void add(int payload) {

//    Node leftCurrNode = new Node(payload);
//    Node rightCurrNode = new Node(0);

    Node testLeftNode = root;
    Node testRightNode = root;

    if (testLeftNode.getLeftChild() != null) {
      while (testLeftNode.getLeftChild() != null) {
        testLeftNode = findLeftChild(testLeftNode);
      }
      addPart(testLeftNode, new Node(payload), new Node(0));
      checkRight(testLeftNode.getParent(), payload);
    }

    if (testRightNode.getLeftChild() != null) {
      while (testRightNode.getLeftChild() != null) {
        testRightNode = findLeftChild(testRightNode);
      }
      addPart(testRightNode, new Node(payload), new Node(0));
      checkLeft(testRightNode.getParent(), payload);
    }

    if (!root.hasChildren()) {
      addPart(root, new Node(payload), new Node(0));
    }

  }

  public void addPart(Node curRoot, Node leftNode, Node rightNode) {

    leftNode.setParent(curRoot);
    rightNode.setParent(curRoot);

    sum(leftNode);
    sum(rightNode);
    curRoot.setLeftChild(leftNode);
    curRoot.setRightChild(rightNode);

    System.out
        .println(curRoot.getPayload() + " " + leftNode.getPayload() + " " + rightNode.getPayload());
  }

  public void checkRight(Node node, int payload) {
    if (node.getRightChild() != null) {
      while (node.getRightChild() != null) {
        node = findRightChild(node);
      }
      addPart(node, new Node(payload), new Node(0));
    }
  }

  public void checkLeft(Node node, int payload) {
    if (node.getLeftChild() != null) {
      while (node.getLeftChild() != null) {
        node = findLeftChild(node);
      }
      addPart(node, new Node(payload), new Node(0));
    }
  }

  public Node findRightChild(Node node) {
    return node.getRightChild();
  }

  public Node findLeftChild(Node node) {
    return node.getLeftChild();
  }

  public void sum(Node node) {
    node.setPayload(node.getPayload() + node.getParent().getPayload());
  }

  public void display() {
    if (this.root == null) {
      System.out.println("Empty Tree");
    }
  }
}