package aStar;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Node {

  private double Ci;
  private double Kpms;
  private double Kmso;
  private Node parent;
  private ArrayList<Node> children = new ArrayList<>();

  Node(double Ci, double Ki, boolean isPMS) {
    this.Ci = Ci;
    if (isPMS) {
      this.Kpms = Ki;
    } else {
      this.Kmso = Ki;
    }
    this.parent = null;
  }

  void setChildrenNode(Node node) {
    this.children.add(node);
  }

  double getTok() {
    double Tok = 0;
    if (this.Kpms + this.Kmso != 0) {
      Tok = 0.1 / 365 * ((this.Kpms + this.Kmso) / this.Ci);
    }
    return Tok;
  }
}
