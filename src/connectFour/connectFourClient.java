package connectFour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class ConnectFourClient {
    private static final int GRID_HEIGHT = 7;
    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    public static void main(String[] args) throws IOException {
        ConnectFourModel model = new ConnectFourModel();
        Socket remote = new Socket("139.126.184.155", 9000);
        try {
            // wait for a connection
            // remote is now the connected socket
            System.out.println("Connection, sending data.");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    remote.getInputStream()));
            PrintWriter out = new PrintWriter(remote.getOutputStream());

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            out.println("Martha?");
            String str = "";
            while(true) {
                // the greeting
                str = in.readLine();
                // will print gameplay
                System.out.println(str);
                if(str.startsWith("|")) {
                    // is part of the grid; get next 7 lines
                    String[] grid = new String[GRID_HEIGHT];
                    grid[0] = str;
                    for(int i = 1; i < GRID_HEIGHT; i++) {
                        str = in.readLine();
                        grid[i] = str;
                        System.out.println(str);
                    }
                    String rawSymbol = in.readLine();
                    String symbol = rawSymbol.replaceAll(".*\\[", "");
                    symbol = symbol.replaceAll("\\].*", "");
                    int response = model.parseInput(grid, symbol);
                    int temp = (int) (Math.random() * 7);
                    out.println(temp);
                }
                // Send the response
//                out.print(str);
                out.flush();
            }
//            remote.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}