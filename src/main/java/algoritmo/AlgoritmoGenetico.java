package algoritmo;

import classes.Individuo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
@NoArgsConstructor
public class AlgoritmoGenetico {

    private int tamanhoPopulacao;
    private List<Individuo> populacao = new ArrayList<>();
    private int geracao;
    private Individuo melhorIndividuo;
    private List<Individuo> melhoresCromossomos = new ArrayList<>();

    public AlgoritmoGenetico(int tamanhoPopulacao){
        this.tamanhoPopulacao = tamanhoPopulacao;
    }

    private void inicializarPopulacao(List<Double> espacos, List<Double> valores, Double limiteEspacos){
        for(int i = 0; i < this.tamanhoPopulacao; i++){
            this.populacao.add(new Individuo(espacos, valores, limiteEspacos));
        }
        this.melhorIndividuo = this.populacao.get(0);
    }

    private void avaliarPopulacao(){
        for(Individuo individuo : this.populacao){
            individuo.avaliar();
        }
    }

    private Individuo encontrarMelhorIndividuo(){
        return this.populacao.stream().max(Comparator.comparing(Individuo::getNotaAvaliacao)).orElseThrow(NoSuchElementException::new);
    }

    private void melhorIndividuoDasPopulacoes(){
        Individuo melhorIndividuoAtual = encontrarMelhorIndividuo();
        this.setMelhorIndividuo((melhorIndividuoAtual.getNotaAvaliacao() > this.melhorIndividuo.getNotaAvaliacao())? melhorIndividuoAtual : this.melhorIndividuo);
    }

    private void ordernarPopulacaoDesc(){
        this.populacao.sort(Comparator.comparing(Individuo::getNotaAvaliacao).reversed());
    }

    private Double somaAvaliacoes(){
        return this.populacao.stream().mapToDouble(Individuo::getNotaAvaliacao).sum();
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
        Individuo melhorIndividuoAtual = encontrarMelhorIndividuo();
        System.out.println("G: " + melhorIndividuoAtual.getGeracao()
            + " Valor: " + melhorIndividuoAtual.getNotaAvaliacao()
            + " Espaço: " + melhorIndividuoAtual.getEspacoUsado()
            + " Cromossomo: " + melhorIndividuoAtual.getCromossomo());
        this.melhoresCromossomos.add(melhorIndividuoAtual);
    }

    public List<String> resolver(Double taxaMutacao, int numeroGeracoes, List<Double> espacos, List<Double> valores, Double limiteEspacos){
        this.inicializarPopulacao(espacos, valores, limiteEspacos);
        this.avaliarPopulacao();
        this.melhorIndividuoDasPopulacoes();
        this.ordernarPopulacaoDesc();
        this.visualizarGeracao();

        for(int geracao = 0; geracao < numeroGeracoes; geracao++){
            Double soma = this.somaAvaliacoes();
            List<Individuo> novaPopulacao = new ArrayList<>();

            //Cada vez que temos um crossover criamos 2 novos individuos,
            // e nao podemos estourar o tamanho dela
            for(int i = 0; i < this.getPopulacao().size() / 2; i ++) {
                int pai1 = this.selecionaPai(soma);
                int pai2 = this.selecionaPai(soma);

                List<Individuo> filhos = this.getPopulacao().get(pai1).crossover(this.getPopulacao().get(pai2));
                filhos.forEach((Individuo individuo) -> individuo.mutacao(taxaMutacao));
                novaPopulacao.addAll(filhos);
            }

            this.setPopulacao(novaPopulacao);
            this.avaliarPopulacao();
            this.melhorIndividuoDasPopulacoes();
            this.ordernarPopulacaoDesc();
            this.visualizarGeracao();

        }

        System.out.println("Melhor solucao G -> " + this.melhorIndividuo.getGeracao()
                + " Valor: " + this.melhorIndividuo.getNotaAvaliacao()
                + " Espaço: " + this.melhorIndividuo.getEspacoUsado()
                + " Cromossomo: " + this.melhorIndividuo.getCromossomo());

        return this.melhorIndividuo.getCromossomo();
    }

}
