import java.util.Scanner;

public class ColeccionLibros {
    public static void main(String[] args) {
        String[][] coleccionLibros = crearColeccionLibros();
        Menu(coleccionLibros);
    }

    private static void agregarLibro(String[][] libros) {
        String[] detallesLibro = new String[3];

        if (coleccionNoEstaLlena(libros)) {
            mostrarMensajeIngresarAutor();
            detallesLibro[0] = ingresarDetallesLibro();
            mostrarMensajeIngresarTitulo();
            detallesLibro[1] = ingresarDetallesLibro();
            mostrarMensajeIngresarEditorial();
            detallesLibro[2] = ingresarDetallesLibro();
            int fila = buscarFilaVacia(libros);
            libros[fila] = detallesLibro;
        }
    }

    private static int buscarFilaVacia(String[][] libros) {
        int fila = 0;
        for (int i = 0; i < libros.length; i++) {
            if (libros[i][0] == null) {
                fila = i;
            }
        }
        return fila;
    }

    private static boolean coleccionNoEstaLlena(String[][] libros) {
        return libros[libros.length - 1][0] == null;
    }

    private static void mostrarMensajeIngresarEditorial() {
        System.out.println("Ingrese editorial del libro");
    }

    private static void mostrarMensajeIngresarTitulo() {
        System.out.println("Ingrese titulo del libro");
    }

    private static String ingresarDetallesLibro() {
        Scanner teclado = new Scanner(System.in);

        String textoIngresado = teclado.next();

        if (textoIngresado.trim().length() < 1) {
            System.out.println("Ingrese un texto valido");
            ingresarDetallesLibro();
        }
        return textoIngresado;
    }

    private static void mostrarMensajeIngresarAutor() {
        System.out.println("Ingrese autor del libro");
    }

    private static void Menu(String[][] coleccionLibros) {
        verMenu();
        int opcion = ingresarOpcion();

        switch (opcion) {
            case 1:
                agregarLibro(coleccionLibros);
                break;
        }
    }

    public static int ingresarOpcion() {
        Scanner teclado = new Scanner(System.in);
        boolean esNumero = false;
        int opcion = 0;

        do {
            try {
                opcion = teclado.nextInt();
                esNumero = true;
            } catch (Exception e) {
                teclado.next();
                System.out.println("Ingrese una opcion valida");
            }
        } while (!esNumero);

        if (opcion < 1 || opcion > 6) {
            System.out.println("Ingrese una opcion valida");
            return ingresarOpcion();
        }
        return opcion;
    }

    private static void verMenu() {
        System.out.println("Seleccione una opcion");
        System.out.println("1-> AGREGAR LIBRO");
        System.out.println("2-> BUSCAR LIBRO");
        System.out.println("3-> MOSTRAR ESPACIOS USADOS");
        System.out.println("4-> MOSTRAR ESPACIOS DISPONIBLES");
        System.out.println("5-> MOSTRAR TODA LA COLECCION");
        System.out.println("6-> SALIR");
    }

    private static String[][] crearColeccionLibros() {
        return new String[100][3];
    }
}
