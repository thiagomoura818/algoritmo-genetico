package crossover;

import individuo.Individuo;

public interface Crossover<T> {
    Double[][] crossover(Individuo ind1, Individuo ind2, int nDim);
}
