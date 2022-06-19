package vue;

import javafx.scene.layout.VBox;
import modele.Chemin;
import modele.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import static modele.Chemin.cheminScenario;
import static modele.Outils.FICHIER_SCENARIO_0;
import static modele.Outils.lectureScenario;

public class VBoxTest extends VBox {
    Scenario ecriture = new Scenario();
    Scenario lecture = new Scenario();
    TreeMap<String, String> treeMapMembres = new  TreeMap <String, String> ();
    TreeMap<String, ArrayList<Integer>> mapDistance = new TreeMap<String, ArrayList<Integer>>();
    String afficheDistance = "";

    VBoxTest() throws IOException {
/*
        //Teste de la fonction ecritureScenario
        ecriture.ajoutVendeurAcheteur("Velizy", "Nanterre");
        ecriture.ajoutVendeurAcheteur("Rouen", "Nice");
        ecritureScenario("test_lecture_ecriture.txt", ecriture);
*/

/*
        //Teste de la fonction lectureScenario
        lecture = lectureScenario(new File(("test_lecture_ecriture.txt")));
        Label lablecture = new Label(lecture.toString());
        System.out.println(lecture.toString());
        getChildren().add(lablecture);
*//*
        //Teste de la lecture des membres
        File fileMembre = new File(FICHIER_MEMBRES_VILLES);
        treeMapMembres = lectureMembre(fileMembre);
        Label labMapMembres = new Label(treeMapMembres.toString());
        System.out.println(treeMapMembres.toString());
        getChildren().add(labMapMembres);
*/
/*
        File fileDistance = new File(FICHIER_DISTANCE);
        mapDistance = lectureDistance(fileDistance);
        Set<String> keys = mapDistance.keySet();
        for (String cle : keys ){
            System.out.println(cle + " " + mapDistance.get(cle));
            afficheDistance = cle + " " + mapDistance.get(cle)+ "\n";
            Label labMapDistance = new Label(afficheDistance);
            getChildren().addAll(labMapDistance);
        }
*//*
        System.out.println(distanceVille("Amiens", "Nice"));
        System.out.println(distanceVille("Nice", "Amiens"));
        System.out.println(distanceVille("Paris", "Paris"));
        System.out.println(distanceVille("Amiens", "None"));

        getChildren().add(new Label((String) distanceVille("Amiens", "Nice").toString()));
        getChildren().add(new Label((String) distanceVille("Nice", "Amiens").toString()));
        getChildren().add(new Label((String) distanceVille("Paris", "Paris").toString()));
        //getChildren().add(new Label((String) distanceVille("Amiens", "None").toString()));
*/

        /*//Affichage du resultat.
        TreeMap<String, TreeSet<String>> mapMembresParVille = mapMembresParVille();

        String affichageMembresParVilles ="";
        Set<String> cleVille = mapMembresParVille.keySet();
        for (String listeVille : cleVille) {
            affichageMembresParVilles += listeVille  + " : "+ mapMembresParVille.get(listeVille) + "\n";
        }
        System.out.println(affichageMembresParVilles);
    */

        /*
        // Teste de calcul de chemin
        Scenario scenario_1 = lectureScenario(new File(FICHIER_SCENARIO_1));
        //System.out.println();
        getChildren().add(new Label("Scénario :\n"+scenario_1.toString()+"\n"));

        //Ordre du scénario
        TreeSet<String> ordre = new TreeSet<>(); //On utilise un Treeset car il n'accepte pas les doublons
        ordre.addAll(scenario_1.getVendeurs());
        ordre.addAll(scenario_1.getAcheteurs());
        System.out.println(ordre + " ; il y a " + ordre.size() + " sommets");
        getChildren().add(new Label("Ordre du scnénario : \n"+ordre + " ; il y a " + ordre.size() + " sommets\n\n"));

        //Liste des voisins sortants
        TreeMap<String,TreeSet<String>> voisinSortants = new TreeMap<>();
        for (String sommetVendeur : ordre){
            TreeSet<String> treeSetAcheteur = new TreeSet<>();
            for (String sommetAcheteur : ordre) {
                for (int i = 0; i < scenario_1.getVendeurs().size() ; i++){
                    if(sommetVendeur.equals(scenario_1.getVendeurs().get(i)) && sommetAcheteur.equals(scenario_1.getAcheteurs().get(i))){
                        treeSetAcheteur.add(sommetAcheteur);
                    }
                }
            }
            System.out.println(treeSetAcheteur);
            voisinSortants.put(sommetVendeur,treeSetAcheteur);
        }
        System.out.println(voisinSortants);

        //Afficher les voisins sortants
        Set<String> cle = voisinSortants.keySet();
        String str = "Voisin sortant :\n";
        for (String i  : cle){
            System.out.println(i +" : " +voisinSortants.get(i));
            str += i +" : " + voisinSortants.get(i) +"\n";
        }
        getChildren().add(new Label(str));


        // Calcul des degrés entrants
        TreeMap<String, Integer> degrésEntrants = new TreeMap<>();
        for (String i : cle){
            degrésEntrants.put(i,voisinSortants.get(i).size());
        }
        System.out.println(degrésEntrants);

        // Affiche degrés entrant
        String afficheDegresEntrant = "\nDegrés Entrant :\n";
        Set<String> degre = degrésEntrants.keySet();
        for(String i : degre){
            afficheDegresEntrant += i +" = "+ degrésEntrants.get(i) + "\n";
        }
        System.out.println(afficheDegresEntrant);
        getChildren().add(new Label(afficheDegresEntrant));

        Chemin chemin = new Chemin();
        chemin = cheminScenario(lectureScenario(new File(FICHIER_SCENARIO_0)));
        System.out.println(chemin.toString());
*/
    }

}
