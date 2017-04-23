package sz.shopmapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


public class GenerareImagineActivity extends Activity {
    Produs produsCurent;
    Categorie categorieCurenta;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generare_imagine);

        try {
            produsCurent = (Produs) getIntent().getSerializableExtra("p");
        }catch (Exception e){
            Log.d("Android: ", e.toString());
        }
        try{
            categorieCurenta = (Categorie) getIntent().getSerializableExtra("c");
        } catch (Exception e){
            Log.d("Android: ", e.toString());
        }

        if(produsCurent == null)
            Log.d("Android: ", "nu e produs");
        if(categorieCurenta == null)
            Log.d("Android: ","nu e categ");
        if(produsCurent == null && categorieCurenta == null) {  //afisez harta cu produsele din lista
            PrelucrareImagine pi = new PrelucrareImagine(BitmapFactory.decodeResource(getResources(), R.drawable.magazin));
            ZoomableImageView touch = (ZoomableImageView) findViewById(R.id.imageEnhance);
            touch.setImageBitmap(pi.Prelucrare());
        } else{     // a selectat un produs sau categorie si i-l arat pe harta
            if(categorieCurenta == null) {  // a selectat produs
                PrelucrareImagine pi = new PrelucrareImagine(BitmapFactory.decodeResource(getResources(), R.drawable.magazin));
                ZoomableImageView touch = (ZoomableImageView) findViewById(R.id.imageEnhance);
                touch.setImageBitmap(pi.DesenareProdus(produsCurent));
            } else {  // a selectat categorie
                PrelucrareImagine pi = new PrelucrareImagine(BitmapFactory.decodeResource(getResources(), R.drawable.magazin));
                ZoomableImageView touch = (ZoomableImageView) findViewById(R.id.imageEnhance);
                touch.setImageBitmap(pi.DesenareCategorie(categorieCurenta));
            }
        }
    }
}