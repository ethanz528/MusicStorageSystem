package persistence;

import music.Playlist;
import music.Song;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read playlist data from a file
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of playlists parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Playlist> readPlaylists(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of playlists parsed from list of strings
    // where each string contains data for one playlist
    private static List<Playlist> parseContent(List<String> fileContent) {
        List<Playlist> playlists = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            playlists.add(parsePlaylist(lineComponents));
        }

        return playlists;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 2 where element 0 represents the
    // id of the next account to be constructed, element 1 represents
    // the playlist
    // EFFECTS: returns an account constructed from components
    private static Playlist parsePlaylist(List<String> components) {
        String name = components.get(0);
        Playlist playlist = new Playlist(name);
        for (int i = 1; i < components.size() - 2; i += 3) {
            String songName = components.get(i);
            String artist = components.get(i + 1);
            int songLength = Integer.parseInt(components.get(i + 2));
            playlist.addSong(new Song(songName, artist, songLength));
        }
        return playlist;
    }
}