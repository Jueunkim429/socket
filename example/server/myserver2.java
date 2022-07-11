import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;
import java.io.*;

public class myserver2 {
    public static void main(String args[]) throws Exception{
        ServerSocket sersock = new ServerSocket(8888);
        System.out.println("Server ready....");
        Socket sock = sersock.accept();

        OutputStream ostream = sock.getOutputStream();
        BufferedWriter bwl = new BufferedWriter(new OutputStreamWriter(ostream));
        String s2 = "Thanks client for your calling on " + new java.util.Date();
        bwl.write(s2);

        bwl.close();
        ostream.close();
        sock.close();
        sersock.close();
    }
}
