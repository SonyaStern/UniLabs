package partlyEnumerationMethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Node {

  private double Ci;
  private double Kpms;
  private double Kmso;
  private Node parent;
  private Node leftChild;
  private Node rightChild;

  Node(double Ci, double Ki, boolean isPMS) {
    this.Ci = Ci;
    if (isPMS) {
      this.Kpms = Ki;
    } else {
      this.Kmso = Ki;
    }
    this.parent = null;
    this.leftChild = null;
    this.rightChild = null;
  }

  double getTok() {
    double Tok = 0;
    if (this.Kpms + this.Kmso != 0) {
      Tok = 0.1 / 365 * ((this.Kpms + this.Kmso) / this.Ci);
    }
    return Tok;
  }

  boolean hasChildren() {
    boolean flag = false;
    if (leftChild != null && rightChild != null) {
      flag = true;
    }
    return flag;
  }
}