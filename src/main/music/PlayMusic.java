package music;

public class PlayMusic {
    private Playlist playlist;
    private int currentSong;
    private long start;

    public PlayMusic(Playlist playlist) {
        this.playlist = playlist;
        currentSong = 0;
        start = System.nanoTime();
    }

    public void checkSongEnded() {
        if (playlist.getPlaylist().get(currentSong).getSongLength() < (System.nanoTime() - start)) {
            nextSong();
        }
    }

    public String viewCurrentSong() {
        Song song = playlist.getPlaylist().get(currentSong);
        return "Playing " + song.getName() + " by " + song.getArtist() + " - " + ((int) System.nanoTime() - start) + "/"
                + song.getSongLength();
    }

    public void nextSong() {
        if (currentSong == playlist.getPlaylist().size() - 1) {
            currentSong = 0;
        } else {
            currentSong++;
        }
        start = System.nanoTime();
    }

    public void previousSong() {
        if (currentSong == 0) {
            currentSong = playlist.getPlaylist().size() - 1;
        } else {
            currentSong--;
        }
        start = System.nanoTime();
    }

    public void selectSong(int selectedSong) {
        currentSong = selectedSong;
        start = System.nanoTime();
    }
}