package sg.edu.rp.c346.id22017979.songlist;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String song;
    private String singer;
    private String year;
    private int rating;

    public Song(int id, String song, String singer, String year, int rating) {
        this.id = id;
        this.song = song;
        this.singer = singer;
        this.year = year;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getSong() { return song; }
    public String getSinger() { return singer; }
    public String getYear() { return year; }
    public int getRating() { return rating; }
    public String getStar(){
        String star = "⭐".repeat(rating);
        return star; }

    @NonNull
    @Override
    public String toString() {
        String star = "⭐".repeat(rating);
        String output = String.format("%s\n%s - %s\n" +star, song, singer, year );
        return output;
    }

    public void setSongContent(String song, String singer, String year, int rating) {
        this.song = song;
        this.singer = singer;
        this.year = year;
        this.rating = rating;
    }

}

