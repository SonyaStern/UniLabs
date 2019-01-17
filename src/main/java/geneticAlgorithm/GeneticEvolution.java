package geneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GeneticEvolution {

  //POPULATION SIZE NUMBERS

  public static final int population_size = 4;

  //GENETIC EVOLUTION PARAMETERS

  private static final double mutation_pos = 0.01;

  //TASK LIMITS

  private int lowerLimitNumber;

  private int upperLimitNumber;

  private Function geneticFunction;

  public GeneticEvolution(int[] geneticFunctionParameters, int min, int max) {
    lowerLimitNumber = min;
    upperLimitNumber = max;
    this.geneticFunction = new Function(geneticFunctionParameters);
  }

  public List<Chromosom> getMasterChrom(List<Chromosom> startingPopulation) {
    List<Chromosom> newPopulationGroup = populationIteration(startingPopulation);

    for (int i = 0; i < 301; i++) {
      //generate new 50 chromosomes
      newPopulationGroup.addAll(generateRandomChromosoms(50));
    }

    return findTheBestCharacter(newPopulationGroup);
  }

  public List<Chromosom> populationIteration(List<Chromosom> populationChroms) {
    List<Chromosom> survivedChromosomes = new ArrayList<>();
    List<Chromosom> charactersForGeneticOperations = getBestCharactersBetween(populationChroms, -10,
        53);
    for (Chromosom chrom : charactersForGeneticOperations) {
      if (shouldMutate()) {
        mutate(chrom);
      }
    }
    List<Chromosom> crossedChroms = new ArrayList<Chromosom>();
    while (charactersForGeneticOperations.size() != 0) {
      List<Chromosom> crossedDouble = crossover(charactersForGeneticOperations.get(0),
          charactersForGeneticOperations.get(1));
      charactersForGeneticOperations.remove(1);
      charactersForGeneticOperations.remove(0);
      crossedChroms.addAll(crossedDouble);
    }
    survivedChromosomes.addAll(crossedChroms);
    return survivedChromosomes;
  }

  /**
   * @param population list of chromosomes
   * @param lowerLimit get chromosomes ordered by it's genetic function result from that number in
   * the list
   * @param upperLimit get chromosomes ordered by it's genetic function result to that number in the
   * list
   * @return list of chromosomes between given numbers in the list
   */
  public List<Chromosom> getBestCharactersBetween(List<Chromosom> population, int lowerLimit,
      int upperLimit) {
    List<Chromosom> bestCharaсters = new ArrayList<>();
    Map<Double, Chromosom> bestCharactersMap = new TreeMap<>();
    for (Chromosom chromosom : population) {
      double chromFunctionResult = geneticFunction.getFunctionResult(chromosom.getChromosomValue());
      bestCharactersMap.put(chromFunctionResult, chromosom);
    }
    int iterationCounter = 0;
    for (Map.Entry<Double, Chromosom> entry : bestCharactersMap.entrySet()) {
      if (iterationCounter >= lowerLimit && iterationCounter <= upperLimit) {
        bestCharaсters.add(entry.getValue());
      }
      iterationCounter++;
    }
    return bestCharaсters;
  }

  public List<Chromosom> crossover(Chromosom chrom1, Chromosom chrom2) {
    List<Chromosom> crossedChroms = new ArrayList<>();
    int fenotypeSize = chrom2.getChromBits().length;
    int crossPoint = Generator.generateRandomInteger(0, fenotypeSize / 2);
    for (int i = 0; i < crossPoint; i++) {
      int chrom1Bit = chrom1.getChromBits()[i];
      chrom1.getChromBits()[i] = chrom2.getChromBits()[i];
      chrom2.getChromBits()[i] = chrom1Bit;
    }
    crossedChroms.add(chrom1);
    crossedChroms.add(chrom2);
    return crossedChroms;
  }

  public void mutate(Chromosom chromosom) {
    int[] chromBits = chromosom.getChromBits();
    int generatedValue = Generator.generateRandomInteger(0, chromBits.length - 1);
    chromBits[generatedValue] = changeBit(chromBits[generatedValue]);
    chromosom.setChromBits(chromBits);
  }

  public List<Chromosom> generateRandomChromosoms(int amount) {
    Operations operations = new Operations(lowerLimitNumber, upperLimitNumber);
    return operations.generatePopulation(amount);
  }

  private int changeBit(int bit) {
    if (bit == 1) {
      return 0;
    } else {
      return 1;
    }
  }

  private boolean shouldMutate() {
    return Generator.generateRandomDoubleWithLimits(0, 1) < mutation_pos;
  }

  private boolean fulfilLimits(double value) {
    return value < upperLimitNumber && value > lowerLimitNumber;
  }

  public List<Chromosom> findTheBestCharacter(List<Chromosom> chromosoms) {
    Chromosom theLowestChromosom = chromosoms.get(0);
    Chromosom theHighestChromosom = chromosoms.get(0);
    List<Chromosom> bestAndWorst = new ArrayList<>();
    for (Chromosom chrom : chromosoms) {
      double functionResult = geneticFunction.getFunctionResult(chrom.getChromosomValue());
      if (functionResult < geneticFunction.getFunctionResult(theLowestChromosom.getChromosomValue())
          && fulfilLimits(chrom.getChromosomValue())) {
        theLowestChromosom = chrom;
      } else if (functionResult > geneticFunction
          .getFunctionResult(theHighestChromosom.getChromosomValue()) && fulfilLimits(
          chrom.getChromosomValue())) {
        theHighestChromosom = chrom;
      }
    }
    bestAndWorst.add(theLowestChromosom);
    bestAndWorst.add(theHighestChromosom);
    return bestAndWorst;
  }
}
