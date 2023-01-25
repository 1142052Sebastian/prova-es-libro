import java.net.*;

public class UDPServerTempo {
    public static void main(String[] args) throws Exception {
        DatagramSocket ssc = new DatagramSocket(9876);     // assegna la porta al socket del server
        byte[] reciveBuffer = new byte[1024];                   // buffer per i dati ricevuti
        byte[] sendBuffer = new byte[1024];                     // buffer per i dati inviati
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(reciveBuffer, reciveBuffer.length);
            ssc.receive(receivePacket);                         // riceve il datagramma dal client
            long tempo = System.currentTimeMillis() / 1000;     // calcola il numero di secondi trascorsi dal 1 gennaio 1970
            sendBuffer = Long.toBinaryString(tempo).getBytes(); // converte il numero di secondi in formato binario e lo inserisce nel buffer dei dati inviati
            InetAddress indirizzoIp = receivePacket.getAddress(); // prende l'indirizzo IP del client
            int port = receivePacket.getPort();                 // prende la porta del client
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, indirizzoIp, port);
            ssc.send(sendPacket);                               // invia il datagramma di risposta al client
        }
    }
}
