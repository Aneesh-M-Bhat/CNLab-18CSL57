import java.net.*;
import java.util.Scanner;
public class Prog10Server {
    public static void main(String[] args)throws Exception{
        DatagramSocket serverSocket = new DatagramSocket(9876);
        System.out.println("Server Started");
        byte[] recieveData = new byte[1024];
        DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
        serverSocket.receive(recievePacket);
        recievePacket.getData();
        InetAddress IPAddress =  recievePacket.getAddress();
        int port = recievePacket.getPort();
        System.out.println("Client Connected");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter message");
        String message = input.nextLine();
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,port);
        serverSocket.send(sendPacket);
        serverSocket.close();
        input.close();
        System.exit(0);
    }
}
