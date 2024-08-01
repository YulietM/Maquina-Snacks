import java.io.Serializable;
import java.util.Objects;

//esta es la clase de entidad o dominio del problema (la clase mas importante del problema)
public class Snack implements Serializable {
    private static int contadorSnack = 0;
    private int idSnack;
    private String nombre;
    private double precio;

    public Snack() {
        this.idSnack = ++Snack.contadorSnack;
    }

    public Snack(String nombre, double precio) {
        this(); //llamar el constructor vacio
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return idSnack;
    }

    public int getIdSnack() {
        return idSnack;
    }

    public void setIdSnack(int idSnack) {
        this.idSnack = idSnack;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Snack{" +
                "idSnack=" + idSnack +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nombre, precio);
    }
}
