import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient{
    public static void main(String args[]){
        try{
        Registry registry=LocateRegistry.getRegistry("localhost", 1099);
        HelloService service=(HelloService) registry.lookup("HelloService");

        for(int i=0;i<5;i++){
            final int id=i;
            new Thread(()->{
                try{
                    String response=service.sayHello("Client"+id);
                    System.out.println("Response: "+response);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}