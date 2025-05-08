import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class RMIServer {
    public static void main(String args[]){
        try{
           Registry registry=LocateRegistry.createRegistry( 1099);
            HelloService service=new HelloServiceImp();

            registry.rebind("HelloService",service);
            System.out.println("RMI server is running....");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
