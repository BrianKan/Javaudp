import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Server {
	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(4445);

		boolean reverseActive = false;
		boolean upperActive = false;
		boolean lowerActive = false;
		byte[] receiveData = new byte[512];
		byte[] sendData = new byte[512];
		ArrayList<String> storedData=new ArrayList<String>();
		System.out.print("Server Online\n");
		while (true) {
			sendData=new byte[512];
			receiveData = new byte[512];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String sentence = new String(receivePacket.getData()).trim();
			System.out.println(IPAddress+" sends : " + sentence);
		
			
			if (reverseActive || upperActive || lowerActive||sentence.equals("REVERSE") || sentence.equals("UPPERCASE") || sentence.equals("LOWERCASE")
					|| sentence.equals("EXIT")) {
				if (reverseActive) {
					String reversedSentence = new StringBuilder(sentence).reverse().toString().trim();
					reversedSentence="SERVER: "+reversedSentence;
					sendData =reversedSentence.getBytes();
					reverseActive = false;
				}
				if (upperActive) {
					if(sentence.trim().equals(".")){
						String totalString="";
						for(String string:storedData) {
							totalString+="SERVER: "+string+"\n";
						}
						sendData=totalString.getBytes();
						upperActive = false;
					}
					else {
						String upperSentence=sentence.toUpperCase().trim();
						storedData.add(upperSentence);
					}
				}
				if (lowerActive) {
					if(sentence.trim().equals(".")){
						String totalString="";
						for(String string:storedData) {
							totalString+="SERVER: "+string+"\n";
						}
						sendData=totalString.getBytes();
						lowerActive = false;
					}
					else {
						String lowerSentence=sentence.toLowerCase().trim();
						storedData.add(lowerSentence);
					}
				}
				
				// Initial Commands 
				if (sentence.equals("REVERSE")) {
					String rstring = "SERVER: 200 OK";
					sendData = rstring.trim().getBytes();
					reverseActive = true;
				}
				if (sentence.equals("UPPERCASE")) {
					storedData=new ArrayList<String>();
					
					String rstring = "SERVER: 200 OK";
					sendData = rstring.trim().getBytes();
					upperActive = true;
				}
				if (sentence.equals("LOWERCASE")) {
					storedData=new ArrayList<String>();
					
					String rstring = "SERVER: 200 OK";
					sendData = rstring.trim().getBytes();
					lowerActive = true;
				}
				if (sentence.equals("EXIT")) {
					String rstring = "SERVER: 200 OK";
					sendData = rstring.trim().getBytes();
				}
			}else{
				String errorString = "SERVER: 400 Not A Valid Command";
				sendData = errorString.trim().getBytes();
			}

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}