import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.*;
import java.net.*;

public class MyClient2 {
    public static void main(String args[]) throws Exception {
        Socket sock = new Socket("192.168.219.108", 8888);

        InputStream istream = sock.getInputStream();
        BufferedReader br1 = new BufferedReader(new InputStreamReader(istream));

        String s1= br1.readLine();
        System.out.println(s1);

        br1.close();
        istream.close();
        sock.close();
    }
}
