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
        System.out.println("Song removed from playlist");
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
        // The entry is made in the tbl but I still get console: Error in the sql parameter, please test this in SQLServer first
        //The statement did not return a result set.
        }
    }

    public void DeletePlaylist(){
        int PlaylistIdSelectedForDeletion = ListViewPlayListNames.getSelectionModel().getSelectedItem();//getSelectedItem or getSelectedIndex?
        DB.selectSQL("DELETE FROM tbl_Playlists WHERE fld_playlistId='"+PlaylistIdSelectedForDeletion+"'");
        DB.selectSQL("DELETE FROM tbl_PlaylistContents WHERE fld_PlaylistId='"+PlaylistIdSelectedForDeletion+"'");
        System.out.println("Playlist has been deleted");
    }

    //listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue extends String> ov, String old_val, String new_val) ->
    public void AddSongToPlaylist() {
        int selectedPlaylist = ListViewPlayListNames.getSelectionModel().getSelectedIndex(); //or getselectedItem?
        String selectedPlaylistConverted=String.valueOf(selectedPlaylist);

        String playlistCounterBeforeConversion = DB.selectSQL("SELECT count(fld_SongPosition) FROM tbl_PlaylistContents WHERE fld_PlaylistId='"+selectedPlaylistConverted+"'");
        playlistCounterAfterConversion=Integer.parseInt(playlistCounterBeforeConversion);

        DB.selectSQL("INSERT INTO tbl_Employee(fldEmployeeID, fldName) VALUES(253, 'Lise Hansen'))");
        int selectedSong = ListViewAllSongs.getSelectionModel().getSelectedIndex(); //or getselectedItem?
        String selectedSongConverted=String.valueOf(selectedSong);

        DB.selectSQL("INSERT INTO tbl_PlaylistContents (fld_PlaylistId, fld_SongId, fld_SongPosition) ('"+selectedPlaylistConverted+"', '"+selectedSongConverted+"', '"+songPosition)
                "SELECT column1, column2 FROM Table1 WHERE fld_SongId='"+selectedSongConverted+"'");
        System.out.println("Song added to playlist");
    }

    public void RemoveSongFromPlaylist(){

        //AddToPlaylist button connected to handle
        //AddToPlaylist()
        //den valgte sang i ListView - send songID - som så kan bruges i...
        //sql, som tilføjer row med sang til den valgte playlist tbl_ (dvs. vi skal også have valgte playlist i Listview registreret
        //DB.selectSQL    delete FROM tbl_playlistContents WHERE fld_SongPosition='*' AND );
        //INSERT INTO tbl_Playlists (fld_Name)
        //VALUES ('det som jeg skrev i ');
        // DELETE FROM tbl_Playlist WHERE condition;
        //counterpådenplaylist--;   (jeg sql count er godt nok)
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




