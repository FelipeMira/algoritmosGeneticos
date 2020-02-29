package executor;

import algoritmo.AlgoritmoGenetico;
import classes.Produto;
import graficos.Grafico;
import org.jfree.ui.RefineryUtilities;

import java.util.ArrayList;
import java.util.List;

import static classes.GeracaoDeProdutos.gerarListaProdutos;

public class Runner {

    public static void main(String[] args){
        List<Produto> listaProduto = new ArrayList<>();

        gerarListaProdutos(listaProduto);
        //gerarListaProdutosPeloDB(listaProduto);

        List<Double> espacos = new ArrayList<>();
        List<Double> valores = new ArrayList<>();

        for(Produto produto : listaProduto){
            espacos.add(produto.getEspaco());
            valores.add(produto.getValor());
        }

        Double limite = 3.0;
        Double taxaMutacao = 0.05;
        int quantidadeGeracoes = 100;

        //Instanciando algoritmoGenetico com o tamanho de populacao igual o da lista.
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(listaProduto.size());

        List<String> resultado = algoritmoGenetico.resolver(taxaMutacao, quantidadeGeracoes, espacos, valores, limite);

        int i = 0;
        System.out.println("****** PRODUTOS ******");
        for (Produto produto : listaProduto) {
            System.out.println(produto.getNome() + (resultado.get(i).equals("1")? " OK " : " NOK "));
            i++;
        }
        System.out.println("******** FIM ********");

        Grafico g = new Grafico("Algoritmo Genetico", "Evolucao das solucoes", algoritmoGenetico.getMelhoresCromossomos());
        g.pack();
        RefineryUtilities.centerFrameOnScreen(g);
        g.setVisible(true);
    }


}
