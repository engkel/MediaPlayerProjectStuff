package sample;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.scene.media.MediaView;

import java.util.ArrayList;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.util.Duration;

public class Controller implements Initializable{


    @FXML
    private ListView<String> ListViewAllSongs;

    @FXML
    private  ListView<String> ListViewPlayListNames;

    @FXML
    private ListView<String> ListViewPlayList;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider ProgressBar;
    @FXML
    private Slider VolumeSlider;

    private String path;

    private MediaPlayer mediaPlayer;
    private Media media;


    int selectedPlaylistIndex;
    int selectedPlaylistSongIndex;
    int selectedSongIndex;

    boolean shuffle = false;

    @FXML
    Button Play;

    public void HandlePlay(ActionEvent event) {
        PlayMusic();
    }


    @FXML
    Button PlayTheChosenButton;

    public void HandlePlayTheChosen(ActionEvent event){
        mediaPlayer.play();
        HandleProgressBar();
        HandleVolumeSlider();
    }

    @FXML
    Button Shuffle;

    @FXML
    Button Stop;

    public void HandleStop(ActionEvent event) {
        mediaPlayer.stop();
    }

    @FXML
    Button Pause;

    public void HandlePause(ActionEvent event) {
        mediaPlayer.pause();
    }


    @FXML
    Button Next;

    public void HandleNext(ActionEvent event) {  //MISSING
    System.out.println("It works...");
    }

    @FXML
    Button Previous;

    public void HandlePrevious(ActionEvent event) {  //MISSING
        System.out.println("It works.... ");
    }

    @FXML
    Button Replay;

    public void HandleReplay(ActionEvent event) {  //MISSING
        System.out.println("It works.... ");
    }

    @FXML
    Button Search;

    public void HandleSearch(ActionEvent event) {
        Search();
    }

    @FXML
    Button NewPlaylistButton;

    @FXML public void HandleNewPlaylist(ActionEvent event) {
        NewPlaylist();
    }

    @FXML
    Button DeletePlaylistButton;

    @FXML public void HandleDeletePlaylist() {
        DeletePlaylist();
    }

    @FXML
    Button AddSongToPlaylistButton;

    @FXML public void HandleAddSongToPlaylist() {   //naming scheme, consistency?
        AddSongToPlaylist();
    }

    @FXML
    Button RemoveSongFromPlaylistButton;

    @FXML public void HandleRemoveSongFromPlaylist() {   //naming scheme, consistency?
        RemoveSongFromPlaylist();
    }

    @FXML
    TextField PlaylistName = new TextField();

    @FXML
    TextField EnterSearch = new TextField();

    //FUNCTIONS

    public void PlayMusic(){
        //selected index fra listviews - enten en playlist eller en sang fra library
        int selectedIndex = 1;
        DB.selectSQL("SELECT fld_Path FROM tbl_Songs WHERE fld_SongId="+selectedIndex+"");
        String path = DB.getData();
        System.out.print(path);
        // Build the path to the location of the media file

        // Create new Media object (the actual media content)
        String pathC = new File("src/sample/media/"+path+"").getAbsolutePath();
        // Create new Media object (the actual media content)
        media = new Media(new File(pathC).toURI().toString());
        // Create new MediaPlayer and attach the media to be played
        mediaPlayer = new MediaPlayer(media);
        //
        //mediaV.setMediaPlayer(mp); This Isn't video so shouldn't be used (?)
        // mp.setAutoPlay(true);
        // If autoplay is turned of the method play(), stop(), pause() etc controls how/when medias are played
        mediaPlayer.setAutoPlay(false);


        mediaPlayer.play();
        //HandleProgressBar();   //doesn't work here
        //HandleVolumeSlider();   //doesn't work here
    }

    public void playOpenedFile(){

    }

    public void Search() {
    /*
        String searchString = EnterSearch.getText();
        // use contains() to check if the library arraylist contains the search string
        boolean ans = SongNameList.contains(""+searchString+"");

		if (ans){
			System.out.println("Song found.");
            int index = SongNameList.indexOf(""+searchString+"");
            if(index == -1)
            {System.out.println("ArrayList does not contain search string"); }
            else{
                //popup
                // new arraylist SearchResults = searchResults
                //SearchResultsListview

                //vælg søgeresultat på popuplisten

                FxTimer.runLater(Duration.ofMillis(250);
                        popup væk
                        listView.scrollTo(N);
                listView.getFocusModel().focus(N);
                listView.getSelectionModel().select(N);


                //() -> listView.getSelectionModel().select(selectedItem));
                System.out.println("Search completed");

            }else {
                System.out.println("The list does not contain search string");
                //Popup with that message
                //wait
                //popup disappear
            }

        }

     */
     }


    /**
     * Function that creates a new playlist in the DB
     */
    public void NewPlaylist() {
        String playlistName = PlaylistName.getText();
        if( playlistName.equals("")){
            System.out.println("Invalid name. Playlist not created."); }
        else {
        System.out.println("playlist named "+playlistName+" created.");
        DB.selectSQL("INSERT INTO tbl_Playlists (fld_Name) VALUES ('"+playlistName+"')");
          // The entry is made successfully in the table in the DB but I still get an error in console:
        // "Error in the sql parameter, please test this in SQLServer first
        //The statement did not return a result set."
        //GET SQL FRA DB
            //FÅ DEN DATA IND I LISTVIEW
            //get
            //UPDATE LISTVIEW SOMEHOW - observeable skulle kunne gøre det selv.
            //   ListViewPlayListNames.add(playlistName);
        updatePlaylistsInListview();
        }
    }

    /**
     * Function that deletes the selected playlist and its contents in the playListContents table.
     */
    public void DeletePlaylist(){
        //The selected in the Listview saved in a variable
        int PlaylistIdSelectedForDeletion = ListViewPlayListNames.getSelectionModel().getSelectedIndex();//getSelectedItem or getSelectedIndex?
        //that value is then used for an sql query to delete the correct playlist and playlist contents.
        DB.selectSQL("DELETE FROM tbl_Playlists WHERE fld_playlistId='"+PlaylistIdSelectedForDeletion+"'");
        DB.selectSQL("DELETE FROM tbl_PlaylistContents WHERE fld_PlaylistId='"+PlaylistIdSelectedForDeletion+"'");
        updatePlaylistsInListview();
        System.out.println("Playlist has been deleted");
    }


    /**
     * Function that adds a selected song in the library listview to the selected playlist
     */
    public void AddSongToPlaylist() {
        //get the selected playlist in ListViewPlayListNames to know which playlist the song is supposed to be added to.
        int selectedPlaylist = ListViewPlayListNames.getSelectionModel().getSelectedIndex(); //or getselectedItem?

        //count all rows in PlaylistContents with the selected playlistId to get the correct songPosition for the new song.
        DB.selectSQL("SELECT COUNT(fld_SongPosition) FROM tbl_PlaylistContents WHERE fld_PlaylistId='"+selectedPlaylist+"'");
        String countedSongs = DB.getData();
        int countedSongsConverted = Integer.parseInt(countedSongs);
        int songPosition=countedSongsConverted+1;

        //get the selected song in the library listview and insert the new song in playlistContents.
        int selectedSong = ListViewAllSongs.getSelectionModel().getSelectedIndex(); //or getselectedItem?
        DB.selectSQL("INSERT INTO tbl_PlaylistContents (fld_PlaylistId, fld_SongId, fld_SongPosition) VALUES ('"+selectedPlaylist+"', '"+selectedSong+"', '"+songPosition+"");

        //add the number of songs to fld_NumberOfSongs in tbl_Playlists where fld_PlaylistId=x - is this necessary anymore?
        DB.selectSQL("UPDATE tbl_Playlists SET fld_NumberOfSongs = "+songPosition+"WHERE fld_PlaylistId="+selectedPlaylist+"");

        updatePlaylistContentsInListview();
        System.out.println("Song added to playlist");
    }

    /**
     * Function that removes the selected song from the selected playlist.
     */
    public void RemoveSongFromPlaylist(){
        //get the selected song in the library listview and insert the new song in playlistContents.
        int selectedPlaylist = ListViewPlayList.getSelectionModel().getSelectedIndex();
        int selectedSong = ListViewPlayListNames.getSelectionModel().getSelectedIndex(); //or getselectedItem?
        DB.selectSQL("DELETE * FROM tbl_PlaylistContents WHERE fld_PlaylistId='"+selectedPlaylist+"' AND fld_SongPosition='"+selectedSong+"'");

        //update number of songs in selected playlist - maybe not necessary anymore.
        DB.selectSQL("SELECT COUNT(fld_SongPosition) FROM tbl_PlaylistContents WHERE fld_PlaylistId='"+selectedPlaylist+"'");
        String countedSongs = DB.getData();
        DB.selectSQL("UPDATE tbl_Playlists SET fld_NumberOfSongs = "+countedSongs+"WHERE fld_PlaylistId="+selectedPlaylist+"");

        updatePlaylistContentsInListview();
        System.out.println("Song removed from playlist");
    }

    public void HandleShuffle(ActionEvent event) {
        if (shuffle=true){
            shuffleOff();
        }
        else{
            shuffleOn();
        }
    }

    private void shuffleOn() {
        shuffle = true;
        Shuffle.setText("Shuffle on");
    }

    private void shuffleOff() {
        shuffle = false;
        Shuffle.setText("Shuffle off");
    }

    /**
     * Creating a fileChooser in order to select a desired file from your computer
     * We use path in order to get the path where the file is located
     */
    public void HandleChooseFile(ActionEvent event){

    FileChooser fileChooser = new FileChooser();
    File fileToOpen = fileChooser.showOpenDialog(null);
        String path = fileToOpen.toURI().toString();

    //Checking if path contains something and is not null
        if(path !=null){
            //The selected file(path) is used as an argument for media
            //The media is then used with Media Player
             Media media = new Media(path);
             mediaPlayer = new MediaPlayer(media);
        }
    }


    /**
     * Makes the progress Bar functional and scalable with interface
     * If the user presses somewhere on the progress bar, the song will jump to that time
     * As well as if the user dragged the progress bar, the song will move in accordance
     */

    public void HandleProgressBar(){

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                ProgressBar.setValue(newValue.toSeconds());
                //Making the progress bar scalable with interface
                Duration total = mediaPlayer.getTotalDuration();
                ProgressBar.setMax(total.toSeconds());

            }
        });

        //If the user clicks somewhere on the progress Bar the song should jump to that time
        ProgressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Gets the time where the user clicks and sets it
                mediaPlayer.seek(Duration.seconds(ProgressBar.getValue()));
            }
        });

        //Used if the user dragged the progress bar in order to set the time
        ProgressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Gets the time where the user clicks and sets it
                mediaPlayer.seek(Duration.seconds(ProgressBar.getValue()));
            }
        });
    }

    /**
     * Makes the volume slider functional
     * if the volume slider is dragged or clicked the volume will change accordingly
     */

    public void HandleVolumeSlider(){
        VolumeSlider.setValue(mediaPlayer.getVolume() * 100);
        VolumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(VolumeSlider.getValue()/100);
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String SongNameData;

        System.out.print("start...");
        //Select Tbl_Songs from the Database
        DB.selectSQL("Select fld_SongName from tbl_Songs");

        //Get the data
        ArrayList<String> SongNameList= new ArrayList<>();
        do {
            SongNameData = DB.getDisplayData();
            if (SongNameData.equals(DB.NOMOREDATA)) {
                break;
            } else if (SongNameList.add(SongNameData)) {
                ListViewAllSongs.setItems((FXCollections.observableArrayList(SongNameList)));
            }
        } while (true);

        updatePlaylistsInListview();
        updatePlaylistContentsInListview();
        updateSongLibrary();
    }


    public void updatePlaylistsInListview(){
        String PlayListNameData;

        //Select Tbl_Playlists from the Database
        DB.selectSQL("Select fld_Name from tbl_Playlists");

        //Get the data
        ArrayList<String> PlayListNamesList= new ArrayList<>();
        do {
            PlayListNameData = DB.getDisplayData();
            if (PlayListNameData.equals(DB.NOMOREDATA)) {
                break;
            } else if (PlayListNamesList.add(PlayListNameData)) {
                ListViewPlayListNames.setItems((FXCollections.observableArrayList(PlayListNamesList)));
            }
        } while (true);

        ListViewPlayListNames.getItems().setAll(PlayListNamesList);
    }



    public void updatePlaylistContentsInListview(){
        String PlayListData;

        //Select Tbl_PlaylistContents from the Database
        DB.selectSQL("Select fld_SongPosition, fld_SongId from tbl_PlaylistContents");

        //Get the data
        ArrayList<String> PlayListContentsList= new ArrayList<>();

        do {
            PlayListData = DB.getDisplayData();
            if (PlayListData.equals(DB.NOMOREDATA)) {
                break;
            } else if (PlayListContentsList.add(PlayListData)) {
                ListViewPlayList.setItems((FXCollections.observableArrayList(PlayListContentsList)));
            }
        } while (true);
    }



    public void updateSongLibrary(){
        String SongPathData;

        //Select Tbl_Path from the Database
        DB.selectSQL("Select fld_Path from tbl_Songs");

        //Get the data
        ArrayList<String> SongPathList = new ArrayList<>();

        do {
            SongPathData = DB.getDisplayData();
            if (SongPathData.equals(DB.NOMOREDATA)) {
                break;
            }
            else{
                SongPathList.add(""+SongPathData+"");
            }
        } while (true);


        System.out.print(SongPathList);
    }
}





