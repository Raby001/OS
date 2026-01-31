import java.net.*;
import java.io.*;

public class DateClient {
    public static void main(String[] args) {
        try {
            // Make connection to server socket at localhost:6013
            Socket sock = new Socket("127.0.0.1", 6013);

            // Set up input stream and wrap with BufferedReader
            InputStream in = sock.getInputStream();
            BufferedReader bin = new BufferedReader(
                new InputStreamReader(in));

            // Read the date from the server (server sends exactly one line)
            String line;
            while ((line = bin.readLine()) != null) {
                System.out.println(line);
            }

            // Close the socket connection
            sock.close();
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}