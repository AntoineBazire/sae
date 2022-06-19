package vue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class projet extends Application {
    public void start(Stage stage) throws IOException {

         VBox root = new VBoxRoot();

        //VBox root = new VBoxTest();
        //VBox root = new VBoxAccueil();
        //VBox root = new VBoxMenuBar();
        //VBox root = new VBoxCreer();
        //VBox root = new VBoxMembre();
        //VBox root = new VBoxDistance();

        Scene scene = new Scene(root, 800,650);
        stage.setTitle("Sae 2.01");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();

        File css = new File("css"+File.separator+ "sae201.css");
        scene.getStylesheets().add(css.toURI().toString());
    }



    public static void main(String[] args) {
        Application.launch();
    }


}