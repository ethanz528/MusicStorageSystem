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

import java.util.ArrayList;

public class PlaylistsMenu extends Main {
    Scene playlistsScene;

    TableView<Playlist> table;
    TableColumn<Playlist, String> nameColumn;
    TableColumn<Playlist, Integer> numOfSongsColumn;
    TableColumn<Playlist, Integer> lengthColumn;

    TextField nameInput;

    Button addPlaylistButton;
    Button removePlaylistButton;
    Button editPlaylistButton;
    Button returnButton;

    ArrayList<Playlist> playlists;

    @SuppressWarnings("checkstyle:MethodLength")
    public void start(Stage window, Scene previousScene, String previousTitle, RepackagedMusicApp musicApp) {
        playlists = musicApp.playlists;

        editPlaylistMenu = new EditPlaylistMenu();

        nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        numOfSongsColumn = new TableColumn<>("# of Songs");
        numOfSongsColumn.setMinWidth(200);
        numOfSongsColumn.setCellValueFactory(new PropertyValueFactory<>("numOfSongs"));

        lengthColumn = new TableColumn<>("Playlist Length");
        lengthColumn.setMinWidth(50);
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        makeButtons(window, previousScene, previousTitle);

        HBox toolbar = new HBox(10);
        toolbar.setPadding(new Insets(10, 0, 10, 0));
        toolbar.getChildren().addAll(
                nameInput, addPlaylistButton, removePlaylistButton, editPlaylistButton, returnButton);

        VBox playlistsLayout = new VBox(0);
        playlistsLayout.getChildren().addAll(table, toolbar);
        playlistsLayout.setAlignment(Pos.CENTER);
        playlistsLayout.setPadding(new Insets(10, 10, 0, 10));

        playlistsScene = new Scene(playlistsLayout, 600, 400);

        window.setScene(playlistsScene);
        window.setTitle("Playlists");
    }

    public void makeButtons(Stage window, Scene previousScene, String previousTitle) {
        table = new TableView<>();
        table.setItems(getPlaylists());
        table.getColumns().addAll(nameColumn, numOfSongsColumn, lengthColumn);

        nameInput = new TextField();
        nameInput.setPromptText("Playlist Name");
        nameInput.setMaxWidth(120);

        addPlaylistButton = new Button("Add");
        addPlaylistButton.setOnAction(this);

        removePlaylistButton = new Button("Remove");
        removePlaylistButton.setOnAction(this);

        editPlaylistButton = new Button("Edit");
        editPlaylistButton.setOnAction(e -> {
            ObservableList<Playlist> playlistSelected = table.getSelectionModel().getSelectedItems();
            editPlaylistMenu.start(window, playlistsScene, "Playlists", playlistSelected.get(0), musicApp.musicLibrary);
        });

        returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            window.setScene(previousScene);
            window.setTitle(previousTitle);
        });
    }

    public ObservableList<Playlist> getPlaylists() {
        ObservableList<Playlist> playlists = FXCollections.observableArrayList();
        for (Playlist playlist : this.playlists) {
            playlists.add(playlist);
        }
        return playlists;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == addPlaylistButton) {
            Playlist playlist = new Playlist(nameInput.getText());
            playlists.add(playlist);
            table.getItems().add(playlist);
            nameInput.clear();
        } else if (event.getSource() == removePlaylistButton) {
            ObservableList<Playlist> playlistSelected = table.getSelectionModel().getSelectedItems();
            for (Playlist playlist : playlistSelected) {
                playlists.remove(playlist);
                table.getItems().remove(playlist);
            }
        }
    }
}