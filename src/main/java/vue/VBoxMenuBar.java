package vue;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;

public class VBoxMenuBar extends VBox {
    //Création d'une barre de menu
    MenuBar menuBar = new MenuBar();

    //Création des menus
    Menu menuAccueil = new Menu("Accueil");
    Menu menuCréerScenario = new Menu("Créer");
    Menu menuInfo = new Menu("Info");

    //Création des items de menus
    MenuItem menuItemAccueil = new MenuItem("Accéder à l'accueil");
    MenuItem menuItemCréerScenario = new MenuItem("Créer un scénario");
    MenuItem menuItemMembres = new MenuItem("Membres");
    MenuItem menuItemDistances = new MenuItem("Distances");

    SeparatorMenuItem separator= new SeparatorMenuItem();

    VBoxMenuBar(){
        menuAccueil.getItems().addAll(menuItemAccueil);
        menuCréerScenario.getItems().add(menuItemCréerScenario);
        menuInfo.getItems().addAll(menuItemMembres,separator,menuItemDistances);
        menuBar.getMenus().addAll(menuAccueil, menuCréerScenario, menuInfo);



        getChildren().addAll(menuBar);


    }
}
