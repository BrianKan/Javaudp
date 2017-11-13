Coded Brian Kan CS4740
-----------------------------------------------
Language: Java

Platform: Windows
-----------------------------------------------
Requirements:

 1. Windows OS
 2. Java SDK 
 
 ( You need to add /java-sdk x.x.x/bin to the Environment Variables)
 ** MAKE SURE JAVAC/JAVA WORKS FROM THE COMMAND PROMPT **
------------------------------------------------

Instructions:
1. Run Compile.bat
2. Run Start.bat
3. Type in commands such as - REVERSE, UPPERCASE, LOWERCASE, and EXIT

------------------------------------------------



Description:
A basic client and server setup that uses sockets and datagrams to transmit data between each other. 
The protocol being used is UDP.  The Server opens up a socket and creates a transmit and receive buffer.
The Client connects to the server using the socket number and also creates a transmit and receive buffer.
The Client reads from the input stream and sends a line of text as a packet to the server. The server sends
a packet back to the client. 