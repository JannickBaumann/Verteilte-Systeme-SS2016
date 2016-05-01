package protobuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import protobuf.presenceProtocol.*;
import protobuf.presenceProtocol.SlaveAuthentication.Status;

public class functions {
	
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

	public static SlaveAuthentication sendAuthentication(Status status) {

		SlaveAuthentication message = presenceProtocol.SlaveAuthentication.newBuilder().setStatus(status).build();
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
}
