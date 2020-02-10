package classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Individuo {

    private List<Double> espacos;
    private List<Double> valores;
    private Double limiteEspacos;
    private Double notaAvaliacao;
    private Double espacoUsado;
    private int geracao;
    private List<String> cromossomo = new ArrayList<>(); //solucao do problema

    public Individuo(List<Double> espacos, List<Double> valores, Double limiteEspacos) {
        this.espacos = espacos;
        this.valores = valores;
        this.limiteEspacos = limiteEspacos;
        this.notaAvaliacao = 0.0;
        this.espacoUsado = 0.0;
        this.geracao = 0;

        for(int i = 0; i < this.espacos.size(); i++){
            if(Math.random() < 0.5){
                this.cromossomo.add("0");
            }else{
                this.cromossomo.add("1");            }
        }
    }

    public void avaliar(){
        Double nota = 0.0;
        Double somaEspacos = 0.0;

        for(int i = 0; i < this.getCromossomo().size(); i++){
            if(this.getCromossomo().get(i).equals("1")){
                nota += this.valores.get(i);
                somaEspacos += this.espacos.get(i);
            }
        }

        if(somaEspacos > this.limiteEspacos){
            nota = 1.0;
        }

        this.notaAvaliacao = nota;
        this.espacoUsado = somaEspacos;
    }

    public List<Individuo> crossover(Individuo outroIndividuo){
        int corte = (int) Math.round(Math.random() * this.cromossomo.size());
        //System.out.println("Corte: " + corte);

        List<String> filho1 = new ArrayList<>();
        filho1.addAll(outroIndividuo.getCromossomo().subList(0, corte));
        filho1.addAll(this.getCromossomo().subList(corte, this.cromossomo.size()));

        List<String> filho2 = new ArrayList<>();
        filho2.addAll(this.getCromossomo().subList(0, corte));
        filho2.addAll(outroIndividuo.getCromossomo().subList(corte, outroIndividuo.getCromossomo().size()));

        List<Individuo> filhos = new ArrayList<>();
        filhos.add(new Individuo(this.espacos, this.valores, this.limiteEspacos));
        filhos.add(new Individuo(this.espacos, this.valores, this.limiteEspacos));

        filhos.get(0).setCromossomo(filho1);
        filhos.get(0).setGeracao(this.geracao + 1);

        filhos.get(1).setCromossomo(filho2);
        filhos.get(1).setGeracao(this.geracao + 1);

        return filhos;
    }

    public void mutacao(Double taxaMutacao){
        //System.out.println("Antes da mutacao: " + this.cromossomo);
        for(int i = 0; i < this.cromossomo.size(); i++){
            if(Math.random() < taxaMutacao){
                this.cromossomo.set(i, (this.cromossomo.get(i).equals("1")? "0" : "1"));
            }
        }
        //System.out.println("Depois da mutacao: " + this.cromossomo);
    }
}
