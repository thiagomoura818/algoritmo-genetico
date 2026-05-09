package langermann;

import crossover.Crossover;
import individuo.Individuo;
import individuo.IndividuoFactory;

public class IndividuoLangermannFactory implements IndividuoFactory {

    private int nDim;
    private Crossover c;

    public IndividuoLangermannFactory(int nDim, Crossover c){
        this.c=c;
        this.nDim = nDim;
    }
    @Override
    public Individuo getIndividuo() {
        return new IndividuoLangermann(nDim, c);
    }
}
