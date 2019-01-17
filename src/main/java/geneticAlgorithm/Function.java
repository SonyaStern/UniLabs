package geneticAlgorithm;

import lombok.Getter;

public class Function {

  @Getter
  private int[] functionParameters;

  private int size;

  public Function(int[] functionParameters) {
    this.functionParameters = functionParameters;
    size = functionParameters.length;
  }

  public double getFunctionResult(double variableValue) {
    double functionResult = 0;
    int counter = size;
    for (int i = 0; i < size; i++) {
      //for each function parameter multiply it whatever user wants.
      double multipliedValue = multiply(variableValue, --counter);
      functionResult += functionParameters[i] * multipliedValue;
    }
    return Rounder.round(functionResult);
  }

  private double multiply(double value, int howMany) {
    if (howMany == 0) {
      return 1;
    } else {
      return Math.pow(value, howMany);
    }
  }
}
