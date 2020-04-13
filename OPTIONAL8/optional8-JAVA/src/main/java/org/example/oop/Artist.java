package org.example.oop;
import org.example.controller.ArtistController;

public class Artist {

    public int idArtist;
    public String nameArtist;
    public String countryArtist;

    ArtistController artistController=new ArtistController();

    public Artist(){}

    public Artist(String newName,String newCountry){
        this.idArtist=artistController.getLastIdArtist();
        this.nameArtist=newName;
        this.countryArtist=newCountry;

        artistController.create(newName,newCountry);
    }

    public int getIdArtist() {
        return idArtist;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public String getCountryArtist() {
        return countryArtist;
    }


}
