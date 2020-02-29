package classes;

import java.util.List;
import java.util.Random;

public class GeracaoDamas {

    public static void gerarListaDamas(List<String> listaDamas, double qtdDamas){
        for(int i = 0; i < qtdDamas; i++){
            listaDamas.add(String.valueOf(new Random().nextInt((int) qtdDamas)));
        }
    }
}
