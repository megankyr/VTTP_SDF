package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String host;
        int port;
        if (args.length == 1){
            host = "localhost";
            port = Integer.parseInt(args[0]);
        } else if (args.length == 2){
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            host = "localhost";
            port = 3000;
        }

        try (
            Socket clientSocket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        ){
            System.out.println("Connected to server");

            String response = in.readLine();
            if (response != null && response.startsWith("item_count")){
                String count = response;
                System.out.println(count);
            } else if (response != null && response.startsWith("budget")){
                String budget = response;
                System.out.println(budget);
            } else if (response != null && response.startsWith("prod_list")){
                String products = response;
                System.out.println(products);
            } else if (response != null && response.startsWith("prod_start")){
                String description = response;
                System.out.println(description);
            } else if (response != null && response.startsWith("prod_id")){
                String id = response;
                System.out.println(id);
            }
        
    }
}
}