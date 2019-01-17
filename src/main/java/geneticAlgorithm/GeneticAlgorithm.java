package geneticAlgorithm;

import java.util.List;

public class GeneticAlgorithm {

  public static void main(String[] args) {

    int lowerLimitNumber = -10;
    int upperLimitNumber = 53;
    Operations operations = new Operations(lowerLimitNumber, upperLimitNumber);

    int[] userInputParameters = {1, -46, 10, 25};

    //generate starting population
    List<Chromosom> startingPopulation = operations
        .generatePopulation(GeneticEvolution.population_size);

    //get resolution
    GeneticEvolution geneticEvolution = new GeneticEvolution(userInputParameters, lowerLimitNumber,
        upperLimitNumber);
    List<Chromosom> bestAndWorst = geneticEvolution.getMasterChrom(startingPopulation);

    System.out.println(
        "End the result is :" + bestAndWorst.get(0).getChromosomValue() + " " + bestAndWorst.get(1)
            .getChromosomValue());

  }


}
