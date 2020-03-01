package executor;

import algoritmo.AlgoritmoGeneticoDamas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static classes.GeracaoDamas.gerarListaDamas;

public class RunnerDamas {

    public static void main(String[] args){
        List<List<String>> resultado = new ArrayList<>();
        do{
            List<String> listaDamas = new ArrayList<>();

            Double numeroDamas = 8.0;

            gerarListaDamas(listaDamas, numeroDamas);
            //gerarListaProdutosPeloDB(listaProduto);

            Double taxaMutacao = 0.05;
            int quantidadeGeracoes = 100;

            //Instanciando algoritmoGenetico com o tamanho de populacao igual o da lista.
            AlgoritmoGeneticoDamas algoritmoGeneticoDamas = new AlgoritmoGeneticoDamas(listaDamas.size());

            List<String> resultadoAtual = algoritmoGeneticoDamas.resolver(taxaMutacao, quantidadeGeracoes, numeroDamas, numeroDamas, Optional.of(true));

            boolean achou = false;

            for(List<String> list : resultado){
                if (list.equals(resultadoAtual)) {
                    achou = true;
                    break;
                }
            }
            if(!achou){
                resultado.add(resultadoAtual);
            }
        }while(resultado.size() < 92);

        for(List<String> list : resultado){
            System.out.println(list.toString());
        }

        /*
        GraficoDamas g = new GraficoDamas("Algoritmo Genetico", "Evolucao das solucoes", algoritmoGeneticoDamas.getMelhoresCromossomos());
        g.pack();
        RefineryUtilities.centerFrameOnScreen(g);
        g.setVisible(true);
        */

    }


}
