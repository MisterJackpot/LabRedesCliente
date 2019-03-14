import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDPBasico {


    public static void main(String args[]) throws Exception
    {

        // declara socket cliente
        DatagramSocket clientSocket = new DatagramSocket();
        File file = new File("assets/Triboar.jpg");
        final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        final DataOutputStream dataOut = new DataOutputStream(byteOut);

        byte [] mybytearray  = new byte [(int)file.length()];
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        InetAddress IPAddress = InetAddress.getByName("localhost");
        if(mybytearray.length > 64000){
            int aux = mybytearray.length;
            System.out.println(aux);
            aux = aux/64000;
            dataOut.writeInt(aux);
            dataOut.flush();
            byte[] bytes = byteOut.toByteArray();
            DatagramPacket sendSize = new DatagramPacket(bytes, bytes.length, IPAddress, 3000);
            clientSocket.send(sendSize);
            for(int i = 0; i < aux; i++){
                bis.read(mybytearray,0,(mybytearray.length/aux));
                System.out.println(i + " Sending " + file.getAbsolutePath() + "(" + (mybytearray.length/aux) + " bytes)");
                DatagramPacket sendPacket = new DatagramPacket(mybytearray, (mybytearray.length/aux), IPAddress, 3000);
                Thread.sleep(900);
                clientSocket.send(sendPacket);
            }
        }else {
            dataOut.writeInt(1);
            dataOut.flush();
            byte[] bytes = byteOut.toByteArray();
            DatagramPacket sendSize = new DatagramPacket(bytes, bytes.length, IPAddress, 3000);
            clientSocket.send(sendSize);
            bis.read(mybytearray, 0, mybytearray.length);
            System.out.println("Sending " + file.getAbsolutePath() + "(" + mybytearray.length + " bytes)");
            DatagramPacket sendPacket = new DatagramPacket(mybytearray, mybytearray.length, IPAddress, 3000);
            clientSocket.send(sendPacket);
        }

        // fecha o cliente
        clientSocket.close();
    }

}
