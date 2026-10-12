package tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MultiplyClient {
    public static void main(String[] args) {
        String serverIP = "127.0.0.1";
        int serverPort = 12345;

        try (
            // Abrimos el socket:
            Socket socket = new Socket(serverIP, serverPort);
            // Stream de salida
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Stream de entrada
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))
        ) {
            // Leemos la entrada del usuario:
            Scanner userIn = new Scanner(System.in);
            boolean terminar = false;

            while (!terminar) {
                System.out.print("Introduzca un número (o exit para terminar): ");
                String userInput = userIn.nextLine();

                if ("exit".equals(userInput)) {
                    terminar = true;
                } else {
                    // Enviamos el número al servidor:
                    out.println(userInput);
                    // Leemos el mensaje del servidor y lo mostramos:
                    System.out.println("Respuesta del servidor: " + in.readLine());
                }
            }
            System.out.println("Programa finalizado.");

        } catch (IOException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }
}
