package tema1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlExample2 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://java.com");

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
