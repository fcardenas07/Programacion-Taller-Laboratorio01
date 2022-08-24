import java.util.Arrays;
import java.util.Scanner;

public class ColeccionLibros {
    public static void main(String[] args) {
        String[][] coleccionLibros = crearColeccionLibros();
        Menu(coleccionLibros);
    }

    private static void Menu(String[][] coleccionLibros) {
        verMenu();
        int opcion = ingresarOpcion();
        int espaciosUsados;

        do {
            switch (opcion) {
                case 1:
                    coleccionLibros = agregarLibro(coleccionLibros);
                    break;
                case 2:
                    buscarLibroPorAutor(coleccionLibros);
                    break;
                case 3:
                    espaciosUsados = buscarFilaVacia(coleccionLibros);
                    mostrarEspaciosUsados(espaciosUsados);
                    break;
                case 4:
                    espaciosUsados = buscarFilaVacia(coleccionLibros);
                    mostrarEspaciosDisponibles(coleccionLibros, espaciosUsados);
                    break;
                case 5:
                    mostrarTodaLaColeccion(coleccionLibros);
                    break;
            }
            verMenu();
            opcion = ingresarOpcion();
        } while (opcion != 6);
    }

    private static String[][] agregarLibro(String[][] libros) {
        if (coleccionNoEstaLlena(libros)) {
            int fila = buscarFilaVacia(libros);

            mostrarMensajeIngresarAutor();
            libros[fila][0] = ingresarDetallesLibro();

            mostrarMensajeIngresarTitulo();
            libros[fila][1] = ingresarDetallesLibro();

            mostrarMensajeIngresarEditorial();
            libros[fila][2] = ingresarDetallesLibro();
        } else {
            System.out.println("La coleccion esta llena");
        }
        return libros;
    }

    private static int buscarFilaVacia(String[][] libros) {
        int fila = 0;
        while (libros[fila][0] != null) {
            fila++;
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

    private static void mostrarMensajeIngresarAutor() {
        System.out.println("Ingrese autor del libro");
    }

    private static String ingresarDetallesLibro() {
        Scanner teclado = new Scanner(System.in);

        String textoIngresado = teclado.nextLine();

        if (textoIngresado.trim().length() < 1) {
            System.out.println("Ingrese un texto valido");
            ingresarDetallesLibro();
        }
        return textoIngresado;
    }

    private static void mostrarTodaLaColeccion(String[][] coleccionLibros) {
        if (coleccionVacia(coleccionLibros)) {
            System.out.println("Coleccion vacia");
        } else {
            int fila = 0;
            do {
                System.out.println(Arrays.toString(coleccionLibros[fila]));
                fila++;
            } while (buscarFilaVacia(coleccionLibros) != fila);
        }
    }

    private static void mostrarEspaciosDisponibles(String[][] coleccionLibros, int espaciosUsados) {
        System.out.println("Cantidad espacios disponibles en la coleccion: " + (coleccionLibros.length - espaciosUsados));

    }

    private static void mostrarEspaciosUsados(int espaciosUsados) {
        System.out.println("Cantidad de libros en la coleccion: " + (espaciosUsados));
    }

    private static void buscarLibroPorAutor(String[][] coleccionLibros) {
        if (coleccionVacia(coleccionLibros)) {
            System.out.println("Coleccion vacia");
        } else {
            System.out.println("Busqueda de Libro");
            mostrarMensajeIngresarAutor();

            String libroABuscar = ingresarDetallesLibro();
            int fila = 0;
            int posicionLibro = -1;

            while (coleccionLibros[fila][0] != null) {
                if (coleccionLibros[fila][0].equals(libroABuscar)) {
                    posicionLibro = fila;
                }
                fila++;
            }
            mostrarResultadoBusqueda(posicionLibro);
        }
    }

    private static boolean coleccionVacia(String[][] coleccionLibros) {
        return buscarFilaVacia(coleccionLibros) == 0;
    }

    private static void mostrarResultadoBusqueda(int posicionLibro) {
        if (posicionLibro != -1) {
            System.out.println("El libro se encuentra en la posicion: " + posicionLibro);
        } else {
            System.out.println("El libro no se encuentra");
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
        System.out.println("------------------------------------");
        System.out.println("Seleccione una opcion");
        System.out.println("1-> AGREGAR LIBRO");
        System.out.println("2-> BUSCAR LIBRO");
        System.out.println("3-> MOSTRAR ESPACIOS USADOS");
        System.out.println("4-> MOSTRAR ESPACIOS DISPONIBLES");
        System.out.println("5-> MOSTRAR TODA LA COLECCION");
        System.out.println("6-> SALIR");
        System.out.println("------------------------------------");
    }

    private static String[][] crearColeccionLibros() {
        return new String[100][3];
    }
}
