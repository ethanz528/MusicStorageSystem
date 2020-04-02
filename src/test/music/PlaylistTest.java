package music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistTest {
    Playlist playlist1;
    String name = "playlist1";
    Song song1 = new Song("Boats", "Jacob", 237);
    Song song2 = new Song("Airplanes", "Jessica", 152);

    @BeforeEach
    void runBefore() {
        playlist1 = new Playlist(name);
        playlist1.addSong(song1);
        playlist1.addSong(song2);
    }

    @Test
    void testPlaylist() {
        String name = "playlist2";
        Playlist playlist2 = new Playlist(name);
        assertEquals(name, playlist2.getName());
        assertEquals(0, playlist2.getNumOfSongs());
    }

    @Test
    void testAddSong() {
        Playlist playlist2 = new Playlist("playlist2");
        assertEquals(0, playlist2.getNumOfSongs());
        playlist2.addSong(song1);
        assertEquals(1, playlist2.getNumOfSongs());
        playlist2.addSong(song1);
        assertEquals(1, playlist2.getNumOfSongs());
        playlist2.addSong(song2);
        assertEquals(2, playlist2.getNumOfSongs());
    }

    @Test
    void testRemoveSong() {
        assertEquals(2, playlist1.getNumOfSongs());
        playlist1.removeSong(song2);
        assertEquals(1, playlist1.getNumOfSongs());
        playlist1.removeSong(song2);
        assertEquals(1, playlist1.getNumOfSongs());
        playlist1.removeSong(song2.getName());
        assertEquals(1, playlist1.getNumOfSongs());
        playlist1.removeSong(song1.getName());
        assertEquals(0, playlist1.getNumOfSongs());
    }

    @Test
    void testViewPlaylist() {
        assertEquals("playlist1:\n2 songs - 389 seconds\n1. Boats - Jacob (237s)\n2. Airplanes - Jessica (152s)",
                playlist1.viewPlaylist());
    }

    @Test
    void testSetName() {
        String name = "Music Library";
        playlist1.setName(name);
        assertEquals(name, playlist1.getName());
    }

    @Test
    void testGetName() {
        assertEquals("playlist1", playlist1.getName());
    }

    @Test
    void testGetPlaylist() {
        ArrayList<Song> playlist = new ArrayList();
        playlist.add(song1);
        playlist.add(song2);
        assertEquals(playlist, playlist1.getPlaylist());
    }
}