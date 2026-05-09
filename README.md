# Algoritmos Geneticos

Implementacao em Java de um Algoritmo Genetico (AG) para problemas de minimizacao. O projeto modela individuos, populacoes, mutacao, recombinacao e selecao, e aplica o AG a funcoes de benchmark de otimizacao continua.

## Sobre o projeto

O algoritmo principal esta em `src/ag/AG.java`. Ele executa o ciclo evolutivo a partir de uma fabrica de individuos (`IndividuoFactory`), permitindo trocar o problema otimizado sem alterar o fluxo do AG.

Em cada execucao, o algoritmo:

1. Gera uma populacao inicial aleatoria.
2. Cria filhos por recombinacao entre pares de individuos.
3. Cria mutantes a partir da populacao atual.
4. Junta pais, filhos e mutantes em uma populacao auxiliar.
5. Seleciona a nova populacao usando elitismo e selecao probabilistica baseada na avaliacao.
6. Repete o processo pelo numero definido de geracoes.

## Problemas implementados

O projeto possui representacoes de individuos para:

- `DixonPrice`: funcao Dixon-Price, com genes reais.
- `Langermann`: funcao Langermann, com genes reais.
- `PermFunction`: Perm Function, com genes reais.
- `NRainhas`: problema das N-Rainhas, com genes inteiros em forma de permutacao.

No `AGRunner`, a execucao atual compara as funcoes Dixon-Price, Langermann e Perm Function usando dois operadores de crossover.

## Operadores geneticos

### Crossover aritmetico

Implementado em `src/crossover/CrossoverAritmeticoDouble.java`.

Combina os genes de dois pais por media ponderada usando `alpha = 1/3`, gerando dois filhos:

- Filho 1: `alpha * p1 + (1 - alpha) * p2`
- Filho 2: `alpha * p2 + (1 - alpha) * p1`

### Crossover gaussiano

Implementado em `src/crossover/CrossoverGaussianoDouble.java`.

Gera filhos deslocando os genes dos pais por um fator aleatorio com distribuicao gaussiana, proporcional a distancia entre os genes dos dois pais.

### Mutacao

Cada tipo de individuo implementa sua propria mutacao:

- Funcoes continuas: alteracao dos genes com ruido gaussiano.
- N-Rainhas: troca de posicoes na permutacao.

## Estrutura do projeto

Principais responsabilidades:

- `AG`: ciclo evolutivo do algoritmo genetico.
- `AGRunner`: ponto de entrada para executar os experimentos.
- `Individuo`: classe abstrata base para os individuos.
- `IndividuoFactory`: interface usada para gerar individuos iniciais.
- `Crossover`: interface para operadores de recombinacao.

