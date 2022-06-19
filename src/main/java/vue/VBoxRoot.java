package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;


public class VBoxRoot extends VBox {
    VBoxMenuBar menuBar = new VBoxMenuBar();
    VBoxAccueil accueil = new VBoxAccueil();
    VBoxCreerScenario creer = new VBoxCreerScenario();
    VBoxDistance distance = new VBoxDistance();
    VBoxMembre membres = new VBoxMembre();
    StackPane stackPane = new StackPane();

    VBoxRoot() throws IOException {
        creer.setId("fondStackPane");
        accueil.setId("fondStackPane");
        membres.setId("fondStackPane");
        distance.setId("fondStackPane");
        menuBar.setId("menuBar");

        creer.labCreer.setId("titre");
        accueil.labAccueil.setId("titre");
        membres.labMembres.setId("titre");
        distance.labDistance.setId("titre");

        stackPane.getChildren().addAll(distance,membres,creer,accueil);

        stackPane.setAlignment(Pos.CENTER);

        getChildren().addAll(menuBar,stackPane);

        List<Node> liste = stackPane.getChildren();

        menuBar.menuItemAccueil.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().get(liste.indexOf(accueil)).toFront();
            }
        });
        menuBar.menuItemCréerScenario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().get(liste.indexOf(creer)).toFront();
            }
        });
        menuBar.menuItemMembres.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().get(liste.indexOf(membres)).toFront();
            }
        });


        menuBar.menuItemDistances.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stackPane.getChildren().get(liste.indexOf(distance)).toFront();

            }
        });

    }
}
