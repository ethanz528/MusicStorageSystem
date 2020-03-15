package ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import music.PlayMusic;
import music.Playlist;
import music.Song;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private Stage stage;
    private Scene mainMenu;
    private static final String MUSIC_FILE = "./data/music.txt";
    protected Playlist musicLibrary;
    private ArrayList<Playlist> playlists;
    private PlayMusic playMusic;
    protected Playlist currentPlaylist;
    protected Song selectedSong;

    @FXML
    private Text text;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();
        loadMusic();
        //((Controller) loader.getController()).setPrimaryStage(primaryStage);

        mainMenu = new Scene(root);
        primaryStage.setTitle("Music App");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public void musicLibrary() throws Exception {
        currentPlaylist = musicLibrary;
        Main abc = new ViewPlaylist();
        abc.start(stage);
    }

    // MODIFIES: this
    // EFFECTS: loads musicLibrary and playlists from MUSIC_FILE, if that file exists;
    // otherwise initializes accounts with default values
    public void loadMusic() {
        try {
            List<Playlist> music = Reader.readPlaylists(new File(MUSIC_FILE));
            musicLibrary = music.get(0);
            playlists = new ArrayList<Playlist>();
            for (int i = 1; i < music.size(); i++) {
                playlists.add(music.get(i));
            }
        } catch (IOException e) {
            musicLibrary = new Playlist("Music Library");
            playlists = new ArrayList();
            playMusic = new PlayMusic(musicLibrary);
        }
    }

    // EFFECTS: saves musicLibrary and playlists to MUSIC_FILE
    public void saveMusic() {
        try {
            Writer writer = new Writer(new File(MUSIC_FILE));
            writer.write(musicLibrary);
            for (Playlist playlist : playlists) {
                writer.write(playlist);
            }
            writer.close();
            text.setText("Music saved to file " + MUSIC_FILE);
        } catch (FileNotFoundException e) {
            text.setText("Music saved to file " + MUSIC_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}