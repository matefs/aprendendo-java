import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class SharedMemoryReader {
    public static void main(String[] args) throws Exception {
        RandomAccessFile sharedMemoryFile = new RandomAccessFile("shared_state.dat", "r");
        MappedByteBuffer sharedMemoryBuffer = sharedMemoryFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, 4);

        int sharedCounterValue = sharedMemoryBuffer.getInt(0);
        System.out.println("Valor lido pelo outro PID: " + sharedCounterValue);

        sharedMemoryFile.close();
    }
}
