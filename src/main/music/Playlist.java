package music;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Song> playlist;

    public Playlist(String name) {
        this.name = name;
        playlist = new ArrayList<Song>();
    }

    public void addSong(Song song) {
        for (Song s : playlist) {
            if (s.equals(song)) {
                System.out.println(song.getName() + " is already in " + name);
                return;
            }
        }
        playlist.add(song);
        System.out.println(song.getName() + " has been successfully added to " + name);
    }

    public void removeSong(Song song) {
        playlist.remove(song);
        System.out.println(song.getName() + " has been successfully removed from " + name);
    }

    public void removeSong(String name) {
        for (Song s : playlist) {
            if (s.getName().equals(name)) {
                removeSong(s);
                return;
            }
        }
        System.out.println(name + " does not exist in " + getName());
    }

    public int playlistSize() {
        return playlist.size();
    }

    public int playlistTime() {
        int time = 0;
        for (Song s : playlist) {
            time += s.getSongLength();
        }
        return time;
    }

    public String viewPlaylist() {
        String information = "";
        information += name + ":\n";
        information += playlistSize() + " songs - " + playlistTime() + " seconds\n";
        int index = 1;
        for (Song song : playlist) {
            information += index + ". " + song.getName() + " - " + song.getArtist() + "\n";
            index++;
        }
        return information;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getPlaylist() {
        return playlist;
    }
}