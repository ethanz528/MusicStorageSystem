package ui;

import music.Playlist;
import music.Song;
import music.PlayMusic;

import java.util.ArrayList;
import java.util.Scanner;

// Music application
public class MusicApp {
    private Playlist musicLibrary;
    private ArrayList<Playlist> playlists;
    private PlayMusic playMusic;
    private Scanner input;

    // EFFECTS: runs the music application
    public MusicApp() {
        runMusicApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runMusicApp() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("m")) {
            doMusicLibrary();
        } else if (command.equals("e")) {
            doPlaylists();
        } else if (command.equals("p")) {
            doPlay();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes musicLibrary, playlists and music playing
    private void init() {
        musicLibrary = new Playlist("Music Library");
        playlists = new ArrayList();
        playMusic = new PlayMusic(musicLibrary);
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tm -> edit Music Library");
        System.out.println("\te -> edit playlists");
        System.out.println("\tp -> play songs from playlist or Music Library");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: edit musicLibrary
    private void doMusicLibrary() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view Music Library");
        System.out.println("\ta -> add song to Music Library");
        System.out.println("\tr -> remove song from Music Library");
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("v")) {
            System.out.println("\n" + musicLibrary.viewPlaylist());
        } else if (command.equals("a")) {
            doAddSong(musicLibrary);
        } else if (command.equals("r")) {
            doRemoveSong(musicLibrary);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds song to musicLibrary
    private void doAddSong(Playlist musicLibrary) {
        System.out.println("\nEnter name of song: ");
        String name = input.nextLine();
        name = input.nextLine();
        System.out.println("Enter song's artist: ");
        String artist = input.nextLine();
        System.out.println("Enter length of song in seconds: ");
        int songLength = input.nextInt();
        musicLibrary.addSong(new Song(name, artist, songLength));
    }

    // MODIFIES: this
    // EFFECTS: removes song from playlist
    private void doRemoveSong(Playlist playlist) {
        System.out.println("\nEnter name of song: ");
        String name = input.nextLine();
        name = input.nextLine();
        playlist.removeSong(name);
    }

    // MODIFIES: this
    // EFFECTS: chooses playlist to edit or add a new playlist
    private void doPlaylists() {
        System.out.println("\nChoose playlist to edit:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println(i + 1 + ". " + playlists.get(i).getName());
        }
        System.out.println("\na -> add new playlist");
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("a")) {
            doAddPlaylist();
        } else {
            try {
                int index = Integer.parseInt(command);
                if (index <= playlists.size() && index > 0) {
                    doPlaylist(playlists.get(index - 1));
                } else {
                    System.out.println("Selection not valid...");
                }
            } catch (NumberFormatException e) {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: edit a playlist
    private void doPlaylist(Playlist playlist) {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view " + playlist.getName());
        System.out.println("\ta -> add song to " + playlist.getName());
        System.out.println("\tr -> remove song from " + playlist.getName());
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("v")) {
            System.out.println("\n" + playlist.viewPlaylist());
        } else if (command.equals("a")) {
            doAddSongPlaylist(playlist);
        } else if (command.equals("r")) {
            doRemoveSong(playlist);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new playlist
    private void doAddPlaylist() {
        System.out.println("\nEnter name of playlist: ");
        String name = input.nextLine();
        name = input.nextLine();
        playlists.add(new Playlist(name));
    }

    // MODIFIES: this
    // EFFECTS: add a song to playlist
    private void doAddSongPlaylist(Playlist playlist) {
        System.out.print("\n" + musicLibrary.viewPlaylist() + "\n");
        System.out.println("\nEnter index of song: ");
        int index = input.nextInt();
        if (index <= musicLibrary.getPlaylist().size() && index > 0) {
            playlist.addSong(musicLibrary.getPlaylist().get(index - 1));
        } else {
            System.out.println("Enter a valid index");
        }
    }

    // MODIFIES: this
    // EFFECTS: edits the music playing
    private void doPlay() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> pause/skip songs");
        System.out.println("\tp -> pick playlist to play from");
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("s")) {
            doPlayMusic();
        } else if (command.equals("p")) {
            doPlayPlaylist();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: changes Playlist to play music from
    private void doPlayPlaylist() {
        System.out.println("\nChoose playlist to play:");
        System.out.println("1. Music Library");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println(i + 2 + ". " + playlists.get(i).getName());
        }
        int index = input.nextInt();
        if (index < playlists.size() + 1 && index > 0) {
            if (index == 1) {
                playMusic = new PlayMusic(musicLibrary);
                playMusic.viewCurrentSong();
                playMusic.pause();
            } else {
                playMusic = new PlayMusic(playlists.get(index));
                playMusic.viewCurrentSong();
                playMusic.pause();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: change the currentSong or pause it
    private void doPlayMusic() {
        playMusic.viewCurrentSong();
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view current song");
        System.out.println("\tp -> play/pause song");
        System.out.println("\tf -> skip to forward song");
        System.out.println("\tb -> skip to backwards song");
        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("v")) {
            System.out.println(playMusic.viewCurrentSong());
        } else if (command.equals("p")) {
            playMusic.pause();
        } else if (command.equals("f")) {
            playMusic.nextSong();
            playMusic.viewCurrentSong();
        } else if (command.equals("b")) {
            playMusic.previousSong();
            playMusic.viewCurrentSong();
        } else {
            System.out.println("Selection not valid...");
        }
    }
}