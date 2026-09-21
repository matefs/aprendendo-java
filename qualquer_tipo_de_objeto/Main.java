import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<String> nomePessoas = Arrays.asList("Ana","Banana","Fulana");
        List<Integer> listaNumerosPessoas = Arrays.asList(10,20,30);
        
        imprimirListaQualquerTipo(listaNumerosPessoas);
        imprimirListaQualquerTipo(nomePessoas);
    }

    public static void imprimirListaQualquerTipo(List<?> listaGenericaArg){
        for(Object elementoGenerico: listaGenericaArg){
          System.out.println(elementoGenerico);
        }
      }
}
