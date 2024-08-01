package servicio;
import dominio.Snack;
import java.util.List;

public interface IservicioSnacks {

    //por dedault son publicos y abtractos
    void agregarSnack(Snack snack);
    void mostrarSnak();
    List<Snack> getSnacks();

}
