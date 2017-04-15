package sz.shopmapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Sckipper on 15.04.2017.
 */

public class CustomView extends View {
    private Rect rectangle;
    private Paint paint;

    public CustomView(Context context) {
        super(context);
        int x = 0;
        int y = 0;
        int sideLength = 200;

        // create a rectangle that we'll draw later
        rectangle = new Rect(x, y, sideLength, sideLength);

        // create the Paint and set its color
        paint = new Paint();


        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.MAGENTA);

        // border
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(rectangle, paint);
    }
}
