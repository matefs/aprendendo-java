import java.util.ArrayList;
import java.util.List;

public class MemoryLeak {

    private static List<byte[]> bufferDeMemoriaAcumulada = new ArrayList<>();

    public static void main(String[] argumentosDaLinhaDeComando) {
        System.out.println("Iniciando alocacao continua de memoria...");

        long contadorDeBlocosAlocados = 0;

        while (true) {
            byte[] blocoDeMemoriaDeUmMegabyte = new byte[1024 * 1024];
            bufferDeMemoriaAcumulada.add(blocoDeMemoriaDeUmMegabyte);

            contadorDeBlocosAlocados++;
            System.out.println("Megabytes alocados: " + contadorDeBlocosAlocados);
        }
    }
}
