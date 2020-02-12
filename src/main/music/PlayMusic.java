package music;

// Represents a song from a playlist being played or paused
public class PlayMusic {
    private Playlist playlist;
    private int currentSong;
    private boolean playing;

    // EFFECTS: playMusic has been given a playlist and song, and it is not playing
    public PlayMusic(Playlist playlist) {
        this.playlist = playlist;
        currentSong = 0;
        playing = false;
    }

    // EFFECTS: returns information about the currentS
    public String viewCurrentSong() {
        Song song = playlist.getPlaylist().get(currentSong);
        return "Playing " + song.getName() + " by " + song.getArtist();
    }

    // MODIFIES: this
    // EFFECTS: changes the currentSong to the next song
    public void nextSong() {
        if (currentSong == playlist.getPlaylist().size() - 1) {
            currentSong = 0;
        } else {
            currentSong++;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the currentSong to the previous song
    public void previousSong() {
        if (currentSong == 0) {
            currentSong = playlist.getPlaylist().size() - 1;
        } else {
            currentSong--;
        }
    }

    // MODIFIES: this
    // EFFECTS: toggles playing
    public void pause() {
        if (!playing) {
            playing = true;
        } else {
            playing = false;
        }
    }

    // EFFECTS: returns playlist
    public Playlist getPlaylist() {
        return playlist;
    }

    // EFFECTS: returns currentSong
    public int getCurrentSong() {
        return currentSong;
    }

    // EFFECTS: returns playing
    public boolean getPlaying() {
        return playing;
    }
}