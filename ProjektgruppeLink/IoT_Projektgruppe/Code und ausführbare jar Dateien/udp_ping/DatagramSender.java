package udp_ping;

import java.net.*;
import java.io.*;

/**
 *
 * @author  cli
 * @version 
 */
public class DatagramSender extends java.lang.Thread {
    InetAddress     _targetAddress;
    int             _targetPort;
    DatagramSocket  _socket;
    long            _pingerID;
    int             _datagramSize;
    int             _datagramNumber;

    /** Creates new DatagramReflector */
    public DatagramSender() 
    {
    	//do nothing
    }
    
    public void initialize(long id, String targetAddress, String targetPort, int datagramSize) throws UnknownHostException, SocketException 
    {
        _pingerID       = id;
        _targetPort     = Integer.parseInt(targetPort);
        _targetAddress  = InetAddress.getByName(targetAddress);
        _datagramSize   = datagramSize;
        _datagramNumber = 0;
        _socket = new DatagramSocket();
        _socket.setSoTimeout(1000);
    }
    
    public void run()
    {
        boolean loop = true;
        boolean gotDatagram = false;
        byte[] buffer = new byte[_datagramSize];
        DatagramPacket receiveDatagram = new DatagramPacket(buffer, _datagramSize);
        while(loop)
        {
            try
            {
            	//Das Paket p zum Senden vorbereiten und abschicken
                DatagramPacket p = nextPacket();
                _socket.send(p);
            }
            catch(IOException ioe)
            {
                System.out.println("DatagramSender::run() " + ioe.toString());
            }
            try
            {
            	/*
            	 * Auf das Antwortpaket warten.
            	 * 
            	 */
                receiveDatagram.setData(buffer, 0, _datagramSize);
                _socket.receive(receiveDatagram);
                
                //Ist die Laenge des Pakets groesser 0, enthaelt es Inhalt und ist somit gueltig
                if(receiveDatagram.getLength() > 0)
                {
                    gotDatagram = true;
                }
            } 
            catch(IOException ioe)
            {
                //loop = false;
                gotDatagram = false;
                if(ioe instanceof InterruptedIOException)
                {
                    // OK: timeout
                }else
                {
                    System.out.println("DatagramSender::run() " + ioe.getMessage());
                }
            }
            //Wenn Paket angekommen und noch in der Schleife, dann Paket verarbeiten
            if(loop && gotDatagram)
            {
                handlePacket(receiveDatagram);
            }
            sleepABit();
            yield();
        }
    }
    
    protected DatagramPacket nextPacket()
    {
    	/*
    	 * Den ByteArrayOutputStream erstellen (Buffer) und mit dem neu angelegten
    	 * DataOutputStream verknuepfen
    	 */
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(_datagramSize);
        DataOutputStream dataStream = new DataOutputStream(byteStream);
        
        //Daten in den Stream bzw. Buffer reinlegen
        try
        {
            dataStream.writeLong(_pingerID);
            dataStream.writeLong(System.currentTimeMillis());
            dataStream.writeInt(_datagramNumber++);
            dataStream.writeChars("UDPPing");
            //Nutzdaten reinlegen
            byte[] tail = new byte[50000];
            for(int i=0;i<tail.length; i++)
            {
                tail[i] = (byte)((i%64) + 30);
            }
            dataStream.write(tail,0,tail.length);
        } 
        catch(IOException ioe)
        {
            System.out.println("DatagramSender::nextPacket()");
            ioe.printStackTrace();
        }
        /*
         * Den ByteStreamBuffer in ein ByteArray umwandeln und in ein neues
         * Datagram-Objekt legen. Das Datagram-Objekt erwartet zwecks der Serialisierbarkeit
         * ein Byte-Array. Per return wird das vorbereitete Packet zurueckgegeben 
         * und abgeschickt. 
         * Siehe dazu Zeile 47
         * 
         */       
        byte[] datagramBuffer = byteStream.toByteArray();
        DatagramPacket datagram = new DatagramPacket(datagramBuffer, datagramBuffer.length, _targetAddress, _targetPort);
        return datagram;
    }

    /*
     * Diese Funktion verarbeitet das nun angekommene Paket
     */
    protected void handlePacket(DatagramPacket packet)
    {
        long currentTime = System.currentTimeMillis();
        long packetID = 0;
        long packetTransmitTimestamp = 0;
        int  packetNumber = 0;
        byte[] buffer = packet.getData();
        
        /*
         * Fuer ein eingehendes Paket muss - analog zum ausgehenden Paket - ein ByteArrayInputStream
         * sowie der dazugehoerige DataInputStream angelegt werden. Das ByteArray wird wie beim Senden
         * auch mit dem DataInputStream verknuepft.
         * 
         */
        ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer, 0, packet.getLength());
        DataInputStream dataStream = new DataInputStream(byteStream);
        
        //Daten auslesen
        try
        {
            packetID = dataStream.readLong();
            packetTransmitTimestamp = dataStream.readLong();
            packetNumber = dataStream.readInt();
        }
        catch(IOException ioe)
        {
            System.out.println("DatagramSender::handlePacket() 1");
            ioe.printStackTrace();
        }
        
        /*
         * Entspricht die packetID der pingerID, handelt es sich um das soeben verschickte Paket.
         * Dadurch wird sichergestellt das dieses Paket nicht von einem anderen Client kommt.
         */
        
        if(packetID == _pingerID)
        {
            System.out.println("received packet #" + packetNumber + " roundtrip time: " + (currentTime-packetTransmitTimestamp) + "ms");
        }
        else
        {
            System.out.println("received unknown packet!");
        }
    }
    
    //Diese Funktion laesst das Programm 1 Sekunde warten.
    protected void sleepABit()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("DatagramSender::sleepABit()");
            e.printStackTrace();
        }
    }
}
