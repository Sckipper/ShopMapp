package sz.shopmapp;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by tavi2 on 09.04.2017.
 */

public class Produs implements Serializable{
    int id;
    int categorieID;
    String denumire;
    double pret;
    double cantitate;
    String greutate;
    String dataExpirare;
    String descriere;
    String imagine;

    public Produs(int i, int ci, String den, double p, double ca, String g, String de, String des,String img){
        id = i;
        categorieID = ci;
        denumire = den;
        pret = p;
        cantitate = ca;
        greutate = g;
        dataExpirare = de;
        descriere = des;
        imagine = img;
    }

    public void Show(){
        Log.d("Android: ",id + " " + categorieID + " " + denumire + " " + pret + " " + cantitate + " " + greutate + " " + descriere + " ");
    }

    public String getDenumire(){return denumire;}
    public int getId(){ return this.id;}
    public int getCategorieID(){return this.categorieID;}
    public double getPret(){return this.pret;}
    public double getCantitate(){return this.cantitate;}
    public String getGreutate(){return this.greutate;}
    public String getDataExpirare(){return this.dataExpirare;}
    public String getDescriere(){return this.descriere;}
    public String getImagine(){return this.imagine;}

    public void setId(int i){this.id  = i;}
    public void setCategorieID(int i){this.categorieID = i;}
    public void setDenumire(String s){this.denumire = s;}
    public void setPret(double p){this.pret = p;}
    public void setCantitate(double c){this.cantitate = c;}
    public void setGreutate(String s){this.greutate = s;}
    public void setDataExpirare(String s){this.dataExpirare = s;}
    public void setDescriere(String s){this.descriere = s;}
    public void setImagine(String s){this.imagine = s;}
}
