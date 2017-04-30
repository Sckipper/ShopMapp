package sz.shopmapp;

import android.util.Log;

import java.io.Serializable;

public class Categorie implements Serializable{
    int id;
    int categorieID;
    String denumire;
    int raion;int raft;int nrMaxRafturi;
    String imagine;
    String descriere;

    public Categorie(int i, int ci, String den, int rai, int raf,int nrmr, String img, String desc){
        id = i;
        categorieID = ci;
        denumire = den;
        raion = rai;
        raft = raf;
        nrMaxRafturi = nrmr;
        imagine = img;
        descriere = desc;
    }

    public String getDenumire(){return denumire;}
    public int getId(){ return this.id;}
    public int getCategorieID(){return this.categorieID;}
    public int getRaion(){return this.raion;}
    public int getRaft(){return this.raft;}
    public String getImagine(){return this.imagine;}
    public String getDescriere(){return this.descriere;}
    public int getNrMaxRafturi(){return this.nrMaxRafturi;}

    public void setId(int i){this.id  = i;}
    public void setCategorieID(int i){this.categorieID = i;}
    public void setDenumire(String s){this.denumire = s;}
    public void setRaion(int r){this.raion = r;}
    public void setRaft(int r){this.raft = r;}
    public void setImagine(String s){this.imagine = s;}
    public void setDescriere(String s){this.descriere = s;}
    public void setNrMaxRafturi(int n){this.nrMaxRafturi = n;}
}
