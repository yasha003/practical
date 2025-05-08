import java.util.*;
import java.net.*;
import java.time.*;
import java.io.*;
public class Server {
    public static void main(String args[]) throws IOException{
       ServerSocket server=new ServerSocket(5050);
       System.out.println("Waiting for 3 clients...."); 

       List<Socket>Clients=new ArrayList<>();
       List<LocalDateTime>ClientTimes=new ArrayList<>();

       while(Clients.size()<3){
        Socket clientSocket=server.accept();
        System.out.println("Client "+(Clients.size()+1)+"connected");
        Clients.add(clientSocket);
       
       BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String timeStr=in.readLine();
        LocalDateTime clientTime=LocalDateTime.parse(timeStr);
        ClientTimes.add(clientTime);
        System.out.println("Recieved Client time: "+clientTime);
       }
    LocalDateTime serverTime=LocalDateTime.now();
    ClientTimes.add(serverTime);
    System.out.println("Server Time: "+serverTime);

    LocalDateTime avgTime=CalculatorAvg(ClientTimes);
    System.out.println("Average time: "+avgTime);

    //sending offset
    for(int i=0;i<Clients.size();i++){
        long offset=Duration.between(ClientTimes.get(i), avgTime).toMillis();
        PrintWriter out=new PrintWriter(Clients.get(i).getOutputStream(),true);
        out.println(offset);
        System.out.println("Sent offset to client"+(i+1)+"offset "+offset+"ms");
    }
    server.close();
}
private static LocalDateTime CalculatorAvg(List<LocalDateTime> times){
    long totalMillis=0;
    for(LocalDateTime time:times) {
        totalMillis+=time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
    long avgmiliis=totalMillis/times.size();
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(avgmiliis),ZoneId.systemDefault());

}
}
