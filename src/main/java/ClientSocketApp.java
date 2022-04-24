import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketApp {

    public static void main(String[] args) {

        String host = "netology.homework";
        int port = 8089;

        Scanner scanner = new Scanner(System.in);

        try (Socket clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String resp = in.readLine(); // Write your name
            System.out.println(resp);

            String name = scanner.nextLine();
            out.println(name);
            resp = in.readLine(); // Are you child? (yes/no)
            System.out.println(resp);

            while (true) {
                String answer = scanner.nextLine();
                out.println(answer);
                resp = in.readLine();
                System.out.println(resp);
                if (resp == null || !resp.equals("Enter yes/no or exit")) {
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
