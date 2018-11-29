package partlyEnumerationMethod;

import java.text.DecimalFormat;

public class BinaryTree {

  private static Node root = new Node(0, 0, false);
  private static double minValue = 100;
  private static int width = 8;

  public static void main(String[] args) {

    BinaryTree bt = new BinaryTree();
    bt.add(150, 3_832_500, true);
    bt.add(300, 6_570_000, false);
    bt.add(350, 4_215_750, false);
    bt.add(200, 4_015_000, false);
    bt.add(250, 4_106_250, true);
    bt.add(400, 5_840_000, true);
    printTree(root, 1);
    findMin(root);

    System.out.println(new DecimalFormat("#0.00").format(minValue));

  }

  private static void printSpace(int n) {
    for (int i = 0; i < n; ++i) {
      System.out.print(" ");
    }
  }

  private static void printTree(Node node, int level) {
    if (node == null) {
      return;
    }
    printTree(node.getRightChild(), level + 1);
    printSpace(level * width);
    if (node.getCi() == 0) {
      System.out.println("0");
    } else {
      System.out.println(new DecimalFormat("#0.00").format(node.getTok()));
    }
    printTree(node.getLeftChild(), level + 1);
  }

  private static void findMin(Node node) {

    if (node != null) {
      findMin(node.getRightChild());

      if (node.getKpms() >= 3_000_000 && node.getKmso() >= 4_500_000) {
        if (minValue > node.getTok()) {
          minValue = node.getTok();
        }
      }
      findMin(node.getLeftChild());
    }
  }

  private void add(double Ci, double Ki, boolean isPMS) {
    postOrder(root, Ci, Ki, isPMS);
  }

  private void postOrder(Node node, double Ci, double Ki, boolean isPMS) {
    if (!node.hasChildren()) {
      addPart(node, new Node(Ci, Ki, isPMS), new Node(0, 0, false));
    } else {
      postOrder(node.getLeftChild(), Ci, Ki, isPMS);
      postOrder(node.getRightChild(), Ci, Ki, isPMS);
    }
  }

  private void addPart(Node curRoot, Node leftNode, Node rightNode) {
    if (curRoot == root) {
      justAdd(curRoot, leftNode, rightNode);
    } else {
      if ((curRoot.getKmso() + curRoot.getKpms()) < 12_000_000 && curRoot.getTok() < 7) {
        justAdd(curRoot, leftNode, rightNode);
      }
    }
  }

  private void justAdd(Node curRoot, Node leftNode, Node rightNode) {
    leftNode.setParent(curRoot);
    rightNode.setParent(curRoot);

    sum(leftNode);
    sum(rightNode);
    curRoot.setLeftChild(leftNode);
    curRoot.setRightChild(rightNode);
  }

  private void sum(Node node) {
    node.setCi(node.getCi() + node.getParent().getCi());
    node.setKpms(node.getKpms() + node.getParent().getKpms());
    node.setKmso(node.getKmso() + node.getParent().getKmso());
  }
}