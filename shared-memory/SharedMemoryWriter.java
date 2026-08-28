import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class SharedMemoryWriter {
    public static void main(String[] args) throws Exception {
        RandomAccessFile sharedMemoryFile = new RandomAccessFile("shared_state.dat", "rw");
        MappedByteBuffer sharedMemoryBuffer = sharedMemoryFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 4);

        int counterValue = 42;
        sharedMemoryBuffer.putInt(0, counterValue);
        
        System.out.println("Valor gravado na memoria compartilhada: " + counterValue);
        Thread.sleep(60000);
        
        sharedMemoryFile.close();
    }
}
