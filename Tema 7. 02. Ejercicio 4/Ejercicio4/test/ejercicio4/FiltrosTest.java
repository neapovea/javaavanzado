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
        
        
        // Ciudadano 4: Aitor Tilla Concebolla (con dos expedientes: EXP4 y EXP6)
        ciudadano = new Ciudadano("44444444A", "AITOR TILLA CONCEBOLLA");
        expediente = new Expediente("EXP4");
        expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200006", "OTRA SALIDA LEÍDA", true));
        expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200007", "OTRA SALIDA NO LEÍDA", false));
        expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200007", "SOLICITUD BECA"));
        ciudadano.getExpedientes().add(expediente);

        expediente = new Expediente("EXP6");
        expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200008", "OTRA SALIDA LEÍDA", true));
        expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200008", "DOCUMENTACION ADICIONAL"));
        expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200009", "JUSTIFICANTE DE PAGO"));
        ciudadano.getExpedientes().add(expediente);
        ciudadanosPrueba.add(ciudadano);

        // Ciudadano 5: Helen Chufe Corriente (expediente EXP7 con salidas no leídas)
        ciudadano = new Ciudadano("55555555B", "HELEN CHUFE CORRIENTE");
        expediente = new Expediente("EXP7");
        expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200009", "OTRA SALIDA NO LEÍDA", false));
        expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200010", "OTRA SALIDA NO LEÍDA MÁS",false));
        ciudadano.getExpedientes().add(expediente);
        ciudadanosPrueba.add(ciudadano);

        // Ciudadano 6: Esteban Dido Honrado (EXP9 vacío para probar flatMap y EXP10 con salidas leídas)
        ciudadano = new Ciudadano("66666666C", "CARMELO COTÓN ROJO");
        expediente = new Expediente("EXP9"); 
        // Expediente vacío para verificar que flatMap maneja colecciones sin elementos sin lanzar errores
        ciudadano.getExpedientes().add(expediente);

        expediente = new Expediente("EXP10");
        expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200011", "OTRA SALIDA LEÍDA", true));
        expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200010", "RECLAMACION FORMAL"));
        ciudadano.getExpedientes().add(expediente);
        ciudadanosPrueba.add(ciudadano);

        // Ciudadano 7: Lola Mento Mucho
        ciudadano = new Ciudadano("77777777D", "LOLA MENTO MUCHO");
        expediente = new Expediente("EXP11");
        expediente.getSalidasRegistro().add(new SalidaRegistro("S000202200012", "OTRA SALIDA NO LEÍDA MÁS",false));
        ciudadano.getExpedientes().add(expediente);
        ciudadanosPrueba.add(ciudadano);

        // Ciudadano 8: Armando Adistancia Boton
        ciudadano = new Ciudadano("88888888E", "ARMANDO ADISTANCIA BOTON");
        expediente = new Expediente("EXP12");
        expediente.getEntradasRegistro().add(new EntradaRegistro("E000202200011", "INSTANCIA GENERAL"));
        ciudadano.getExpedientes().add(expediente);
        ciudadanosPrueba.add(ciudadano);		
		
    }

    /*
     * NIF tiene la cantidad correcta y el mismo orden.
     */
    @Test
    void testObtenerNifsCiudadanos() {
        List<String> resultado = Filtros.obtenerNifsCiudadanos(ciudadanosPrueba);
        List<String> esperados = Arrays.asList("11111111H", "22222222J", "33333333P", "44444444A", "55555555B", "66666666C", "77777777D", "88888888E");

        // Verificar que la funión de Filtros devuelve datos
        assertNotNull(resultado);

        // Verificar que tamaño no es igual que 0
        assertNotEquals(0, resultado.size());
        // Verificar tamaño
        assertEquals(8, resultado.size());
        

        // 	Verificar elementos de las dos listas
        assertIterableEquals(esperados, resultado);


    }

    /*
     * ID expedientes tiene la cantidad correcta y el mismo orden.
     */
    @Test
    void testObtenerIdsExpedientes() {
        List<String> resultado = Filtros.obtenerIdsExpedientes(ciudadanosPrueba);
        List<String> esperados = Arrays.asList("EXP1", "EXP10", "EXP11", "EXP12", "EXP2", "EXP3", "EXP4", "EXP5", "EXP6", "EXP7", "EXP8", "EXP9");
        
        // Verificar que la funión de Filtros devuelve datos
        assertNotNull(resultado);

        // Verificar tamaño
        assertEquals(12, resultado.size());
        assertNotEquals(18, resultado.size());
        
        // 	Verificar elementos de las dos listas
        assertIterableEquals(esperados, resultado);

    }

    
    /*
     * Salidas no leídas tiene la cantidad correcta y el mismo orden por título.
     */
    @Test
    void testObtenerSalidasNoLeidas() {
        List<SalidaRegistro> resultado = Filtros.obtenerSalidasNoLeidas(ciudadanosPrueba);
        String cadenaSalida = "";

        // Verificar que la funión de Filtros devuelve datos
        assertNotNull(resultado);

        // Verificar tamaño
        assertEquals(7, resultado.size());        
        assertNotEquals(18, resultado.size());
        
        // Verificar títulos según orden.
        for (int i = 0; i < resultado.size(); i++) {
        	cadenaSalida = switch (i) {
	            case 0, 1, 2 -> cadenaSalida="OTRA SALIDA NO LEÍDA";
	            case 3, 4, 5 -> cadenaSalida="OTRA SALIDA NO LEÍDA MÁS";
	            default -> cadenaSalida="SALIDA NO LEÍDA";
            };
            // Verificar que la salida esta como no leída            
            assertFalse(resultado.get(i).isLeida());

            // Verificar títulos.
            assertEquals(cadenaSalida, resultado.get(i).getTitulo());
        }
    }

    /*
     * Registros de salida que no se han leído tiene la cantidad correcta y el mismo orden inverso.
     */
    @Test
    void testObtenerCodigosRegistroSalidasNoLeidas() {
        List<String> resultado = Filtros.obtenerCodigosRegistroSalidasNoLeidas(ciudadanosPrueba);
        List<String> esperados = Arrays.asList("S000202200012", "S000202200010", "S000202200009", "S000202200007", "S000202200005", "S000202200002", "S000202200001");

        // Verificar que la funión de Filtros devuelve datos
        assertNotNull(resultado);

        // Verificar tamaño
        assertEquals(7, resultado.size());
        
        // 	Verificar elementos de las dos listas
        assertIterableEquals(esperados, resultado);
    }
}