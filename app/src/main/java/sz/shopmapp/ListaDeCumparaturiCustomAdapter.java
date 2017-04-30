package sz.shopmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaDeCumparaturiCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public ListaDeCumparaturiCustomAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_de_cumparaturi_item, null);
        }

        TextView listItemText = (TextView)view.findViewById(R.id.lista_cupmaraturi_item_textview);
        listItemText.setText(list.get(position));

        Button deleteBtn = (Button)view.findViewById(R.id.lista_cupmaraturi_item_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ListaDeCumparaturi.StergeProdusCategorie(list.get(position));
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
