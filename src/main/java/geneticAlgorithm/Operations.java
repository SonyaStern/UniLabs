package geneticAlgorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Operations {

  private final int minRange;

  private final int maxRange;


  /**
   * @param doubleNumber change into bits representation
   * @return int array with double variable bits representation
   */
  public static int[] changeIntoBits(double doubleNumber) {
    long longBits = Double.doubleToLongBits(doubleNumber);
    char[] longBitLetters = Long.toBinaryString(longBits).toCharArray();
    int[] bits = new int[longBitLetters.length];

    for (int i = 0; i < longBitLetters.length; i++) {
      bits[i] = Character.getNumericValue(longBitLetters[i]);
    }
    return bits;
  }

  /**
   * @param bits that represents chromosome value
   * @return a double value if a chromosome
   */
  public static double changeBitsIntoDoubleValue(int[] bits) {
    StringBuilder bitsString = new StringBuilder();
    for (int bit : bits) {
      bitsString.append(bit);
    }
    return Rounder
        .round(Double.longBitsToDouble(new BigInteger(bitsString.toString(), 2).longValue()));
  }

  public List<Chromosom> generatePopulation(int amount) {
    double[] randomDoubles = new double[amount];
    for (int i = 0; i < amount; i++) {
      double randomDouble = Generator.generateRandomDoubleWithLimits(minRange, maxRange);
      //skip when random value is zero
      if (randomDouble == 0) {
        i--;
        continue;
      }
      randomDoubles[i] = Rounder.round(randomDouble);
    }
    List<Chromosom> chromosoms = new ArrayList<>();
    for (double number : randomDoubles) {
      chromosoms.add(new Chromosom(changeIntoBits(number)));
    }
    return chromosoms;
  }

}
