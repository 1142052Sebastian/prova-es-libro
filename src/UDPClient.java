import java.net.*;

public class UDPClient{
    public static void main(String[] args) throws Exception {
        DatagramSocket cs = new DatagramSocket(); // crea il socket del client
        InetAddress indirizzoIp = InetAddress.getByName("localhost"); // indirizzo IP del server
        byte[] sendBuff = new byte[1024]; // buffer per i dati inviati
        byte[] reciveBuff = new byte[1024]; // buffer per i dati ricevuti
        DatagramPacket invioDatagramma = new DatagramPacket(sendBuff, sendBuff.length, indirizzoIp, 9876);
        cs.send(invioDatagramma); // invio il datagramma al server
        cs.setSoTimeout(1000); // impostazione di un timeout di 1 secondo
        try {
            DatagramPacket ricevoDatagramma = new DatagramPacket(reciveBuff, reciveBuff.length);
            cs.receive(ricevoDatagramma); // riceve il datagramma di risposta dal server
            String risposta = new String(ricevoDatagramma.getData());
            System.out.println("Tempo ricevuto dal server: " + risposta +" Secondi");
        } catch (SocketTimeoutException e) {
            System.out.println("TIMEOUT: Non è stata ricevuta alcuna risposta in tempo");
        }
        cs.close();
    }
}