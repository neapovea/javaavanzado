package restclient;

import java.util.List;
import restclient.api.ConsumidorCiudadanoAPI;
import restclient.data.Ciudadano;

public class RestClientTestApp {
    public static void main(String[] args) {
        ConsumidorCiudadanoAPI ciudadanoAPI = new ConsumidorCiudadanoAPI();

        // Obtener un ciudadano:
        Ciudadano ciudadano1 = ciudadanoAPI.get(1);
        System.out.println("=====================================");
        System.out.println(" Obtener un ciudadano:");
        mostrarCiudadano(ciudadano1);

        // Obtener todos los ciudadanos:
        System.out.println("=====================================");
        System.out.println(" Obtener todos los ciudadanos:");
        List<Ciudadano> ciudadanos = ciudadanoAPI.get();
        ciudadanos.stream().forEach(c -> mostrarCiudadano(c));

        // Editar un ciudadano (post):
        ciudadano1.setNombre("Nombre 1 modificado");
        ciudadano1.setApellidos("Apellidos 1 modificados");
        boolean correcto = ciudadanoAPI.edit(ciudadano1);
        System.out.println("=====================================");
        System.out.println(" Editar ciudadano");
        System.out.println(" Correcto: " + correcto);

        // Recuperamos el ciudadano de nuevo a ver si se ha modificado:
        ciudadano1 = ciudadanoAPI.get(1);
        System.out.println(" Ciudadano modificado:");
        mostrarCiudadano(ciudadano1);
    }

    public static void mostrarCiudadano(Ciudadano ciudadano) {
        System.out.println(" Id: " + ciudadano.getId());
        System.out.println(" Nombre: " + ciudadano.getNombre());
        System.out.println(" Apellidos: " + ciudadano.getApellidos());
        System.out.println();
    }
}
