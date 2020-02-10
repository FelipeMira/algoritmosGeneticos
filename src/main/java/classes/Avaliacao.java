package classes;

import algoritmo.AlgoritmoGeneticoJGAP;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class Avaliacao extends FitnessFunction {

    private final AlgoritmoGeneticoJGAP algoritmoGeneticoJGAP;

    public Avaliacao(AlgoritmoGeneticoJGAP algoritmoGeneticoJGAP) {
        this.algoritmoGeneticoJGAP = algoritmoGeneticoJGAP;
    }

    @Override
    protected double evaluate(IChromosome iChromosome) {
        Double nota = 0.0;
        Double somaEspacos = 0.0;

        for(int i = 0; i < iChromosome.size(); i++){
            if(iChromosome.getGene(i).getAllele().toString().equals("1")){
                nota += this.algoritmoGeneticoJGAP.getListaProdutos().get(i).getValor();
                somaEspacos += this.algoritmoGeneticoJGAP.getListaProdutos().get(i).getEspaco();
            }
        }
        if(somaEspacos > this.algoritmoGeneticoJGAP.getLimite()){
            nota = 1.0;
        }
        return nota;
    }
}
