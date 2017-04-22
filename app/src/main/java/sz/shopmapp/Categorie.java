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
    String imagine;

    public Categorie(int i, int ci, String den, int rai, int raf, String img){
        id = i;
        categorieID = ci;
        denumire = den;
        raion = rai;
        raft = raf;
        imagine = img;
    }

    public void Show(){
        Log.d("Android: ",id + " " + categorieID + " " + denumire + " " + raion + " " + raft);
    }

    public String getDenumire(){return denumire;}
    public int getId(){ return this.id;}
    public int getCategorieID(){return this.categorieID;}
    public int getRaion(){return this.raion;}
    public int getRaft(){return this.raft;}
    public String getImagine(){return this.imagine;}

    public void setId(int i){this.id  = i;}
    public void setCategorieID(int i){this.categorieID = i;}
    public void setDenumire(String s){this.denumire = s;}
    public void setRaion(int r){this.raion = r;}
    public void setRaft(int r){this.raft = r;}
    public void setImagine(String s){this.imagine = s;}

}
