package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

import static modele.Constantes.FICHIER_MEMBRES_VILLES;

public class Membres {
    public static TreeMap<String, String> lectureMembre (File fichier) throws IOException {
// Renvoie une Map triée par le nom des membres grâce à un fichier membre
        TreeMap <String, String> mapMembres = new  TreeMap <String, String> ();

        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne ;

        StringTokenizer tokenizer ;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                String membre = tokenizer.nextToken();//premier token est le membre
                String ville = tokenizer.nextToken();//deuxième token est la ville
                mapMembres.put(membre,ville);//on ajoute à la map des membres
            }
        } while (ligne != null);
        bufferEntree.close();
        return mapMembres;
    }
    public static String getVille(String nom) throws IOException {
//renvoie la ville du membre en paramètre
        TreeMap<String, String> listMembre = lectureMembre(new File(FICHIER_MEMBRES_VILLES)); //on récupère le fichier membre
//on récupère les noms en clefs et on regarde à quelle ville est correspnde dans la map des membres
        Set<String> keys = listMembre.keySet();
        for (String key : keys){
            //System.out.println(key);
            if (key.equals(nom)){
                return listMembre.get(key); //retourne la ville
            }
        }
        return "Ce nom n'existe pas";
    }

    public static TreeMap<String, TreeSet<String>> mapMembresParVille() throws IOException {
//map des VILLES et des membres qui y habitent.
        TreeMap<String, TreeSet<String>> mapMembresParVille = new TreeMap<>(); //map des VILLES et des membres qui y habitent.
        TreeSet<String> listVilles = new TreeSet<>(); //liste de toute les villes des membres.
        TreeMap<String, String> listMembres = lectureMembre(new File(FICHIER_MEMBRES_VILLES)); //map des MEMBRES et leur ville.

// Liste des villes seulement.
        Set<String> noms = listMembres.keySet();//on met les noms des membres en clefs
        for (String nom : noms) {
            listVilles.add(String.valueOf(listMembres.get(nom)));//on liste les villes
        }
// Map de villes en cle, puis un Treeset de tout les membres qui habitent dans la ville cle.
        for (String ville : listVilles) {
            TreeSet<String> listNom = new TreeSet<>();
            for (String nom : noms) {
                if (ville.equals(listMembres.get(nom))) {
                    //System.out.println(ville +" "+ nom);
                    listNom.add(nom);
                    //System.out.println(nom);
                }
            }
            //System.out.println( ville +" " +listNom);
            mapMembresParVille.put(ville, listNom);
        }
        return mapMembresParVille;
    }
}
