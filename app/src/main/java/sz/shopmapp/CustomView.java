package sz.shopmapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Display;
import 	android.graphics.Point;
import android.widget.ImageView;

import static android.R.attr.state_first;
import static android.R.attr.x;
import static android.R.attr.y;
import static android.graphics.Color.rgb;

/**
 * Created by Sckipper on 15.04.2017.
 */

public class CustomView extends View {
    private Paint paint;

    public CustomView(Context context) {
        super(context);
        // create the Paint and set its color
        paint = new Paint();
        // border
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#444444"));
        paint.setStrokeWidth(3);
        setBackgroundResource(R.drawable.magazin);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        /*int w = canvas.getWidth();
        int h = canvas.getHeight();
        int splitWidth = 10;
        int splitHeight = 20;
        canvas.drawRect(new Rect(0, 0, w, h), paint);
        for(int i=1;i<=w;i+= w/splitWidth)
            for(int j=1;j<=h;j+=h/splitHeight)
                canvas.drawRect(new Rect(i,j, w, h), paint);*/
    }

}
