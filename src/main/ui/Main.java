package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application implements EventHandler<ActionEvent> {
    Stage window;
    Scene mainMenuScene;

    Button musicLibraryButton;
    Button playlistsButton;
    Button playMusicButton;
    Button saveMusicButton;
    Button loadMusicButton;
    Button quitButton;

    Label text;

    MusicLibraryMenu musicLibraryMenu;
    PlaylistsMenu playlistsMenu;
    EditPlaylistMenu editPlaylistMenu;
    PlayMusicMenu playMusicMenu;

    RepackagedMusicApp musicApp;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;

        musicLibraryMenu = new MusicLibraryMenu();
        playlistsMenu = new PlaylistsMenu();
        editPlaylistMenu = new EditPlaylistMenu();
        playMusicMenu = new PlayMusicMenu();

        musicApp = new RepackagedMusicApp();

        musicApp.init();

        loadButtons();

        window.setOnCloseRequest(e -> closeWindow());

        text = new Label();

        VBox mainMenuLayout = new VBox(25);
        mainMenuLayout.getChildren().addAll(musicLibraryButton, playlistsButton, playMusicButton,
                saveMusicButton, loadMusicButton, quitButton, text);
        mainMenuLayout.setAlignment(Pos.CENTER);

        mainMenuScene = new Scene(mainMenuLayout, 600, 400);

        window.setScene(mainMenuScene);
        window.setTitle("Main Menu");
        window.show();
    }

    public void loadButtons() {
        musicLibraryButton = new Button("Music Library");
        musicLibraryButton.setOnAction(this);
        playlistsButton = new Button("Playlists");
        playlistsButton.setOnAction(this);
        playMusicButton = new Button("Play Music");
        playMusicButton.setOnAction(this);
        saveMusicButton = new Button("Save Music");
        saveMusicButton.setOnAction(this);
        loadMusicButton = new Button("Load Music");
        loadMusicButton.setOnAction(this);
        quitButton = new Button("Quit");
        quitButton.setOnAction(this);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == musicLibraryButton) {
            musicLibraryMenu.start(window, mainMenuScene, "Main Menu", musicApp.musicLibrary);
        } else if (event.getSource() == playlistsButton) {
            playlistsMenu.start(window, mainMenuScene, "Main Menu", musicApp);
        } else if (event.getSource() == playMusicButton) {
            playMusicMenu.start(window, mainMenuScene, "Main Menu", musicApp);
        } else if (event.getSource() == saveMusicButton) {
            musicApp.saveMusic();
            text.setText(musicApp.saveMusic());
        } else if (event.getSource() == loadMusicButton) {
            musicApp.loadMusic();
            text.setText(musicApp.loadMusic());
        } else if (event.getSource() == quitButton) {
            closeWindow();
        }
    }

    private void closeWindow() {
        window.close();
    }
}