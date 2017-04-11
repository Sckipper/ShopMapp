package sz.shopmapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by tavi2 on 09.04.2017.
 */

public class ProductDetailsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        EditText et = (EditText) findViewById(R.id.editText);

        et.setText(getIntent().getExtras().getString("produs"));
        Categorie c = Search(getIntent().getExtras().getString("produs"),SearchActivity.categorieArrayList,SearchActivity.produsArrayList);
        Log.d("Android: ", Integer.toString(c.getRaion()));
    }

    public Categorie Search(String caut, ArrayList<Categorie> arlCat, ArrayList<Produs> arlProd){
        for (Categorie c: arlCat
             ) {
            if(c.getDenumire().equals(caut))
                return c;
        }
        return null;
    }

}
