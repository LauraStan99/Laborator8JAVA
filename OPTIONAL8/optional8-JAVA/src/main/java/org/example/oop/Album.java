package org.example.oop;

import org.example.controller.AlbumController;

public class Album {
    public int idAlbum;
    public String nameAlbum;
    public int idArtist;
    public int releaseYear;
    AlbumController albumController=new AlbumController();

    public Album(){}

    public Album(String newName, int newIdArtist,int newReleaseYear)
    {
        this.idAlbum=albumController.getLastIdAlbum();
        this.nameAlbum=newName;
        this.idArtist=newIdArtist;
        this.releaseYear=newReleaseYear;

        albumController.create(newName,newIdArtist,newReleaseYear);
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public int getIdArtist() {
        return idArtist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

}
