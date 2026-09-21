// ExemploVarLoop.java
import java.util.List;

public class ExemploVarLoop {
    public static void main(String[] args) {
        var listaNomesPessoas = List.of("Ana", "Bruno", "Carlos");

        // var no for-each
        for (var nomePessoa : listaNomesPessoas) {
            System.out.println(nomePessoa);
        }

        // var no for tradicional
        for (var contadorIndice = 0; contadorIndice < listaNomesPessoas.size(); contadorIndice++) {
            System.out.println(contadorIndice + ": " + listaNomesPessoas.get(contadorIndice));
        }
    }
}
