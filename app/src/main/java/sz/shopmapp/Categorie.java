package sz.shopmapp;

import android.util.Log;

/**
 * Created by tavi2 on 08.04.2017.
 */

public class Categorie {
    int id;
    int categorieID;
    String denumire;
    int raion;int raft;

    public Categorie(int i, int ci, String den, int rai, int raf){
        id = i;
        categorieID = ci;
        denumire = den;
        raion = rai;
        raft = raf;
    }

    public void Show(){
        Log.d("Android: ",id + " " + categorieID + " " + denumire + " " + raion + " " + raft);
    }

    public String getDenumire(){
        return denumire;
    }
}
