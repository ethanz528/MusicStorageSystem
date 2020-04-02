package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import music.PlayMusic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlayMusicPopup {

    public static Clip clip;
    public static Label label;
    public static Stage window;
    public static Button nextSongButton;
    public static Button closeButton;

    public static void display(String title, String message, PlayMusic playMusic) {
        window = new Stage();

        Image image = new Image(new File("./data/Noticed.jpg").toURI().toString(), 300, 0,true,true);
        ImageView background = new ImageView(image);

        window.initModality(Modality.NONE);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(100);

        label = new Label(message);

        makeButtons(playMusic);

        window.setOnCloseRequest(e -> clip.stop());

        VBox layout = new VBox(5);
        layout.getChildren().addAll(background, label, nextSongButton, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();

        playSound();
    }

    public static void makeButtons(PlayMusic playMusic) {
        nextSongButton = new Button("Next Song");
        nextSongButton.setOnAction(e -> {
            playMusic.nextSong();
            label.setText(playMusic.viewCurrentSong());
            clip.stop();
            playSound();
        });

        closeButton = new Button("Close Window");
        closeButton.setOnAction(e -> {
            clip.stop();
            window.close();
        });
    }

    public static void playSound() {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("./data/Noticed.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}