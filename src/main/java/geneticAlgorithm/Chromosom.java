package geneticAlgorithm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chromosom implements Comparable {

  private int[] chromBits;

  public Chromosom(int[] chromBits) {
    this.chromBits = chromBits;
  }

  public double getChromosomValue() {
    return Rounder.round(Operations.changeBitsIntoDoubleValue(chromBits));
  }

  public int compareTo(Object chromosom) {
    return Double.compare(this.getChromosomValue(), ((Chromosom) chromosom).getChromosomValue());
  }
}
