package executor;

import algoritmo.AlgoritmoGeneticoJGAP;
import classes.GeracaoDeProdutos;
import classes.Produto;
import graficos.GraficoJGAP;
import org.jfree.ui.RefineryUtilities;
import org.jgap.IChromosome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class RunnerJGAP {

    public static void main(String[] args) {
        AlgoritmoGeneticoJGAP algoritmoGeneticoJGAP = new AlgoritmoGeneticoJGAP();
        algoritmoGeneticoJGAP.carregar();
        algoritmoGeneticoJGAP.procurarMelhorSolucao();

        IChromosome melhorCromossomo = algoritmoGeneticoJGAP.getMelhoresCromossomos().stream().max(Comparator.comparing(IChromosome::getFitnessValue)).orElseThrow(NoSuchElementException::new);
        System.out.println("\nMelhor solucao: ");
        algoritmoGeneticoJGAP.visualizarGeracao(melhorCromossomo, melhorCromossomo.getAge());

        List<Produto> listaProdutos = new ArrayList<>();
        GeracaoDeProdutos.gerarListaProdutos(listaProdutos);

        int i = 0;
        System.out.println("****** PRODUTOS ******");

        for (Produto produto : listaProdutos) {
            System.out.println(produto.getNome() + (melhorCromossomo.getGene(i).getAllele().toString().equals("1")? " OK " : " NOK "));
            i++;
        }
        System.out.println("******** FIM ********");

        GraficoJGAP grafico = new GraficoJGAP("Algoritmo genetico", "Evolucao das solucoes", algoritmoGeneticoJGAP.getMelhoresCromossomos());
        grafico.pack();
        RefineryUtilities.centerFrameOnScreen(grafico);
        grafico.setVisible(true);
    }
}
