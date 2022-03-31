// import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Prog10Client {
    public static void main(String[] args) throws Exception{
        
        // BufferedReader infromuser = new BufferedReader(new InputStreamReader(System.in));
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] SendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        System.out.println("Enter Start to Connect to Server");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        while(!str.equals("Start")){
            str = s.nextLine();
        }
        SendData = str.getBytes();
        DatagramPacket SendPacket = new DatagramPacket(SendData, SendData.length ,IPAddress,9876);
        DatagramSocket ClientSocket = new DatagramSocket();
        ClientSocket.send(SendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
        ClientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("Message recieved from server:"+ modifiedSentence);
        ClientSocket.close();
        // infromuser.close();
        s.close();
    }
}
