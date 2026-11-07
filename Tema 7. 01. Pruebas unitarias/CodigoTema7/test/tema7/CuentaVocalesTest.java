package tema7;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaVocalesTest {

    @Test
    void test() {
        String s = "Cadena de prueba";
        long cuenta = CuentaVocales.cuentaVocales(s);
        assertEquals(7, cuenta);
    }

    @Test
    void testVacia() {
        String s = "";
        long cuenta = CuentaVocales.cuentaVocales(s);
        assertEquals(0, cuenta);
    }

    @Test
    void testNull() {
        String s = null;
        long cuenta = CuentaVocales.cuentaVocales(s);
        assertEquals(0, cuenta);
    }

    @Test
    void testTodasSinAcentos() {
        String s = "tatetitotuTATETITOTU";
        long cuenta = CuentaVocales.cuentaVocales(s);
        assertEquals(10, cuenta);
    }

    @Test
    void testTodasConAcentos() {
        String s = "áéíóúÁÉÍÓÚ";
        long cuenta = CuentaVocales.cuentaVocales(s);
        assertEquals(10, cuenta);
    }

    @Test
    void testTodasConDieresicales() {
        String s = "äëïöüÄËÏÖÜ";
        long cuenta = CuentaVocales.cuentaVocales(s);
        assertEquals(10, cuenta);
    }

    // Ejemplo alternativa de comprobación de excepciones
    @Test
    void testNullException() {
        String s = null;
        try {
            long cuenta = CuentaVocales.cuentaVocales(s);
            // Si el método debiera lanzar NullPointerException, usamos fail:
            // fail("Se debería haber lanzado una excepción NullPointerException");
        } catch (NullPointerException e) {
            // Excepción esperada
        } catch (Exception e) {
            fail("Se ha lanzado una excepción pero no es de tipo NullPointerException");
        }
    }

    // Comprobación equivalente utilizando assertThrows
    @Test
    void testNullExcepctionAssertThrows() {
        String s = null;
        // Descomentar en caso de que la lógica requiera lanzar excepción ante valores null:
        // assertThrows(NullPointerException.class, () -> CuentaVocales.cuentaVocales(s));
    }
}
