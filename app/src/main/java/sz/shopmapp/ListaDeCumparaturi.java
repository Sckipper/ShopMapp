package sz.shopmapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tavi2 on 15-04-2017.
 */

interface ListaDeCumparaturiBooleanChangedListener {
    public void ListaADevenitGoala();
}

public class ListaDeCumparaturi {
    public static ArrayList<Produs> listaProduse = new ArrayList<>();
    public static ArrayList<Categorie> listaCategorii = new ArrayList<>();
    public static boolean empty = true;
    private static List<ListaDeCumparaturiBooleanChangedListener> listeners = new ArrayList<ListaDeCumparaturiBooleanChangedListener>();

    public static void adaugaProdus(Produs p){
        listaProduse.add(p);
        empty = false;
    }

    public static void adaugaCategorie(Categorie c){
        listaCategorii.add(c);
        empty = false;
    }

    public static int ExistaProdusInLista(Produs p){
        return listaProduse.indexOf(p);
    }

    public static int ExistaCategorieInLista(Categorie c){
        return listaCategorii.indexOf(c);
    }


    public static void StergeProdusCategorie(String s){
        for (Categorie c:listaCategorii
             ) {
            if(c.getDenumire().compareTo(s) == 0) {
                listaCategorii.remove(c);
                break;
            }
        }
        for (Produs p:listaProduse
             ) {
            if(p.getDenumire().compareTo(s) == 0){
                listaProduse.remove(p);
                break;
            }
        }
        if(listaCategorii.isEmpty() && listaProduse.isEmpty()) {
            for (ListaDeCumparaturiBooleanChangedListener l : listeners) {
                l.ListaADevenitGoala();
            }
            empty = true;
        }
    }
    public static void addListaGoalaListener(ListaDeCumparaturiBooleanChangedListener l) {
        listeners.add(l);
    }
}


