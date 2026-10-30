package restserver.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import restserver.data.Ciudadano;

public class CiudadanosDAO {
    private ArrayList<Ciudadano> ciudadanos = new ArrayList<Ciudadano>();
    private static CiudadanosDAO instance = new CiudadanosDAO();

    static {
        Ciudadano c = new Ciudadano();
        c.setId(1);
        c.setNombre("Nombre 1");
        c.setApellidos("Apellidos 1");
        instance.ciudadanos.add(c);

        c = new Ciudadano();
        c.setId(2);
        c.setNombre("Nombre 2");
        c.setApellidos("Apellidos 2");
        instance.ciudadanos.add(c);
    }

    public static CiudadanosDAO getInstance() {
        return instance;
    }

    public Ciudadano getCiudadanoById(int id) {
        Ciudadano result = null;
        Optional<Ciudadano> ciudadano = ciudadanos.stream().filter(c -> c.getId() == id).findFirst();
        if (ciudadano.isPresent()) {
            result = ciudadano.get();
        }
        return result;
    }

    public ArrayList<Ciudadano> getCiudadanos() {
        return ciudadanos;
    }

    public void updateCiudadano(Ciudadano ciudadano) {
        if (ciudadano.getId() == 0) {
            // insertar
            ciudadano.setId(Collections.max(ciudadanos.stream().map(c -> c.getId()).toList()) + 1);
            ciudadanos.add(ciudadano);
        } else {
            // actualizar
            for (int i = 0; i < ciudadanos.size(); i++) {
                if (ciudadanos.get(i).getId() == ciudadano.getId()) {
                    ciudadanos.set(i, ciudadano);
                }
            }
        }
    }
}
