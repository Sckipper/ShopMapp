package sz.shopmapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by tavi2 on 09.04.2017.
 */

public class ProductDetailsActivity extends Activity {
    Produs produsCurent = null;
    Categorie categorieCurenta = null;
    String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        TextView TextViewPret = (TextView) findViewById(R.id.view_prod_pret);
        TextView TextViewDenumire = (TextView) findViewById(R.id.view_prod_name);
        TextView TextViewDescr = (TextView) findViewById(R.id.view_prod_descr);
        Button btnAddToShopList = (Button) findViewById(R.id.btn_addShopList);


        String type = getIntent().getExtras().getString("type");
        Integer id = getIntent().getExtras().getInt("id");
        Search(SearchActivity.categorieArrayList,SearchActivity.produsArrayList,type,id);
        Log.d("Android: ", type + id);
        if(produsCurent != null){
            TextViewPret.setText("Pret: " + produsCurent.getPret());
            TextViewDenumire.setText(produsCurent.getDenumire());
            TextViewDescr.setText(produsCurent.getDescriere());
            btnAddToShopList.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(ListaDeCumparaturi.ExistaProdusInLista(produsCurent) == -1){
                        ListaDeCumparaturi.adaugaProdus(produsCurent);
                        customToast("Produs adaugat cu succes", "GREEN").show();
                    }else {
                        customToast("Produsul exista deja in lista", "RED").show();
                    }
                }
            });

        }else if(categorieCurenta != null){
            //TextViewPret.setText(type);
            TextViewDenumire.setText(categorieCurenta.getDenumire());
            TextViewDescr.setText(categorieCurenta.getDenumire());
            btnAddToShopList.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if (ListaDeCumparaturi.ExistaCategorieInLista(categorieCurenta) == -1){
                        ListaDeCumparaturi.adaugaCategorie(categorieCurenta);
                        customToast("Categorie adaugata cu succes", "GREEN").show();
                    }else {
                        customToast("Categoria exista deja in lista", "RED").show();
                    }
                }
            });
        }
    }

    public Toast customToast(String message, String textColor){
        Toast toast = Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT);
        TextView view = (TextView) toast.getView().findViewById(android.R.id.message);
        view.setTextColor(Color.parseColor(textColor));
        return toast;
    }

    public void Search(ArrayList<Categorie> arlCat, ArrayList<Produs> arlProd, String type, int id){

        if(type.compareToIgnoreCase("categorie") == 0) {
            for (Categorie c : arlCat
                    ) {
                if (c.getId() == id) {
                    categorieCurenta = c;
                }
            }
        } else if(type.compareToIgnoreCase("produs") == 0){
            for (Produs p: arlProd
                 ) {
                if(p.getId() == id) {
                    produsCurent = p;
                }
            }
        }
    }

    public String getLocatie(ArrayList<Categorie> arlCat){
        for (Categorie c:arlCat
             ) {
            if(c.getId() == produsCurent.getCategorieID())
                return c.getRaion() + " ... " + c.getRaft();
        }
        return "";
    }

}
