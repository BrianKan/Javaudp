import java.io.*;
import java.net.*;

public class Client{
 
	
	public static void main(String args[]) throws Exception
   {
		boolean connected=true;
		System.out.print("Client Connected:\n");
		
		// INITIALIZATION
		 DatagramSocket clientSocket = new DatagramSocket();
	      InetAddress IPAddress = InetAddress.getByName("localhost");
	      byte[] sendData = new byte[512];
	      byte[] receiveData = new byte[512];
	      String sentence = "Connection from "+IPAddress;
	      sendData = sentence.getBytes();
	      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 4445);
	      clientSocket.send(sendPacket);
	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	      clientSocket.receive(receivePacket);
	      String modifiedSentence = new String(receivePacket.getData()).trim();
	      System.out.println("FROM SERVER: Server is Ready");
	      clientSocket.close();
	      
	while(connected) {
		 System.out.print("Client: ");
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      
      clientSocket = new DatagramSocket();
      
      IPAddress = InetAddress.getByName("localhost");
      
      
      sendData = new byte[512];
      receiveData = new byte[512];
      
      
      sentence = inFromUser.readLine();
      sendData = sentence.getBytes();
      sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 4445);
      
      clientSocket.send(sendPacket);
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      modifiedSentence = new String(receivePacket.getData()).trim();
      System.out.println(modifiedSentence+"\n");
      clientSocket.close();
      if(sentence.contains("EXIT")) {
    	  connected=false;
      }
	}
   }
}