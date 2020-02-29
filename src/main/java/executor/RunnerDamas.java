package executor;

import algoritmo.AlgoritmoGeneticoDamas;
import graficos.GraficoDamas;
import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static classes.GeracaoDamas.gerarListaDamas;

public class RunnerDamas {

    public static void main(String[] args){
        List<String> listaDamas = new ArrayList<>();

        Double numeroDamas = 8.0;

        gerarListaDamas(listaDamas, numeroDamas);
        //gerarListaProdutosPeloDB(listaProduto);

        Double taxaMutacao = 0.05;
        int quantidadeGeracoes = 100;

        //Instanciando algoritmoGenetico com o tamanho de populacao igual o da lista.
        AlgoritmoGeneticoDamas algoritmoGeneticoDamas = new AlgoritmoGeneticoDamas(listaDamas.size());

        List<String> resultado = algoritmoGeneticoDamas.resolver(taxaMutacao, quantidadeGeracoes, numeroDamas, numeroDamas, Optional.of(true));


        GraficoDamas g = new GraficoDamas("Algoritmo Genetico", "Evolucao das solucoes", algoritmoGeneticoDamas.getMelhoresCromossomos());
        g.pack();
        RefineryUtilities.centerFrameOnScreen(g);
        g.setVisible(true);

    }


}
