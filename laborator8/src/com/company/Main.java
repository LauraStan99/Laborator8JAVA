package com.company;
import java.sql.*;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /*
        * Am apelat metoda fillDatabase pentru a creea tabelele ce se doresc a fi in baza de date
        * apoi ajutandu-ma de doua obiecte , unul de tipul ArtistController si unul AlbumController
        * am introdus o serie de "artisti" si de "albume" care au fost inserate in baza de date
        * urmand sa apelez metodele FIND.. pentru a interoga tabelele si a verifica existenta anumitor informatii
        */
        Database.fillDatabase();
        ArtistController artist=new ArtistController();
        artist.create("Christina Aguilera","SUA");
        artist.create("David Bowie","UK");
        artist.create("Bruno Mars","SUA");
        artist.create("Shakira","Columbia");
        artist.findByName("Bruno Mars");

       AlbumController album=new AlbumController();
        album.create("Mi Reflejo ",1,2000);
        album.create("El Dorado",4,2017);
        album.findByArtist(1);
    }
}