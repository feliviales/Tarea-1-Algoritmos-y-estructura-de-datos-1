import javax.swing.*;
import java.net.*;
import java.io.*;


public class Cliente {



    public static void main(String args[])throws Exception{

        JFrame frame = new chatCliente("Chat Cliente");
        frame.setVisible(true);

        // Abre un nuevo socket.
        Socket s=new Socket("localhost",3333);
        // Guarda lo que se recibe.
        DataInputStream din=new DataInputStream(s.getInputStream());
        // Guarda lo que se envia.
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        //
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str="",str2="";
        while(!str.equals("stop")){
            str=br.readLine(); // recoge lo que envia el servidor
            dout.writeUTF(str);
            dout.flush();
            str2=din.readUTF();
            System.out.println("Server says: "+str2);
        }

        dout.close();
        s.close();
    }

}
/*
***Para iniciar cliente se debe iniciar primero el servidor y despues cliente***
 */
