import java.util.*;
import static java.lang.System.out;


public class Main {

    public static String identificaraTipoDeDia(String diaDaSemana ){
      return switch(diaDaSemana.toUpperCase()){
        case "SEGUNDA", "TERÇA" -> "Dia útil";
        case "SÁBADO", "DOMINGO" -> "Final de semana";
        default -> "Dia inválido";
      };
    }

    public static void main(String[] args) {
      out.println(
        identificaraTipoDeDia("terça")
      );
    }
}
