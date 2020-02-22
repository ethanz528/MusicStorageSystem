package music;

import persistence.Reader;
import persistence.Saveable;

import java.io.PrintWriter;

// Represents a song having a name, artist and length (in seconds)
public class Song implements Saveable {
    private String name;
    private String artist;
    private int songLength;

    // REQUIRES: songLength is positive, name and artist do not contain
    // a comma (,)
    // EFFECTS: Song has been given a name, an artist and a songLength
    public Song(String name, String artist, int songLength) {
        this.name = name;
        this.artist = artist;
        this.songLength = songLength;
    }

    // MODIFIES: this
    // EFFECTS: changes the name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: changes the artist
    public void setArtist(String artist) {
        this.artist = artist;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns artist
    public String getArtist() {
        return artist;
    }

    // EFFECTS: returns songLength
    public int getSongLength() {
        return songLength;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(name);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(artist);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(songLength);
        printWriter.print(Reader.DELIMITER);
    }
}