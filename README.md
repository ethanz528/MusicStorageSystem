#Regrade Missed Phase 3

## Music Storage System

The project I am designing this semester is a **music storage system**. Things the application can do include:

<ul>
<li>Add songs to store in your library</li>
<li>Remove music from your library</li>
<li>Create and edit multiple named playlists</li>
<li>Play music and skip songs</li>
<li>Get information about your library and playlists</li>
</ul>

This application will be used by people who need a program to store and organize their music. I decided to make this
project because I listen to music a lot, and I do not have an application I can use to organize and sort my music files.

#### User Stories

<ul>
<li>As a user, I want to be able to add and remove songs to my music library</li>
<li>As a user, I want to be able to create multiple playlists</li>
<li>As a user, I want to be able to add and remove songs from the playlists</li>
<li>As a user, I want to be able to view my music library and playlists</li>
<li>As a user, I want to be able to play music and skip songs</li>
<li>As a user, I want to be able to save my music library and playlists when I quit the program</li>
<li>As a user, I want to be able to still access my previous music library and playlists when the program starts</li>
</ul>

#### Instructions for Grader

<ul>
<li>You can generate the first required event (add and remove songs to my music library) by clicking music library in the main menu, then clicking add and remove</li>
<li>You can generate the second required event by (create multiple playlists) by clicking playlsits then clicking add and remove</li>
<li>You can locate my visual component by clicking play music, then selecting a playlist and clicking play</li>
<li>You can trigger my audio component by clicking play music, then selecting a playlist and clicking play</li>
<li>You can save the state of my application by clicking the save muci button in the main menu</li>
<li>You can reload the state of my application by clicking the load music button in the main menu</li>
</ul>

#### Phase 4: Task 2
I included a type hierarchy in my code. The super class is the Main class, and MusicLibraryMenu, PlaylistsMenu and PlayMusicMenu all inherit from it. They all override the start method in the Main class.

#### Phase 4: Task 3
The first problem is that the fields numOfSongs and length were added late to Playlist, and some of the classes have not been altered to account to these new fields.
For example, they used to take .size() to find the numOfSongs, when we could have just returned the field numOfSongs. I fixed this by going through all the methods and changing them to reflect the new fields.
Also this new field was not being used in the load and save music methods.

The second problem is that I restructured the MusicApp class to RepackagedMusicApp last phase, because the gui required less functions than the phase 2.
Also, since the gui access the playlists in the ArrayList Playlists directly, instead of by index, and other optimizations, we can remove many functions in the class.
I cleaned up the number of methods in the class so it becomes easier to check for mistakes and so forth.

UML class diagram is in the data folder