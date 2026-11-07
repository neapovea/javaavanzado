package tema7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;

class FechaNacimientoTest {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    void test01_01_2022() throws ParseException {
        assertEquals(43, Utilidades.calculaEdad(dateFormat.parse("08/08/1978"), dateFormat.parse("01/01/2022")));
    }

    @Test
    void test07_08_2022() throws ParseException {
        assertEquals(43, Utilidades.calculaEdad(dateFormat.parse("08/08/1978"), dateFormat.parse("07/08/2022")));
    }

    @Test
    void test08_08_2022() throws ParseException {
        assertEquals(44, Utilidades.calculaEdad(dateFormat.parse("08/08/1978"), dateFormat.parse("08/08/2022")));
    }

    @Test
    void test09_08_2022() throws ParseException {
        assertEquals(44, Utilidades.calculaEdad(dateFormat.parse("08/08/1978"), dateFormat.parse("09/08/2022")));
    }

    @Test
    void test31_12_2022() throws ParseException {
        assertEquals(44, Utilidades.calculaEdad(dateFormat.parse("08/08/1978"), dateFormat.parse("31/12/2022")));
    }

    @Test
    void test07_08_1979() throws ParseException {
        assertEquals(0, Utilidades.calculaEdad(dateFormat.parse("08/08/1978"), dateFormat.parse("07/08/1979")));
    }

    @Test
    void test08_08_1979() throws ParseException {
        assertEquals(1, Utilidades.calculaEdad(dateFormat.parse("08/08/1978"), dateFormat.parse("08/08/1979")));
    }

    @Test
    void test09_08_1979() throws ParseException {
        assertEquals(1, Utilidades.calculaEdad(dateFormat.parse("08/08/1978"), dateFormat.parse("09/08/1979")));
    }
}
