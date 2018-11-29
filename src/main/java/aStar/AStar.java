package aStar;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AStar {

  private static Node root = new Node(0, 0, false);
  private static double minValue = 100;
  private static Node minNode;
  private static int minIndex;
  private static int width = 5;

  public static void main(String[] args) {

    ArrayList<Node> rootList = new ArrayList<>();
    rootList.add(new Node(150, 821250, true));
    rootList.add(new Node(250, 1186250, true));
    rootList.add(new Node(400, 2628000, true));
//    ArrayList<Node> rootList = new ArrayList<>();
//    rootList.add(new Node(200, 2500000, true));
//    rootList.add(new Node(150, 4200000, true));
//    rootList.add(new Node(250, 2000000, true));
    addRootChildren(rootList);
    combineRootChildren();
    for (Node child : root.getChildren()) {
      System.out.print(new DecimalFormat("#0.00").format(child.getTok()) + " ");
    }
    findMinNode();

    ArrayList<Node> childrenList = new ArrayList<>();
    childrenList.add(new Node(300, 5475000, false));
    childrenList.add(new Node(350, 8942500, false));
    childrenList.add(new Node(200, 1825000, false));
//    ArrayList<Node> childrenList = new ArrayList<>();
//    childrenList.add(new Node(200, 3500000, false));
//    childrenList.add(new Node(130, 5000000, false));
//    childrenList.add(new Node(250, 4500000, false));
    addNotRootChildren(childrenList);
    combineNotRootChildren();
    System.out.println();
    printSpace(width * minIndex);
    for (Node child : minNode.getChildren()) {
      System.out.print(new DecimalFormat("#0.00").format(child.getTok()) + " ");
    }
    System.out.println();
    findMin();
  }

  private static void printSpace(int n) {
    for (int i = 0; i < n; ++i) {
      System.out.print(" ");
    }
  }

  private static void combineRootChildren() {
    ArrayList<Node> rootChildren = root.getChildren();
    for (int i = 0; i < 2; i++) {
      root.setChildrenNode(new Node(
          rootChildren.get(i).getCi() +
              rootChildren.get(i + 1).getCi(),
          rootChildren.get(i).getKpms() +
              rootChildren.get(i + 1).getKpms(),
          true));
    }
    for (int j = 0; j < 1; j++) {
      root.setChildrenNode(new Node(
          rootChildren.get(j).getCi() +
              rootChildren.get(j + 2).getCi(),
          rootChildren.get(j).getKpms() +
              rootChildren.get(j + 2).getKpms(),
          true));
    }
    root.setChildrenNode(new Node(
        rootChildren.get(0).getCi() +
            rootChildren.get(1).getCi() +
            rootChildren.get(2).getCi(),
        rootChildren.get(0).getKpms() +
            rootChildren.get(1).getKpms() +
            rootChildren.get(2).getKpms(),
        true));
  }

  private static void combineNotRootChildren() {
    ArrayList<Node> notRootChildren = minNode.getChildren();
    for (int i = 0; i < 2; i++) {
      minNode.setChildrenNode(new Node(
          notRootChildren.get(i).getCi() +
              notRootChildren.get(i + 1).getCi(),
          notRootChildren.get(i).getKmso() +
              notRootChildren.get(i + 1).getKmso(),
          false));
    }
    for (int j = 0; j < 1; j++) {
      minNode.setChildrenNode(new Node(
          notRootChildren.get(j).getCi() +
              notRootChildren.get(j + 2).getCi(),
          notRootChildren.get(j).getKmso() +
              notRootChildren.get(j + 2).getKmso(),
          false));
    }
    minNode.setChildrenNode(new Node(
        notRootChildren.get(0).getCi() +
            notRootChildren.get(1).getCi() +
            notRootChildren.get(2).getCi(),
        notRootChildren.get(0).getKmso() +
            notRootChildren.get(1).getKmso() +
            notRootChildren.get(2).getKmso(),
        true));
  }


  private static void findMin() {
    for (Node node : minNode.getChildren()) {
      if ((node.getKpms() + node.getKmso()) <= 12_000_000 && node.getKpms() >= 3_000_000
          && node.getKmso() >= 4_500_000) {
        if (minValue > node.getTok()) {
          minValue = node.getTok();
        }
      }
    }
    System.out.println(new DecimalFormat("#0.00").format(minValue));
  }

  private static void findMinNode() {
    double minRootValue = 100;
    for (Node node : root.getChildren()) {
      if (node.getKpms() <= 12_000_000 && node.getKpms() >= 3_000_000) {
        if (minRootValue > node.getTok()) {
          minRootValue = node.getTok();
          minNode = node;
        }
      }
    }
    minIndex = root.getChildren().indexOf(minNode);
  }

  private static void addRootChildren(ArrayList<Node> rootList) {
    root.setChildren(rootList);
  }

  private static void addNotRootChildren(ArrayList<Node> childList) {
    minNode.setChildren(childList);
    for (Node child : childList) {
      child.setParent(minNode);
      child.setKpms(minNode.getKpms());
      child.setCi(minNode.getCi() + child.getCi());
    }
  }
}
