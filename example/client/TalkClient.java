import java.io.*;
import java.net.Socket;
import java.awt.*;
import java.net.ServerSocket;

public class TalkClient {
    public static void main(String args[]) throws Exception {
        Socket sock = new Socket("192.168.219.108", 8888);

        //reading from keyboard (keyRead object)
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

        //sending to client (pwrite object)
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);

        //receiving form server (receivedRead objcet)
        InputStream istream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));

        System.out.println("Start the chitchat, type and press Enter key");

        String receiveMessage, sendMessage;
        while (true){
            System.out.println("Client>> Enter sending Message: ");
            sendMessage = keyRead.readLine(); //keybodar reading
            pwrite.println(sendMessage); //sending to server
            System.out.println("-> Over: Client Waiting");
            pwrite.flush(); //flush the data

            if((receiveMessage = receiveRead.readLine()) !=null){ //receive form server
                System.out.println("Client>> Received Message from Server: "+receiveMessage);//display at DOS prompt
            }
        }
    }
}
