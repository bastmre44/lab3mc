//Universidad del Valle de Guatemala 
//Mishell Ciprian 231169
//lab3


//se importan las librerias
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//se crea la clase inventario que es en donde se ejectua el programa 
public class Inventario {
    public static void main(String[] args) {
        // Crear un ArrayList para almacenar los productos
        ArrayList<Producto> inventario = new ArrayList<Producto>();

        try {
            File file = new File("tiendita.csv");
            Scanner scanner = new Scanner(file);
        
            // Omitir la primera línea (encabezados)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] campos = line.split(";");
        

                // Convertir los campos a los tipos de datos correspondientes
                int id = Integer.parseInt(campos[0]);
                String nombre = campos[1];
                int cantidadDisponible = Integer.parseInt(campos[2]);
                int cantidadVendidos = Integer.parseInt(campos[3]);
                boolean estado = Boolean.parseBoolean(campos[4]);
                double precio = Double.parseDouble(campos[5]);

                Producto producto;

                // Crear un objeto de la clase correspondiente según la categoría del producto
                String categoria = campos[6];
                switch (categoria) {
                    case "Bebida":
                        int mililitros = Integer.parseInt(campos[7]);
                        String tipo = campos[8];
                        producto = new Bebida(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, mililitros, tipo);
                        break;
                    case "Snack":
                        int gramos = Integer.parseInt(campos[9]);
                        String sabor = campos[10];
                        String tamaño = campos[11];
                        producto = new Snack(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, gramos, sabor, tamaño);
                        break;
                    case "Helado":
                        String tamañoHelado = campos[7];
                        String saborHelado = campos[8];
                        producto = new Helado(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio, tamañoHelado, saborHelado);
                        break;
                    default:
                        producto = new Producto(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio);
                        break;
                }

                // Agregar el producto al inventario
                inventario.add(producto);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo cargar el archivo CSV.");
        }

        // Menú de consola
        Scanner input = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Buscar producto por ID");
            System.out.println("2. Listar productos por categoría");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el ID del producto a buscar: ");
                    int idBuscado = input.nextInt();
                    buscarProductoPorID(inventario, idBuscado);
                    break;
                case 2:
                    System.out.print("Ingrese la categoría de productos a listar: ");
                    input.nextLine(); // Consumir la nueva línea pendiente
                    String categoriaBuscada = input.nextLine();
                    listarProductosPorCategoria(inventario, categoriaBuscada);
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }

        } while (opcion != 3);

        input.close();
    }

    // Método para buscar un producto por ID
    private static void buscarProductoPorID(ArrayList<Producto> inventario, int idBuscado) {
        for (Producto producto : inventario) {
            if (producto.getId() == idBuscado) {
                System.out.println("Producto encontrado: " + producto.getNombre());
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    // Método para listar productos por categoría
    private static void listarProductosPorCategoria(ArrayList<Producto> inventario, String categoriaBuscada) {
        for (Producto producto : inventario) {
            if (producto.getCategoria().equals(categoriaBuscada)) {
                System.out.println("Nombre: " + producto.getNombre());
            }
        }
    }

    }



// Clase padre para todos los productos
class Producto {
    // Atributos de la clase padre
    private int id;
    private String nombre;
    private int cantidadDisponible;
    private int cantidadVendidos;
    private boolean estado;
    private double precio;

// Constructores
    public Producto(int id, String nombre, int cantidadDisponible, int cantidadVendidos, boolean estado, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadVendidos = cantidadVendidos;
        this.estado = estado;
        this.precio = precio;
    }

    public Object getCategoria() {
        return null;
    }

    // Getters y setters para los atributos privados
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public int getCantidadVendidos() {
        return cantidadVendidos;
    }

    public boolean getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }


}

class Bebida extends Producto {
    // Atributos de la clase hija
    private int mililitros;
    private String tipo;

// Constructor para la clase Bebida
    public Bebida(int id, String nombre, int cantidadDisponible, int cantidadVendidos, boolean estado, double precio, int mililitros, String tipo) {
        super(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio);
        this.mililitros = mililitros;
        this.tipo = tipo;
    }

    // Getters
    public int getMililitros() {
        return mililitros;
    }

    public String getTipo() {
        return tipo;
    }

 
}

class Snack extends Producto {
    //atributos de la clase hija
    private int gramos;
    private String sabor;
    private String tamaño;
//constructor
    public Snack(int id, String nombre, int cantidadDisponible, int cantidadVendidos, boolean estado, double precio, int gramos, String sabor, String tamaño) {
        super(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio);
        this.gramos = gramos;
        this.sabor = sabor;
        this.tamaño = tamaño;
    }
//getters
    public int getGramos() {
        return gramos;
    }

    public String getSabor() {
        return sabor;
    }

    public String getTamaño() {
        return tamaño;
    }

   
}

class Helado extends Producto {
    // Atributos de la clase hija
    private String tamañoHelado;
    private String saborHelado;
// Constructor
    public Helado(int id, String nombre, int cantidadDisponible, int cantidadVendidos, boolean estado, double precio, String tamañoHelado, String saborHelado) {
        super(id, nombre, cantidadDisponible, cantidadVendidos, estado, precio);
        this.tamañoHelado = tamañoHelado;
        this.saborHelado = saborHelado;
    }
//getters
    public String getTamañoHelado() {
        return tamañoHelado;
    }

    public String getSaborHelado() {
        return saborHelado;
    }

   
}
