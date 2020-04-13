package org.example;


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
        String URL="jdbc:mysql://localhost:3308/MusicAlbums?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "dba";
        String password = "sql";

        try {
            connect = DriverManager.getConnection(URL, user, password);
            //System.out.println("succes");

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
     * Dupa realizarea conexiunii , cu un obiect de tipul Statement (pentru a executa comenziile (DDL) de creare a tabelelor)
     * creez cele 2 tabele artists si albums
     * apoi se inchide conexiunea cu baza de date
     * */

    public static void createTables()
    {
        String URL="jdbc:mysql://localhost:3308/MusicAlbums?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
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
    /*
     * Folosindu-ne de identificatorul bazei de date, care este specific protocolului jdbc ,putem specifica tipul bazei de date
     * baza de date fiind de tipul mysql (sub-protocol) , fiind o baza de date locala (localhost) care se regaseste la portul 3308
     * cu numele MusicAlbums , avand un useraccount cu numele "dba" si parola "sql"
     * Dupa realizarea conexiunii , cu un obiect de tipul Statement sterg tabelul chart in caz ca exista si il creez din nou
     * apoi se inchide conexiunea cu baza de date
     * */
    public static void createTableCharts()
    {
        String URL="jdbc:mysql://localhost:3308/MusicAlbums?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "dba";
        String password = "sql";

        try {
            connect = DriverManager.getConnection(URL, user, password);

            Statement statement=connect.createStatement();
            statement.execute("create table charts(\n" +
                    " id_album integer not null,\n"+
                    " name_album varchar(100) not null,\n" +
                    " artist_id integer not null,\n" +
                    " name_artist varchar(100) not null,\n" +
                    " primary key (id_album)\n" +
                    ");");

            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

