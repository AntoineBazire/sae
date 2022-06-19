package modele;

import java.io.*;
import java.util.StringTokenizer;


public class Outils implements Constantes{
    public static Scenario lectureScenario(File fichier) throws IOException {
        // Prend en paramètre un fichier, convertit et renvoie un scénario

        Scenario scenario = new Scenario();

        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;

        StringTokenizer tokenizer;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ->");
                String tokenV = tokenizer.nextToken();
                String tokenA = tokenizer.nextToken();
                scenario.ajoutVendeurAcheteur(tokenV, tokenA);
            }
        } while (ligne != null);
            bufferEntree.close();
        return scenario;
    }
    public static void ecritureScenario (String nomFichier, Scenario scenario) throws IOException {
        // Convertit un scénario en fichier

        PrintWriter sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));
        int i = 0;
        for (String vendeur : scenario.getVendeurs()) {
            sortie.println(vendeur + " -> "+scenario.getAcheteurs().get(i))  ;
            i++;
        }
        sortie.close();
    }



    /*
    public static TreeMap<String, TreeSet<String>> lectureVille (File fichier) throws IOException {
        // Renvoie une Map triée par le nom des vile grâce à un fichier membre
        TreeMap<String, TreeSet<String>> mapMembres = new TreeMap<String, TreeSet<String>>();

        BufferedReader bufferEntree = new BufferedReader(new FileReader (fichier));
        String ligne ;

        StringTokenizer tokenizer ;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                String membre = tokenizer.nextToken();
                String ville = tokenizer.nextToken();
                mapMembres.put(ville,membre);
            }
        } while (ligne != null);
        bufferEntree.close();
        return mapMembres;
    }
*/


}


