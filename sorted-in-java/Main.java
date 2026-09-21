import java.util.List;

public class Main {
    public static void main(String[] args) {
        var listaItemValores = List.of(10, 20, 30, 40);
        var somaTotalValores = listaItemValores.stream()
                .filter(numeroItem -> numeroItem > 15)
                .mapToInt(numeroItem -> numeroItem)
                .sum();
        
        System.out.println(somaTotalValores);        
    }
}
