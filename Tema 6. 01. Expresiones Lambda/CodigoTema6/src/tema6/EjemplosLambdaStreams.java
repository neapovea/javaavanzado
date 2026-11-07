package tema6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EjemplosLambdaStreams {

    public static List<Persona> crearListaPersonas() {
        List<Persona> listaPersonas = new ArrayList<>();
        listaPersonas.add(new Persona("JUAN", "FERNANDEZ", "RODRIGUEZ"));
        listaPersonas.add(new Persona("ROSA", "LOPEZ", "ALVAREZ"));
        listaPersonas.add(new Persona("MARTIN", "MARQUEZ", "MANRIQUE"));
        listaPersonas.add(new Persona("ELENA", "FERNANDEZ", "MORA"));
        listaPersonas.add(new Persona("FRANCISCO", "MARTINEZ", "OLIVAR"));
        listaPersonas.add(new Persona("MIGUEL", "SANCHEZ", "HERRERA"));
        return listaPersonas;
    }

    public static void main(String[] args) {
        List<Persona> listaPersonas = crearListaPersonas();

        System.out.println("=== Ejemplo 1: Recorrido básico con for, Consumer anónimo y Lambda ===");
        // Bucle tradicional
        System.out.println("--- Bucle tradicional ---");
        for (Persona p : listaPersonas) {
            System.out.println(p.getNombre());
        }

        // Clase anónima Consumer
        System.out.println("--- Clase anónima Consumer ---");
        listaPersonas.forEach(new Consumer<Persona>() {
            @Override
            public void accept(Persona p) {
                System.out.println(p.getNombre());
            }
        });

        // Lambda
        System.out.println("--- Método forEach con expresión Lambda ---");
        listaPersonas.forEach(p -> System.out.println(p.getNombre()));

        System.out.println("\n=== Ejemplo 2: Ordenación con Comparator e interfaz funcional ===");
        listaPersonas.sort((p1, p2) -> p1.getApellido1().compareTo(p2.getApellido1()));
        listaPersonas.forEach(p -> System.out.println(p.getApellido1()));

        System.out.println("\n=== Ejemplo 3: Expresión Lambda multilínea con llaves ===");
        listaPersonas.forEach(p -> {
            System.out.println(p.getApellido1());
            System.out.println();
        });

        System.out.println("=== Ejemplos de Streams ===");
        // Filtrado, conversión (map), distintos, ordenados e impresión
        System.out.println("--- Apellidos filtrados (no HERRERA), ordenados y sin duplicados ---");
        listaPersonas.stream()
                .filter(p -> !p.getApellido2().equals("HERRERA"))
                .map(p -> p.getApellido1())
                .distinct()
                .sorted()
                .forEach(apellido -> System.out.println(apellido));

        // Uso de peek para depuración
        System.out.println("--- Uso del operador peek ---");
        listaPersonas.stream()
                .filter(p -> !p.getApellido2().equals("HERRERA"))
                .map(p -> p.getApellido1())
                .peek(ap -> System.out.println("apellido es: " + ap))
                .distinct()
                .sorted()
                .forEach(apellido -> System.out.println(apellido));

        // Operaciones de reducción
        System.out.println("\n--- Operaciones de reducción (min, count, groupingBy) ---");
        Persona pMasCorto = listaPersonas.stream()
                .min((p1, p2) -> p1.getApellido1().length() - p2.getApellido1().length())
                .get();
        System.out.println("Persona con el apellido más corto: " + pMasCorto);

        long c = listaPersonas.stream()
                .filter(persona -> persona.getApellido1().equals("FERNANDEZ"))
                .count();
        System.out.println("Número de personas que se apellidan FERNANDEZ: " + c);

        // Agrupar personas por primer apellido
        Map<String, List<Persona>> agrupadas = listaPersonas.stream()
                .collect(Collectors.groupingBy(Persona::getApellido1));
        System.out.println("\nPersonas agrupadas por primer apellido:");
        agrupadas.entrySet().forEach(entry -> {
            String primerApellido = entry.getKey();
            System.out.println(primerApellido + ":");
            List<Persona> listPersonas = entry.getValue();
            listPersonas.forEach(persona -> System.out.println("  " + persona.toString()));
        });

        // Contar personas por la inicial del primer apellido
        Map<String, Long> cuentaPorIniciales = listaPersonas.stream()
                .collect(Collectors.groupingBy(
                        persona -> persona.getApellido1().substring(0, 1),
                        Collectors.counting()
                ));
        System.out.println("\nConteo por inicial del primer apellido:");
        cuentaPorIniciales.entrySet().forEach(entry -> {
            String inicial = entry.getKey();
            Long cuenta = entry.getValue();
            System.out.println("Hay " + cuenta + " persona(s) cuyo primer apellido empieza por " + inicial);
        });
    }
}
