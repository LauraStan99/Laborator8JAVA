package org.example.controller;

import org.example.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlbumController {
    public AlbumController(){}

    /*
     * Aceasta functie creeaza o noua inregistrare in tabelul Albums
     * mai intai se realizeaza conexiunea cu baza de date prin apelarea metodei doConnection
     * Am ales sa folosesc un obiect de tipul PreparedStatement pentru a realiza o interogare SQL
     * cu parametrii , care sunt setati in functie de numarul parametrului din interogare si tipul dorit
     * Dupa care se apeleaza metoda executeUpdate() pentru realizarea comenzii SQL si se inchide conexiunea
     * cu baza de date
     */
    public void create(String name, int artistId, int releaseYear)
    {
        try {
            Connection connect = Database.doConnection();

            String sql = "insert into albums (name,artist_id,release_year) values(?,?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, artistId);
            preparedStatement.setInt(3, releaseYear);

            preparedStatement.executeUpdate();
            Database.closeConnection();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    /*
     * Aceasta functie creeaza o interogarea asupra tabelului Albums din baza de date
     * mai intai se realizeaza conexiunea cu baza de date prin apelarea metodei doConnection
     * Am ales sa folosesc un obiect de tipul PreparedStatement pentru a realiza o interogare SQL
     * cu parametrii , care sunt setati in functie de numarul parametrului din interogare si tipul dorit
     * Dupa care se apeleaza metoda executeQuery() pentru realizarea interogarii SQL
     * Toata informatia extrasa din baza de date in urma interogarii este stocata intr-un obiect de tipul ResultSet
     * pentru a se putea face mai apoi deplasarea prin rezultat cu ajutorul metodelor ResultSet
     * astfel am parcurs randul salvat si am afisat pe rand fiecare informatie din fiecare coloana
     * apoi se apeleaza metoda closeConnection() pentru incheierea conexiunii cu baza de date
     */
    public void findByArtist(int artistId){
        try {
            Connection connection = Database.doConnection();
            String sql = "select * from albums where artist_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, artistId);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            System.out.println("Id album:"+result.getInt(1)+" Name album :"+result.getString(2)
                    +" Id artist: "+result.getInt(3)+" Release year :"+result.getInt(4));
            Database.closeConnection();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public int getLastIdAlbum()
    {  int res=0;
        try {
            Connection connection = Database.doConnection();
            String maxId = "select max(id) from albums";
            Statement statement=connection.createStatement();
            ResultSet result=statement.executeQuery(maxId);
            result.next();
            res=result.getInt(1);

            Database.closeConnection();
        } catch (Exception e) {
            e.getMessage();
        }
        return res+1;
    }

}
