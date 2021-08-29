import javax.swing.*;
import java.io.*;
import java.net.*;



public class Servidor {




    public static void main(String args[])throws Exception{

        ServerSocket ss = new ServerSocket(3333);
        Socket s=ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str="",str2="Digite el peso del producto";

        int contador = 0;
        int peso = 0;
        int valor = 0;

        while(contador < 2)
        {


            if (contador == 0)
            {
                str = "";
                str=din.readUTF();
                System.out.println("client says: "+str);
                valor = Integer.parseInt(str);
            }
            else
            {
                str = "";
                str=din.readUTF();
                System.out.println("client says: "+str);
                peso = Integer.parseInt(str);
            }
            dout.writeUTF(str2);
            dout.flush();



                double resultado = valor * 0.13 + peso * 0.15;
                dout.writeUTF("resultado: " + resultado);
                dout.flush();


            contador = contador + 1;

        }



    }
}
