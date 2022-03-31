/*
RSA Program
Algorithm:
1.Consider two prime numbers p and q.
2.Compute n = p*q
3.Compute ϕ(n) = (p – 1) * (q – 1)
4.Choose e such gcd(e , ϕ(n) ) = 1
5.Calculate d such e*d mod ϕ(n) = 1
6.Public Key {e,n} Private Key {d,n}
7.Cipher text C = Pe mod n where P = plaintext
8.For Decryption D = Dd mod n where D will refund the plaintext.
*/
import java.util.Scanner;
class Prog11{
    public static int mult(int text,int key1,int key2){
        int k=1;
        for(int i=1;i<=key1;i++)k=(k*text)%key2;
        return k;
    }
    public static int gcd(int m, int n){return n==0 ? m : gcd(n,m%n);}
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter 2 unique prime numbers");
        int p = s.nextInt();
        int q = s.nextInt();
        int n = p*q;
        int phi = (p-1)*(q-1);
        System.out.println("Enter message to be encryped (integer)");
        int m = s.nextInt();
        int e,d=phi;
        do{
            System.out.println("Enter an integer which isnt a factor of "+phi);
            e = s.nextInt();
        }while(gcd(phi,e)!=1);
        while((e*d)%phi!=1)d++;
        System.out.println("Public Key is "+e+","+n);
        System.out.println("Private Key is "+d+","+n);
        int cipherText = mult(m,e,n);
        System.out.println("CipherText: "+cipherText);
        int decipherText = mult(cipherText, d, n);
        System.out.println("DecipherText: "+decipherText);
        s.close();
    }
} 