import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImp extends UnicastRemoteObject implements HelloService {
    protected HelloServiceImp() throws RemoteException{
        super();
    }
    public String sayHello(String name) throws RemoteException{
        System.out.println("Received Request from "+name);
        return "Hello "+name+" you are connected to RMi server";
    }
    
}
