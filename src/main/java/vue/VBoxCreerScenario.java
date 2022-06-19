package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

import static modele.Constantes.FICHIER_MEMBRES_VILLES;
import static modele.Membres.lectureMembre;
import static modele.Outils.FICHIER_SAISIE_SCENARIO;
import static modele.Outils.ecritureScenario;


//####################################################//
public class VBoxCreerScenario extends VBox {
////////////////////////////////////////////////////////
    // CREATION DES COMPOSANTS GRAPHIQUES //
////////////////////////////////////////////////////////
    GridPane gridPaneSaisie = new GridPane();//tous les composants seront placés dedans
    TreeMap<String, String> treeMapMembres = lectureMembre(new File(FICHIER_MEMBRES_VILLES)); //TreeMap des membres
    Scenario newScenario = new Scenario(); //Scénario que l'utilisateur créera
    TextArea textArea = new TextArea();//Voir ce que l'utilisateur à sélectionné

    Label labCreer = new Label("Création scénario"); //titre de la page
    Label labVendeur = new Label("Vendeur");
    Label labAcheteur = new Label("Acheteur");
    Button boutonAjouter = new Button("Ajouter"); //Ajouter un vendeur et un acheteur dans le TextArea
    Button boutonReset = new Button("Reset");//Reset le contenu du TextArea
    Button boutonValider = new Button("Valider");//Valider le scénario du TextArea


    ComboBox comboVendeur = new ComboBox<String>();
    ComboBox comboAcheteur = new ComboBox<String>();


//####################################################//
    public VBoxCreerScenario() throws IOException {

////////////////////////////////////////////////////////
        // AJOUT DES MEMBRES DANS LES COMBOBOX //
////////////////////////////////////////////////////////
        Set<String> keys = treeMapMembres.keySet();
            comboVendeur.getItems().addAll(keys);
            comboAcheteur.getItems().addAll(keys);


////////////////////////////////////////////////////////
        // AJOUT DES DONNEES DANS LE GRIDPANE //
////////////////////////////////////////////////////////
        gridPaneSaisie.add(labCreer,1,0,3,1);
        gridPaneSaisie.add(comboVendeur,1,3);
        gridPaneSaisie.add(comboAcheteur,3,3);
        gridPaneSaisie.add(labVendeur,0,3);
        gridPaneSaisie.add(labAcheteur,4,3);
        gridPaneSaisie.add(boutonAjouter,2,5);
        gridPaneSaisie.add(textArea, 0,6,5,1);
        gridPaneSaisie.add(boutonReset,1,9);
        gridPaneSaisie.add(boutonValider, 3,9);



////////////////////////////////////////////////////////
        // MISE EN PAGE DU GRIDPANE //
////////////////////////////////////////////////////////
        gridPaneSaisie.setPadding(new Insets(20));
        gridPaneSaisie.setHgap(15);
        gridPaneSaisie.setVgap(15);

        gridPaneSaisie.setAlignment(Pos.CENTER);
        textArea.setEditable(false);

        gridPaneSaisie.setHalignment(labCreer, HPos.CENTER);
        gridPaneSaisie.setHalignment(boutonAjouter, HPos.CENTER);
        gridPaneSaisie.setHalignment(boutonReset, HPos.RIGHT);
        gridPaneSaisie.setHalignment(boutonValider, HPos.LEFT );

        getChildren().add(gridPaneSaisie);


////////////////////////////////////////////////////////
        // CREATION DES EVENEMENTS //
////////////////////////////////////////////////////////
    boutonAjouter.setOnAction(new EventHandler<ActionEvent>() {
//Ajoute au TextArea les membres et leurs caractéristiques choisis par l'utitisateur
        @Override
        public void handle(ActionEvent actionEvent) {
            if (comboVendeur.getValue() != null &&
                    comboAcheteur.getValue() != null){
                newScenario.ajoutVendeurAcheteur((String) comboVendeur.getValue(),(String) comboAcheteur.getValue());
                textArea.setText(newScenario.toString());
            }
        }
    });

    boutonReset.setOnAction(new EventHandler<ActionEvent>() {
//Reset le contenu du TextArea
        @Override
        public void handle(ActionEvent actionEvent) {
            textArea.clear();
        }
    });

    boutonValider.setOnAction(new EventHandler<ActionEvent>() {
//Créer un nouveau scénario selon le choix du client
        @Override
        public void handle(ActionEvent actionEvent) {
            if (textArea.getText() != null) {
                try {
                    ecritureScenario(FICHIER_SAISIE_SCENARIO, newScenario);
                    System.out.println("Ecriture du scénario réussi");
                } catch (IOException e) {
                    System.out.println("Erreur lors de l'écriture du scénario");
                }
            }
        }
    });

    }
}
