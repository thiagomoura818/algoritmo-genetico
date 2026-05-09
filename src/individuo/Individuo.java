package individuo;

import java.util.List;

public abstract class Individuo<T> {

    private double avaliacao;
    private boolean avaliado = false;
    private boolean minimizacao = true;

    public abstract List<Individuo<T>> recombinar(Individuo<T> p2);
    public abstract Individuo<T> mutar();
    public abstract double avaliar();
    public abstract T[] getGenes();

    public double getAvaliacao(){
        if(!avaliado){
            avaliacao = avaliar();
        }

        return avaliacao;
    }

    public boolean isMinimizacao(){
        return minimizacao;
    }


}
