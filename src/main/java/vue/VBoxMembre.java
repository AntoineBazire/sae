package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modele.Constantes;
import modele.Membres;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static modele.Membres.getVille;
import static modele.Membres.lectureMembre;

public class VBoxMembre extends VBox implements Constantes {
    //
    //Permet à l'utilisateur de connaître la ville d'un membres et/ou de connaître tous les membres d'une ville.
    //

////////////////////////////////////////////////////////
    // CREATION DES COMPOSANTS GRAPHIQUES //
////////////////////////////////////////////////////////
    TreeMap<String, String> listMembres = lectureMembre(new File(FICHIER_MEMBRES_VILLES)); //map des MEMBRES et leur ville.
    TreeMap<String, TreeSet<String>> mapMembresParVille = Membres.mapMembresParVille(); //map des VILLES et des membres qui y habitent.
    GridPane gridPaneMembres = new GridPane(); //gridPane qui servira à l'utilisateur de chercher les membres d'une ville
    Label labMembres = new Label("Membres");
    Label labChercherMembres = new Label("Chercher les membres d'une villes");
    Label labChercherVille = new Label("Chercher la ville d'un membre");
    ComboBox comboBoxVille = new ComboBox<>();//list des villes
    ComboBox comboBoxMembres = new ComboBox<>();//list des membres
    TextArea textAreaMembres = new TextArea();
    TextArea textAreaVille = new TextArea();
    VBoxMembre() throws IOException {
////////////////////////////////////////////////////////
        // AJOUT DES MEMBRES ET DES VILLES DANS LES COMBOBOX //
////////////////////////////////////////////////////////
        Set<String> membres = listMembres.keySet();
        for (String cle : membres ) {
            comboBoxMembres.getItems().add(cle);
        }
        for (String cleville : mapMembresParVille.keySet() ) {
            comboBoxVille.getItems().add(cleville);
        }
////////////////////////////////////////////////////////
        // AJOUT DES COMPOSANTS DANS LE GRIDPANE //
////////////////////////////////////////////////////////
        gridPaneMembres.setPadding(new Insets(20));
        gridPaneMembres.setHgap(15);
        gridPaneMembres.setVgap(15);

        gridPaneMembres.add(labMembres,2,0);

        gridPaneMembres.add(labChercherMembres,0,2);
        gridPaneMembres.add(labChercherVille,3,2);

        gridPaneMembres.add(comboBoxVille,0,3);
        gridPaneMembres.add(comboBoxMembres,3,3);

        gridPaneMembres.add(textAreaMembres,3,4);
        gridPaneMembres.add(textAreaVille,0,4);

////////////////////////////////////////////////////////
        // CARACTERISTIQUES DU GRIDPANE //
////////////////////////////////////////////////////////
        textAreaMembres.setMaxSize(300,350);
        textAreaMembres.setMinSize(300,350);
        textAreaVille.setMaxSize(300,350);
        textAreaVille.setMinSize(300,350);

        gridPaneMembres.setAlignment(Pos.CENTER);

        textAreaVille.setEditable(false);
        textAreaMembres.setEditable(false);

        getChildren().add(gridPaneMembres);

////////////////////////////////////////////////////////
        // AJOUT DES EVENEMENTS //
////////////////////////////////////////////////////////
        comboBoxVille.setOnAction(new EventHandler<ActionEvent>() {
// Affiche dans le textAreaMembre le nom des membres habitants dans la ville sélectionné dans le comboboxVille 

            @Override
            public void handle(ActionEvent actionEvent) {
                String resultat = "";
                String ville = (String) comboBoxVille.getValue();
                for (String cleville : mapMembresParVille.keySet() ) {
                    if(cleville.equals(ville)){
                        resultat+= "Habitants de "+ville +" :\n\n";
                            for (String nom : mapMembresParVille.get(cleville)){
                            resultat += nom +"\n";
                        }
                    }
                }
                textAreaVille.setText(resultat);
            }
        });
        comboBoxMembres.setOnAction(new EventHandler<ActionEvent>() {
// Affiche dans TextAreaVille la ville du membre choisi dans le comboboxMembre
            @Override
            public void handle(ActionEvent actionEvent) {
                String nom = (String) comboBoxMembres.getValue();
                String resultat = "";
                try {
                    resultat = nom +" habite à :\n\n" +getVille(nom);
                } catch (IOException e) {
                    System.out.println("Erreur pour lors de la récupération de la ville");
                }
                textAreaMembres.setText(resultat);

            }
        });


    }
}
