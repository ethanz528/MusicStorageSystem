package music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongTest {
    Song song1;
    String name = "Boats";
    String artist = "Jacob";
    int songLength = 237;

    @BeforeEach
    void runBefore() {
        song1 = new Song(name, artist, songLength);
    }

    @Test
    void testSong() {
        String name = "Airplanes";
        String artist = "Jessica";
        int songLength = 152;
        Song song2 = new Song(name, artist, songLength);
        assertEquals(name, song2.getName());
        assertEquals(artist, song2.getArtist());
        assertEquals(songLength, song2.getSongLength());
    }

    @Test
    void testSetName() {
        String name = "Cars";
        song1.setName(name);
        assertEquals(name, song1.getName());
    }

    @Test
    void testSetArtist() {
        String artist = "James";
        song1.setArtist(artist);
        assertEquals(artist, song1.getArtist());
    }

    @Test
    void testGetName() {
        assertEquals(name, song1.getName());
    }

    @Test
    void testGetArtist() {
        assertEquals(artist, song1.getArtist());
    }

    @Test
    void testGetSongLength() {
        assertEquals(songLength, song1.getSongLength());
    }
}