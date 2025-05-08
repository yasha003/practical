import java.util.*;
import java.net.*;
import java.time.*;
import java.io.*;
public class Client {
    public static void main(String args[]) throws IOException{
        Socket socket=new Socket("127.0.0.1", 5050);
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out=new PrintWriter(socket.getOutputStream(),true);

        LocalDateTime now=LocalDateTime.now();
        out.println(now);
        System.out.println("Client time: "+now);
        String offsetStr=in.readLine();
        long offset=Long.parseLong(offsetStr);
        System.out.println("Received offset: "+offset);

        LocalDateTime adjusted=now.plus(Duration.ofMillis(offset));
        System.out.println("Adjusted time: "+adjusted);
        socket.close();
    }

}
