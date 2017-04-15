package sz.shopmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btnCautaProdus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCautaProdus = (Button) findViewById(R.id.button);
        btnCautaProdus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SearchActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button btnTest = (Button) findViewById(R.id.button3);
        btnTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GenerareImagine.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button btnListaCumparaturi = (Button) findViewById(R.id.button2);
        btnListaCumparaturi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("Android: ","Categorii:");
                for (Categorie c:ListaDeCumparaturi.listaCategorii
                     ) {
                    Log.d("Android: ",c.getDenumire());
                }
                Log.d("Android: ","Produse:");
                for (Produs p:ListaDeCumparaturi.listaProduse
                        ) {
                    Log.d("Android: ",p.getDenumire());
                }
            }
        });

        InitializareObiecte();
    }

    private String urlJsonObjCateg = "https://apex.oracle.com/pls/apex/shopmap/odbt/categorie";
    private String urlJsonObjProdus = "https://apex.oracle.com/pls/apex/shopmap/odbt/produs";

    private void InitializareObiecte(){
        makeJsonObjectCategorieRequest();
    }

    private void makeJsonObjectCategorieRequest() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObjCateg, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    SearchActivity.categorieArrayList = JsonTool.parseCategorieJSONData(response);
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
                    SearchActivity.produsArrayList = JsonTool.parseProduseJSONData(response);

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
}
