import java.rmi.*;
public interface HelloService extends Remote {
    String sayHello(String name) throws RemoteException;
} 