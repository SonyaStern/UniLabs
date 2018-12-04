package fuzzyLogic;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

/**
 * Test parsing an FCL file
 *
 * @author pcingola@users.sourceforge.net
 */
public class TestTipper {

  public static void getChartAndValue(double temperature) {
    // Load from 'FCL' file
    String fileName = "src\\main\\resources\\data.fcl";

    FIS fis = FIS.load(fileName, true);

    // Error while loading?
    if (fis == null) {
      System.err.println("Can't load file: '" + fileName + "'");
      return;
    }

    FunctionBlock fb = fis.getFunctionBlock(null);

    // Set inputs
    fb.setVariable("temperature", temperature);

    // Evaluate
    fb.evaluate();

    Variable velocity = fb.getVariable("velocity");

    velocity.defuzzify();

    // Show
//    JFuzzyChart.get().chart(fb);

    // Show output variable's chart
//    JFuzzyChart.get().chart(velocity, velocity.getDefuzzifier(), true);

    System.out.println(velocity.getLatestDefuzzifiedValue());
  }

  public static void main(String[] args) throws Exception {

    for (int i = 0; i < 5; i++) {
      getChartAndValue(16.5 + 0.3 * i);
    }
  }
}