package geneticAlgorithm;

import java.util.Random;

public class Generator {

  public static int generateRandomInteger(int min, int max) {
    return min + (int) (Math.random() * ((max - min) + 1));
  }

  public static double generateRandomDoubleWithLimits(int min, int max) {
    return Rounder.round(min + (max - min) * new Random().nextDouble());
  }

}
