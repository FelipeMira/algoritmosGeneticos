package classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@ToString
public class IndividuoDama {

    private Double espacos;
    private Double limiteEspacos;
    private Double notaAvaliacao;
    private int geracao;
    private List<String> cromossomo = new ArrayList<>(); //solucao do problema

    public IndividuoDama(Double espacos, Double limiteEspacos) {
        this.espacos = espacos;
        this.limiteEspacos = limiteEspacos;
        this.geracao = 0;

        for(int i = 0; i < this.espacos; i++){
            this.cromossomo.add(String.valueOf(new Random().nextInt((int) (this.espacos + 1))));
        }
        this.avaliar();
    }

    public void avaliar(){
        Double nota = 100.0;
        int posicaoDama;
        int posicaoDamaValidar;

        for(int i = 0; i < cromossomo.size(); i++){
            posicaoDama = Integer.parseInt(i + cromossomo.get(i));

            for(int j = 0; j < cromossomo.size(); j++){
                if(j != i){
                    posicaoDamaValidar = Integer.parseInt(j + cromossomo.get(j));
                    if((posicaoDamaValidar - ((i - j) * 9) == posicaoDama) || (posicaoDamaValidar + ((i - j) * 9) == posicaoDama)){
                        nota -= 1.0;
                    }else if((posicaoDamaValidar - ((i - j) * 10) == posicaoDama) || (posicaoDamaValidar + ((i - j) * 10) == posicaoDama)){
                        nota -= 1.0;
                    }else if((posicaoDamaValidar - ((i - j) * 11) == posicaoDama) || (posicaoDamaValidar + ((i - j) * 11) == posicaoDama)){
                        nota -= 1.0;
                    }
                }
            }
        }
        this.notaAvaliacao = nota;
    }

    @SuppressWarnings("DuplicatedCode")
    public List<IndividuoDama> crossover(IndividuoDama outroIndividuo){
        int corte = (int) Math.round(Math.random() * this.cromossomo.size());
        //System.out.println("Corte: " + corte);

        List<String> filho1 = new ArrayList<>();
        filho1.addAll(outroIndividuo.getCromossomo().subList(0, corte));
        filho1.addAll(this.getCromossomo().subList(corte, this.cromossomo.size()));

        List<String> filho2 = new ArrayList<>();
        filho2.addAll(this.getCromossomo().subList(0, corte));
        filho2.addAll(outroIndividuo.getCromossomo().subList(corte, outroIndividuo.getCromossomo().size()));

        List<IndividuoDama> filhos = new ArrayList<>();
        filhos.add(new IndividuoDama(this.espacos, this.limiteEspacos));
        filhos.add(new IndividuoDama(this.espacos, this.limiteEspacos));

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
                this.cromossomo.set(i, (String.valueOf(randomExcluirNumero(1, this.cromossomo.size(), Integer.parseInt(this.cromossomo.get(i))))));
            }
        }
        //System.out.println("Depois da mutacao: " + this.cromossomo);
    }

    private int randomExcluirNumero(@SuppressWarnings("SameParameterValue") int inicio, int fim, int... exclui) {
        int random = inicio + new Random().nextInt(fim - inicio + 1 - exclui.length);
        for (int ex : exclui) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }
}
