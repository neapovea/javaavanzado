package ejercicio4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltrosTest {

    private List<Ciudadano> ciudadanosPrueba;

    @BeforeEach
    void setUp() {

		// Crear datos para TEST
    	ciudadanosPrueba = new ArrayList<>();

    	Ciudadano ciudadano = null;
		Expediente expediente = null;
		ciudadano = new Ciudadano("11111111H", "JUAN ESPAÑOL ESPAÑOL");
		expediente = new Expediente("EXP5");
		expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200001","SALIDA NO LEÍDA",false));
		expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200002","OTRA SALIDA NO LEÍDA",false));
		expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200001","UNA ENTRADA"));
		expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200002","OTRA ENTRADA"));
		ciudadano.getExpedientes().add(expediente);
		
		
		expediente = new Expediente("EXP3");
		expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200003","UNA SALIDA LEÍDA",true));
		expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200005","OTRA ENTRADA MÁS"));
		ciudadano.getExpedientes().add(expediente);
		ciudadanosPrueba.add(ciudadano);
		
		ciudadano = new Ciudadano("33333333P", "FRANCISCO LOPEZ LOPEZ");
		expediente = new Expediente("EXP1");
		expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200003","ENTRADA DE EJEMPLO"));
		ciudadano.getExpedientes().add(expediente);
		ciudadanosPrueba.add(ciudadano);
		
		ciudadano = new Ciudadano("22222222J", "MARIA GARCIA GARCIA");
		expediente = new Expediente("EXP2");
		expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200004","OTRA SALIDA LEÍDA",true));
		expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200005","OTRA SALIDA NO LEÍDA MÁS",false));
		expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200006","ZZZZZZZZZZZZZZZZZZZ"));
		ciudadano.getExpedientes().add(expediente);
		expediente = new Expediente("EXP8");
		expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200004","ENTRADA DE EJEMPLO"));
		ciudadano.getExpedientes().add(expediente);
		ciudadanosPrueba.add(ciudadano);
		
    }

    @Test
    void testObtenerNifsCiudadanos() {
        List<String> resultado = Filtros.obtenerNifsCiudadanos(ciudadanosPrueba);
        List<String> esperados = Arrays.asList("11111111H", "22222222J", "33333333P");

        // Comprobación de tamaño
        assertEquals(3, resultado.size());

        // Comprobación elemento a elemento
        for (int i = 0; i < esperados.size(); i++) {
            assertEquals(esperados.get(i), resultado.get(i));
        }
    }

    @Test
    void testObtenerIdsExpedientes() {
        List<String> resultado = Filtros.obtenerIdsExpedientes(ciudadanosPrueba);
        List<String> esperados = Arrays.asList("EXP1", "EXP2", "EXP3", "EXP5", "EXP8");

        assertEquals(5, resultado.size());

        for (int i = 0; i < esperados.size(); i++) {
            assertEquals(esperados.get(i), resultado.get(i));
        }
    }

    @Test
    void testObtenerSalidasNoLeidas() {
        List<SalidaRegistro> resultado = Filtros.obtenerSalidasNoLeidas(ciudadanosPrueba);

        assertEquals(3, resultado.size());

        // Comprobación de ordenación por título:
        // 1. "OTRA SALIDA NO LEÍDA"
        // 2. "OTRA SALIDA NO LEÍDA MÁS"
        // 3. "SALIDA NO LEÍDA"
        assertEquals("OTRA SALIDA NO LEÍDA", resultado.get(0).getTitulo());
        assertEquals("OTRA SALIDA NO LEÍDA MÁS", resultado.get(1).getTitulo());
        assertEquals("SALIDA NO LEÍDA", resultado.get(2).getTitulo());
    }

    @Test
    void testObtenerCodigosRegistroSalidasNoLeidas() {
        List<String> resultado = Filtros.obtenerCodigosRegistroSalidasNoLeidas(ciudadanosPrueba);
        List<String> esperados = Arrays.asList("S000202200005", "S000202200002", "S000202200001");

        assertEquals(3, resultado.size());

        for (int i = 0; i < esperados.size(); i++) {
            assertEquals(esperados.get(i), resultado.get(i));
        }
    }
}