import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;

public class TCPClientSingle {
    static int SERVER_PORT = 5544;
    static String FILE_TO_RECIEVE = "fishRecieve";
    static String SERVER_ADDRESS = "169.254.10.219";

    public static void clientRun(int FILES_TO_RECIEVE) throws IOException {

        File file = new File(FILE_TO_RECIEVE);
		double FILESIZE_TO_RECIEVE = 0;
		Duration onlyRecieve = Duration.ZERO;
		Duration getTime = Duration.ZERO;
        Instant firstConnect;
        Instant done;
        Duration timeForAllFiles;
        InetSocketAddress serverAddr = new InetSocketAddress(SERVER_ADDRESS,
                SERVER_PORT);
		Duration elapsedTime  = Duration.ZERO;
		Instant start = Instant.now();
		Instant current = Instant.now();
		FILES_TO_RECIEVE = 0;
		while(Duration.between(start, current).getSeconds()<80){
     //   for (int i = 0; i < FILES_TO_RECIEVE; i++) {
          	Instant inst = Instant.now();
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            byte[] contents = new byte[10000];

            InputStream inputStream = socket.getInputStream();

            //No of bytes read in one read() call
            int bytesRead = 0;
            BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(new FileOutputStream(file));
					
            while((bytesRead=inputStream.read(contents))!=-1)
                bufferedOutputStream.write(contents, 0, bytesRead);
            bufferedOutputStream.flush();
			
            socket.close();
			bufferedOutputStream.close();
			Instant last = Instant.now();
			getTime = Duration.between(inst,last);
			onlyRecieve = onlyRecieve.plus(Duration.between(inst,last));		
            long test = getTime.toMillis();
			System.out.println("Receive time: "+getTime.toMillis());
			if(FILES_TO_RECIEVE == 0){
			FILESIZE_TO_RECIEVE = file.length();
			}
		FILES_TO_RECIEVE++;
		 file.delete();
		 current = Instant.now();
        }

 
        double totalFileSizeMB = (FILESIZE_TO_RECIEVE * FILES_TO_RECIEVE) / 1000000.0;
        double totalDurationSec = onlyRecieve.toNanos() / 1000000000.0;
        double megaBytePerSec = totalFileSizeMB / totalDurationSec;
		
        System.out.println("Total file size: " + totalFileSizeMB + " MB");
        System.out.println(onlyRecieve.toMillis() + "ms");
		System.out.println(megaBytePerSec + " MB/sec");
		
    }
}
