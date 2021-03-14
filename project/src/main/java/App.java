import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {

    private final static String appPassword = "AppPassword";
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene (FXMLLoader.load(getClass().getResource("/MovieListView.fxml"))));
        stage.setOnCloseRequest((e)->{

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            PasswordField password = new PasswordField();
            Button checkPassword = new Button("WPISZ");
            Label wrongPassword = new Label();
            wrongPassword.setStyle("-fx-text-fill: #FF5714");
            VBox vbox = new VBox(new Label("Podaj haslo, aby wyjść z aplikacji"), password, wrongPassword, checkPassword);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(30));
            vbox.setSpacing(10);
            vbox.setMinWidth(300);
            dialogStage.setScene(new Scene(vbox));

            checkPassword.setOnAction((a)->{
                if (password.getText().equals(appPassword)) {
                    dialogStage.close();
                    stage.close();
                }
                else {
                    wrongPassword.setText("Nieprawidłowe hasło!");
                }
            });
            dialogStage.showAndWait();
            e.consume();
        });

        stage.show();
    }

}
