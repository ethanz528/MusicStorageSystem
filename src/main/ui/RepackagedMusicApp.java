package ui;

import music.PlayMusic;
import music.Playlist;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

// Music application
public class RepackagedMusicApp {
    private static final String MUSIC_FILE = "./data/music.txt";
    Playlist musicLibrary;
    ArrayList<Playlist> playlists;
    PlayMusic playMusic;

    // EFFECTS: runs the music application
    public RepackagedMusicApp() {
        runMusicApp();
    }

    // MODIFIES: this
    // EFFECTS: loads music from MUSIC_FILE if its exists, processes user input
    private void runMusicApp() {
        //loadMusic();
    }

    // MODIFIES: this
    // EFFECTS: loads musicLibrary and playlists from MUSIC_FILE, if that file exists;
    // otherwise initializes accounts with default values
    String loadMusic() {
        try {
            List<Playlist> music = Reader.readPlaylists(new File(MUSIC_FILE));
            musicLibrary = music.get(0);
            playlists = new ArrayList<Playlist>();
            for (int i = 1; i < music.size(); i++) {
                playlists.add(music.get(i));
            }
            return "Music loaded from file " + MUSIC_FILE;
        } catch (IOException e) {
            init();
            return "Unable to load music";
        }
    }

    // EFFECTS: saves musicLibrary and playlists to MUSIC_FILE
    String saveMusic() {
        try {
            Writer writer = new Writer(new File(MUSIC_FILE));
            writer.write(musicLibrary);
            for (Playlist playlist : playlists) {
                writer.write(playlist);
            }
            writer.close();
            return "Music saved to file " + MUSIC_FILE;
        } catch (FileNotFoundException e) {
            return MUSIC_FILE + " not found";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Unable to save music to " + MUSIC_FILE;
    }

    // MODIFIES: this
    // EFFECTS: initializes musicLibrary, playlists and music playing
    void init() {
        musicLibrary = new Playlist("Music Library");
        playlists = new ArrayList();
        playMusic = new PlayMusic(musicLibrary);
    }
}