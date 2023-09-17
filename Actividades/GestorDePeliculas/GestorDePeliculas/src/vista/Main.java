package vista;


import modelo.GestorPelicula;
import modelo.Pelicula;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // Se crea un nuevo gestor de peliculas
        GestorPelicula gestorPelicula = new GestorPelicula();

        // Se crean peliculas base
        Pelicula FastAndFurious = new Pelicula(0,"Fast And Furious", true);
        Pelicula Batman = new Pelicula(1,"The Batman", false);
        Pelicula Warriors = new Pelicula(2,"The Warriors", false);

        // Se agregan las peliculas instanciadas anteriormente
        gestorPelicula.AgregarPelicula(FastAndFurious);
        gestorPelicula.AgregarPelicula(Batman);
        gestorPelicula.AgregarPelicula(Warriors);

        // Estado actual del gestor de peliculas
        gestorPelicula.ObtenerPeliculas(true);

        // Eliminacion de una pelicula
        gestorPelicula.EliminarPelicula(0);
        gestorPelicula.ObtenerPeliculas(true);

        // Hacer disponible una pelicula
        gestorPelicula.MarcarListaComoDisponible(2);
        gestorPelicula.ObtenerPeliculas(true);

        // Lista de peliculas disponibles
        gestorPelicula.ObtenerPeliculasDisponibles(true);

        // Lista de peliculas no dispoibles
        gestorPelicula.ObtenerPeliculasNoDisponibles(true);



    }
}