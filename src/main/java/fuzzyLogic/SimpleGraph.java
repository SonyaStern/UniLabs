package fuzzyLogic;

import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleGraph extends JFrame {

  private final int WIDTH = 330;
  private final int HEIGHT = 330;
  private Container drawable;
  private JPanel canvas;

  public SimpleGraph(double[] someData) {
    super("SimpleGraph");
    drawable = getContentPane();
    canvas = new GraphCanvas(someData);
    drawable.add(canvas);
    setSize(WIDTH, HEIGHT);
  }

  public static void main(String[] args) {
    double[] d = {60, 62,
        80, 69,
        30, 50,
        100, 200,
        110, 180};
    Frame f = new SimpleGraph(d);
    f.setVisible(true);
  }

  public class GraphCanvas extends JPanel {

    private double[] data;
    private int points;
    private double[] XData;
    private double[] YData;

    public GraphCanvas(double[] someData) {
      super();
      data = someData;
      points = data.length / 2;
      XData = new double[points];
      YData = new double[points];
      for (int i = 0; i < points; i++) {
        XData[i] = data[i * 2];
        YData[i] = data[i * 2 + 1];
      }
    }

    public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      g2.drawLine(10, 260, 10, 10);
      g2.drawString(("50"), 10, 10);
      g2.drawLine(10, 260, 260, 260);
      g2.drawString(("750"), 260, 260);
      for (int i = 0; i < points - 1; i++) {
        int x0 = (int) (XData[i] + 0.5);
        int x1 = (int) (XData[i + 1] + 0.5);
        int y0 = (int) (YData[i] + 0.5);
        int y1 = (int) (YData[i + 1] + 0.5);
        g2.drawLine(x0, y0, x1, y1);
//        if (i == 0) {
//          g2.drawString(("" + x0 + ", " + y0), x0 - 20, y0 + 10);
//        }
//        if (i == points - 2) {
//          g2.drawString(("" + x1 + ", " + y1), x1, y1);
//        }
      }
    }
  }
}