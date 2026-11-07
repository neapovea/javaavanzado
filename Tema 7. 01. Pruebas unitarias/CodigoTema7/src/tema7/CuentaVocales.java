package tema7;

public class CuentaVocales {

	/**
	 * Cuenta el número de vocales en una cadena dada (incluyendo mayúsculas,
	 * minúsculas, vocales con acento y con diéresis). Si la cadena es null,
	 * devuelve 0.
	 *
	 * @param s Cadena de texto a analizar
	 * @return Cantidad de vocales en la cadena
	 */
	public static long cuentaVocales(String s) {
		long result = 0;
		if (s != null) {
			return s.chars()
					.filter(c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E'
							|| c == 'I' || c == 'O' || c == 'U' || c == 'á' || c == 'é' || c == 'í' || c == 'ó'
							|| c == 'ú' || c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú' || c == 'ä'
							|| c == 'ë' || c == 'ï' || c == 'ö' || c == 'ü' || c == 'Ä' || c == 'Ë' || c == 'Ï'
							|| c == 'Ö' || c == 'Ü')
					.count();
		}
		return result;

	}

//	public static long cuentaVocales(String s) {
//		return s.chars().filter(c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E'
//				|| c == 'I' || c == 'O' || c == 'U').count();
//	}
}
