package sz.shopmapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListaDeCumparaturiActivity extends Activity {
    ArrayList<String> produse = new ArrayList<>();
    ListaDeCumparaturiCustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_cumparaturi);

        final Button btnGen = (Button) findViewById(R.id.btn_genereaza_imagine);
        ListaDeCumparaturi.addListaGoalaListener(new ListaDeCumparaturiBooleanChangedListener() {
            @Override
            public void ListaADevenitGoala() {
                TextView tv = (TextView) findViewById(R.id.lista_cupmaraturi_textView);
                tv.setText("Lista de cumparaturi este goala");
                btnGen.setVisibility(View.GONE);
            }
        });

        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GenerareImagineActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        if(ListaDeCumparaturi.empty){
            TextView tv = (TextView) findViewById(R.id.lista_cupmaraturi_textView);
            tv.setText("Lista de cumparaturi este goala");
            btnGen.setVisibility(View.GONE);
        } else{
            TextView tv = (TextView) findViewById(R.id.lista_cupmaraturi_textView);
            tv.setText("");
            GenerareListaCumparaturi();
            ListView lView = (ListView)findViewById(R.id.lista_cupmaraturi_listView);
            lView.setAdapter(adapter);
        }
    }

    public void GenerareListaCumparaturi(){
        for (Categorie c:ListaDeCumparaturi.listaCategorii
                ) {
            produse.add(c.getDenumire());
        }
        for (Produs p:ListaDeCumparaturi.listaProduse
                ) {
            produse.add(p.getDenumire());
        }
        adapter = new ListaDeCumparaturiCustomAdapter(produse, this);
        adapter.notifyDataSetChanged();
    }


}
