import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Vector;

public class SeatSelectController {

    @FXML
    public Button acceptButton;
    @FXML
    public GridPane cinemaGrid;
    @FXML
    public Label warningText;
    public Button backButton;
    private String fileName;

    private Vector<String> selectedSeats;

    void movieSelect(String fileName){
        this.fileName = fileName;
        selectedSeats = new Vector<>();
        updateSeats();
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

    @FXML
    public void selectSeat(ActionEvent actionEvent) {
        Button seat = ((Button) actionEvent.getSource());

        if (! "1".equals(seat.getAccessibleText())) {
            selectedSeats.add(seat.getId());
            seat.setStyle("-fx-background-color:  #1094A8");
            seat.setAccessibleText("1");
        } else {
            selectedSeats.remove(seat.getId());
            seat.setStyle("-fx-background-color:  #6EEB83");
            seat.setAccessibleText("0");
        }

    }

    public void editSeats(ActionEvent actionEvent) {

        String adminPassword = "AdminPassword";

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        PasswordField password = new PasswordField();
        Button checkPassword = new Button("WPISZ");
        Label wrongPassword = new Label();
        wrongPassword.setStyle("-fx-text-fill: #FF5714");

        VBox vbox = new VBox(new Label("Podaj haslo"), password, wrongPassword, checkPassword);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        vbox.setSpacing(10);

        dialogStage.setScene(new Scene(vbox));
        checkPassword.setOnAction((e)->{
            if (password.getText().equals(adminPassword)) {
                dialogStage.close();
                changeSceneToAdmin();
            }
            else
                wrongPassword.setText("Nieprawidłowe hasło!");
        });
        dialogStage.showAndWait();
    }

    private void changeSceneToAdmin(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/AdminChange.fxml"));
        Parent adminChange = null;
        try {
            adminChange = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene adminChangeScene = new Scene(adminChange);
        AdminChangeController kc = loader.getController();
        kc.movieSelect(fileName);
        Stage window = (Stage) (cinemaGrid.getScene().getWindow());
        window.setScene(adminChangeScene);
        window.show();
    }

    public void changeSceneToForm(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Form.fxml"));
        Parent form = null;
        try {
            form = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene tableViewScene = new Scene(form);
        FormController kc = loader.getController();
        kc.finalization(selectedSeats,fileName);
        Stage window = (Stage) cinemaGrid.getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    public void buyTicket(ActionEvent actionEvent) {

        if(!selectedSeats.isEmpty()) {
            changeSceneToForm();
        }
        else
            warningText.setText("Proszę wybrać miejsca.");
    }

    public void changeSceneToMovies(ActionEvent actionEvent) {

        Stage window = (Stage) cinemaGrid.getScene().getWindow();
        try {
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("MovieListView.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.show();

    }
}
