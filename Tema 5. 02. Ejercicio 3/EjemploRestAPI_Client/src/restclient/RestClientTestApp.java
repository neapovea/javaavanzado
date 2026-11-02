package restclient;

import java.util.List;
import restclient.api.ConsumidorCiudadanoAPI;
import restclient.data.Ciudadano;

public class RestClientTestApp {
    public static void main(String[] args) {
        ConsumidorCiudadanoAPI ciudadanoAPI = new ConsumidorCiudadanoAPI();

        // Paso 1: obtener todos los ciudadanos:
        System.out.println("=====================================");
        System.out.println(" Obtener todos los ciudadanos:");
        List<Ciudadano> ciudadanos = ciudadanoAPI.get();
        ciudadanos.stream().forEach(c -> mostrarCiudadano(c));

      
        // Paso 2: si la lista no está vacía, borrar el primer ciudadano
        if (!ciudadanos.isEmpty()) {
            Ciudadano primerCiudadano = ciudadanos.get(0);
            System.out.println("==========================");
            System.out.println("Borrar ciudadano:");
            boolean correcto = ciudadanoAPI.delete(primerCiudadano.getId());
            System.out.println("Correcto: " + correcto);
        }
        
        // Paso 3. consultar de nuevo la lista para comprobar que el borrado fue exitoso
        System.out.println("=====================================");
        System.out.println(" Obtener todos los ciudadanos:");
        ciudadanos = ciudadanoAPI.get();
        ciudadanos.stream().forEach(c -> mostrarCiudadano(c));

    }

    public static void mostrarCiudadano(Ciudadano ciudadano) {
        System.out.println(" Id: " + ciudadano.getId());
        System.out.println(" Nombre: " + ciudadano.getNombre());
        System.out.println(" Apellidos: " + ciudadano.getApellidos());
        System.out.println();
    }
}
