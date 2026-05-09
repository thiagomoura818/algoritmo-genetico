package crossover;

import individuo.Individuo;

public class CrossoverAritmeticoDouble implements Crossover<Double>{

    private final Double alpha = 1.0/3.0;

    @Override
    public Double[][] crossover(Individuo ind1, Individuo ind2, int nDim) {
        Double[] p1 = (Double[]) ind1.getGenes();
        Double[] p2 = (Double[]) ind2.getGenes();

        Double[][] genes = new Double[2][nDim];

        for(int i = 0; i < nDim; i++){
            genes[0][i] = alpha * p1[i] + (1.0 - alpha)*p2[i];
            genes[1][i] = alpha * p2[i] + (1.0 - alpha)*p1[i];
        }

        return genes;
    }
}
