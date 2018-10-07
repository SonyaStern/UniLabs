package Math;

import edu.hws.jcm.data.Expression;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import lombok.Cleanup;

public class App {

  private static String[] reader(String path) throws IOException {
    @Cleanup InputStream in_stream = new FileInputStream(path);
    StringBuilder builder = new StringBuilder();
    int s;
    while ((s = in_stream.read()) != -1) {
      builder.append((char) s);
    }
    System.out.println(builder);
    String[] text = builder.toString().split(" ");
    for (String aText : text) {
      System.out.println(aText);
    }
    return text;
  }

  public static void main(String[] args) throws IOException {
    String[] text = reader("src\\main\\resources\\function.txt");
    String function = text[0];
    double min = Double.parseDouble(text[1]);
    double max = Double.parseDouble(text[2]);
    Expression functionForWolfram = Derivative.getDerivative(function);

    System.out.println("Golden section method");
    System.out.println(GoldenSection
        .find("f(x) + " + function, max, min, GoldenSection.EPSILON));
    System.out.println(GoldenSection
        .find("f(x) + " + function, min, max, GoldenSection.EPSILON));

    System.out.println("Math.Derivative method");
    Derivative.wolframTask("extrema " + functionForWolfram + " over [" + min + "," + max + "]");
  }
}
