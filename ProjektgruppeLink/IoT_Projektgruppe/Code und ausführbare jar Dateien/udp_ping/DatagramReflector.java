package udp_ping;

import java.net.*;
import java.io.*;

/**
 *
 * @author  cli
 * @version 
 */
/*
 * Der Reflector funktioniert exakt genauso wie der Sender, nur in umgekehrter Reihenfolge.
 * Daher siehe Kommentare fuer DatagramSender.
 * Der Sender verschickt ein Paket und wartet dann (blockierend) auf eine Antwort.
 * Der Reflector wartet zuerst (blockierend) und sendet dann das Antwortpaket.
 * 
 */

public class DatagramReflector extends Thread {
    InetAddress     _localAddress;
    int             _localPort;
    DatagramSocket  _socket;
    long            _pingerID;
    int             _datagramSize;
    

    /** Creates new DatagramReflector */
    public DatagramReflector() 
    {
    	//nothing to do
    }
    
    public void initialize(long id, String localIP, String localPort, int datagramSize) throws UnknownHostException, SocketException 
    {
        _pingerID       = id;
        _localPort      = Integer.parseInt(localPort);
        _localAddress   = InetAddress.getByName(localIP);
        _datagramSize   = datagramSize;
        _socket = new DatagramSocket(_localPort, _localAddress);
    }
    
    public void run()
    {
        boolean loop = true;
        byte[] buffer = new byte[_datagramSize];
        DatagramPacket receiveDatagram = new DatagramPacket(buffer, _datagramSize);
        while(loop)
        {
            try
            {
                _socket.receive(receiveDatagram);
            }
            catch(IOException ioe)
            {
                loop = false;
                System.out.println("DatagramReflector::run()");
                ioe.printStackTrace();
            }
            if(loop)
            {
                handlePacket(receiveDatagram);
            }
            yield();
        }
    }

    protected void handlePacket(DatagramPacket packet)
    {
        long currentTime = System.currentTimeMillis();
        long packetID = 0;
        long packetTransmitTimestamp = 0;
        int  packetNumber = 0;
        byte[] buffer = packet.getData();
        ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer, 0, packet.getLength());
        DataInputStream dataStream = new DataInputStream(byteStream);
        try
        {
            packetID = dataStream.readLong();
            packetTransmitTimestamp = dataStream.readLong();
            packetNumber = dataStream.readInt();
        }
        catch(IOException ioe)
        {
            System.out.println("DatagramReflector::handlePacket() 1");
            ioe.printStackTrace();
        }
        if(packetID != _pingerID)
        {
            try
            {
                DatagramPacket echoPacket = packet;
                System.out.println("echoing #" + packetNumber + " containing " + echoPacket.getLength() + " bytes to " + echoPacket.getAddress().toString() + ":" + echoPacket.getPort());
                _socket.send(echoPacket);
            }
            catch(IOException ioe)
            {
                System.out.println("DatagramReflector::handlePacket() 2");
                ioe.printStackTrace();
            }
        }
        else
        {
            System.out.println("received packet #" + packetNumber + " roundtrip time: " + (currentTime-packetTransmitTimestamp) + "ms");
        }
    }
}
