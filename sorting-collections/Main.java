// Main.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] arrayDeArgumentosDaLinhaDeComando) {

        List<String> listaDePalavrasParaOrdenacao = new ArrayList<>();
        listaDePalavrasParaOrdenacao.add("One");
        listaDePalavrasParaOrdenacao.add("abc");
        listaDePalavrasParaOrdenacao.add("BCD");

    System.out.println(listaDePalavrasParaOrdenacao);

        Collections.sort(listaDePalavrasParaOrdenacao, (String primeiraPalavraParaComparacao, String segundaPalavraParaComparacao) -> {
            return primeiraPalavraParaComparacao.compareToIgnoreCase(segundaPalavraParaComparacao);
        });

        System.out.println(listaDePalavrasParaOrdenacao);       
    }
}
