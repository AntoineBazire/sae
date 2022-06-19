package modele;

import java.io.IOException;
import java.util.*;

import static modele.Distances.distanceVilles;
import static modele.Membres.getVille;

public class Chemin {
    static TreeSet<String> ordre;
    static TreeMap<String, Integer> degrésEntrants;
    static TreeMap<String,TreeSet<String>> voisinSortants;
    static List<String> itinenaire;
    static Integer distance;

    public Chemin(){
        ordre = new TreeSet<>(); //On utilise un Treeset car il n'accepte pas les doublons
        voisinSortants = new TreeMap<>(); //list des voisins sortants
        degrésEntrants = new TreeMap<>(); //list des degrés entrants
        itinenaire = new ArrayList<>(); //Résultat final indiquant un itinéraire
        distance = 0; //Distance total de l'itinéraire

    }



    public static Chemin cheminScenario(Scenario scenario) throws IOException {
//Création du chemin a partir d'un scénario
        Chemin chemin = new Chemin();

////////////////////////////////////////////////////////
        // CREATION DE L'ORDRE //
////////////////////////////////////////////////////////
//On rérupère les villes des membres du scénrario
//puis on les ajoutes dans l'ordre
        for (String iVendeur : scenario.vendeurs){
            ordre.add(getVille(iVendeur));
        }
        for (String iAcheteur : scenario.acheteurs){
            ordre.add(getVille(iAcheteur));
        }

////////////////////////////////////////////////////////
        // CREATION DE LA MAP DES VOISINS SORTANT  //
////////////////////////////////////////////////////////
        //pour les villes dans ordre comme ville du vendeur
        for (String sommetVendeur : ordre){
            //on créer une nouvelle liste
            TreeSet<String> treeSetAcheteur = new TreeSet<>();
            //pour les villes dans ordre ville de l'acheteur
            for (String sommetAcheteur : ordre) {
                //pour les index du scénario
                for (int i = 0; i < scenario.getVendeurs().size() ; i++){
                    //pour chaque ville vendeur, il y a une ou plus villes acheteur grâce au scénario
                    if(sommetVendeur.equals(getVille(scenario.getVendeurs().get(i))) && sommetAcheteur.equals(getVille(scenario.getAcheteurs().get(i)))){
                        //on ajout toute les ville acheteur dans la liste
                        treeSetAcheteur.add(sommetAcheteur);
                    }
                }
            }
            //on ajout dans voisin sortant la cle vendeur et la valeur la liste des acheteurs
            voisinSortants.put(sommetVendeur,treeSetAcheteur);
        }
////////////////////////////////////////////////////////
        // CREATION DE LA MAP DES DEGRES ENTRANTS //
////////////////////////////////////////////////////////
        Set<String> keysVoisins = voisinSortants.keySet();
        for (String villekey : keysVoisins){
            int nbr_degre = 0;
            //boucles pour récupérer les valeurs
            for (String villekey2 : keysVoisins){
                for (String villeValeur : voisinSortants.get(villekey2)){
                    //si on retrouve la cle dans les valeurs
                    if (villekey.equals(villeValeur)){
                        //on augmente le nombres de degrés
                        nbr_degre += 1;
                    }
                }
            }
            degrésEntrants.put(villekey,nbr_degre);
        }
////////////////////////////////////////////////////////
        // CALCUL D'ITINERAIRE//
////////////////////////////////////////////////////////
        itinenaire.add("Vélizy"); //on ajoute Vélizy au départ du voyage
        while (voisinSortants.size() != 0){
            System.out.println(voisinSortants);
            System.out.println(degrésEntrants);

            Set<String> cleDegres = degrésEntrants.keySet();
            int degreMin = degrésEntrants.get(degrésEntrants.firstKey());
            String source = degrésEntrants.firstKey();

            if (degrésEntrants.size() >= 2){
                for (String i : cleDegres ){
                    if (degrésEntrants.get(i) < degreMin) { //si degré entrant d'une ville = 0
                        source = i ; // la ville devient la nouvelle source
                    }
                }
            }
            itinenaire.add(source); //on l'ajoute à l'itinéraire
            //System.out.println(itinenaire.get(itinenaire.size()-2)+ " " +itinenaire.get(itinenaire.size()-1));
            //System.out.println(distanceVilles(itinenaire.get(itinenaire.size()-2),itinenaire.get(itinenaire.size()-1)));

            //on prend les 2 dernières villes de l'ininéraire et on fait la distance qui les sépare
            distance +=  distanceVilles(itinenaire.get(itinenaire.size()-2),itinenaire.get(itinenaire.size()-1));


//Dans la liste des voisins sortant de la source
            for (String villeEntrant : voisinSortants.get(source)){
                //System.out.println(villeEntrant + " : " + degrésEntrants.get(villeEntrant));
                degrésEntrants.replace(villeEntrant, degrésEntrants.get(villeEntrant) -1);
            }
//On supprime la source des voisins sortants et des degés entrants
            voisinSortants.remove(source);
            degrésEntrants.remove(source);
        }
//On ajoute à la fin de l'itinéraire l'arrivée qui est Vélizy et sa distance par rapport à la dernière ville parcouru
        itinenaire.add("Vélizy");
        distance +=  distanceVilles(itinenaire.get(itinenaire.size()-2),itinenaire.get(itinenaire.size()-1));

        System.out.println(itinenaire+"\n");
        return chemin;
    }




    public String toString(){
// Affiche l'itineraire
        String affichage = "";
        affichage += "Itineraire :\n";
        for (int i = 0; i <itinenaire.size()-1;i++){
            affichage += itinenaire.get(i) +" -> ";
        }
        affichage += itinenaire.get(itinenaire.size()-1) + " | " +distance + " km";
        return affichage;
    }

}
