package crossover;

import individuo.Individuo;

import java.util.Random;

public class CrossoverGaussianoDouble implements Crossover<Double>{

    @Override
    public Double[][] crossover(Individuo ind1, Individuo ind2, int nDim) {
        Double[] p1 = (Double[]) ind1.getGenes();
        Double[] p2 = (Double[]) ind2.getGenes();

        Double[][] genes = new Double[2][nDim];

        Random random = new Random();

        for(int i = 0; i < nDim; i++){

            double alpha = random.nextGaussian();
            genes[0][i] = p1[i] + alpha * Math.abs(p1[i] - p2[i]);
            genes[1][i] = p2[i] + alpha * Math.abs(p1[i] - p2[i]);
        }

        return genes;
    }
}
