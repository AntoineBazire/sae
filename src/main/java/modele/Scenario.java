package modele;

import javafx.scene.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static modele.Distances.distanceVilles;
import static modele.Membres.getVille;

public class Scenario extends Node {
    List<String> vendeurs;
    List<String> acheteurs;

    public Scenario() {
        vendeurs = new ArrayList<>();
        acheteurs = new ArrayList<>();
    }

    public void ajoutVendeurAcheteur(String vendeur, String acheteur) {
        vendeurs.add(vendeur);
        acheteurs.add(acheteur);
    }

    public String toString() {
        String affichage = "";
        for (int i = 0; i < vendeurs.size(); i++) {
            try {
                affichage += vendeurs.get(i) + " -> " + acheteurs.get(i) +" | "
                        + getVille(vendeurs.get(i)) + " -> " + getVille(acheteurs.get(i))+" | "
                        + distanceVilles(getVille(vendeurs.get(i)),getVille(acheteurs.get(i)))+"km\n";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return affichage;
    }
    public List<String> getVendeurs() {
        return vendeurs;
    }

    public List<String> getAcheteurs() {
        return acheteurs;
    }

}
