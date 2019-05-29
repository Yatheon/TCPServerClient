import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;

import java.io.*;


public class TCPSingleServer {


    static int SERVER_PORT = 5544;


String dataFromClient;

    public static void serverRun(String FILE_TO_SEND, int STREAMS) throws IOException {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		Socket socket = null;
        File file = new File("FilesToSend/" + FILE_TO_SEND);
		byte[] bytes = new byte[(int)file.length()];
		byte[] endByte = "0".getBytes();
       // long fileSize = file.length();
        while (true) {

			
            System.out.println("Waiting");
			try{
            socket = serverSocket.accept();
			os = socket.getOutputStream();
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			for(int i = 0; i<STREAMS; i++ ){
				bis.read(bytes, 0, bytes.length);
				os.write(bytes, 0, bytes.length);
				os.flush();
			}
			System.out.println("Done");
			}finally{
			if(bis != null) bis.close();
			if(os != null)os.close();
			if(socket!=null)socket.close();
			
			}
            System.out.println("closed!");
  
        }
    }
}
