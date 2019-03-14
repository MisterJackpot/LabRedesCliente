import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ClienteTCPBasico {
    public static void main(String[] args) {
        try {
            Socket cliente = new Socket("localhost",3000);
            System.out.println("Connection Established: ");
            File file = new File("assets/Triboar.jpg");
            byte [] mybytearray  = new byte [(int)file.length()];
            FileInputStream  fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(mybytearray,0,mybytearray.length);
            OutputStream os = cliente.getOutputStream();
            System.out.println("Sending " + file.getAbsolutePath() + "(" + mybytearray.length + " bytes)");
            os.write(mybytearray,0,mybytearray.length);
            os.flush();
            System.out.println("Done.");

            bis.close();
            os.close();
            cliente.close();
        }
        catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}