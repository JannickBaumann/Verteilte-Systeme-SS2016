package protobuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import protobuf.presenceProtocol.*;
import protobuf.TypeLengthValue;
import protobuf.presenceProtocol.SlaveAuthentication.Status;

public class main {
	private static final int STATUSINFO = 0;
	private static final int ACKINFO = 1;
	private static final int STRING = 555;
	public static void main(String[] args) {
		//CREATING A BINARY FILE
		//try {
			// write
			//FileOutputStream output = new FileOutputStream("message.ser");
			//Status st = new Status(0, 1) ;
			//SlaveAuthentication message = functions.sendAuthentication(new Status);
			int finish =0;
			SlaveAuthentication message1 = presenceProtocol.SlaveAuthentication.newBuilder().setStatus(Status.ON).build();
			//System.out.println(message1);
			SlaveAuthentication message2 = presenceProtocol.SlaveAuthentication.newBuilder().setStatus(Status.OFF).build();
			//System.out.println(message2);
			MasterAuthentication message3 = presenceProtocol.MasterAuthentication.newBuilder().setAck(true).build();
			//System.out.println(message3);
			String message4 = "hallo ich bin ein String";
			
			byte[] dreiMessagesTLV = new byte[100]; 
			//nachricht 1
			int offset =0;
			byte[] value = message1.toByteArray();
			byte[] test = functions.toTypeLengthValue(value, (byte)STATUSINFO);
			//test in dreiMessagesTLV kopieren ab offset
			System.arraycopy (test, 0, dreiMessagesTLV, offset, test.length);
			//System.out.println("1: "+Arrays.toString(dreiMessagesTLV));
			
			//nachricht 2
			offset = test.length;
			value = message2.toByteArray();
			test = functions.toTypeLengthValue(value, (byte)STATUSINFO);
			//test in dreiMessagesTLV kopieren ab offset
			System.arraycopy (test, 0, dreiMessagesTLV, offset, test.length);
			//System.out.println("2: "+Arrays.toString(dreiMessagesTLV));
			
			offset += test.length;
			value = message3.toByteArray();
			test = functions.toTypeLengthValue(value, (byte)ACKINFO);
			//test in dreiMessagesTLV kopieren ab offset
			System.arraycopy (test, 0, dreiMessagesTLV, offset, test.length);
			//System.out.println("3: "+Arrays.toString(dreiMessagesTLV));
			
			offset += test.length;
			value = message4.getBytes();
			test = functions.toTypeLengthValue(value, (byte)STRING);
			//test in dreiMessagesTLV kopieren ab offset
			System.arraycopy (test, 0, dreiMessagesTLV, offset, test.length);
			//System.out.println("3: "+Arrays.toString(dreiMessagesTLV));
			System.out.println("alle erstellt");
			
			//alle 3 nachrichten aus message lesen
			functions.readAllMessages(dreiMessagesTLV);
		
			/*TypeLengthValue tlv = null;
			try {
				tlv = TypeLengthValue.read(test, 0);
				System.out.println(Arrays.toString(tlv.value));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//message.writeTo(output);
			//output.close();

		//} catch (IOException e) {
		//	e.printStackTrace();
	//	}
	}

}
