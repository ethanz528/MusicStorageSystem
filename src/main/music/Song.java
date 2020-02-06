package music;

public class Song {
    private String name;
    private String artist;
    private int songLength;

    public Song(String name, String artist, int songLength) {
        this.name = name;
        this.artist = artist;
        this.songLength = songLength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public int getSongLength() {
        return songLength;
    }
}