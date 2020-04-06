package com.company;

import java.sql.*;

public class Database {
    private static Connection connect = null;
    private Database(){ }


    /*
     * Folosindu-ne de identificatorul bazei de date, care este specific protocolului jdbc ,putem specifica tipul bazei de date
     * baza de date fiind de tipul mysql (sub-protocol) , fiind o baza de date locala (localhost) care se regaseste la portul 3308
     * cu numele MusicAlbums , avand un useraccount cu numele "dba" si parola "sql"
     * Clasa DriverManager conectează o aplicație la o sursa de date, care este specificată de adresa URL a bazei de date ,
     * insotite de user-ul si parola corespunzatoare
     * clasa implică apelarea la metoda DriverManager.getConnection , ea e cea care realizeaza conexiunea
     */

    public static Connection doConnection()
    {
        String URL = "jdbc:mysql://localhost:3308/MusicAlbums?autoReconnect=true&useSSL=false";
        String user = "dba";
        String password = "sql";


            try {
                connect = DriverManager.getConnection(URL, user, password);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        return connect;
    }

    /*
    *Functia aceasta trebuie apelata la finalul operatiilor cu baza de date pentru a se incheia conexiunea cu aceasta
    */
    public static void closeConnection() throws SQLException {
        connect.close();
    }

    /*
    * Folosindu-ne de identificatorul bazei de date, care este specific protocolului jdbc ,putem specifica tipul bazei de date
    * baza de date fiind de tipul mysql (sub-protocol) , fiind o baza de date locala (localhost) care se regaseste la portul 3308
    * cu numele MusicAlbums , avand un useraccount cu numele "dba" si parola "sql"
    * Dupa realizarea conexiunii , cu un obiect de tipul Statement pentru a executa comenziile (DDL) de creare a tabelelor
    * apoi se inchide conexiunea cu baza de date
    * */

   public static void fillDatabase()
   {
       String URL = "jdbc:mysql://localhost:3308/MusicAlbums?autoReconnect=true&useSSL=false";
       String user = "dba";
       String password = "sql";

       try {
           connect = DriverManager.getConnection(URL, user, password);

           Statement statement=connect.createStatement();
                statement.execute("create table artists(\n" +
                        " id integer not null auto_increment,\n" +
                        " name varchar(100) not null,\n" +
                        " country varchar(100),\n" +
                        " primary key (id)\n" +
                        ");");
                statement.execute("create table albums(\n" +
                                " id integer not null auto_increment,\n" +
                                " name varchar(100) not null,\n" +
                                " artist_id integer not null references artists on delete restrict,\n" +
                                "  release_year integer,\n" +
                                " primary key (id)\n" +
                                ");");

           connect.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
}



