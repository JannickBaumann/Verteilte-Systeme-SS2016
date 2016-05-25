package protobuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import protobuf.presenceProtocol.*;
import protobuf.brokerageProtocol.*;
import protobuf.presenceProtocol.SlaveAuthentication.Status;

public class functions {
	
	//typen: atom, kohle, öl, gas, 
	//nicht in der börse: wind, wasser, sonne
	//strombörse in leibzig eex
	//wenn verbraucher zu viel haben wird das dort 
	
	//für wie viele tage weil es einige verbraucher gibt, die für die nächsten 6 jahre strom kaufen
public static consumer sendInformation(int kwh, int price, creator.Type type, int anzTage) {
		consumer message = brokerageProtocol.consumer.newBuilder().setKWh(kwh)
		.setPrice(price).setAnzTage(anzTage).setType(type)		
		.build();
		return message;

		// create binary
		// send File on Socket
	}


public static creator sendInformation(int kwh, creator.Type type) {
	creator message = brokerageProtocol.creator.newBuilder().setKWh(kwh).setType(type)	
	.build();
	return message;

	// create binary
	// send File on Socket
}
	
	
	public static SlaveAuthentication receiveMessage() {

		// recieve File from Socket
		try {
			// read
			SlaveAuthentication messageFromFile = SlaveAuthentication.parseFrom(new FileInputStream("message.ser"));
			System.out.println(messageFromFile);
			return messageFromFile;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static SlaveAuthentication sendAuthentication(Status status, int id, String type, long time, String deviceInfo) {
		

		SlaveAuthentication message = presenceProtocol.SlaveAuthentication.newBuilder().setStatus(status)
		.setId(id).setType(type).setSystemTime(time).setDeviceInfo(deviceInfo)				
		.build();
		return message;

		// create binary
		// send File on Socket
	}

	
	public static void receiveAuthentication() {

		SlaveAuthentication authMessage = receiveMessage();
		//handle Authentication
	}

	public static MasterAuthentication sendAck(boolean ack) {

		MasterAuthentication message = presenceProtocol.MasterAuthentication.newBuilder().setAck(ack).build();
		return message;

		// send File on Socket

	}

	public static void receiveAck() {

		SlaveAuthentication ackMessage = receiveMessage();
		// Handle Ack Data.TBD
	}
	
	/*public static void toTypeLengthValue(byte[] value)
	{
		byte[] typeLength = {(byte)0, (byte)value.length}; 
		byte[] typeLengthValue = new byte[typeLength.length + value.length];
		System.out.println("länge: "+ typeLength.length);
		System.arraycopy(typeLength, 0, typeLengthValue, 0, typeLength.length);
		System.arraycopy(value, 0, typeLengthValue, typeLength.length, value.length);
		System.out.println("Type Length Value: "+Arrays.toString(typeLengthValue));
		
		TypeLengthValue test = null;
		
		try {
			test = TypeLengthValue.read(typeLengthValue, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Value: "+Arrays.toString(test.value));
	}*/
	
	public static byte[] toTypeLengthValue(byte[] value, byte type)
	{
		TypeLengthValue tlv = new TypeLengthValue(type, (byte)value.length, value);
		byte[] typeLengthValue = new byte[tlv.getSize()]; 
		tlv.write(typeLengthValue, 0);
		System.out.println("Type Length Value: "+ Arrays.toString(typeLengthValue));
		return typeLengthValue;
	}
	
	public static ArrayList<TypeLengthValue> readAllMessages(byte[] message)
	{
		ArrayList<TypeLengthValue> result = new ArrayList<>();
		int offset =0, finish = 0, counter =0; //counter für die position der ArraayList
		System.out.println();
		System.out.println("Read a Message with multiple TLV Objekts an save them in an array");
		while(finish != 1)
		{
			try {
				result.add(TypeLengthValue.read(message, offset));
				//System.out.println("Value: "+Arrays.toString(result.get(counter).value));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//for(int j = offset; j< (offset+4); j++)
				//System.out.print((dreiMessagesTLV[j])+ "  ");
			offset += result.get(counter).getSize();
			counter++;
			System.out.println();
			//wenn die Länge des nächsten = 0 ist oder wir am ende des Byte arrays angekommen sind
			if(offset >= message.length || message[offset+1] == 0 )
				finish = 1;
		}
		return result;
	}
}
