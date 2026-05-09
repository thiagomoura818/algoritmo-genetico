package nrainhas;

import individuo.Individuo;

import java.util.*;

public class IndividuoNRainhas extends Individuo<Integer>{

    private int nRainhas;
    private Integer[] genes;

    public IndividuoNRainhas(Integer[] genes){
        this.genes = genes;
        this.nRainhas = genes.length;
    }

    public IndividuoNRainhas(int nRainhas){
        this.nRainhas = nRainhas;
        this.genes = new Integer[nRainhas];

        // iniciliazar o tamanho do vetor de genes e seus valores de forma aleatoria
        // Os valores aleatorios devem obedecer a ideia de permutação
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < nRainhas; i++){
            list.add(i);
        }

        Collections.shuffle(list);
        for(int i = 0; i < nRainhas; i++){
            genes[i] = list.get(i);
        }
    }

    @Override
    public List<Individuo> recombinar(Individuo p2) {

        List<Individuo> individuos = new ArrayList<>();
        individuos.add(recombinarIndividuo(this.genes, (Integer[]) p2.getGenes()));
        individuos.add(recombinarIndividuo((Integer[]) p2.getGenes(), this.genes));

        return individuos;
    }

    public Individuo recombinarIndividuo(Integer[] genes1, Integer[] genes2){
        int n = this.nRainhas;
        Integer[] novosGenes = new Integer[n];
        boolean[] visited = new boolean[n];

        int k = n / 2;

        for(int i = 0; i < k; i++){
            novosGenes[i] = genes1[i];
            visited[genes1[i]] = true;
        }

        int idx = k;
        for(int i = 0; i < n; i++){
            if(!visited[genes2[i]]){
                novosGenes[idx++] = genes2[i];
            }
        }

        return new IndividuoNRainhas(novosGenes);
    }

    @Override
    public Integer[] getGenes(){
        return this.genes;
    }

    @Override
    public Individuo mutar() {
        // gerar um novo individuo mutante com os genes do invididuo self mutados em uma taxa de mutaçao de 10% ou 5%
        float taxaMutacao = 0.1f;
        Random random = new Random();
        Integer[] novosGenes = Arrays.copyOf(genes, nRainhas);

        for(int i = 0; i < nRainhas; i++){
            if(random.nextFloat() <= taxaMutacao){
                int j = random.nextInt(nRainhas);
                while(j == i)
                    j = random.nextInt(nRainhas);

                int temp = novosGenes[i];
                novosGenes[i] = novosGenes[j];
                novosGenes[j] = temp;
            }
        }

        return new IndividuoNRainhas(novosGenes);
    }

    @Override
    public double avaliar() {
        int colisoes = 0;

        for(int i = 0; i < this.genes.length; i++){
            for(int j = i + 1; j < this.genes.length; j++){
                if(Math.abs(i - j) == Math.abs(genes[i] - genes[j])){
                    colisoes++;
                }
            }
        }

        return colisoes;
    }

    @Override
    public String toString() {
        return "nrainhas.IndividuoNRainhas{" +
                "nRainhas=" + nRainhas +
                ", genes=" + Arrays.toString(genes) +
                " Avaliacao: " + this.getAvaliacao() + '}';
    }
}
