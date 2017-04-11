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


    private String urlJsonObjCateg = "https://apex.oracle.com/pls/apex/shopmap/odbt/categorie";
    private String urlJsonObjProdus = "https://apex.oracle.com/pls/apex/shopmap/odbt/produs";
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
        makeJsonObjectCategorieRequest();
    }

    private void makeJsonObjectCategorieRequest() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObjCateg, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    categorieArrayList = JsonTool.parseCategorieJSONData(response);
                    //adapter.notifyDataSetChanged();
                    makeJsonObjectProduseRequest();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Android ", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void makeJsonObjectProduseRequest() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObjProdus, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    produsArrayList = JsonTool.parseProduseJSONData(response);
                    ///
                    OrdonareListe();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Android ", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void OrdonareListe() {
        productsID = new ArrayList<>();
        productsType = new ArrayList<>();
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
        /*
        for (Categorie c:categorieArrayList
             ) {
            products.add(c.getDenumire());
        }
        for (Produs p:produsArrayList
                ) {
            products.add("    " + p.getDenumire());
        }*/
        //Collections.sort(products);

        adapter.notifyDataSetChanged();
    }
}
