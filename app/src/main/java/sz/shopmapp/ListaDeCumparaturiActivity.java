package sz.shopmapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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
        ListaDeCumparaturi.addListaGoalaListener(new ListaDeCumparaturiBooleanChangedListener() {
            @Override
            public void ListaADevenitGoala() {
                TextView tv = (TextView) findViewById(R.id.lista_cupmaraturi_textView);
                tv.setText("Lista de cumparaturi este goala");
            }
        });

        if(ListaDeCumparaturi.empty){
            TextView tv = (TextView) findViewById(R.id.lista_cupmaraturi_textView);
            tv.setText("Lista de cumparaturi este goala");
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
