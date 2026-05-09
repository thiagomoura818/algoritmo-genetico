package permfunction;

import crossover.Crossover;
import individuo.Individuo;
import individuo.IndividuoFactory;

public class IndividuoPermFunctionFactory implements IndividuoFactory {
    private int nDim;
    private Crossover c;

    public IndividuoPermFunctionFactory(int nDim, Crossover c){
        this.nDim = nDim;
        this.c = c;
    }

    @Override
    public Individuo getIndividuo() {
        return new IndividuoPermFunction(nDim, c);
    }
}
