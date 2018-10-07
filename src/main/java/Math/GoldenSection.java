package Math;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

class GoldenSection {

  static final double EPSILON = 0.001;
  private static final double PHI = (1 + Math.sqrt(5)) / 2;

  private static double function(String function, double x) {

    Function function_ = new Function(function);
    Expression expression = new Expression("f(" + x + ")", function_);
    return function_.calculate(x);
  }

  static double find(String function, double a, double b, double e) {
    double x1, x2;
    do {
      x1 = b - (b - a) / PHI;
      x2 = a + (b - a) / PHI;
      if (function(function, x1) >= function(function, x2)) {
        a = x1;
      } else {
        b = x2;
      }
    } while (!(Math.abs(b - a) < e));
    return (a + b) / 2;
  }
}
