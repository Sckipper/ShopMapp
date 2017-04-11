package sz.shopmapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by tavi2 on 09.04.2017.
 */

public class ProductDetailsActivity extends Activity {
    Produs produsCurent = null;
    Categorie categorieCurenta = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        TextView TextViewType = (TextView) findViewById(R.id.view_prod_type);
        TextView TextViewDenumire = (TextView) findViewById(R.id.view_prod_name);
        TextView TextViewLoc = (TextView) findViewById(R.id.view_prod_location);

        String type = getIntent().getExtras().getString("type");
        Integer id = getIntent().getExtras().getInt("id");
        Search(SearchActivity.categorieArrayList,SearchActivity.produsArrayList,type,id);
        Log.d("Android: ", type + id);
        if(produsCurent != null){
            TextViewType.setText(type);
            TextViewDenumire.setText(produsCurent.getDenumire());
            TextViewLoc.setText(getLocatie(SearchActivity.categorieArrayList));
        }else if(categorieCurenta != null){
            TextViewType.setText(type);
            TextViewDenumire.setText(categorieCurenta.getDenumire());
            TextViewLoc.setText(categorieCurenta.getRaion() + " ... " + categorieCurenta.getRaft());
        }
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
