package geneticAlgorithm;

public class Rounder {

  public static double round(double value) {

    long factor = (long) Math.pow(10, 3);
    value = value * factor;
    return (double) Math.round(value) / factor;
  }

}
