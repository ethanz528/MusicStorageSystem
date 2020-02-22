package persistence;

import music.Playlist;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    Reader testReader;

    @Test
    void testReader() {
        testReader = new Reader();
    }

    @Test
    void testParsePlaylistFile() {
        try {
            List<Playlist> playlists = Reader.readPlaylists(new File("./data/testPlaylistFile.txt"));
            Playlist musicLibrary = playlists.get(0);
            assertEquals("Music Library", musicLibrary.getName());
            assertEquals("Boats", musicLibrary.getPlaylist().get(0).getName());
            assertEquals("Jacob", musicLibrary.getPlaylist().get(0).getArtist());
            assertEquals(237, musicLibrary.getPlaylist().get(0).getSongLength());
            assertEquals("Airplanes", musicLibrary.getPlaylist().get(1).getName());
            assertEquals("Jessica", musicLibrary.getPlaylist().get(1).getArtist());
            assertEquals(152, musicLibrary.getPlaylist().get(1).getSongLength());
            assertEquals("Trains", musicLibrary.getPlaylist().get(2).getName());
            assertEquals("Jean", musicLibrary.getPlaylist().get(2).getArtist());
            assertEquals(183, musicLibrary.getPlaylist().get(2).getSongLength());

            Playlist playlist1 = playlists.get(1);
            assertEquals("Airplanes", playlist1.getPlaylist().get(0).getName());
            assertEquals("Jessica", playlist1.getPlaylist().get(0).getArtist());
            assertEquals(152, playlist1.getPlaylist().get(0).getSongLength());
            assertEquals("Trains", playlist1.getPlaylist().get(1).getName());
            assertEquals("Jean", playlist1.getPlaylist().get(1).getArtist());
            assertEquals(183, playlist1.getPlaylist().get(1).getSongLength());

            Playlist playlist2 = playlists.get(2);
            assertEquals("What is the Ground?", playlist2.getName());
            assertEquals("Boats", playlist2.getPlaylist().get(0).getName());
            assertEquals("Jacob", playlist2.getPlaylist().get(0).getArtist());
            assertEquals(237, playlist2.getPlaylist().get(0).getSongLength());
            assertEquals("Airplanes", playlist2.getPlaylist().get(1).getName());
            assertEquals("Jessica", playlist2.getPlaylist().get(1).getArtist());
            assertEquals(152, playlist2.getPlaylist().get(1).getSongLength());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}