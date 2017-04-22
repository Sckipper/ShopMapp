package sz.shopmapp;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class GenerareImagineActivity extends AppCompatActivity {
    private ImageView IMG;
    Matrix matrix;
    ScaleGestureDetector SGD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generare_imagine);
        IMG = (ImageView) findViewById(R.id.imageView);
        matrix = new Matrix();
        SGD = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SGD.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float SF = 1f;
            SF *= detector.getScaleFactor();
            SF = Math.max(0.1f, Math.min(SF, 0.5f));
            matrix.setScale(SF,SF);
            IMG.setImageMatrix(matrix);
            return true;
        }
    }
}
