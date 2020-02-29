package graficos;

import classes.IndividuoDama;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.List;

public class GraficoDamas extends ApplicationFrame {
    private List<IndividuoDama> melhoresCromossomos;

    public GraficoDamas(String tituloJanela, String tituloGrafico, List<IndividuoDama> melhores) {
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
        for (IndividuoDama individuo : melhoresCromossomos) {
            dados.addValue(individuo.getNotaAvaliacao(), "Melhor solucao", "" + i);
            i++;
        }
        return dados;
    }
}
