import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

public class chatCliente extends JFrame {

    private JPanel mainPanel;
    public JTextArea escrituraCliente;
    public JTextField valorProducto;
    public JButton botonEnviar;
    public JTextField pesoPrducto;
    private Socket s;
    private DataInputStream din;
    private DataOutputStream dout;
    private BufferedReader br;
    String str= valorProducto.getText();
    String str2 = "";

    public chatCliente(String title) throws IOException {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        s=new Socket("localhost",3333);
        din = new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //accion del boton
        String str= valorProducto.getText();
        String str2 = "";

        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valor= valorProducto.getText();
                try {
                    enviaCadena(valor);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }


    public void enviaCadena(String cadena) throws IOException {
       // envia nuemros enteros desde el campo de texto
        try {
            dout.writeUTF(cadena);
            dout.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        // recibe respuesta del servidor y la muestra en el area de texto
        str2=din.readUTF();
        escrituraCliente.setText(str2);

    }


    public static void main(String[] args) throws IOException {
        //instancea el nuevo frame
        JFrame frame = new chatCliente("Chat Cliente");
        // lo hace visible
        frame.setVisible(true);

    }
}


