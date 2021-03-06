
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class pc_client {
    /*public static void main(String[] args) {
        JFrame jf = new ClientFrame();
        jf.setSize(550,500);
        jf.setVisible(true);
    }*/
    public static void main(String args[]) throws Exception {
        Socket sock = new Socket("10.101.15.36", 7773);
        JFrame jf = new ClientFrame();
        jf.setSize(550,500);
        jf.setVisible(true);

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

class ClientFrame extends JFrame implements ActionListener,KeyListener{
    JLabel ip = new JLabel("Ip");
    JTextField ipField = new JTextField(13);
    JLabel port = new JLabel("Port");
    JTextField portField = new JTextField(5);
    JLabel id = new JLabel("ID");
    JTextField idField = new JTextField(5);
    JButton con = new JButton("????????????");
    JButton disCon = new JButton("????????????");
    List list = new List();
    JTextField uMsg = new JTextField(20);
    JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    String uid = "";

    //????????? ??????
    Socket socket;
    DataOutputStream out;

    {
        top.setSize(550,200);
        top.setLayout(new FlowLayout());
        top.add(ip);
        top.add(ipField);
        top.add(port);
        top.add(portField);
        top.add(id);
        top.add(idField);
        top.add(con);
        bottom.add(disCon);
        bottom.add(uMsg);
        bottom.setSize(550, 200);
        con.addActionListener(this);
        disCon.addActionListener(this);
        uMsg.addKeyListener(this);
    }
    public ClientFrame() {
        this.setLayout(new BorderLayout());
        this.add("North",top);
        this.add("Center",list);
        this.add("South",bottom);

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                out.writeUTF("["+uid+"]"+uMsg.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            uMsg.setText("");
        }
    }
    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == con){
            String uip = ipField.getText();
            int uport = Integer.parseInt(portField.getText());
            uid = idField.getText();
            init(uid,uip,uport);
        }else if(obj==disCon){
            System.exit(0);
        }

    }
    public void init(String uid, String uip, int uport){
        try {
            String serverIp = uip;
            // ????????? ???????????? ????????? ????????????.
            socket = new Socket(serverIp, uport);
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("????????? ?????????????????????.");
            //????????? ????????????
            System.out.println(uid);
            out.writeUTF(uid);

            //Thread sender = new Thread(new ClientSender(socket, uid));
            Thread receiver = new Thread(new ClientReceiver(socket));

            //sender.start();
            receiver.start();
        } catch(ConnectException ce) {
            ce.printStackTrace();
        } catch(Exception e) {}
    }

    class ClientReceiver extends Thread {
        Socket socket;
        DataInputStream in;

        ClientReceiver(Socket socket) {
            this.socket = socket;
            try {
                in = new DataInputStream(socket.getInputStream());
            } catch(IOException e) {}
        }

        public void run() {
            String text;
            while(in!=null) {
                try {
                    String re = in.readUTF();
                    System.out.println(re);
                    list.add(re);
                    /*text = in.readLine();
                    System.out.println(text);
                    list.add(text);*/
                } catch(IOException e) {}
            }
        } // run
    }//end class ClientReceiver


}
