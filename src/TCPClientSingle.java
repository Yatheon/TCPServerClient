import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;

public class TCPClientSingle {
    static int SERVER_PORT = 4477;
    static int FILES_TO_RECIEVE = 10;
    static String FILE_TO_RECIEVE = "fishRecieve";
    static String SERVER_ADDRESS = "localhost";

    public static void clientRun() throws IOException {
        File file = new File(FILE_TO_RECIEVE);
        Instant firstConnect;
        Instant done;
        Duration timeForAllFiles;
        InetSocketAddress serverAddr = new InetSocketAddress(SERVER_ADDRESS,
                SERVER_PORT);

        firstConnect = Instant.now();

        for (int i = 0; i < FILES_TO_RECIEVE; i++) {
            Instant starts = Instant.now();
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            byte[] contents = new byte[10000];

            BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(new FileOutputStream(file));
            InputStream inputStream = socket.getInputStream();

            //No of bytes read in one read() call
            int bytesRead = 0;

            while((bytesRead=inputStream.read(contents))!=-1)
                bufferedOutputStream.write(contents, 0, bytesRead);

            bufferedOutputStream.flush();
            socket.close();

            Instant ends = Instant.now();
            Duration duration = Duration.between(starts, ends);
            System.out.println("\n\nFILE " + i);
            System.out.println("ReceiveTime: " + duration.toMillis());
            System.out.println("File lenght: " + file.length());

            float fish = (float) file.length() / duration.toMillis();
            file.delete();


        }

        done = Instant.now();
        timeForAllFiles = Duration.between(firstConnect, done);
        System.out.println("Time to get all files: " + timeForAllFiles.toMillis() + "ms");
    }
}
