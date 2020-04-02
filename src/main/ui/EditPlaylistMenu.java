package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.Playlist;
import music.Song;

public class EditPlaylistMenu extends PlaylistsMenu {
    Scene playlistScene;

    TableView<Song> table;
    TableColumn<Song, String> nameColumn;
    TableColumn<Song, String> artistColumn;
    TableColumn<Song, Integer> songLengthColumn;

    ChoiceBox<String> choiceBox;

    Button addSongButton;
    Button removeSongButton;
    Button returnButton;

    Playlist currentPlaylist;

    public void start(Stage window, Scene previousScene, String previousTitle,
                      Playlist playlist, Playlist musicLibrary) {
        currentPlaylist = playlist;

        nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        artistColumn = new TableColumn<>("Artist");
        artistColumn.setMinWidth(200);
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));

        songLengthColumn = new TableColumn<>("Song Length");
        songLengthColumn.setMinWidth(100);
        songLengthColumn.setCellValueFactory(new PropertyValueFactory<>("songLength"));

        makeButtons(window, previousScene, previousTitle, musicLibrary);

        HBox toolbar = new HBox(10);
        toolbar.setPadding(new Insets(10, 0, 10, 0));
        toolbar.getChildren().addAll(choiceBox, addSongButton, removeSongButton, returnButton);

        VBox playlistLayout = new VBox(0);
        playlistLayout.getChildren().addAll(table, toolbar);
        playlistLayout.setAlignment(Pos.CENTER);
        playlistLayout.setPadding(new Insets(10, 10, 0, 10));

        playlistScene = new Scene(playlistLayout, 600, 400);

        window.setScene(playlistScene);
        window.setTitle(currentPlaylist.getName());
    }

    public void makeButtons(Stage window, Scene previousScene, String previousTitle, Playlist musicLibrary) {
        table = new TableView<>();
        table.setItems(getSongs(currentPlaylist));
        table.getColumns().addAll(nameColumn, artistColumn, songLengthColumn);

        choiceBox = new ChoiceBox<>();

        choiceBox.getItems().addAll(getSongInformation(musicLibrary));

        addSongButton = new Button("Add");
        addSongButton.setOnAction(e -> {
            Song song = musicLibrary.getSong(choiceBox.getValue());
            currentPlaylist.addSong(song);
            table.getItems().add(song);
        });

        removeSongButton = new Button("Remove");
        removeSongButton.setOnAction(this);

        returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            window.setScene(previousScene);
            window.setTitle(previousTitle);
        });
    }

    public ObservableList<Song> getSongs(Playlist playlist) {
        ObservableList<Song> songs = FXCollections.observableArrayList();
        for (Song song : playlist.getPlaylist()) {
            songs.add(song);
        }
        return songs;
    }

    public ObservableList<String> getSongInformation(Playlist playlist) {
        ObservableList<String> songs = FXCollections.observableArrayList();
        for (Song song : playlist.getPlaylist()) {
            songs.add(song.getInfo());
        }
        return songs;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == removeSongButton) {
            ObservableList<Song> songSelected = table.getSelectionModel().getSelectedItems();
            for (Song song : songSelected) {
                currentPlaylist.removeSong(song);
                table.getItems().remove(song);
            }
        }
    }
}