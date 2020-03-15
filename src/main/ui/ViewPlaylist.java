package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import music.Song;

public class ViewPlaylist extends Main {
    private Scene playlist;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("playlist.fxml"));
        Parent root = loader.load();

        playlist = new Scene(root);

        TableView table = new TableView();

        TableColumn<String, Song> songName = new TableColumn<>("Song Name");
        songName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, Song> artistName = new TableColumn<>("Artist");
        artistName.setCellValueFactory(new PropertyValueFactory<>("artist"));

        TableColumn<String, Song> songLengths = new TableColumn<>("Song Length");
        songLengths.setCellValueFactory(new PropertyValueFactory<>("songLength"));

        table.getColumns().add(songName);
        table.getColumns().add(artistName);
        table.getColumns().add(songLengths);

        for (Song song : currentPlaylist.getPlaylist()) {
            table.getItems().add(song);
        }

        VBox vbox = new VBox(table);

        Scene scene = new Scene(vbox);

        primaryStage.setTitle(currentPlaylist.getName());
        primaryStage.setScene(playlist);

        primaryStage.show();
    }
}