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
import modele.Chemin;
import modele.Scenario;

import java.io.File;
import java.io.IOException;

import static modele.Chemin.cheminScenario;
import static modele.Outils.lectureScenario;

public class VBoxAccueil extends VBox {
//
// Page Accueil :
// contenant une partie Scénario où on verra le contenu du celui-ci,
// un partie Chemin qui affichera les chemins du scénario,
// le client pourras choisir son scénario à traiter
//

////////////////////////////////////////////////////////
    // CREATION DES COMPOSANTS GRAPHIQUES //
////////////////////////////////////////////////////////
    GridPane gridPaneAccueil = new GridPane();
    Label labAccueil = new Label("Accueil");//Titre de la page
    Label labChoisirScenario = new Label("Scenario : ");
    Label labScenario = new Label("Scenario");
    Label labChemin = new Label("Chemin");
    TextArea textAreaScenario = new TextArea();
    TextArea textAreaChemin = new TextArea();
    Button boutonValiderScenario = new Button("Valider");
    ComboBox comboboxScenario = new ComboBox();
    File[] listFichiers = new File("Scenarios").listFiles();//Liste des fichiers dans le dossier Scénarios

    VBoxAccueil(){
////////////////////////////////////////////////////////
        // CARACTERISTIQUES DES TEXTAREA//
////////////////////////////////////////////////////////


        textAreaScenario.setMaxSize(300,350);
        textAreaScenario.setMinSize(300,350);
        textAreaChemin.setMaxSize(300,350);
        textAreaChemin.setMinSize(300,350);
        textAreaChemin.setEditable(false);
        textAreaScenario.setEditable(false);


////////////////////////////////////////////////////////
        // AJOUT DES FICHIERS SCENARIOS DANS LE COMBOBOX //
////////////////////////////////////////////////////////
        for ( File fichier : listFichiers) {
            comboboxScenario.getItems().add(fichier.getName());
        }

////////////////////////////////////////////////////////
        // AJOUT DES COMPOSANTS DANS LE GRIDPANE //
////////////////////////////////////////////////////////
        gridPaneAccueil.add(labAccueil,2,0);

        gridPaneAccueil.add(labChoisirScenario,0,2);
        gridPaneAccueil.add(comboboxScenario,1,2);

        gridPaneAccueil.add(labScenario,0,3,2,1);
        gridPaneAccueil.add(labChemin,3,3,2,1);
        gridPaneAccueil.add(textAreaScenario,0,4,2,4);
        gridPaneAccueil.add(textAreaChemin,3,4,2,4);

        gridPaneAccueil.add(boutonValiderScenario,2,8);

////////////////////////////////////////////////////////
        // CARACTERISTIQUES DU GRIDPANE //
////////////////////////////////////////////////////////
        gridPaneAccueil.setHalignment(labScenario, HPos.CENTER);
        gridPaneAccueil.setHalignment(labChemin, HPos.CENTER);

        gridPaneAccueil.setPadding(new Insets(20));
        gridPaneAccueil.setHgap(15);
        gridPaneAccueil.setVgap(15);
        gridPaneAccueil.setAlignment(Pos.CENTER);

        getChildren().add(gridPaneAccueil);

////////////////////////////////////////////////////////
        // AJOUT DES EVENEMENTS //
////////////////////////////////////////////////////////
        comboboxScenario.setOnAction(new EventHandler<ActionEvent>() {
//Quand le client sélectionne un scénario, affiche le contenu dans le ScrollPane
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    File file = new File("Scenarios/"+comboboxScenario.getValue());
                    Scenario scenarioFichier = lectureScenario(file);
                    textAreaScenario.setText(String.valueOf(scenarioFichier.toString()));
                    textAreaChemin.clear();
                } catch (IOException e) {
                    System.out.println("Erreur lors de l'ajout du scenario");
                }
            }
        });

        boutonValiderScenario.setOnAction(new EventHandler<ActionEvent>() {
//Quand le client valide son scénario, affiche les differents les différents chemins possibles dans le ScrollPane Chemins
            @Override
            public void handle(ActionEvent actionEvent) {
                if (comboboxScenario.getValue() != null){
                    try {
                        Scenario scenario = lectureScenario(new File("Scenarios/"+comboboxScenario.getValue()));
                        Chemin chemin = cheminScenario(scenario);
                        textAreaChemin.setText(chemin.toString());
                    } catch (IOException e) {
                        System.out.println("Erreur lors de l'affichage du chemin");
                    }
                }
            }
        });


    }

}
