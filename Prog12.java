import java.util.Scanner;

public class Prog12 {
    public static void main(String[] args){
        
        //Input
        Scanner s = new Scanner(System.in);
        System.out.println("Enter bucket size");
        int bucketSize = s.nextInt();
        System.out.println("Enter number of packets");
        int packetCount = s.nextInt();
        System.out.println("Enter data rate");
        int[] data = new int[packetCount];
        for(int i=0;i<packetCount;i++)data[i]=s.nextInt();
        System.out.println("Enter output rate");
        int outputRate = s.nextInt();

        //Logic
        for(int i=0;i<packetCount;i++){
            if(data[i]<=bucketSize){
                while(data[i]>=outputRate){
                    System.out.println("Packet Transmitted "+outputRate);
                    data[i]-=outputRate;
                }
                if(data[i]>0)System.out.println("Packet Transmitted "+data[i]);
            }
            else System.out.println("Data Overflow");
        }
        s.close();
    }    
}
