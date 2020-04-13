package org.example.controller;

import org.example.Database;

import java.sql.*;

public class ArtistController {
    public ArtistController() {
    }
    /*
     * Aceasta functie creeaza o noua inregistrare in tabelul Artists
     * mai intai se realizeaza conexiunea cu baza de date prin apelarea metodei doConnection
     * Am ales sa folosesc un obiect de tipul PreparedStatement pentru a realiza o interogare SQL
     * cu parametrii , care sunt setati in functie de numarul parametrului din interogare si tipul dorit
     * Dupa care se apeleaza metoda executeUpdate() pentru realizarea comenzii SQL si se inchide conexiunea
     * cu baza de date
     */
    public void create(String nameArtist, String country) {
        try {
            Connection connect = Database.doConnection();

            String sql = "insert into artists (name,country) values(?,?)";

            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, nameArtist);
            preparedStatement.setString(2, country);
            preparedStatement.executeUpdate();
            Database.closeConnection();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /*
     * Aceasta functie creeaza o interogarea asupra tabelului Artists din baza de date
     * mai intai se realizeaza conexiunea cu baza de date prin apelarea metodei doConnection
     * Am ales sa folosesc un obiect de tipul PreparedStatement pentru a realiza o interogare SQL
     * cu parametrii , care sunt setati in functie de numarul parametrului din interogare si tipul dorit
     * Dupa care se apeleaza metoda executeQuery() pentru realizarea interogarii SQL
     * Toata informatia extrasa din baza de date in urma interogarii este stocata intr-un obiect de tipul ResultSet
     * pentru a se putea face mai apoi deplasarea prin rezultat cu ajutorul metodelor ResultSet
     * astfel am parcurs randul salvat si am afisat pe rand fiecare informatie din fiecare coloana
     * apoi se apeleaza metoda closeConnection() pentru incheierea conexiunii cu baza de date
     */
    public void findByName(String name) throws ClassNotFoundException, SQLException {
        try {
            Connection connection = Database.doConnection();
            String sql = "select * from artists where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            System.out.println("Id artist:"+result.getInt(1)+" Name artist :"+result.getString(2));
            Database.closeConnection();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     *aceasta functie dupa realizarea conexiunii cu baza de date prin apelarea functiei doConnection()
     * selecteaza numarul maxim al id-urilor , iar rezultatul ii returneza incrementat cu 1 ,
     * acest lucru este necesar cand dorim sa inseram un nou artist folosindu-ne de un obiect de tipul Artist , unde
     * trebuie sa setam id-ul noului artist inserat
     */
    public int getLastIdArtist()
    {  int res=0;
        try {
            Connection connection = Database.doConnection();
            String maxId = "select max(id) from artists";
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
