package algoritmo;

import classes.IndividuoDama;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class AlgoritmoGeneticoDamas {

    private int tamanhoPopulacao;
    private List<IndividuoDama> populacao = new ArrayList<>();
    private int geracao;
    private IndividuoDama melhorIndividuo;
    private List<IndividuoDama> melhoresCromossomos = new ArrayList<>();

    public AlgoritmoGeneticoDamas(int tamanhoPopulacao){
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    private void inicializarPopulacao(Double espacos, Double limiteEspacos){
        for(int i = 0; i < this.tamanhoPopulacao; i++){
            this.populacao.add(new IndividuoDama(espacos, limiteEspacos));
        }
        this.melhorIndividuo = this.populacao.get(0);
    }

    private void avaliarPopulacao(){
        for(IndividuoDama individuo : this.populacao){
            individuo.avaliar();
        }
    }

    private IndividuoDama encontrarMelhorIndividuo(){
        return this.populacao.stream().max(Comparator.comparing(IndividuoDama::getNotaAvaliacao)).orElseThrow(NoSuchElementException::new);
    }

    private void melhorIndividuoDasPopulacoes(){
        IndividuoDama melhorIndividuoAtual = encontrarMelhorIndividuo();
        this.setMelhorIndividuo((melhorIndividuoAtual.getNotaAvaliacao() > this.melhorIndividuo.getNotaAvaliacao())? melhorIndividuoAtual : this.melhorIndividuo);
    }

    private void ordernarPopulacaoDesc(){
        this.populacao.sort(Comparator.comparing(IndividuoDama::getNotaAvaliacao).reversed());
    }

    private Double somaAvaliacoes(){
        return this.populacao.stream().mapToDouble(IndividuoDama::getNotaAvaliacao).sum();
    }

    private int selecionaPai(Double somaAvaliacao){
        int pai = -1;
        Double valorSorteado = Math.random() * somaAvaliacao;
        Double soma = 0.0;
        int i = 0;
        while(i < this.populacao.size() && soma < valorSorteado){
            soma += this.populacao.get(i).getNotaAvaliacao();
            pai += 1;
            i++;
        }
        return pai;
    }

    private void visualizarGeracao(){
        IndividuoDama melhorIndividuoAtual = encontrarMelhorIndividuo();
        /*System.out.println("G: " + melhorIndividuoAtual.getGeracao()
            + " Valor: " + melhorIndividuoAtual.getNotaAvaliacao()
            + " Cromossomo: " + melhorIndividuoAtual.getCromossomo());*/
        this.melhoresCromossomos.add(melhorIndividuoAtual);
    }

    public List<String> resolver(Double taxaMutacao, int numeroGeracoes, Double espacos, Double limiteEspacos, Optional<Boolean> ateSolucao){
        this.inicializarPopulacao(espacos, limiteEspacos);
        this.avaliarPopulacao();
        this.melhorIndividuoDasPopulacoes();
        this.ordernarPopulacaoDesc();
        this.visualizarGeracao();
        if(Objects.isNull(ateSolucao)){
            ateSolucao = Optional.of(false);
        }
        do{
            for(int geracao = 0; geracao < (ateSolucao.filter(aBoolean -> aBoolean).map(aBoolean -> 1).orElse(numeroGeracoes)); geracao++){
                Double soma = this.somaAvaliacoes();
                List<IndividuoDama> novaPopulacao = new ArrayList<>();

                //Cada vez que temos um crossover criamos 2 novos individuos,
                // e nao podemos estourar o tamanho dela
                for(int i = 0; i < this.getPopulacao().size() / 2; i ++) {
                    int pai1 = this.selecionaPai(soma);
                    int pai2 = this.selecionaPai(soma);

                    List<IndividuoDama> filhos = this.getPopulacao().get(pai1).crossover(this.getPopulacao().get(pai2));
                    filhos.forEach((IndividuoDama individuo) -> individuo.mutacao(taxaMutacao));
                    novaPopulacao.addAll(filhos);
                }

                this.setPopulacao(novaPopulacao);
                this.avaliarPopulacao();
                this.melhorIndividuoDasPopulacoes();
                this.ordernarPopulacaoDesc();
                this.visualizarGeracao();

            }


        }while(melhorIndividuo.getNotaAvaliacao() < 100.0 && ateSolucao.orElse(false));


        /*System.out.println("Melhor solucao G -> " + this.melhorIndividuo.getGeracao()
                + " Valor: " + this.melhorIndividuo.getNotaAvaliacao()
                + " Cromossomo: " + this.melhorIndividuo.getCromossomo());*/
        return this.melhorIndividuo.getCromossomo();
    }

}
