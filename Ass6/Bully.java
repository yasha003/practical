import java.util.Scanner;

public class Bully{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] alive=new int[100];
        int[] priority=new int[100];

        System.out.println("Enter number of process: ");
        int n=sc.nextInt();

        for(int i=0;i<n;i++){
            System.out.println("Process "+i+"alive?");
            alive[i]=sc.nextInt();
            System.out.println("Process"+i+" priority");
            priority[i]=sc.nextInt();
        }

        int initiator;
        while(true){
            System.out.println("Enter Initiator process: ");
            initiator=sc.nextInt();
            if(alive[initiator]==1 && initiator>=0 &&initiator<n) break;
            System.out.println("Invalid");
        }
        System.out.println("Election started by initiator "+(initiator+1));
        int coor=initiator;
        for(int i=0;i<n;i++){
            if(alive[i]==1 &&priority[i]>priority[initiator]){
                System.out.println("process send from "+(initiator+1)+" to "+(i+1));
                if (priority[i]>priority[coor]) coor=i;
            }
        }
        System.out.println("cood is"+(coor+1));
        sc.close();
    }
}