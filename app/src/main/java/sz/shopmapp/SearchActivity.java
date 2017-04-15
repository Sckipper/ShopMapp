package sz.shopmapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.AndroidAuthenticator;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by tavi2 on 08.04.2017.
 */

public class SearchActivity extends Activity {

    private ListView lv;
    ArrayAdapter<String> adapter;
    EditText inputSearch;

    ArrayList<String> products;
    ArrayList<String> productsType;
    ArrayList<Integer> productsID;

    public static ArrayList<Categorie> categorieArrayList;
    public static ArrayList<Produs> produsArrayList;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cauta_produs);
        products = new ArrayList<>();
        productsID = new ArrayList<>();
        productsType = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String c = (String) parent.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), c, Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(view.getContext(), ProductDetailsActivity.class);
                int newPos = products.indexOf(c);
                myIntent.putExtra("type", productsType.get(newPos));
                myIntent.putExtra("id", productsID.get(newPos));
                startActivityForResult(myIntent, 0);
            }
        });

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                // TODO - Search inteligent
                SearchActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
        OrdonareListe();
    }

    private void OrdonareListe() {
        for (Categorie c:categorieArrayList
             ) {
            if(c.getCategorieID() == 0){
                products.add(c.getDenumire());
                productsType.add("categorie");
                productsID.add(c.getId());
                for (Categorie c1:categorieArrayList
                     ) {
                    if(c1.getCategorieID() == c.getId()){
                        products.add("    " + c1.getDenumire());
                        productsType.add("categorie");
                        productsID.add(c1.getId());
                        for (Produs p:produsArrayList
                             ) {
                            if(p.getCategorieID() == c1.getId()) {
                                products.add("        " + p.getDenumire());
                                productsType.add("produs");
                                productsID.add(p.getId());
                                //Log.d("Android: ", "idsc:" + c1.getId() + " idc:" + c.getId() + "pid:" + p.getId() + "pden:" + p.getDenumire() + "c1den:" + c1.getDenumire() + "cden:" + c.getDenumire());
                            }
                        }
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
