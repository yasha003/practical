import mpi.MPI;
public class ArrSum {
    public static void main(String args[]) throws Exception{
        MPI.Init(args);
        int rank=MPI.COMM_WORLD.Rank();
        int size=MPI.COMM_WORLD.Size();

        int unitsize=5;
        int root=0;
        int[] sendBuffer=new int[unitsize*size];
        int[] recvBuffer=new int[unitsize];
        int[] resultBuffer=new int[size];

        if(rank==root){
            for(int i=0;i<sendBuffer.length;i++){
                sendBuffer[i]=i+1;
            }
        }
        MPI.COMM_WORLD.Scatter(sendBuffer,0,unitsize,MPI.INT,recvBuffer,0,unitsize,MPI.INT,root);
        int partialsum=0;
        for(int val:sendBuffer) partialsum+=val;
        System.out.println("Process "+rank+"Partial Sum is: "+partialsum);

        MPI.COMM_WORLD.Gather(new int[]{partialsum}, 0, 1, MPI.INT,resultBuffer,0,1,MPI.INT,root);
        if(rank==root){
            int totalsum=0;
            for(int val:resultBuffer) totalsum+=val;
            System.out.println("Totalsum: "+totalsum);
        }
        MPI.Finalize();
    }
}
