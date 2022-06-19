package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import static modele.Constantes.FICHIER_DISTANCE;
import static modele.Distances.distanceVilles;
import static modele.Distances.lectureDistance;

public class VBoxDistance extends VBox {
////////////////////////////////////////////////////////
    // CREATION DES COMPOSANTS GRAPHIQUES //
////////////////////////////////////////////////////////
    File fileDistance = new File(FICHIER_DISTANCE);
    TreeMap<String, ArrayList<Integer>> mapDistance = lectureDistance(fileDistance);
    GridPane gridPaneDistance = new GridPane();
    Label labDistance = new Label("Distances");
    Label labDistanceDescription = new Label("Ici vous pourriez connaître la distance entre 2 villes");
    Label labet = new Label("et");
    Label labDistance2ville = new Label("Distance entre :");
    Label labResultat = new Label("Résultat :");
    Label labEspace = new Label("           ");
    TextField textFieldDistance = new TextField();
    Button boutonDistance = new Button("Valider");

    ComboBox comboBoxVille1 = new ComboBox<>();
    ComboBox comboBoxVille2 = new ComboBox<>();


    VBoxDistance() throws IOException {
////////////////////////////////////////////////////////
        // AJOUT DES VILLES DANS LES COMBOBOX //
////////////////////////////////////////////////////////

        Set<String> keys = mapDistance.keySet();
        for (String cle : keys ) {
            comboBoxVille1.getItems().add(cle);
            comboBoxVille2.getItems().add(cle);
        }
////////////////////////////////////////////////////////
        // AJOUT DES COMPOSANTS DANS LE GRIDPANE //
////////////////////////////////////////////////////////

        getChildren().add(labDistance);

        gridPaneDistance.setPadding(new Insets(20));
        gridPaneDistance.setHgap(15);
        gridPaneDistance.setVgap(15);


        gridPaneDistance.add(labDistance,0,0,5,1);

        gridPaneDistance.add(labDistanceDescription,0,2,5,1);

        gridPaneDistance.add(labDistance2ville,0,3);
        gridPaneDistance.add(comboBoxVille1,1,3);
        gridPaneDistance.add(labet,2,3);
        gridPaneDistance.add(comboBoxVille2,3,3);
        gridPaneDistance.add(labEspace,4,3);

        gridPaneDistance.add(labResultat,1,4);
        gridPaneDistance.add(textFieldDistance,2,4);

        gridPaneDistance.add(boutonDistance,2,5);

////////////////////////////////////////////////////////
        // CARACTERISTIQUES DU GRIDPANE //
////////////////////////////////////////////////////////
        gridPaneDistance.setHalignment(labDistance, HPos.CENTER);
        gridPaneDistance.setHalignment(labet, HPos.CENTER);
        gridPaneDistance.setHalignment(boutonDistance, HPos.CENTER);
        gridPaneDistance.setHalignment(labResultat, HPos.RIGHT);

        gridPaneDistance.setAlignment(Pos.CENTER);

        textFieldDistance.setEditable(false);

        getChildren().add(gridPaneDistance);

////////////////////////////////////////////////////////
        // AJOUT DES EVENEMENTS //
////////////////////////////////////////////////////////
        boutonDistance.setOnAction(new EventHandler<ActionEvent>() {
// Affiche la distance des 2 villes selectionnées dans le TextFile
            @Override
            public void handle(ActionEvent actionEvent) {
                if (comboBoxVille1.getValue() != null && comboBoxVille2.getValue() != null){
                    try {
                        textFieldDistance.setText(String.valueOf(distanceVilles(String.valueOf(comboBoxVille1.getValue()), String.valueOf(comboBoxVille2.getValue())))+" km");
                    } catch (IOException e) {
                        System.out.println("Probleme lors du caclule de distance");
                    }
                }
            }
        });
    }

}
