import java.io.IOException;

public class StartSingleServer {
    public static void main(String[] args) throws IOException {
        try {
                   TCPSingleServer.serverRun(args[0], Integer.parseInt(args[1]));
               }catch (Exception e){
                   e.printStackTrace();
               }
    }
       
}
