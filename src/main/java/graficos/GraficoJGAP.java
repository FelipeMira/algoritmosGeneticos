package graficos;

import classes.Individuo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jgap.IChromosome;

import java.util.List;

public class GraficoJGAP extends ApplicationFrame {
    private List<IChromosome> melhoresCromossomos;

    public GraficoJGAP(String tituloJanela, String tituloGrafico, List<IChromosome> melhores) {
        super(tituloJanela);
        this.melhoresCromossomos = melhores;
        JFreeChart graficoLinha = ChartFactory.createLineChart(tituloJanela, "Geracao", "Valor", carregarDados(), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel janelaGrafico = new ChartPanel(graficoLinha);
        janelaGrafico.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(janelaGrafico);
    }

    private DefaultCategoryDataset carregarDados(){
        DefaultCategoryDataset dados = new DefaultCategoryDataset();

        int i = 0;
        for (IChromosome individuo : melhoresCromossomos) {
            dados.addValue(individuo.getFitnessValue(), "Melhor solucao", "" + i);
            i++;
        }
        return dados;
    }
}
