package restclient.api;

import java.util.List;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import restclient.data.Ciudadano;

/**
 * Clase que sirve para invocar los métodos del API remoto.
 */
public class ConsumidorCiudadanoAPI {
    private WebTarget webTarget;

    public ConsumidorCiudadanoAPI() {
        Client client = ClientBuilder.newClient();
        // Creamos un target para la raíz del api
        webTarget = client.target("http://localhost:8080/EjemploRestAPI_Server/api/ciudadano");
    }

    /**
     * Obtiene todos los ciudadanos.
     * @return
     */
    public List<Ciudadano> get() {
        // Creamos un invocation builder de tipo JSON:
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        // Invocamos get, indicando como argumento que esperamos recibir una lista de ciudadanos:
        return invocationBuilder.get(new GenericType<List<Ciudadano>>() {});
    }

    /**
     * Obtiene el ciudadano con el id indicado
     * @param id el id del ciudadano a obtener
     * @return
     */
    public Ciudadano get(int id) {
        // Necesitamos un target con el path que incluya el id,
        // lo construimos concatenando el id al target original:
        WebTarget targetObtenerCiudadano = webTarget.path(String.valueOf(id));
        // Creamos un invocation builder de tipo JSON:
        Invocation.Builder invocationBuilder = targetObtenerCiudadano.request(MediaType.APPLICATION_JSON);
        // Invocamos get, indicando como argumento que esperamos recibir un ciudadano:
        return invocationBuilder.get(Ciudadano.class);
    }

    /**
     * Inserta o actualiza un ciudadano. Si el id indicado es cero,
     * se inserta un nuevo ciudadano, en caso contrario se actualiza
     * el ciudadano con el id indicado.
     * @param ciudadano
     * @return true si la operación se realiza correctamente.
     */
    public boolean edit(Ciudadano ciudadano) {
        // Creamos invocation builder de tipo JSON:
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        // Invocamos post, en este caso pasando como argumento el ciudadano modificado:
        Response response = invocationBuilder.post(Entity.entity(ciudadano, MediaType.APPLICATION_JSON));
        // La respuesta será correcta si el código devuelto es 200:
        return response.getStatus() == 200;
    }
}
