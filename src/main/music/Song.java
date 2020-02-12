package music;

// Represents a song having a name, artist and length (in seconds)
public class Song {
    private String name;
    private String artist;
    private int songLength;

    // REQUIRES: songLength is positive
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
}