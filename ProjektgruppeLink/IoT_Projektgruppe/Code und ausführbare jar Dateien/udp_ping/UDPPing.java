package udp_ping;
/**
 *
 * @author  cli
 * @version 
 */
public class UDPPing extends Object {
    
    /** Creates new UDPPing */
    public UDPPing() 
    {
        //do nothing
    }

    public void startThreads(String args[]){
        String IP      = args[0];
        String Port    = args[1];
        long id = System.currentTimeMillis();
        
        /*
         * Sind beim Programmstart genau zwei Argumente uebergeben worden (IP-Adresse und Port),
         * so wird ein Sender erstellt.
         */
        if(args.length == 2){
            DatagramSender sender = new DatagramSender();
            try
            {
                sender.initialize(id, IP, Port, 128);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.exit(13);
            }
            sender.start();
        }
        /*
         * Ist das dritte Argument ( args[2] ) mit "echo" angegeben, 
         * so wird der Reflector erstellt.
         * Beide Klassen, der Sender und der Reflector, erben jeweils von der Klasse Thread.
         * Demnach ueberschreiben beide Klassen die vererbte Methode run(). Durch den Aufruf von
         * sender.start() bzw. reflector.start() wird die run-Methode aufgerufen und erstellt
         * einen Thread.
         */
        else if(args[2].equals("echo"))
        {
            DatagramReflector reflector = new DatagramReflector();
            try
            {
                reflector.initialize(id, IP, Port, 128);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.exit(12);
            }
            reflector.start();
        }
    }
    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {

    	/*
    	 * Sind weniger als 2 oder mehr als 3 Argumente angegeben, ist
    	 * der Programmaufruf ung�ltig.
    	 */
        if(args.length < 2 || args.length > 3)
        {
            System.out.println("usage:");
            System.out.println("java UDPPing targetIP targetPort [echo]");
            System.out.println();
        }
        
        /*
         * Sind 2 oder 3 Argumente angegeben wird das Programm ausgefuehrt,
         * entweder als Sender oder als Reflector. 
         */
        else
        {
            UDPPing pinger = new UDPPing();
            pinger.startThreads(args);
        }
    }
}
