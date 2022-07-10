import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManagerThread extends Thread{

    private Socket m_socket;
    private String m_ID;

    @Override
    public void run(){
        super.run();
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
            String text;

            while(true){
                text = in.readLine();
                if(text!=null) {
                    for(int i=0;i<server.m_OutputList.size();++i){
                        server.m_OutputList.get(i).println(text);
                        server.m_OutputList.get(i).flush();
                    }
                    System.out.println(text);
                }
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setSocket(Socket _socket){
        m_socket = _socket;
    }
}