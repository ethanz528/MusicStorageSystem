package music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayMusicTest {
    String name = "musicLibrary";
    Playlist musicLibrary = new Playlist(name);
    PlayMusic playMusic;

    @BeforeEach
    void runBefore() {
        musicLibrary.addSong(new Song("Boats", "Jacob", 2));
        musicLibrary.addSong(new Song("Airplanes", "Jean", 13));
        musicLibrary.addSong(new Song("Train", "Jenny", 9));
        playMusic = new PlayMusic(musicLibrary);
    }

    @Test
    void testPlayMusic() {
        PlayMusic playMusic = new PlayMusic(musicLibrary);
        assertEquals(musicLibrary, playMusic.getPlaylist());
        assertEquals(0, playMusic.getCurrentSong());
        assertFalse(playMusic.getPlaying());
        System.out.println(playMusic.getPlaylist().getPlaylist().size());
    }

    @Test
    void testViewCurrentSong() {
        assertEquals("Playing Boats by Jacob", playMusic.viewCurrentSong());
    }

    @Test
    void testNextSong() {
        assertEquals("Playing Boats by Jacob", playMusic.viewCurrentSong());
        playMusic.nextSong();
        assertEquals("Playing Airplanes by Jean", playMusic.viewCurrentSong());
        playMusic.nextSong();
        playMusic.nextSong();
        assertEquals("Playing Boats by Jacob", playMusic.viewCurrentSong());
    }

    @Test
    void testPreviousSong() {
        assertEquals("Playing Boats by Jacob", playMusic.viewCurrentSong());
        playMusic.previousSong();
        assertEquals("Playing Train by Jenny", playMusic.viewCurrentSong());
        playMusic.previousSong();
        assertEquals("Playing Airplanes by Jean", playMusic.viewCurrentSong());
    }

    @Test
    void testPause() {
        assertFalse(playMusic.getPlaying());
        playMusic.pause();
        assertTrue(playMusic.getPlaying());
        playMusic.pause();
        assertFalse(playMusic.getPlaying());
    }

    @Test
    void testGetPlaylist() {
        assertEquals(musicLibrary, playMusic.getPlaylist());
    }

    @Test
    void testGetCurrentSong() {
        assertEquals(0, playMusic.getCurrentSong());
    }

    @Test
    void getPlaying() {
        assertFalse(playMusic.getPlaying());
    }
}
