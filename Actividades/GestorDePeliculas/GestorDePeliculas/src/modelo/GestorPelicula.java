package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GestorPelicula {

    private final List<Pelicula> peliculas = new ArrayList<>();

    public  String  AgregarPelicula(Pelicula pelicula){
        peliculas.add(pelicula.getId(),pelicula);
        return "Se ha agregado la pelicula" + pelicula.getNombre();
    }
    public  void  EliminarPelicula(int id){
        peliculas.removeIf(pelicula -> pelicula.getId()== id);
        System.out.println("\n!!!Se ha eliminado la pelicula con el id: "+id+"!!!");
    }

    // Parametro "consola"
    // true -> Se devuelve la lista de peliculas y se imprimen los datos en pantalla
    // false -> Se devuelve la lista de peliculas
    public List<Pelicula> ObtenerPeliculas(boolean consola){
        if (consola){
            System.out.println("\nPeliculas en stock:");
            for (Pelicula pelicula: peliculas) {
                System.out.println(pelicula.getId()+": "+pelicula.getNombre()+" -- Disponible: "+ pelicula.isDisponible());
            }
        }
        return peliculas;
    }

    // Parametro "consola"
    // true -> Se devuelve la lista de peliculas y se imprimen los datos en pantalla
    // false -> Se devuelve la lista de peliculas
    public List<Pelicula> ObtenerPeliculasDisponibles(boolean consola){

        List<Pelicula> peliculasDisponibles = peliculas.stream()
            .filter(pelicula -> Objects.equals(pelicula.isDisponible(), true))
            .collect(Collectors.toList());

        if (consola){
            System.out.println("\nPeliculas disponibles:");
            for (Pelicula pelicula: peliculasDisponibles) {
                System.out.println(pelicula.getId()+": "+pelicula.getNombre()+" -- Disponible: "+ pelicula.isDisponible());
            }
        }

        return peliculasDisponibles;
    }

    // Parametro "consola"
    // true -> Se devuelve la lista de peliculas y se imprimen los datos en pantalla
    // false -> Se devuelve la lista de peliculas
    public List<Pelicula> ObtenerPeliculasNoDisponibles(boolean consola){
        List<Pelicula> peliculasNoDisponibles = peliculas.stream()
                .filter(pelicula -> Objects.equals(pelicula.isDisponible(), false))
                .collect(Collectors.toList());

        if (consola){
            System.out.println("\nPeliculas no disponibles:");
            for (Pelicula pelicula: peliculasNoDisponibles) {
                System.out.println(pelicula.getId()+": "+pelicula.getNombre()+" -- Disponible: "+ pelicula.isDisponible());
            }
        }

        return peliculasNoDisponibles;
    }

    public void MarcarListaComoDisponible(int id){
        Pelicula peliculaToMarkAvail = peliculas.stream()
                .filter(pelicula -> Objects.equals(pelicula.getId(),id))
                .findAny()
                .orElse(null);

        if (peliculaToMarkAvail != null) {
            peliculaToMarkAvail.setDisponible(true);
            System.out.println("\n---La pelicula con id: "+id+" se ha marcado como disponible---");
        }else{
            System.out.println("Pelicula no encontrada");
        }

    }

}
