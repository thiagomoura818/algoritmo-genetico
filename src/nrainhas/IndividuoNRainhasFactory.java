package nrainhas;

import individuo.Individuo;
import individuo.IndividuoFactory;

public class IndividuoNRainhasFactory implements IndividuoFactory {

    private int nRainhas;

    public IndividuoNRainhasFactory(int nRainhas){
        this.nRainhas = nRainhas;
    }

    @Override
    public Individuo getIndividuo() {
        return new IndividuoNRainhas(this.nRainhas);
    }
}
