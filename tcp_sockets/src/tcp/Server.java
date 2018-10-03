package tcp;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8088);
            while(true) {
                Socket client = server.accept();
                new Thread(new Handler(client)).start();
            }
        } catch( IOException iox) {
            iox.printStackTrace();
        }

    }
}
