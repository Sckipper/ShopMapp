package sz.shopmapp;

import java.util.ArrayList;

/**
 * Created by tavi2 on 15-04-2017.
 */

public class ListaDeCumparaturi {
    public static ArrayList<Produs> listaProduse = new ArrayList<>();
    public static ArrayList<Categorie> listaCategorii = new ArrayList<>();

    public static void adaugaProdus(Produs p){
        listaProduse.add(p);
    }

    public static void adaugaCategorie(Categorie c){
        listaCategorii.add(c);
    }

    public static int ExistaProdusInLista(Produs p){
        return listaProduse.indexOf(p);
    }

    public static int ExistaCategorieInLista(Categorie c){
        return listaCategorii.indexOf(c);
    }
}

