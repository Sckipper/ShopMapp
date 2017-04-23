package sz.shopmapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DebugUtils;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by tavi2 on 22-04-2017.
 */

public class PrelucrareImagine {
    private Bitmap initialImage;

    private ArrayList<Rect> rects;

    public PrelucrareImagine(Bitmap b){
        this.initialImage = b;
        rects = new ArrayList<>();
    }

    public Bitmap Prelucrare(){
        Bitmap bm = initialImage.copy(Bitmap.Config.ARGB_8888, true);    //prelucrare pe bm si returnare imagine pentru afisat
        Log.d("Android: ",bm.getHeight() + " " + bm.getWidth());
        Canvas c = new Canvas(bm);

        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(6F);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Paint.Style.STROKE);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);

        for (Rect r:rects
             ) {
            c.drawRect(r,rectPaint);
            c.drawText("Raion",r.centerX() - (r.centerX()-r.left),r.centerY(),textPaint);
        }
        return bm;
    }

    public void GenerareRect(){     //pentru raionul 1 ArrayList:index:1 => rect pentru primul raion...etc
        //Raion 1
        rects.add(new Rect(54,124,123,437));
        //Raion 2
        rects.add(new Rect(337,195,406,588));
        //Raion 6
        rects.add(new Rect(122,858,747,927));
        //Raion 10
        rects.add(new Rect(512,187,580,573));
    }
}
