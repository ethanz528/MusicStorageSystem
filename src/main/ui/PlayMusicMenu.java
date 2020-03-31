package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.PlayMusic;
import music.Playlist;
import music.Song;

import java.util.ArrayList;

public class PlayMusicMenu extends Main {
    Scene playMusicScene;

    TableView<Playlist> table;

    Button playButton;
    Button returnButton;

    Playlist musicLibrary;
    ArrayList<Playlist> playlists;

    Playlist currentPlaylist;
    Song currentSong;

    public void start(Stage window, Scene previousScene, String previousTitle, RepackagedMusicApp musicApp) {
        musicLibrary = musicApp.musicLibrary;
        playlists = musicApp.playlists;

        editPlaylistMenu = new EditPlaylistMenu();

        TableColumn<Playlist, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Playlist, Integer> numOfSongsColumn = new TableColumn<>("# of Songs");
        numOfSongsColumn.setMinWidth(200);
        numOfSongsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfSongs"));

        TableColumn<Playlist, Integer> lengthColumn = new TableColumn<>("Playlist Length");
        lengthColumn.setMinWidth(50);
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        table = new TableView<>();
        table.setItems(getPlaylists());
        table.getColumns().addAll(nameColumn, numOfSongsColumn, lengthColumn);

        playButton = new Button("Play");
        playButton.setOnAction(e -> {
            ObservableList<Playlist> playlistSelected = table.getSelectionModel().getSelectedItems();
            musicApp.playMusic = new PlayMusic(playlistSelected.get(0));
            PlayMusicPopup.display("Playing from " + playlistSelected.get(0).getName(),
                    musicApp.playMusic.viewCurrentSong(), musicApp.playMusic);
        });

        returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            window.setScene(previousScene);
            window.setTitle(previousTitle);
        });

        HBox toolbar = new HBox(10);
        toolbar.setPadding(new Insets(10, 0, 10, 0));
        toolbar.getChildren().addAll(playButton, returnButton);

        VBox playlistsLayout = new VBox(0);
        playlistsLayout.getChildren().addAll(table, toolbar);
        playlistsLayout.setAlignment(Pos.CENTER);
        playlistsLayout.setPadding(new Insets(10, 10, 0, 10));

        playMusicScene = new Scene(playlistsLayout, 600, 400);

        window.setScene(playMusicScene);
        window.setTitle("Play Music");
    }

    public ObservableList<Playlist> getPlaylists() {
        ObservableList<Playlist> playlists = FXCollections.observableArrayList();
        playlists.add(musicLibrary);
        for (Playlist playlist : this.playlists) {
            playlists.add(playlist);
        }
        return playlists;
    }

    public ObservableList<String> getSongInformation(Playlist playlist) {
        ObservableList<String> songs = FXCollections.observableArrayList();
        for (Song song : playlist.getPlaylist()) {
            songs.add(song.getInfo());
        }
        return songs;
    }
}