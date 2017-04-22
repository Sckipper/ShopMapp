package sz.shopmapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class GenerareImagine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Log.d("Android: " , imgView.toString());
        //imgView.setImageResource(R.drawable.magazin);
        setContentView(new CustomView(this));
    }
}
