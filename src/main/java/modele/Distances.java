package modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static modele.Constantes.FICHIER_DISTANCE;

public class Distances {

    public static TreeMap<String, ArrayList<Integer>> lectureDistance(File fichier) throws IOException {
        //Lit le fichier des ditances et renvoi une TreeMap
        TreeMap<String, ArrayList<Integer>> distance = new TreeMap<String, ArrayList<Integer>>();
        String ligne;
        StringTokenizer tokenizer;

        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                String ville = tokenizer.nextToken();//ville = première mot de la ligne
                //System.out.println(ville);
                ArrayList<Integer> intDistance = new ArrayList<Integer>();//on stockera les distances dans une arraylist

                while (tokenizer.hasMoreTokens()) {
                    String int_ville  = tokenizer.nextToken();
                    intDistance.add(Integer.valueOf(int_ville)); //on ajoute toutes les distances dans la liste
                }
                //System.out.println(intDistance);
                distance.put(ville, intDistance);//on ajoute la ville en clef et la liste des distances en valeur
            }
        }
        while (ligne != null) ;//fini quand plus de ligne à parcourir
        bufferEntree.close();
        //System.out.println(distance);
        return distance;

    }

    public static Integer distanceVilles(String ville1, String ville2) throws IOException {
        //Renvoie la distance entre deux villes
        TreeMap<String, ArrayList<Integer>> mapDistance = new TreeMap<String, ArrayList<Integer>>();
        mapDistance = lectureDistance(new File(FICHIER_DISTANCE));//on récupère le fichier des distances
        Set<String> keys = mapDistance.keySet();

//on met les distances de la ville1 dans une arraylist
        ArrayList<Integer> cleVille1 = mapDistance.get(ville1);
        //System.out.println(cleVille1);
        //System.out.println(mapDistance.size());

//on met aussi toutes les villes dans une arraylist
        ArrayList<String> cleDistance = new ArrayList<>();
        for (String cle : keys){
            cleDistance.add(cle);
        }
        //System.out.println(cleDistance);

//on fait corresprondre l'index des distances de la ville1 et le l'index de la ville2
        for(int i =0; i < cleDistance.size(); i++) {
            //System.out.println(cleDistance.get(i));
            if (cleDistance.get(i).equals(ville2)){
                int resultat = cleVille1.get(i);
                //System.out.println(resultat);
                return resultat;

            }

        }
        return null;//si une des villes n'existe pas
    }
}
