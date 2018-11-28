package partlyEnumerationMethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {

  private int payload;
  private Node parent;
  private Node leftChild;
  private Node rightChild;

  public Node(int payload) {
    this.payload = payload;
    this.parent = null;
    this.leftChild = null;
    this.rightChild = null;
  }

  public boolean hasChildren() {
    boolean flag = false;
    if (leftChild != null && rightChild != null) {
      flag = true;
    }
    return flag;
  }
}