package org.example.oop;

import org.example.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Chart {
    public List<String> ranking=new ArrayList<>();
    public Chart(){}

    /**
     * Dupa realizarea conexiunii cu baza de date cu ajutorul functiei getConnection(..) , cu un obiect de tip Statement
     * am facut 2 operatii de tip DDL de steregere a tabelului Charts(in caz de exista) si de creare a acestuia
     * Am selectat toate id-urile albumelor dupa o ordonare crescatoare si parcurgand rezultatul , pentru fiecare id de album
     * din tabela Albums am selectat id si numele albumului si id-ul artistului care au corespuns si le inserez in noua tabela
     * Chart , insa coloana cu nemele artistului cntinand null, ulterior am facut un update cu numele artistului care
     * are acelasi id cu cel din tabela Chart
     */
    public void fillChatsTable(){

        String URL="jdbc:mysql://localhost:3308/MusicAlbums?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "dba";
        String password = "sql";

        try {
            Connection connect = DriverManager.getConnection(URL, user, password);
            Statement statement=connect.createStatement();

            statement.execute("drop table charts");
            statement.execute("create table charts(\n" +
                    " id_album integer not null,\n"+
                    " name_album varchar(100) not null,\n" +
                    " artist_id integer not null,\n" +
                    " name_artist varchar(100) not null,\n" +
                    " primary key (id_album)\n" +
                    ");");

            String maxId="select id from albums order by id asc";

            ResultSet res=statement.executeQuery(maxId);


            while(res.next())
            { //System.out.println(res.getInt(1));

                String sql="select id,name,artist_id from albums where id=?";
                PreparedStatement preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setInt(1,res.getInt(1));
                ResultSet result=preparedStatement.executeQuery();
                result.next();

                //System.out.println(result.getInt(1)+" "+result.getString(2)+" "+result.getInt(3));

                String insert = "insert into charts (id_album ,name_album,artist_id,name_artist) values(?,?,?,?)";
                preparedStatement = connect.prepareStatement(insert);
                preparedStatement.setInt(1, res.getInt(1));
                preparedStatement.setString(2,result.getString(2) );
                preparedStatement.setInt(3, result.getInt(3));
                preparedStatement.setString(4,"null");
                preparedStatement.executeUpdate();

                String nameArtist = "select name from artists where id=?";
                preparedStatement = connect.prepareStatement(nameArtist);
                preparedStatement.setInt(1, result.getInt(3));
                ResultSet resultName=preparedStatement.executeQuery();
                resultName.next();
              //  System.out.println(resultName.getString(1));

                String updateNameArtist = "update charts set name_artist=? where artist_id=?";
                PreparedStatement prep= connect.prepareStatement(updateNameArtist);
                prep.setString(1, resultName.getString(1));
                prep.setInt(2, result.getInt(3));
                prep.executeUpdate();

            }

            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
* Dupa realizarea conexiunii cu baza de date, cu un obiect de tipul Statement execut comanda sql prin care selectez toate
* numele artistilor in ordine , obtinand clasamentul
* De asemenea acest clasament il salvez si intr-o lista pe care o voi folosi pentru a forma continutul fisierului html*/

    public void getRankingList(){


        try {
            Connection connection = Database.doConnection();

            String rankingQuery= "select name_artist from charts";
            Statement statement=connection.createStatement();
            ResultSet result=statement.executeQuery(rankingQuery);

            int prag=1;
            while(result.next())
            {
                System.out.println(prag+"."+result.getString(1));

                ranking.add(prag+"."+result.getString(1));
                prag++;
            }


            Database.closeConnection();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public List<String> getRanking(){
        return ranking;
    }
}
