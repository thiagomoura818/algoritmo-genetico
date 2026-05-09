package langermann;

import crossover.Crossover;
import dixonprice.IndividuoDixonPrice;
import individuo.Individuo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IndividuoLangermann extends Individuo<Double> {
    private Double[] genes;
    private int nDim;
    private Crossover<Double> c;

    public IndividuoLangermann(Double[] genes, Crossover<Double> c){
        this.c = c;
        this.genes = Arrays.copyOf(genes, genes.length);
        this.nDim = genes.length;

    }

    public IndividuoLangermann(int nDim, Crossover<Double> c){
        this.c = c;
        this.genes = new Double[nDim];
        this.nDim = nDim;

        Random random = new Random();
        for(int i = 0; i < nDim; i++){
            this.genes[i] = 0.0 + (10.0 + 0.0)*random.nextDouble();
        }
    }

    @Override
    public List<Individuo> recombinar(Individuo p2) {
        Double[][] genes = this.c.crossover(this,p2,nDim);
        Individuo ind1 = new IndividuoLangermann(genes[0],this.c);
        Individuo ind2 = new IndividuoLangermann(genes[1],this.c);
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

        for(int i = 0; i < nDim; i++)
            if(random.nextDouble() <= taxaMutacao)
                genes[i] += random.nextGaussian();
        return new IndividuoLangermann(genes,this.c);
    }


    @Override
    public double avaliar() {
        double result = 0.0;
        Double[] cVector = {1.0, 2.0, 5.0, 2.0, 3.0};
        Double[][] mS = new Double[][]{{3.0, 5.0}, {5.0, 2.0}, {2.0, 1.0},{1.0,4.0},{7.0,9.0}};

        for(int i = 0; i < mS.length; i++){
            double args = 0.0;

            for(int j = 0; j < nDim; j++)
                args += Math.pow((genes[j]-mS[i][j]),2);

            result += cVector[i]*Math.exp((-1.0/Math.PI)*args)*Math.cos(Math.PI*args);
        }

        return result;
    }

    @Override
    public Double[] getGenes() {
        return Arrays.copyOf(this.genes,nDim);
    }

    @Override
    public String toString() {
        return "IndividuoLangermann{" +
                "genes=" + Arrays.toString(genes) +
                "avaliacao="+getAvaliacao()+
                '}';
    }
}
