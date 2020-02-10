package algoritmo;

import classes.Avaliacao;
import classes.Produto;
import lombok.Getter;
import lombok.Setter;
import org.jgap.*;
import org.jgap.event.EventManager;
import org.jgap.impl.*;

import java.util.ArrayList;
import java.util.List;

import static classes.GeracaoDeProdutos.gerarListaProdutos;

@Getter
@Setter
public class AlgoritmoGeneticoJGAP {

    Configuration configuration;
    int numeroGeracoes = 101;
    Double limite = 3.0;
    int tamanhoPopulacao = 20;
    //1 por cento no JGAP pois sera 1 dividido por 100
    int taxaMutacao = 100;

    List<IChromosome> melhoresCromossomos = new ArrayList<>();
    List<Produto> listaProdutos = new ArrayList<>();
    IChromosome melhor;

    public void carregar(){
        gerarListaProdutos(listaProdutos);
    }

    public Double somaEsopacos(IChromosome cromossomo){
        Double soma = 0.0;

        for(int i = 0; i < cromossomo.size(); i++){
            if(cromossomo.getGene(i).getAllele().toString().equals("1")){
                soma += this.listaProdutos.get(i).getEspaco();
            }
        }
        return soma;
    }

    public void visualizarGeracao(IChromosome cromossomo, int geracao){
        List<String> lista = new ArrayList<>();

        Gene[] genes = cromossomo.getGenes();

        for(int i = 0; i < cromossomo.size(); i++){
            lista.add(genes[i].getAllele().toString());
        }

        System.out.println("G: " + geracao
                + " Valor: " + cromossomo.getFitnessValue()
                + " Espaco: " + somaEsopacos(cromossomo)
                + " Cromossomo: " + lista);
    }

    public IChromosome criarCromossomo(){
        try{
            Gene[] genes = new Gene[listaProdutos.size()];
            for(int i = 0; i < genes.length; i++){
                genes[i] = new IntegerGene(configuration, 0, 1);
                genes[i].setAllele(i);
            }
            return new Chromosome(configuration, genes);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public FitnessFunction criarFuncaoFitness(){
        return new Avaliacao(this);
    }

    public Configuration criarConfiguration(){
        Configuration configuration = new Configuration();
        configuration.removeNaturalSelectors(true);

        try {
            //true primeiro faz a selecao para depois fazer mutacao ou crossover
            configuration.addNaturalSelector(new BestChromosomesSelector(configuration, 0.4), true);
            configuration.setRandomGenerator(new StockRandomGenerator());
            configuration.addGeneticOperator(new CrossoverOperator(configuration));
            configuration.addGeneticOperator(new SwappingMutationOperator(configuration, this.taxaMutacao));
            configuration.setKeepPopulationSizeConstant(true);
            configuration.setEventManager(new EventManager());
            configuration.setFitnessEvaluator(new DefaultFitnessEvaluator());
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public void procurarMelhorSolucao(){
        this.configuration = criarConfiguration();

        try {
            FitnessFunction funcaoFitness = criarFuncaoFitness();
            configuration.setFitnessFunction(funcaoFitness);

            IChromosome modeloCromossomo = criarCromossomo();
            configuration.setSampleChromosome(modeloCromossomo);

            configuration.setPopulationSize(this.tamanhoPopulacao);
            IChromosome[] cromossomos = new IChromosome[tamanhoPopulacao];

            for(int i = 0; i < this.tamanhoPopulacao; i++){
                cromossomos[i] = criarCromossomo();
            }

            Genotype populacao = new Genotype(configuration, new Population(configuration, cromossomos));

            for(int j = 0; j < this.numeroGeracoes; j++){
                this.visualizarGeracao(populacao.getFittestChromosome(), j);
                populacao.getFittestChromosome().setAge(j);
                this.melhoresCromossomos.add(populacao.getFittestChromosome());
                populacao.evolve();
            }
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
