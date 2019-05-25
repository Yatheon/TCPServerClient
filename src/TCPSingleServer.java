import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;

import java.io.*;


public class TCPSingleServer {


    static int SERVER_PORT = 4477;
    static String FILE_TO_SEND = "fishy10";


    public static void serverRun() throws IOException {

        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        File file = new File("FilesToSend/" + FILE_TO_SEND);


       // long fileSize = file.length();
        while (true) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            System.out.println("Waiting");
            Socket socket = serverSocket.accept();
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[16 * 1024];

            int count;
            while ((count = bufferedInputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, count);
            }
            System.out.println("Closing");
            socket.close();
        }
    }
}
