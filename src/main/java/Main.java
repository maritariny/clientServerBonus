import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        int port = 8089;
        String name = "";

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            Socket clientSocket = serverSocket.accept(); // ждем подключения
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Write your name");

            while(!clientSocket.isClosed()) {

                String input = in.readLine();

                if (name.equals("")) {
                    name = input;
                    out.println("Are you child? (yes/no)");
                } else {
                    switch (input) {
                        case ("exit"):
                            out.println("bye");
                            clientSocket.close();
                            break;
                        case ("yes"):
                            out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                            break;
                        case ("no"):
                            out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                            break;
                        default:
                            out.println("Enter yes/no or exit");
                            break;
                    }
                }
            }

            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
