package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.Playlist;
import music.Song;

public class MusicLibraryMenu extends Main {
    Scene playlistScene;

    TableView<Song> table;

    TextField nameInput;
    TextField artistInput;
    TextField lengthInput;

    Button addSongButton;
    Button removeSongButton;
    Button returnButton;

    Playlist currentPlaylist;

    public void start(Stage window, Scene previousScene, String previousTitle, Playlist playlist) {
        currentPlaylist = playlist;

        TableColumn<Song, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Song, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setMinWidth(200);
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));

        TableColumn<Song, Integer> songLengthColumn = new TableColumn<>("Song Length");
        songLengthColumn.setMinWidth(100);
        songLengthColumn.setCellValueFactory(new PropertyValueFactory<>("songLength"));

        table = new TableView<>();
        table.setItems(getSongs(currentPlaylist));
        table.getColumns().addAll(nameColumn, artistColumn, songLengthColumn);

        nameInput = new TextField();
        nameInput.setPromptText("Song Name");
        nameInput.setMaxWidth(120);
        artistInput = new TextField();
        artistInput.setPromptText("Artist Name");
        artistInput.setMaxWidth(120);
        lengthInput = new TextField();
        lengthInput.setPromptText("Song Length");
        lengthInput.setMaxWidth(120);

        addSongButton = new Button("Add");
        addSongButton.setOnAction(this);

        removeSongButton = new Button("Remove");
        removeSongButton.setOnAction(this);

        returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            window.setScene(previousScene);
            window.setTitle(previousTitle);
        });

        HBox toolbar = new HBox(10);
        toolbar.setPadding(new Insets(10, 0, 10, 0));
        toolbar.getChildren().addAll(
                nameInput, artistInput, lengthInput, addSongButton, removeSongButton, returnButton);

        VBox playlistLayout = new VBox(0);
        playlistLayout.getChildren().addAll(table, toolbar);
        playlistLayout.setAlignment(Pos.CENTER);
        playlistLayout.setPadding(new Insets(10, 10, 0, 10));

        playlistScene = new Scene(playlistLayout, 600, 400);

        window.setScene(playlistScene);
        window.setTitle(currentPlaylist.getName());
    }

    public ObservableList<Song> getSongs(Playlist playlist) {
        ObservableList<Song> songs = FXCollections.observableArrayList();
        for (Song song : playlist.getPlaylist()) {
            songs.add(song);
        }
        return songs;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == addSongButton) {
            Song song = new Song(nameInput.getText(), artistInput.getText(), Integer.parseInt(lengthInput.getText()));
            currentPlaylist.addSong(song);
            table.getItems().add(song);
            nameInput.clear();
            artistInput.clear();
            lengthInput.clear();
        } else if (event.getSource() == removeSongButton) {
            ObservableList<Song> songSelected = table.getSelectionModel().getSelectedItems();
            for (Song song : songSelected) {
                currentPlaylist.removeSong(song);
                table.getItems().remove(song);
            }
        }
    }
}