package dixonprice;

import crossover.Crossover;
import individuo.Individuo;
import individuo.IndividuoFactory;

public class IndividuoDixonPriceFactory implements IndividuoFactory {

    private int nDim;
    private Crossover c;
    public IndividuoDixonPriceFactory(int nDim, Crossover crossover){

        this.nDim = nDim;
        this.c = crossover;
    }

    @Override
    public Individuo<Double> getIndividuo() {
        return new IndividuoDixonPrice(nDim, c);
    }
}
