package protobuf;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

//import org.xlattice.util.UIntLib;

public class TypeLengthValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// DEFIINES ///////////////////////////////////////////
	private static final int STATUSINFO = 0;
	private static final int ACKINFO = 1;
	private static final int SIZEOFINT = 4; //Byte
	private static final int typeD = 3;
   // INSTANCE VARIABLES ///////////////////////////////////////////
   //  public final int fieldSize;
    public final int type;
    private int length;
    public final byte[] value;
    private int size;
 
     // CONSTRUCTORS /////////////////////////////////////////////////

     public TypeLengthValue (int type, int length, byte[] value) 
     {
         if (type < 0)
             throw new IllegalArgumentException("negative type: " + type);
         this.type = type;
         if (value == null || value.length == 0)
             throw new IllegalArgumentException("null or empty value array");
         this.value = value;
         if (length != value.length) {
             // XXX bit of a hack
             throw new IllegalArgumentException(
                     "length specified is different from length of value");
         }
         this.length = length;
         this.setSize(2 + value.length); // für type und für length werden jeweils 4 byte (32 bit) reserviert 
         System.out.println("My Value is: "+Arrays.toString(value));
     }
     
     
 
     // ACCESS METHODS ///////////////////////////////////////////////
 
     public int length() {
         return length;
     }
 
     // OTHER METHODS ////////////////////////////////////////////////
     public static TypeLengthValue read (byte [] message, int offset) throws IOException {
 
         if (message == null)
              throw new IllegalArgumentException("message cannot be null");
         // get big-endian type and length;
         int type;
         int len;
 
         type = message[offset++];
         
         if (type < 0)
        	 type += 256;
         
         len  = message[offset++];
         
         if (len < 0)
        	 len += 256;         
 
         // offset now points to beginning of value
 
         if (message.length < offset + len) {
             throw new IllegalStateException(
 
                 "TLV in buffer of length " + message.length
 
               + " but offset of value is " + offset
 
               + " and length is " + len);
 
         }
 
         byte[] val = new byte[len];
 
         System.arraycopy(message, offset, val, 0, len);
    
         return new TypeLengthValue(type, len, val);
 
     }
     
 
     /**
     * Write this TLV onto the message buffer at the offset indicated.
     */
 
     public int write (byte[] message, int offset) {
         int where = offset;
         message[where++] = (byte) this.type;
         message[where++] = (byte) this.length;
         //where is now behind type and length => value
         //System.arraycopy copies value to message without an reference to message 
         System.arraycopy (this.value, 0, message, where, length);
         return (where + this.length);
    }



	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}
 
 }
 
