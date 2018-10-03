package tcp;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.util.Date;

public class Handler implements Runnable {

    private Socket client;

    public Handler(Socket client) {
        this.client = client;
        System.out.println("Verbindung von: " + client.getInetAddress() + ":" + client.getPort());
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);

            while(true) {
                String line = in.readLine();
                if(line == null || line.length() == 0)
                    break;
                out.println(df.format(new Date()) + ": " + line);
            }
            in.close();
            out.close();
            client.close();

        } catch(IOException iox) {
            iox.printStackTrace();
        }
    }
}
