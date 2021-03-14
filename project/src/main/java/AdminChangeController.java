import com.ctc.wstx.exc.WstxOutputException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.crypto.spec.PSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdminChangeController {

    @FXML
    public GridPane cinemaGrid;
    private Vector<String> selectedSeats;
    private Vector<String> unselectedSeats;
    private String fileName;

    void movieSelect(String fileName){
        this.fileName = fileName;
        updateSeats();
        for(Node node : cinemaGrid.getChildren()){
            node.setDisable(false);
        }
        selectedSeats = new Vector<>();
        unselectedSeats = new Vector<>();
    }

   private void updateSeats(){

       ReadDataFromFile readDataFromFile = new ReadDataFromFile(fileName);

       try {
           for (Reservation reservation : readDataFromFile.getReservationsData().getReservationList()) {
               for (Node node : cinemaGrid.getChildren()) {
                   if (node.getId() != null && node.getId().equals(reservation.getSeat())) {
                       node.setStyle("-fx-background-color:  #FF5714");
                       node.setDisable(true);
                       node.setAccessibleText("1");
                   }
               }
           }
       }catch (NullPointerException ignored){}

   }

    public void selectSeat(ActionEvent actionEvent) {
        Button seat = ((Button) actionEvent.getSource());
        if (seat.getAccessibleText() != "1") {
            seat.setStyle("-fx-background-color:  #FF5714");
            seat.setAccessibleText("1");
            selectedSeats.add(seat.getId());
        } else {
            seat.setStyle("-fx-background-color:  #6EEB83");
            seat.setAccessibleText("");
            unselectedSeats.add(seat.getId());
        }
    }

    public void saveChange(ActionEvent actionEvent) {
        updateFile();
        changeScene();
    }

    private void changeScene(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SeatSelect.fxml"));
        Parent sceneSelect = null;
        try {
            sceneSelect = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene sceneSelectView = new Scene(sceneSelect);
        SeatSelectController kc = loader.getController();
        kc.movieSelect(fileName);
        Stage window = (Stage) (cinemaGrid.getScene().getWindow());
        window.setScene(sceneSelectView);
        window.show();
    }

    private void updateFile(){
        WriteDataToFile writeDataToFile= new WriteDataToFile(fileName);
        for (String selectedSeat : selectedSeats) {
            writeDataToFile.addReservation(new Reservation(selectedSeat, "", "", "", ""));
        }

        List<Reservation> reservations = writeDataToFile.getReservations();
        List<Reservation> newReservations = new ArrayList<>();

        for(Reservation r : reservations){
            if(!unselectedSeats.contains(r.getSeat()))
                newReservations.add(r);
        }

        writeDataToFile.setReservations(newReservations);
        writeDataToFile.reservationsToFile(fileName);
    }


}
