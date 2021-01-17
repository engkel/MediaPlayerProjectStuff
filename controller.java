package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;


//This item is for the ListView all the song


public class Controller {


    private String path;
    private MediaPlayer mediaPlayer;

    public void ListViewAllSongs(Stage stage) throws Exception {

        //Select Data from the Database
        DB.selectSQL("Select * from tbl_Songs");

        //Get the data

        do {
            String SongNameData = DB.getDisplayData();
            if (SongNameData.equals(DB.NOMOREDATA)) {
                break;
            } else {
                System.out.print(SongNameData);
            }
        } while (true);


        //Create the ListView Control:

        //1. Create the List for the Listview
        ObservableList<String> NamesList = FXCollections.observableArrayList("Test Test Test");

        //2. Create the ListView
        ListView TestList= new ListView();


        //3. Creating a scene


}

public void ListViewPlayList(Stage primaryStage) throws Exception {

}

    public void HandlePlay(ActionEvent event) {
        mediaPlayer.play();

    }

    public void HandleStop() {
        mediaPlayer.stop();
    }

    public void HandlePause(ActionEvent event) {
        mediaPlayer.pause();
    }

    public void HandleNext() {
    System.out.println("It works...");
    }

    public void HandlePrevious() {
        System.out.println("It works.... ");
    }

    public void HandleReplay() {
        System.out.println("It works.... ");
    }

    public void HandleSearch() {
        Search();
        System.out.println("Search completed");
    }

    @FXML
    Button NewPlaylistButton;

    @FXML public void HandleNewPlaylist() {
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

    public void NewPlaylist() {
        String playlistName = PlaylistName.getText();
        if( playlistName.equals("")){
            System.out.println("Invalid name. Playlist not created."); }
        else {
        System.out.println("playlist named "+playlistName+" created.");
        DB.selectSQL("INSERT INTO tbl_Playlists (fld_Name) VALUES ('"+playlistName+"');");
        // The entry is made succesfully in the table in the DB but I still get an error in console:
        // "Error in the sql parameter, please test this in SQLServer first
        //The statement did not return a result set."
        }
    }

    public void DeletePlaylist(){
        //The selected in the Listview saved in a variable
        int PlaylistIdSelectedForDeletion = ListViewPlayListNames.getSelectionModel().getSelectedIndex();//getSelectedItem or getSelectedIndex?
        //that value is then used for an sql query to delete the correct playlist and playlist contents.
        DB.selectSQL("DELETE FROM tbl_Playlists WHERE fld_playlistId='"+PlaylistIdSelectedForDeletion+"'");
        DB.selectSQL("DELETE FROM tbl_PlaylistContents WHERE fld_PlaylistId='"+PlaylistIdSelectedForDeletion+"'");
        System.out.println("Playlist has been deleted");
    }

    //Do we need this one somewhere: listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue extends String> ov, String old_val, String new_val) ->

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

        System.out.println("Song added to playlist");
    }

    public void RemoveSongFromPlaylist(){
        //get the selected song in the library listview and insert the new song in playlistContents.
        int selectedPlaylist = ListViewPlayList.getSelectionModel().getSelectedIndex();
        int selectedSong = ListViewPlayListNames.getSelectionModel().getSelectedIndex(); //or getselectedItem?
        DB.selectSQL("DELETE * FROM tbl_PlaylistContents WHERE fld_PlaylistId='"+selectedPlaylist+"' AND fld_SongPosition='"+selectedSong+"'");

        //update number of songs in selected playlist - maybe not necessary anymore.
        DB.selectSQL("SELECT COUNT(fld_SongPosition) FROM tbl_PlaylistContents WHERE fld_PlaylistId='"+selectedPlaylist+"'");
        String countedSongs = DB.getData();
        DB.selectSQL("UPDATE tbl_Playlists SET fld_NumberOfSongs = "+countedSongs+"WHERE fld_PlaylistId="+selectedPlaylist+"");

        System.out.println("Song removed from playlist");
    }


    /**
     * Creating a fileChooser in order to select a desired file from your computer
     * We use path in order to get the path where the file is located
     *
     */

    public void HandleChooseFile(ActionEvent event){

    FileChooser fileChooser = new FileChooser();
    File fileToOpen = fileChooser.showOpenDialog(null);
    path = fileToOpen.toURI().toString();

    //Checking if path contains something and is not null
        if(path !=null){
            //The selected file(path) is used as an argument for media
            //The media is then used with Media Player
             Media media = new Media(path);
             mediaPlayer = new MediaPlayer(media);
             

        }

    }

    private void Search() {

    }
}




