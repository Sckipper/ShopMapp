package sz.shopmapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class ProductDetailsActivity extends Activity {
    Produs produsCurent = null;
    Categorie categorieCurenta = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        TextView TextViewPret = (TextView) findViewById(R.id.view_prod_pret);
        TextView TextViewDenumire = (TextView) findViewById(R.id.view_prod_name);
        TextView TextViewDescr = (TextView) findViewById(R.id.view_prod_descr);
        TextView TextViewCantitate = (TextView) findViewById(R.id.view_prod_cantitate);
        Button btnAddToShopList = (Button) findViewById(R.id.btn_addShopList);
        Button btnViewInMagazin = (Button) findViewById(R.id.btn_view_in_magazin);
        ImageView imgView = (ImageView) findViewById(R.id.view_prod_imageView);
        TextView TextViewGreutate = (TextView) findViewById(R.id.view_prod_greutate);

        String type = getIntent().getExtras().getString("type");
        Integer id = getIntent().getExtras().getInt("id");
        Search(SearchActivity.categorieArrayList,SearchActivity.produsArrayList,type,id);
        if(produsCurent != null){       //setez toate view-urile cu informatii sau fara
            TextViewPret.setText("Pret: " + produsCurent.getPret() + " Lei");
            TextViewDenumire.setText(produsCurent.getDenumire());
            TextViewDescr.setText("Descriere: " + produsCurent.getDescriere());
            TextViewCantitate.setText("Cantitate disponibila: " + String.format("%.0f",produsCurent.getCantitate()) + " buc.");

            int resourceImage = getResources().getIdentifier(produsCurent.getImagine(),"drawable",getPackageName());
            imgView.setImageResource(resourceImage);

            if(produsCurent.getGreutate().compareTo("") == 0)
                TextViewGreutate.setVisibility(View.GONE);
            else
                TextViewGreutate.setText("Greutate: " + produsCurent.getGreutate());
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

        }else if(categorieCurenta != null){ //setez toate view-urile cu informatii sau fara
            TextViewPret.setVisibility(View.GONE);
            TextViewCantitate.setVisibility(View.GONE);
            TextViewGreutate.setVisibility(View.GONE);
            TextViewDenumire.setText(categorieCurenta.getDenumire());

            if(categorieCurenta.getDescriere().compareTo("") == 0)
                TextViewDescr.setVisibility(View.GONE);
            else
                TextViewDescr.setText("Descriere: " + categorieCurenta.getDescriere());

            int resourceImage = getResources().getIdentifier(categorieCurenta.getImagine(),"drawable",getPackageName());
            imgView.setImageResource(resourceImage);

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
        btnViewInMagazin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GenerareImagineActivity.class);
                myIntent.putExtra("p",produsCurent);
                myIntent.putExtra("c",categorieCurenta);
                startActivityForResult(myIntent, 0);
            }
        });
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
