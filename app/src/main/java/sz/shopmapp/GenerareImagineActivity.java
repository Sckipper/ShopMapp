package sz.shopmapp;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;


public class GenerareImagineActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generare_imagine);
        ZoomableImageView touch = (ZoomableImageView) findViewById(R.id.imageEnhance);
        touch.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.magazin));
    }
}