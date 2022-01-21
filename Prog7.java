// Need to see
import java.util.Scanner;
public class Prog7 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of data bits");
        int n = s.nextInt();
        System.out.println("Enter number of generator bits");
        int m = s.nextInt();
        System.out.println("Enter Data Bits");
        int[] d = new int[n+m];
        for(int i=0;i<n;i++)d[i]=s.nextInt();
        for(int i=0;i<m-1;i++)d[n+i]=0;
        System.out.println("Enter Generator Bits");
        int[] g = new int[m];
        for(int i=0;i<m;i++)g[i]=s.nextInt();
        
        int[] r = new int[n+m];
        int[] z = new int[m];
        for(int i=0;i<m;i++){
            r[i] = d[i];
            z[i] = 0;
        }
        for(int i=0;i<n;i++){
            int k=0;
            int msb = r[i];
            for(int j=i;j<m+i;j++){
                r[j] = msb==0 ? xor(r[j],z[k]):xor(r[j],g[k]);
                k++;
            }
            r[m+i]=d[m+i];
        }
        System.out.println("The code bit added");
        for(int i=n;i<m+n-1;i++){
            d[i] = r[i];
            System.out.print(d[i]);
        }
        System.out.println("The Code Data is:");
        for(int i=0;i<m+n-1;i++)System.out.print(d[i]);  
        s.close();        
    }
    public static int xor(int x,int y){
        return x==y?0:1;
    }
}
