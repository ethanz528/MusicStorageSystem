package music;

import java.util.ArrayList;

// Represents a playlist of songs having a name
public class Playlist {
    private String name;
    private ArrayList<Song> playlist;

    // EFFECTS: Playlist has been given a name, and an empty Playlist
    public Playlist(String name) {
        this.name = name;
        playlist = new ArrayList<Song>();
    }

    // MODIFIES: this
    // EFFECTS: prints whether or not the song is already in the playlist
    public void addSong(Song song) {
        for (Song s : playlist) {
            if (s.equals(song)) {
                System.out.println(song.getName() + " is already in " + name);
                return;
            }
        }
        playlist.add(song);
        System.out.println(song.getName() + " by " + song.getArtist() + " has been successfully added to " + name);
    }

    // REQUIRES: the song is in playlist
    // MODIFIES: this
    // EFFECTS: removes the song from the playlist and prints the task has been done
    public void removeSong(Song song) {
        playlist.remove(song);
        System.out.println(song.getName() + " has been successfully removed from " + name);
    }

    // MODIFIES: this
    // EFFECTS: removes the first song with given name from the playlist, and prints a line if the song is not in the
    //          playlist
    public void removeSong(String name) {
        for (Song s : playlist) {
            if (s.getName().equals(name)) {
                removeSong(s);
                return;
            }
        }
        System.out.println(name + " does not exist in " + getName());
    }

    // EFFECTS: returns size of playlist
    public int playlistSize() {
        return playlist.size();
    }

    // EFFECTS: returns accumulative songLength of all the songs in playlist
    public int playlistTime() {
        int time = 0;
        for (Song s : playlist) {
            time += s.getSongLength();
        }
        return time;
    }

    // EFFECTS: returns a string with information about the playlist and all of its songs
    public String viewPlaylist() {
        String information = "";
        information += name + ":";
        information += "\n" + playlistSize() + " songs - " + playlistTime() + " seconds";
        int index = 1;
        for (Song song : playlist) {
            information += "\n" + index + ". " + song.getName() + " - " + song.getArtist() + " (" + song.getSongLength()
                    + "s)";
            index++;
        }
        return information;
    }

    // MODIIFES: this
    // EFFECTS: changes the name
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns playlist
    public ArrayList<Song> getPlaylist() {
        return playlist;
    }
}