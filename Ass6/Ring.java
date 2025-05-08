import java.util.Scanner;
public class Ring {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int n=sc.nextInt();
        boolean[] alive=new boolean[n];

        for(int i=0;i<n;i++) alive[i]=true;
        int init;
        int failed;
        while(true){
            System.out.println("Enter Initor process: ");
            init=sc.nextInt();
            System.out.println("Enter Failed process: ");
            failed=sc.nextInt();
            alive[failed]=false;
            if(init!=failed && init>=0 && init<n && failed>=0 &&failed<n) break;
            System.out.println("Invalid");
        }
        System.out.println("Electeion process started by "+init);
        int coor=init;
        int current=(init+1)%n;

        System.out.println("Message passed: "+init);
        while(current!=init){
            if(alive[current]){
                System.out.println("->"+current);
                if(current>coor)coor=current;
            }
            current=(current+1)%n;
        }
        System.out.println("Coor: "+coor);
        sc.close();
    }
}
