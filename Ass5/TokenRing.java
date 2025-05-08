import java.util.Scanner;
public class TokenRing{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the number of nodes: ");
        int n=sc.nextInt();
        System.out.println("Ring: ");
        for(int i=1;i<=n;i++) System.out.print(i+"->");
        System.out.print("0");

        int token=0;
        System.out.println("Token starts at node 0.");

        while(true){
            int sender=getnode(sc,n,"Enter the sender node(0 to "+(n-1)+"):");
            int receiver=getnode(sc,n,"Enter the Receiver node(0 to "+(n-1)+"):");
            sc.nextLine();

        System.out.print("Enter Data to pass: ");
        String data=sc.nextLine();

        System.out.print("Token Passing: ");
        for(int i=token;i!=sender;i=(i+1)%n) System.out.print(i+"->");
        System.out.print(sender+"Token Recieved");

        System.out.print("Data Passing:");
        for(int i=sender; i!=receiver;i=(i+1)%n) System.out.print(i+"->");
        System.out.print(receiver+"Data Recieved");

        System.out.print("Receiver "+receiver+"got data"+data);
        token=sender;

        System.out.println("Send again(1-yes,0-no): ");
        if(sc.nextInt()!=1) break;
        }
        sc.close();
    }
    private static int getnode(Scanner sc, int n, String msg){
        int node;
        do{
            System.out.println(msg);
            node=sc.nextInt();
        }
        while(node<0||node>=n);
        return node;
    }

}