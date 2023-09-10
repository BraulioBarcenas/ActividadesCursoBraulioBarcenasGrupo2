package modelo;

public class Pelicula {
    private int id;
    private String Nombre;
    private boolean Disponible;

    public Pelicula(int id, String nombre, boolean disponible) {
        this.id = id;
        Nombre = nombre;
        Disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean disponible) {
        Disponible = disponible;
    }
}
