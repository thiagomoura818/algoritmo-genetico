package permfunction;

import crossover.Crossover;
import individuo.Individuo;
import langermann.IndividuoLangermann;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IndividuoPermFunction extends Individuo<Double> {
    private Double[] genes;
    private int nDim;
    private Crossover c;

    public IndividuoPermFunction(Double[] genes, Crossover c){
        this.genes = Arrays.copyOf(genes, genes.length);
        this.c = c;
        this.nDim = genes.length;
    }

    public IndividuoPermFunction(int nDim, Crossover c){
        this.c = c;
        this.genes = new Double[nDim];
        this.nDim = nDim;

        Random random = new Random();
        for(int i = 0; i < nDim; i++){
            this.genes[i] = -nDim + (nDim + nDim)*random.nextDouble();
        }
    }
    @Override
    public List<Individuo> recombinar(Individuo p2) {
        Double[][] doubleGenes = this.c.crossover(this,p2,nDim);
        Individuo ind1 = new IndividuoPermFunction(doubleGenes[0],this.c);
        Individuo ind2 = new IndividuoPermFunction(doubleGenes[1],this.c);
        List<Individuo> list = new ArrayList<>();
        list.add(ind1);
        list.add(ind2);

        return list;
    }

    @Override
    public Individuo mutar() {
        Random random = new Random();
        double taxaMutacao = 0.3;
        Double[] genes = Arrays.copyOf(this.genes, nDim);

        double alpha = random.nextGaussian();
        for(int i = 0; i < nDim; i++)
            if(random.nextDouble() <= taxaMutacao)
                genes[i] += alpha;

        return new IndividuoPermFunction(genes,this.c);
    }

    @Override
    public double avaliar() {
        double beta = 0.5;
        double result = 0.0;
        double arg;
        for(int i = 0; i < nDim; i++){
            arg = 0.0;
            for(int j = 0; j < nDim; j++)
                arg += (Math.pow(j + 1, i + 1) + beta)
                        * (Math.pow(this.genes[j] / (j + 1), i + 1) - 1.0);
            result+=Math.pow(arg,2);
        }

        return result;
    }

    @Override
    public Double[] getGenes() {
        return Arrays.copyOf(this.genes,this.genes.length);
    }

    @Override
    public String toString() {
        return "IndividuoPermFunction{" +
                "genes=" + Arrays.toString(genes) +
                "avaliacao="+getAvaliacao()+
                '}';
    }
}
