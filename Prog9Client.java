import java.io.*;
import java.net.*;
public class Prog9Client {
    public static void main(String[] args) throws IOException,FileNotFoundException{
        Socket sock = new Socket("127.0.0.1",4000);
        System.out.println("Enter filename:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fname = reader.readLine();
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream,true);
        pwrite.println(fname);
        InputStream istream = sock.getInputStream();
        BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));
        String str;
        while((str=socketRead.readLine())!=null)System.out.println(str);
        pwrite.close();
        socketRead.close();
        reader.close();
        sock.close();
    }
}
