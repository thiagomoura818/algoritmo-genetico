package ag;

import individuo.Individuo;
import individuo.IndividuoFactory;

import java.util.*;

public class AG {
    public List<Individuo> executar(IndividuoFactory factory, int numeroGeracoes, int numeroIndividuos, int nElite){
        List<Individuo> popIni = new ArrayList<>(numeroIndividuos);
        // Fazer um for para preencher uma lista com n individuos
        for(int i = 0; i < numeroIndividuos; i++){
            popIni.add(i, factory.getIndividuo());
        }
        // Para gerar os indivuods iniciais utilizar o individuo.IndividuoFactory
        for(int i = 0; i < numeroGeracoes; i++){
            List<Individuo> filhos = getFilhos(popIni);
            List<Individuo> mutantes = getMutantes(popIni);

            List<Individuo> popAux = new ArrayList<>(numeroIndividuos*3);
            popAux.addAll(popIni);
            popAux.addAll(filhos);
            popAux.addAll(mutantes);

            popIni = selecao(popAux, numeroIndividuos, nElite);

            // Imprimir o melhor individuo.Individuo e a avaliacao deste melhor individuo
            //imprimirMelhor(i, popIni);
        }

        return popIni;
    }

    private void imprimirMelhor(int i, List<Individuo> popIni) {

        Individuo ind0 = popIni.get(0);
        boolean minimizacao = ind0.isMinimizacao();


        // Imprimir o numero da geracao, o melhor individuo e a avaliacao deste melhor individuo
        // Levar em consideracao se o problema é de minimizacao ou se maximizacao
        popIni.sort(Comparator.comparingDouble(Individuo::getAvaliacao));
       System.out.printf("G:%d ", i);
       Individuo individuo = popIni.get(0);
       System.out.println(individuo.toString());
    }

    private List<Individuo> selecao(List<Individuo> popAux, int numeroIndividuos, int nElite) {
        Random random = new Random();
        List<Individuo> newPop = new ArrayList<>();

        popAux.sort(Comparator.comparingDouble(Individuo::getAvaliacao));
        for(int i = 0; i < nElite; i++){
            newPop.add(popAux.get(i));
        }

        for(int i = 0; i < numeroIndividuos-nElite; i++){
            double sum2 = 0.0;
            double sum1 = 0.0;
            int j;

            for(int k = nElite; k < numeroIndividuos-nElite-i; k++){
                sum1 += (1.0/popAux.get(k).getAvaliacao());
            }

            double aleatorio = random.nextDouble() * sum1;

            for(j = nElite;j<popAux.size(); j++){
                sum2+=(1.0/popAux.get(j).getAvaliacao());
                if(sum2>=aleatorio){
                    break;
                }
            }
            newPop.add(popAux.remove(j-1));
        }
        return newPop;
    }

    private List<Individuo> getMutantes(List<Individuo> popIni) {
        // percorrer a lista de pais e a cada pai obter o seu mutante retornando uma lista de mutantes.
        List<Individuo> mutantesList = new ArrayList<>();
        List<Individuo> paisList = new ArrayList<>();
        paisList.addAll(popIni);

        for(int i = 0; i < paisList.size(); i++){
            Individuo individuo = paisList.get(i).mutar();
            mutantesList.add(individuo);
        }
        return mutantesList;

    }


    private List<Individuo> getFilhos(List<Individuo> popIni) {
        List<Individuo> filhosList = new ArrayList<>();

        List<Individuo> paisList = new ArrayList<>();
        paisList.addAll(popIni);

        Random random = new Random();
        for(int i = 0; i < popIni.size()/2; i++){
            int r1 = random.nextInt(paisList.size());
            Individuo p1 = paisList.get(r1);
            paisList.remove(p1);

            int r2 = random.nextInt(paisList.size());
            Individuo p2 = paisList.get(r2);
            paisList.remove(p2);

            List<Individuo> filhos = p1.recombinar(p2);
            filhosList.addAll(filhos);
        }

        return filhosList;
    }
}
