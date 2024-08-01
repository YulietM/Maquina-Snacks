package presentacion;
import dominio.Snack;
import servicio.IservicioSnacks;
import servicio.ServicioSnacksArchivo;
import servicio.ServicioSnacksLista;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaSnacks();
    }

    public static void maquinaSnacks() {
        var salir = false;
        var consola = new Scanner(System.in);
        //OBJETO PARA OBTENER EL SERVICIO DE SNACKS
        //IservicioSnacks servicioSnacks = new ServicioSnacksLista();
        IservicioSnacks servicioSnacks = new ServicioSnacksArchivo();
        //creamos la lista de productos de tipo snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("*** Maquina de Snacks ***");
        servicioSnacks.mostrarSnak(); //mostrar el inventario de snacks disponibles
        while (!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos, servicioSnacks);
            }catch(Exception e){
                System.out.println("ocurrio un error "+ e.getMessage());
            }
            finally {
                System.out.println();//imprime un salto de linea en cada iteracion
            }
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                Menu:
                1. Comprar Snack
                2. Mostrar ticket
                3. Agregar nuevo snack
                4. Mostrar inventario
                5. salir
                Elige una opcion:\s""");
        //leemos y retornamos una opcion
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos, IservicioSnacks servicioSnacks){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(consola, productos, servicioSnacks);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola, servicioSnacks);
            case 4 -> listarInventarioSnacks(consola, servicioSnacks);
            case 5 -> {
                System.out.println("Regresa Pronto!");
                salir = true;
            }
            default -> System.out.println("opcion invalida");
        }
        return salir;
    }

    private  static void listarInventarioSnacks(Scanner consola, IservicioSnacks servicioSnacks){
        servicioSnacks.mostrarSnak();
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos, IservicioSnacks servicioSnacks){
        System.out.println("Que snack quieres comprar ?? ");
        var idSnack = Integer.parseInt(consola.nextLine());
        //validar que el snack exista en la lista de snacks
        var snackEncontrado = false;
        for(var snack : servicioSnacks.getSnacks()){ //la lista de snacks
            if(idSnack == snack.getIdSnack()){
                //agregamos el snack a la lista de productos
                productos.add(snack);
                System.out.println("ok snack agregado "+ snack);
                snackEncontrado = true;
                break;
            }
        }
        if(!snackEncontrado){
            System.out.println("no existe snack");
        }
    }

    private static void mostrarTicket(List<Snack> productos){
        var ticket = "*** Ticket de venta ***";
        var total = 0.0;
        for(var producto : productos){
            ticket += "\n\t- " + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\nTotal de la compra: $" + total;
        System.out.println(ticket);
    }

    private static  void agregarSnack(Scanner consola, IservicioSnacks servicioSnacks){
        System.out.println("Nombre del snack: ");
        var nombre = consola.nextLine();
        System.out.println("Precio del snack: ");
        var precio = Double.parseDouble(consola.nextLine());
        servicioSnacks.agregarSnack(new Snack(nombre, precio));
        System.out.println("Tu snack se ha agregado ");
        servicioSnacks.mostrarSnak();
    }

}
