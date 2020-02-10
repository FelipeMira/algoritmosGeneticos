/*
package historico;

import algoritmo.AlgoritmoGenetico;
import classes.Produto;

import java.util.ArrayList;
import java.util.List;

public class Executor.Runner {

    public static void main(String[] args){
        //Produto p1 = new Produto("Geladeira Dako", 0.751, 999.90);

        List<Produto> listaProduto = new ArrayList<>();

        listaProduto.add(new Produto("Geladeira Dako", 0.751, 999.90));
        listaProduto.add(new Produto("Iphone 6", 0.000089, 2911.12));
        listaProduto.add(new Produto("TV 55", 0.400, 4346.99));
        listaProduto.add(new Produto("TV 50", 0.290, 3999.90));
        listaProduto.add(new Produto("TV 42", 0.200, 2999.00));
        listaProduto.add(new Produto("Notebook Dell", 0.00350, 2499.90));
        listaProduto.add(new Produto("Ventilador Pasnasonic", 0.496, 199.90));
        listaProduto.add(new Produto("Microondas Electrolux", 0.0424, 308.66));
        listaProduto.add(new Produto("Microondas LG", 0.0544, 429.90));
        listaProduto.add(new Produto("Microondas Panasonic", 0.0319, 299.29));
        listaProduto.add(new Produto("Geladeira Brastemp", 0.635, 849.00));
        listaProduto.add(new Produto("Geladeira Consul", 0.870, 1199.89));
        listaProduto.add(new Produto("Notebook Lenovo", 0.498, 1999.90));
        listaProduto.add(new Produto("Notebook Asus", 0.527, 3999.00));

        */
/*for(Produto produto : listaProduto){
            System.out.println("- " + produto.getNome() + " {Espaco: " + produto.getEspaco() + " | Valor: " + produto.getValor() + "};");
        }*//*

        List<Double> espacos = new ArrayList<>();
        List<Double> valores = new ArrayList<>();
        List<String> nomes = new ArrayList<>();

        for(Produto produto : listaProduto){
            espacos.add(produto.getEspaco());
            valores.add(produto.getValor());
            nomes.add(produto.getNome());
        }

        Double limite = 3.0;

        */
/*Individuo individuo1 = new Individuo(espacos, valores, limite);
        Individuo individuo2 = new Individuo(espacos, valores, limite);

        System.out.println("Espacos: " + individuo1.getEspacos());
        System.out.println("Valores: " + individuo1.getValores());
        System.out.println("Cromossomo: " + individuo1.getCromossomo());
        System.out.println("\nComponentes de carga:");

        for(int i = 0; i < listaProduto.size(); i++){
            if(individuo1.getCromossomo().get(i).equals("1")){
                System.out.println("- " + listaProduto.get(i).getNome() + " {Espaco: " + listaProduto.get(i).getEspaco() + " | Valor: " + listaProduto.get(i).getValor() + "};");
            }
        }

        System.out.println("\nIndividuo 1: " + individuo1.getCromossomo());
        individuo1.avaliar();
        System.out.println("Nota: " + individuo1.getNotaAvaliacao() + "\nEspaco usado: " + individuo1.getEspacoUsado());

        System.out.println("\nIndividuo 2: " + individuo2.getCromossomo());
        individuo2.avaliar();
        System.out.println("Nota: " + individuo2.getNotaAvaliacao() + "\nEspaco usado: " + individuo2.getEspacoUsado());


        List<Individuo> filhos = individuo1.crossover(individuo2);
        for(Individuo individuo : filhos){
            System.out.println("\nFilho " + (filhos.indexOf(individuo) + 1) + ": " + individuo.getCromossomo());
            individuo.avaliar();
            System.out.println("Nota: " + individuo.getNotaAvaliacao() + "\nEspaco usado: " + individuo.getEspacoUsado());
        }

        individuo1.mutacao(0.05);
        individuo2.mutacao(0.05);*//*


        int tamanhoPopulacao = listaProduto.size();
        Double taxaMutacao = 0.01;
        int quantidadeGeracoes = 100;

        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(tamanhoPopulacao);
        List<String> resultado = algoritmoGenetico.resolver(taxaMutacao, quantidadeGeracoes, espacos, valores, limite);



        */
/*algoritmoGenetico.inicializarPopulacao(espacos, valores, limite);
        algoritmoGenetico.avaliarPopulacao();

        algoritmoGenetico.melhorIndividuoDasPopulacoes();

        Double soma = algoritmoGenetico.somaAvaliacoes();*//*


        */
/*System.out.println("\nMelhor solucao: \n" + algoritmoGenetico.getmelhorIndividuo().toString());
        System.out.println("Soma da avaliacoes: " + soma);*//*


        //algoritmoGenetico.ordernarPopulacaoDesc();

        */
/*for(int i = 0; i < algoritmoGenetico.getTamanhoPopulacao(); i++){
            System.out.println("\n*** Individuo " + (i + 1)
                    + " ***\n\nEspacos = " + algoritmoGenetico.getPopulacao().get(i).getEspacos()
                    + "\nValores = " + algoritmoGenetico.getPopulacao().get(i).getValores()
                    + "\nCromossomo = " + algoritmoGenetico.getPopulacao().get(i).getCromossomo()
                    + "\nNota = " + algoritmoGenetico.getPopulacao().get(i).getNotaAvaliacao());
        }*//*



        */
/*List<Individuo> novaPopulacao = new ArrayList<>();

        //Cada vez que temos um crossover criamos 2 novos individuos,
        // e nao podemos estourar o tamanho dela
        for(int i = 0; i < algoritmoGenetico.getPopulacao().size() / 2; i ++){
            int pai1 = algoritmoGenetico.selecionaPai(soma);
            int pai2 = algoritmoGenetico.selecionaPai(soma);

            List<Individuo> filhos = algoritmoGenetico.getPopulacao().get(pai1).crossover(algoritmoGenetico.getPopulacao().get(pai2));
            filhos.forEach((Individuo individuo) -> { individuo.mutacao(taxaMutacao); });
            novaPopulacao.addAll(filhos);
        }

        algoritmoGenetico.setPopulacao(novaPopulacao);
        algoritmoGenetico.avaliarPopulacao();

        algoritmoGenetico.melhorIndividuoDasPopulacoes();

        algoritmoGenetico.ordernarPopulacaoDesc();

        soma = algoritmoGenetico.somaAvaliacoes();

        System.out.println("\nMelhor solucao: \n" + algoritmoGenetico.getMelhorIndividuo().toString());
        System.out.println("Soma da avaliacoes: " + soma);*//*


    }
}
*/
