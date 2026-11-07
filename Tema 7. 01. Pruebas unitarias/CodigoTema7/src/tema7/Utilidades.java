package tema7;

import java.util.Date;

public class Utilidades {

    /**
     * Calcula la edad en una determinada fecha a partir de la fecha de nacimiento.
     *
     * @param fechaNacimiento la fecha de nacimiento
     * @param fecha          la fecha en la que se quiere calcular la edad
     * @return la edad calculada
     */
    public static int calculaEdad(Date fechaNacimiento, Date fecha) {
        return (int) ((fecha.getTime() - fechaNacimiento.getTime()) / (365.25 * 24 * 60 * 60 * 1000));
    }
}
