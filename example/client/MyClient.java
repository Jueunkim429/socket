import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.OutputStream;
import java.io.DataInputStream;

public class MyClient {
    public static void main(String args[]) throws Exception{
        Socket sock = new Socket("192.168.219.108",8888);

        String message1 = "Accept Best Wishes, My server";

        OutputStream ostream = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(ostream);
        dos.writeBytes(message1);
        dos.close();
        ostream.close();
        sock.close();
    }
}
