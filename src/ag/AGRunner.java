package ag;

import crossover.Crossover;
import crossover.CrossoverAritmeticoDouble;
import crossover.CrossoverGaussianoDouble;
import dixonprice.IndividuoDixonPriceFactory;
import individuo.Individuo;
import individuo.IndividuoFactory;
import langermann.IndividuoLangermannFactory;
import nrainhas.IndividuoNRainhasFactory;
import permfunction.IndividuoPermFunctionFactory;

import java.util.List;

public class AGRunner {
    private static final int GERACOES = 2000;
    private static final int POPULACAO = 20;
    private static final int ELITISMO = 4;

    public static void main(String[] args) {

        CrossoverAritmeticoDouble crossoverArimetico = new CrossoverAritmeticoDouble();
        CrossoverGaussianoDouble crossoverGaussiano = new CrossoverGaussianoDouble();
        AG ag = new AG();

        execute("Dixon Price (Aritmetico)", new IndividuoDixonPriceFactory(5, crossoverArimetico),ag);
        execute("Dixon Price (Gaussiano)", new IndividuoDixonPriceFactory(5, crossoverGaussiano),ag);

        execute("Langermann (Aritmetico)", new IndividuoLangermannFactory(2, crossoverArimetico),ag);
        execute("Langermann (Gaussiano)", new IndividuoLangermannFactory(2, crossoverGaussiano),ag);

        execute("PermFunction (Aritmetico)", new IndividuoPermFunctionFactory(3, crossoverArimetico), ag);
        execute("PermFunction (Gaussiano)", new IndividuoPermFunctionFactory(3, crossoverGaussiano), ag);

    }

    public static void execute(String functionName, IndividuoFactory individuoFactory, AG ag){
        List<Individuo> individuoList = ag.executar(individuoFactory, GERACOES, POPULACAO, ELITISMO);
        System.out.printf("\nMelhor individuo %s: %s", functionName, individuoList.getFirst().toString());

    }

}