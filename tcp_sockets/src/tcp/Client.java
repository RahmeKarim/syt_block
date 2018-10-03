package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public void run() {
        try {
            Socket client = new Socket("127.0.0.1", 8088);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader tin = new BufferedReader((new InputStreamReader(client.getInputStream())));
            PrintWriter tout = new PrintWriter(client.getOutputStream(), true);
            PrintWriter out = new PrintWriter(System.out, true);
            String line;

        while (true) {
            if (tin.ready()) {
                line = tin.readLine();
                out.println(line);
            }

            if (in.ready()) {
                line = in.readLine();
                tout.println(line);
                if (line == null || line.length() == 0 || line.equalsIgnoreCase(line)) {
                    break;
                }
            }
        }

        client.close();

        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().run();
    }
}