import java.util.ArrayList;
import java.util.List;

public class Snacks {
    private static final List<Snack> snacks;

    //bloque de tipo estatico inicializador
    static {
        snacks = new ArrayList<>();
        snacks.add(new Snack("papas", 2300));
        snacks.add(new Snack("jugo hit", 3400));
        snacks.add(new Snack("empanada", 2500));
        snacks.add(new Snack("salpicon", 3200));
    }

    public static void agregarSnack(Snack snack){
        snacks.add(snack);
    }

    public static void mostrarSnak(){
        var inventrioSnack = "";
        for(var snack : snacks){
            inventrioSnack += snack.toString() + "\n";
        }
        System.out.println("------- Snacks en el inventario ------");
        System.out.println(inventrioSnack);
    }

    public static List<Snack> getSnacks(){
        return snacks;
    }
}
