import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {
    public static ArrayList<PrintWriter> m_OutputList;

    public static void main(String[] args){
        m_OutputList = new ArrayList<PrintWriter>();

        try{
            ServerSocket server_socket = new ServerSocket(7773);
            System.out.println("server is ready");
            while(true){
                Socket client_socket = server_socket.accept();
                ClientManagerThread c_thread = new ClientManagerThread();
                c_thread.setSocket(client_socket);
                m_OutputList.add(new PrintWriter(client_socket.getOutputStream()));

                //클라이언트 정보 출력
                System.out.println(m_OutputList.size()+"번 째 Client");
                System.out.println("client 소켓 주소 : "+client_socket.getInetAddress() + " , port 번호 : " + client_socket.getLocalPort());

                c_thread.start();

            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
