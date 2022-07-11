import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class myserver3 {
    public static void main(String args[]) throws Exception{
        //establishing the connection with the server
        ServerSocket sersock = new ServerSocket(8888);
        System.out.println("Server ready....");
        Socket sock = sersock.accept();
        System.out.println("Conntection is succeful and wating for chatting");

        //reading the file name client
        InputStream istream = sock.getInputStream();
        BufferedReader fileRead= new BufferedReader(new InputStreamReader(istream));
        String fname = fileRead.readLine();

        //reading file contents
        BufferedReader contentRead = new BufferedReader(new FileReader(fname));

        //keping outout stream ready to send the contents
        OutputStream ostream = sock.getOutputStream();
        PrintStream pwrite = new PrintStream(ostream, true);

        String str;
        while ((str= contentRead.readLine()) != null) // reading line-by-line from file
        {
            System.out.println("Received Contents From Client: "+str);
            System.out.println("Re-sending Receive Contents" + str);
            pwrite.println(str); //sending each line to client
        }

        sock.close();
        sersock.close(); //closing network sockets
        pwrite.close();
        fileRead.close();
        contentRead.close();
    }
}
