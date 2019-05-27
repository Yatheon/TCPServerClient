import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class StartClientSingle {
    public static void main(String[] args) throws Exception {
       
		   try {
			    TCPClientSingle.clientRun(Integer.parseInt(args[0]));
               }catch (Exception e){
                   e.printStackTrace();
               }
    }
}
  