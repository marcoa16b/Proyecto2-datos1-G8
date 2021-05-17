
package B_Cliente.main;
import  java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
public class MultiClient {
    public static void main(String[] args) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip,2345);

            DataInputStream dis= new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while(true) {
                System.out.println(dis.readUTF());
                String tosend=sc.nextLine();
                dos.writeUTF(tosend);

                if(tosend.equals("Exit")) {
                    s.close();
                    System.out.println("Connection Closed");
                    break;
                }
                String received = dis.readUTF();
                System.out.println("Server Says: "+ received);
            }
            sc.close();
            dis.close();
            dos.close();
        }
        catch(Exception e) {
            System.out.println("Client Error"+e);
        }
    }
}
