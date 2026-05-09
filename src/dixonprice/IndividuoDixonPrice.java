package dixonprice;

import crossover.Crossover;
import individuo.Individuo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IndividuoDixonPrice extends Individuo<Double> {

    private final Double[] genes;
    private final int nDim;
    private final Crossover<Double> c;

    public IndividuoDixonPrice(int nDim, Crossover<Double> c){
        this.nDim = nDim;
        this.genes = new Double[nDim];
        this.c = c;

        Random random = new Random();
        for(int i = 0; i < nDim; i++){
            this.genes[i] = -10.0 + (10.0 + 10.0)*random.nextDouble();
        }
    }

    public IndividuoDixonPrice(Double[] genes, Crossover<Double> crossover){
        this.genes = Arrays.copyOf(genes,genes.length);
        this.nDim = genes.length;
        this.c = crossover;
    }

    @Override
    public List<Individuo<Double>> recombinar(Individuo<Double> p2) {
        Double[][] doubleGenes = this.c.crossover(this, p2, nDim);

        List<Individuo<Double>> individuos = new ArrayList<>();
        Individuo<Double> ind1 = new IndividuoDixonPrice(doubleGenes[0], this.c);
        Individuo<Double> ind2 = new IndividuoDixonPrice(doubleGenes[1], this.c);
        individuos.add(ind1);
        individuos.add(ind2);

        return individuos;
    }

    @Override
    public Individuo<Double> mutar() {
        Random random = new Random();
        double taxaMutacao = 0.3;
        Double[] genes = Arrays.copyOf(this.genes, nDim);

        for(int i = 0; i < nDim; i++)
            if(random.nextDouble() <= taxaMutacao)
                genes[i] += random.nextGaussian();

        return new IndividuoDixonPrice(genes,this.c);
    }

    @Override
    public double avaliar() {
        double value = Math.pow(this.genes[0]-1,2);
        for(int i = 1; i < nDim; i++){
            value+=(i+1)*Math.pow(2*Math.pow(this.genes[i],2) - this.genes[i-1],2);
        }

        return value;
    }

    @Override
    public Double[] getGenes() {
        return Arrays.copyOf(this.genes,nDim);
    }

    @Override
    public String toString() {
        return "DixonPrice.IndividuoDixonPrice{" +
                "genes=" + Arrays.toString(genes) +
                "avaliacao="+avaliar()+
                '}';
    }
}
