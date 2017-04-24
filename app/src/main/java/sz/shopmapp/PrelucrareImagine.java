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
    private ArrayList<String> rectsOrientation;

    public PrelucrareImagine(Bitmap b){
        this.initialImage = b;
        rects = new ArrayList<>();
        rectsOrientation = new ArrayList<>();
        GenerareRect();
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

        Paint circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(6F);
        circlePaint.setColor(Color.GREEN);
        circlePaint.setStyle(Paint.Style.FILL);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);
        // desenare traseu verde
        AStarPathFinder.Cell puncteVerzi = AStarPathFinder.getPath(101, 101, 23, 7, 17, 84,AStarPathFinder.block);
        while(puncteVerzi.parent!=null) {
            c.drawCircle(puncteVerzi.i*10,puncteVerzi.j*10, 4,circlePaint);
            puncteVerzi = puncteVerzi.parent;
        }
        for (Rect r: rects          //ramane de selectate ce raioane sa se deseneze in functie de ce e in lista
             ) {
            c.drawRect(r,rectPaint);

            c.drawText("Denumire Raion",r.centerX() - (r.centerX()-r.left),r.centerY(),textPaint);
        }
        return bm;
    }

    public void GenerareRect(){     //pentru raionul 1 ArrayList:index:1 => rect pentru primul raion...etc    trebuie neaparat apelata numai pentru initializare arraylist
        //Raion 1
        rects.add(new Rect(54,124,123,437));
        rectsOrientation.add("Vertical");
        //Raion 2
        rects.add(new Rect(337,195,406,588));
        rectsOrientation.add("Vertical");
        //Raion 3,4,5 puse sa fie
        rects.add(new Rect()); rects.add(new Rect()); rects.add(new Rect());rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");
        //Raion 6
        rects.add(new Rect(122,858,747,927));
        rectsOrientation.add("Horizontal");
        //Raion 7,8,9 puse sa fie
        rects.add(new Rect()); rects.add(new Rect()); rects.add(new Rect());rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");
        //Raion 10
        rects.add(new Rect(512,187,580,573));
        rectsOrientation.add("Vertical");
        //Raion 11,12 la fel
        rects.add(new Rect()); rects.add(new Rect());rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");
    }

    public Bitmap DesenareProdus(Produs p){
        Bitmap bm = initialImage.copy(Bitmap.Config.ARGB_8888, true);    //prelucrare pe bm si returnare imagine pentru afisat
        Canvas c = new Canvas(bm);

        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(6F);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Paint.Style.STROKE);

        Paint rectPaint1 = new Paint();
        rectPaint1.setAntiAlias(true);
        rectPaint1.setStrokeWidth(6F);
        rectPaint1.setColor(Color.RED);
        rectPaint1.setStyle(Paint.Style.STROKE);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(15);

        int raion = 0;
        int raft = 0;
        int nrMaxRafturi = 0;
        for (Categorie cat:SearchActivity.categorieArrayList
                ) {
            if(cat.getId() == p.getCategorieID()) {
                raion = cat.getRaion();
                raft = cat.getRaft();
                nrMaxRafturi = cat.getNrMaxRafturi();
            }
        }
        int index = raion - 1;
        //partea de impartire a raionului in ....3.... parti
        if(rectsOrientation.get(index).compareTo("Horizontal") == 0) {    //raionul e desenat orizontal deci impartirea se face pe verticala
            //c.drawRect(rects.get(index),rectPaint);
            //c.drawText(p.getDenumire(),rects.get(index).centerX() - (rects.get(index).centerX()-rects.get(index).left),rects.get(index).centerY(),textPaint);
            Rect r = rects.get(index);
            int xdiff = Math.abs(r.right - r.left) / nrMaxRafturi;
            for(int i=0;i<nrMaxRafturi;i++){
                if(i+1 == raft) {
                    Rect newRect = new Rect(r.left + xdiff * i, r.top, r.left + xdiff * (i + 1), r.bottom);
                    c.drawRect(newRect, rectPaint1);
                    c.drawText(p.getDenumire(),newRect.centerX() - (newRect.centerX()-newRect.left),newRect.centerY(),textPaint);
                }
            }

        } else if(rectsOrientation.get(index).compareTo("Vertical") == 0){    //raionul e desenat vertical deci impartirea se face pe orizontala
            //c.drawRect(rects.get(index),rectPaint);

            Rect r = rects.get(index);
            int ydiff = Math.abs(r.bottom - r.top) / nrMaxRafturi;
            for(int i=0;i<nrMaxRafturi;i++){
                if(i+1 == raft) {
                    Rect newRect = new Rect(r.left, r.top + ydiff * i, r.right, r.top + ydiff * (i + 1));
                    c.drawRect(newRect, rectPaint1);
                    c.drawText(p.getDenumire(),newRect.centerX() - (newRect.centerX()-newRect.left),newRect.centerY(),textPaint);
                }
            }
        }
        return bm;
    }

    public Bitmap DesenareCategorie(Categorie cat){
        Bitmap bm = initialImage.copy(Bitmap.Config.ARGB_8888, true);    //prelucrare pe bm si returnare imagine pentru afisat
        Canvas c = new Canvas(bm);

        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(6F);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Paint.Style.STROKE);

        Paint rectPaint1 = new Paint();
        rectPaint1.setAntiAlias(true);
        rectPaint1.setStrokeWidth(6F);
        rectPaint1.setColor(Color.RED);
        rectPaint1.setStyle(Paint.Style.STROKE);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(15);

        if(cat.getCategorieID() == 0){  //e tot raionul
            int index = cat.getRaion() - 1;
            c.drawRect(rects.get(index),rectPaint1);
            c.drawText(cat.getDenumire(),rects.get(index).centerX() - (rects.get(index).centerX()-rects.get(index).left),rects.get(index).centerY(),textPaint);
        } else {
            int raion = cat.getRaion();
            int raft = cat.getRaft();
            int nrMaxRafturi = cat.getNrMaxRafturi();

            int index = raion - 1;
            //partea de impartire a raionului in ....3.... parti
            if (rectsOrientation.get(index).compareTo("Horizontal") == 0) {    //raionul e desenat orizontal deci impartirea se face pe verticala
                //c.drawRect(rects.get(index),rectPaint);
                //c.drawText(p.getDenumire(),rects.get(index).centerX() - (rects.get(index).centerX()-rects.get(index).left),rects.get(index).centerY(),textPaint);
                Rect r = rects.get(index);
                int xdiff = Math.abs(r.right - r.left) / nrMaxRafturi;
                for (int i = 0; i < nrMaxRafturi; i++) {
                    if (i + 1 == raft) {
                        Rect newRect = new Rect(r.left + xdiff * i, r.top, r.left + xdiff * (i + 1), r.bottom);
                        c.drawRect(newRect, rectPaint1);
                        c.drawText(cat.getDenumire(), newRect.centerX() - (newRect.centerX() - newRect.left), newRect.centerY(), textPaint);
                    }
                }

            } else if (rectsOrientation.get(index).compareTo("Vertical") == 0) {    //raionul e desenat vertical deci impartirea se face pe orizontala
                //c.drawRect(rects.get(index),rectPaint);

                Rect r = rects.get(index);
                int ydiff = Math.abs(r.bottom - r.top) / nrMaxRafturi;
                for (int i = 0; i < nrMaxRafturi; i++) {
                    if (i + 1 == raft) {
                        Rect newRect = new Rect(r.left, r.top + ydiff * i, r.right, r.top + ydiff * (i + 1));
                        c.drawRect(newRect, rectPaint1);
                        c.drawText(cat.getDenumire(), newRect.centerX() - (newRect.centerX() - newRect.left), newRect.centerY(), textPaint);
                    }
                }
            }
        }
        return bm;
    }

    public Bitmap GenerareDinListaDeCumparaturi(){
        Bitmap bm = initialImage.copy(Bitmap.Config.ARGB_8888, true);    //prelucrare pe bm si returnare imagine pentru afisat
        Canvas c = new Canvas(bm);
        ArrayList<Produs> listaProduse = new ArrayList<>(ListaDeCumparaturi.listaProduse);
        ArrayList<Categorie> listaCategorii = new ArrayList<>(ListaDeCumparaturi.listaCategorii);

        for (Categorie cat: listaCategorii
             ) {
            if(cat.getCategorieID() != 0){
                bm = GenereazaCategorii(bm,cat,0);
                int count = 0;
                for(int i=0;i<listaProduse.size();i++){
                    Log.d("Android:","amasfn");
                    if(listaProduse.get(i).getCategorieID() == cat.getId()){
                        count++;
                        bm = GenereazaProduse(bm,listaProduse.get(i),count,false);
                        listaProduse.remove(i);
                        i--;
                    }
                }
            } else
                bm = GenereazaCategorii(bm,cat,0);
        }
        for(int i=0 ;i<listaProduse.size();i++){
            int count = 0;
            bm = GenereazaProduse(bm,listaProduse.get(i),count,true);
            for(int j=i+1;j<listaProduse.size();j++){
                if(listaProduse.get(i).getCategorieID() == listaProduse.get(j).getCategorieID()){
                    count++;
                    bm = GenereazaProduse(bm,listaProduse.get(j),count,false);
                    listaProduse.remove(j);
                    j--;
                }
            }
            listaProduse.remove(i);
        }

        return bm;
    }

    private Bitmap GenereazaCategorii(Bitmap bmpInitial,Categorie cat, int count){
        Bitmap bm = bmpInitial.copy(Bitmap.Config.ARGB_8888, true);    //prelucrare pe bm si returnare imagine pentru afisat
        Canvas c = new Canvas(bm);

        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(6F);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Paint.Style.STROKE);

        Paint rectPaint1 = new Paint();
        rectPaint1.setAntiAlias(true);
        rectPaint1.setStrokeWidth(6F);
        rectPaint1.setColor(Color.RED);
        rectPaint1.setStyle(Paint.Style.STROKE);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(15);

        if(cat.getCategorieID() == 0){  //e tot raionul
            int index = cat.getRaion() - 1;
            c.drawRect(rects.get(index),rectPaint1);
            c.drawText(cat.getDenumire(),rects.get(index).centerX() - (rects.get(index).centerX()-rects.get(index).left),rects.get(index).centerY(),textPaint);
        } else {
            int raion = cat.getRaion();
            int raft = cat.getRaft();
            int nrMaxRafturi = cat.getNrMaxRafturi();

            int index = raion - 1;
            //partea de impartire a raionului in ....3.... parti
            if (rectsOrientation.get(index).compareTo("Horizontal") == 0) {    //raionul e desenat orizontal deci impartirea se face pe verticala
                //c.drawRect(rects.get(index),rectPaint);
                //c.drawText(p.getDenumire(),rects.get(index).centerX() - (rects.get(index).centerX()-rects.get(index).left),rects.get(index).centerY(),textPaint);
                Rect r = rects.get(index);
                int xdiff = Math.abs(r.right - r.left) / nrMaxRafturi;
                for (int i = 0; i < nrMaxRafturi; i++) {
                    if (i + 1 == raft) {
                        Rect newRect = new Rect(r.left + xdiff * i, r.top, r.left + xdiff * (i + 1), r.bottom);
                        c.drawRect(newRect, rectPaint1);
                        c.drawText(cat.getDenumire(), 6+newRect.centerX() - (newRect.centerX() - newRect.left), newRect.centerY() + count*10, textPaint);
                    }
                }

            } else if (rectsOrientation.get(index).compareTo("Vertical") == 0) {    //raionul e desenat vertical deci impartirea se face pe orizontala
                //c.drawRect(rects.get(index),rectPaint);

                Rect r = rects.get(index);
                int ydiff = Math.abs(r.bottom - r.top) / nrMaxRafturi;
                for (int i = 0; i < nrMaxRafturi; i++) {
                    if (i + 1 == raft) {
                        Rect newRect = new Rect(r.left, r.top + ydiff * i, r.right, r.top + ydiff * (i + 1));
                        c.drawRect(newRect, rectPaint1);
                        c.drawText(cat.getDenumire(), 6+newRect.centerX() - (newRect.centerX() - newRect.left), newRect.centerY() + count*10, textPaint);
                    }
                }
            }
        }

        return bm;
    }

    private Bitmap GenereazaProduse(Bitmap bmpInitial,Produs prod, int count, boolean paintBorder){
        Bitmap bm = bmpInitial.copy(Bitmap.Config.ARGB_8888, true);    //prelucrare pe bm si returnare imagine pentru afisat
        Canvas c = new Canvas(bm);

        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(6F);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Paint.Style.STROKE);

        Paint rectPaint1 = new Paint();
        rectPaint1.setAntiAlias(true);
        rectPaint1.setStrokeWidth(6F);
        rectPaint1.setColor(Color.RED);
        rectPaint1.setStyle(Paint.Style.STROKE);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(15);

        int raion = 0;
        int raft = 0;
        int nrMaxRafturi = 0;
        for (Categorie cat:SearchActivity.categorieArrayList
                ) {
            if(cat.getId() == prod.getCategorieID()) {
                raion = cat.getRaion();
                raft = cat.getRaft();
                nrMaxRafturi = cat.getNrMaxRafturi();
            }
        }
        int index = raion - 1;
        //partea de impartire a raionului in ....3.... parti
        if(rectsOrientation.get(index).compareTo("Horizontal") == 0) {    //raionul e desenat orizontal deci impartirea se face pe verticala
            //c.drawRect(rects.get(index),rectPaint);
            //c.drawText(p.getDenumire(),rects.get(index).centerX() - (rects.get(index).centerX()-rects.get(index).left),rects.get(index).centerY(),textPaint);
            Rect r = rects.get(index);
            int xdiff = Math.abs(r.right - r.left) / nrMaxRafturi;
            for(int i=0;i<nrMaxRafturi;i++){
                if(i+1 == raft) {
                    Rect newRect = new Rect(r.left + xdiff * i, r.top, r.left + xdiff * (i + 1), r.bottom);
                    if(paintBorder == true)
                        c.drawRect(newRect, rectPaint1);
                    c.drawText(prod.getDenumire(),6+newRect.centerX() - (newRect.centerX()-newRect.left),newRect.centerY() + count * 20,textPaint);
                }
            }

        } else if(rectsOrientation.get(index).compareTo("Vertical") == 0){    //raionul e desenat vertical deci impartirea se face pe orizontala
            //c.drawRect(rects.get(index),rectPaint);

            Rect r = rects.get(index);
            int ydiff = Math.abs(r.bottom - r.top) / nrMaxRafturi;
            for(int i=0;i<nrMaxRafturi;i++){
                if(i+1 == raft) {
                    Rect newRect = new Rect(r.left, r.top + ydiff * i, r.right, r.top + ydiff * (i + 1));
                    if(paintBorder == true)
                        c.drawRect(newRect, rectPaint1);
                    c.drawText(prod.getDenumire(),6+newRect.centerX() - (newRect.centerX()-newRect.left),newRect.centerY() + count * 20,textPaint);
                }
            }
        }
        return bm;
    }
}
