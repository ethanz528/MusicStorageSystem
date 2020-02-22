package persistence;

import music.Playlist;
import music.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testMusic.txt";
    private Writer testWriter;
    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    private Playlist musicLibrary;
    private Playlist playlist1;
    private Playlist playlist2;
    private Playlist playlist3;
    private Playlist playlist4;
    private Playlist playlist5;
    private ArrayList<Playlist> playlists;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        song1 = new Song("Boats", "Jacob", 237);
        song2 = new Song("Airplanes", "Jessica", 152);
        song3 = new Song("Trains", "Jean", 183);
        song4 = new Song("Cars", "Jillian", 76);
        musicLibrary = new Playlist("Music Library");
        musicLibrary.addSong(song1);
        musicLibrary.addSong(song2);
        musicLibrary.addSong(song3);
        musicLibrary.addSong(song4);
        playlist1 = new Playlist("Moves");
        playlist1.addSong(song1);
        playlist1.addSong(song2);
        playlist1.addSong(song3);
        playlist1.addSong(song4);
        playlist2 = new Playlist("Has Wheels");
        playlist2.addSong(song2);
        playlist2.addSong(song3);
        playlist2.addSong(song4);
        playlist3 = new Playlist("What is the Ground?");
        playlist3.addSong(song1);
        playlist3.addSong(song2);
        playlist4 = new Playlist("Space Travel");
        playlist5 = new Playlist("Flies");
        playlist5.addSong(song2);
        playlists = new ArrayList<Playlist>();
        playlists.add(playlist1);
        playlists.add(playlist2);
        playlists.add(playlist3);
        playlists.add(playlist4);
        playlists.add(playlist5);
    }

    @Test
    void testWriteMusic() {
        testWriter.write(musicLibrary);
        for (Playlist playlist : playlists) {
            testWriter.write(playlist);
        }
        testWriter.close();

        try {
            List<Playlist> playlists = Reader.readPlaylists(new File(TEST_FILE));
            Playlist musicLibrary = playlists.get(0);
            assertEquals("Music Library", musicLibrary.getName());
            assertEquals(song1.getName(), musicLibrary.getPlaylist().get(0).getName());
            assertEquals(song1.getArtist(), musicLibrary.getPlaylist().get(0).getArtist());
            assertEquals(song1.getSongLength(), musicLibrary.getPlaylist().get(0).getSongLength());
            assertEquals(song2.getName(), musicLibrary.getPlaylist().get(1).getName());
            assertEquals(song2.getArtist(), musicLibrary.getPlaylist().get(1).getArtist());
            assertEquals(song2.getSongLength(), musicLibrary.getPlaylist().get(1).getSongLength());
            assertEquals(song3.getName(), musicLibrary.getPlaylist().get(2).getName());
            assertEquals(song3.getArtist(), musicLibrary.getPlaylist().get(2).getArtist());
            assertEquals(song3.getSongLength(), musicLibrary.getPlaylist().get(2).getSongLength());
            assertEquals(song4.getName(), musicLibrary.getPlaylist().get(3).getName());
            assertEquals(song4.getArtist(), musicLibrary.getPlaylist().get(3).getArtist());
            assertEquals(song4.getSongLength(), musicLibrary.getPlaylist().get(3).getSongLength());

            Playlist playlist1 = playlists.get(1);
            assertEquals("Moves", playlist1.getName());
            assertEquals(song1.getName(), playlist1.getPlaylist().get(0).getName());
            assertEquals(song1.getArtist(), playlist1.getPlaylist().get(0).getArtist());
            assertEquals(song1.getSongLength(), playlist1.getPlaylist().get(0).getSongLength());
            assertEquals(song2.getName(), playlist1.getPlaylist().get(1).getName());
            assertEquals(song2.getArtist(), playlist1.getPlaylist().get(1).getArtist());
            assertEquals(song2.getSongLength(), playlist1.getPlaylist().get(1).getSongLength());
            assertEquals(song3.getName(), playlist1.getPlaylist().get(2).getName());
            assertEquals(song3.getArtist(), playlist1.getPlaylist().get(2).getArtist());
            assertEquals(song3.getSongLength(), playlist1.getPlaylist().get(2).getSongLength());
            assertEquals(song4.getName(), playlist1.getPlaylist().get(3).getName());
            assertEquals(song4.getArtist(), playlist1.getPlaylist().get(3).getArtist());
            assertEquals(song4.getSongLength(), playlist1.getPlaylist().get(3).getSongLength());

            Playlist playlist2 = playlists.get(2);
            assertEquals("Has Wheels", playlist2.getName());
            assertEquals(song2.getName(), playlist2.getPlaylist().get(0).getName());
            assertEquals(song2.getArtist(), playlist2.getPlaylist().get(0).getArtist());
            assertEquals(song2.getSongLength(), playlist2.getPlaylist().get(0).getSongLength());
            assertEquals(song3.getName(), playlist2.getPlaylist().get(1).getName());
            assertEquals(song3.getArtist(), playlist2.getPlaylist().get(1).getArtist());
            assertEquals(song3.getSongLength(), playlist2.getPlaylist().get(1).getSongLength());
            assertEquals(song4.getName(), playlist2.getPlaylist().get(2).getName());
            assertEquals(song4.getArtist(), playlist2.getPlaylist().get(2).getArtist());
            assertEquals(song4.getSongLength(), playlist2.getPlaylist().get(2).getSongLength());

            Playlist playlist3 = playlists.get(3);
            assertEquals("What is the Ground?", playlist3.getName());
            assertEquals(song1.getName(), playlist3.getPlaylist().get(0).getName());
            assertEquals(song1.getArtist(), playlist3.getPlaylist().get(0).getArtist());
            assertEquals(song1.getSongLength(), playlist3.getPlaylist().get(0).getSongLength());
            assertEquals(song2.getName(), playlist3.getPlaylist().get(1).getName());
            assertEquals(song2.getArtist(), playlist3.getPlaylist().get(1).getArtist());
            assertEquals(song2.getSongLength(), playlist3.getPlaylist().get(1).getSongLength());

            Playlist playlist4 = playlists.get(4);
            assertEquals("Space Travel", playlist4.getName());

            Playlist playlist5 = playlists.get(5);
            assertEquals("Flies", playlist5.getName());
            assertEquals(song2.getName(), playlist5.getPlaylist().get(0).getName());
            assertEquals(song2.getArtist(), playlist5.getPlaylist().get(0).getArtist());
            assertEquals(song2.getSongLength(), playlist5.getPlaylist().get(0).getSongLength());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
