package connectFour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by hedon on 7/7/15.
 */
public class connectFourClient {
    public static void main(String[] args) throws IOException {
        Socket remote = new Socket("139.126.184.155", 9000);
        try {
            // wait for a connection
            // remote is now the connected socket
            System.out.println("Connection, sending data.");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    remote.getInputStream()));
            PrintWriter out = new PrintWriter(remote.getOutputStream());

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            // read the data sent. We basically ignore it,
            // stop reading once a blank line is hit. This
            // blank line signals the end of the client HTTP
            // headers.
            String str = input.readLine();

            // Send the response
            // Send the headers
            out.print(str);
            out.flush();
            remote.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
