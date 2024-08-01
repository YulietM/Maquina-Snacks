package servicio;
import dominio.Snack;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksArchivo implements IservicioSnacks{

    private final String NOMBRE_ARCHIVO = "snacks.txt";
    //lista de snacks
    private List<Snack> snacks = new ArrayList<>();

    //contructor clase
    public ServicioSnacksArchivo(){
        //se crea el archivo si no existe
        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;
        try{
            existe = archivo.exists();
            if(existe){
                this.snacks = obtenerSnacks();
            }
            else {// se crea el archivo
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("se ha creado el archivo");
            }
        }catch (Exception e){
            System.out.println("error creando el archivo"+ e.getMessage());

        }
        //si no existe el archivo, se caragn unos snacks iniciales
        if(!existe){
            cargarSnacksIniciales();
        }
    }

    private void cargarSnacksIniciales(){
        this.agregarSnack(new Snack("papas", 1600));
        this.agregarSnack(new Snack("salpicon", 2000));
        this.agregarSnack(new Snack("empanada pollo queso", 2500));
    }

    private List<Snack> obtenerSnacks(){
        var snacks = new ArrayList<Snack>();
        try{
            List<String> lineas =  Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for(String linea: lineas){
                String[] lineaSnack = linea.split(",");
                var idSnack = lineaSnack[0];
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                var snack = new Snack(nombre, precio);
                snacks.add(snack); //agrga el snack a la lista
            }

        }catch (Exception e){
            System.out.println("error leyendo el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return snacks;
    }

    @Override
    public void agregarSnack(Snack snack) {
        this.snacks.add(snack);
        this.agregarSnackArchivo(snack);
    }

    private void agregarSnackArchivo(Snack snack){
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close();
        }catch (Exception e){
            System.out.println("error agregando el snack"+ e.getMessage());
        }
    }

    @Override
    public void mostrarSnak() {
        System.out.println("---- Snacks en el inventario -----");
        var inventarioSnacks = "";
        for(var snack: this.snacks){
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println(inventarioSnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
