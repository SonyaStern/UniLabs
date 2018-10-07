package Math;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;

class Derivative {

  static void wolframTask(String input) {
    final String APP_ID = "4EY46U-KWKU9KEGKE";
    WAEngine waEngine = new WAEngine();
    waEngine.setAppID(APP_ID);
    waEngine.addFormat("plaintext");
    WAQuery query = waEngine.createQuery();
    query.setInput(input);
    try {
      WAQueryResult queryResult = waEngine.performQuery(query);

      if (queryResult.isError()) {
        System.out.println("Query error. Error code: " + queryResult.getErrorCode()
            + ". Error message: " + queryResult.getErrorMessage());
      } else if (!queryResult.isSuccess()) {
        System.out.println("Query was not understood; no results available.");
      } else {
        System.out.println("Successful");
        for (WAPod pod : queryResult.getPods()) {
          if (!pod.isError()) {
            System.out.println(pod.getTitle());
            for (WASubpod subpod : pod.getSubpods()) {
              for (Object element : subpod.getContents()) {
                if (element instanceof WAPlainText) {
                  System.out.println(((WAPlainText) element).getText());
                }
              }
            }
          }
        }
      }
    } catch (WAException e) {
      e.printStackTrace();
    }
  }

  static Expression getDerivative(String function) {
    Variable xVar = new Variable("x");
    Parser parser = new Parser(Parser.STANDARD_FUNCTIONS);
    parser.add(xVar);
    Expression expr = parser.parse(function);
    System.out.println(expr.derivative(xVar));
    return expr.derivative(xVar);
  }


}
