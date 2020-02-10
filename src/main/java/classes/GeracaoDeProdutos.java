package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class GeracaoDeProdutos {

    public static void gerarListaProdutos(List<Produto> listaProduto){
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
    }

    public static void gerarListaProdutosPeloDB(List<Produto> listaProduto) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:32769/algoritmos_geneticos", "root", "1234");
            Statement consulta = conn.createStatement();
            ResultSet rs = consulta.executeQuery("SELECT NOME, VALOR, ESPACO, QUANTIDADE FROM produtos");
            while (rs.next()){
                for(int i = 0; i < rs.getInt("quantidade"); i++){
                    listaProduto.add(new Produto(rs.getString("nome"), rs.getDouble("espaco"), rs.getDouble("valor")));
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
