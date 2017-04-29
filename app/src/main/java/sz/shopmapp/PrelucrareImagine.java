package sz.shopmapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DebugUtils;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by tavi2 on 22-04-2017.
 */

public class PrelucrareImagine {
    private Bitmap initialImage;

    private ArrayList<Rect> rects;
    private ArrayList<String> rectsOrientation;
    private ArrayList<String> raionAcces;

    private ArrayList<Point> puncte;

    public PrelucrareImagine(Bitmap b){
        this.initialImage = b;
        rects = new ArrayList<>();
        rectsOrientation = new ArrayList<>();
        raionAcces = new ArrayList<>();
        puncte = new ArrayList<>();
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



        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);


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
        raionAcces.add("dreapta");
        //Raion 2
        rects.add(new Rect(337,195,406,588));
        rectsOrientation.add("Vertical");
        raionAcces.add("stanga");
        //Raion 3,4,5 puse sa fie
        rects.add(new Rect()); rects.add(new Rect()); rects.add(new Rect());rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");
        raionAcces.add("stanga");raionAcces.add("stanga");raionAcces.add("stanga");
        //Raion 6
        rects.add(new Rect(122,858,747,927));
        rectsOrientation.add("Horizontal");
        raionAcces.add("sus");
        //Raion 7,8,9 puse sa fie
        rects.add(new Rect()); rects.add(new Rect()); rects.add(new Rect());rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");
        raionAcces.add("stanga");raionAcces.add("stanga");raionAcces.add("stanga");
        //Raion 10
        rects.add(new Rect(512,187,580,573));
        rectsOrientation.add("Vertical");
        raionAcces.add("dreapta");
        //Raion 11,12 la fel
        rects.add(new Rect()); rects.add(new Rect());rectsOrientation.add("Vertical");rectsOrientation.add("Vertical");
        raionAcces.add("stanga");raionAcces.add("stanga");
    }

    public Bitmap DesenareProdus(Produs p){
        Bitmap bm = initialImage.copy(Bitmap.Config.ARGB_8888, true);    //prelucrare pe bm si returnare imagine pentru afisat
        Canvas c = new Canvas(bm);

        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(3F);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Paint.Style.STROKE);

        Paint rectPaint1 = new Paint();
        rectPaint1.setAntiAlias(true);
        rectPaint1.setStrokeWidth(3F);
        rectPaint1.setColor(Color.RED);
        rectPaint1.setStyle(Paint.Style.STROKE);

        Paint textPaint = new Paint();
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
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
        rectPaint.setStrokeWidth(3F);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStyle(Paint.Style.STROKE);

        Paint rectPaint1 = new Paint();
        rectPaint1.setAntiAlias(true);
        rectPaint1.setStrokeWidth(6F);
        rectPaint1.setColor(Color.RED);
        rectPaint1.setStyle(Paint.Style.STROKE);

        Paint textPaint = new Paint();
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
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

    public Bitmap GenerareDinListaDeCumparaturi() {
        Bitmap bm = initialImage.copy(Bitmap.Config.ARGB_8888, true);    //prelucrare pe bm si returnare imagine pentru afisat

        bm = ImpartirePentruDesenat(bm);
        Canvas c = new Canvas(bm);
        Log.d("Android: ",bm.getWidth() + " x " + bm.getHeight() + "");

        Paint circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.GREEN);
        circlePaint.setStyle(Paint.Style.FILL);
        // desenare traseu verde
        Point start = new Point(239,85);
        Point fin = new Point(734,85);
        for (Point p : puncte
             ) {
            AStarPathFinder.Cell puncteVerzi = AStarPathFinder.getPath(101, 101, start.x/10, start.y/10, p.x/10, p.y/10);

            while(puncteVerzi!=null) {
                c.drawCircle(puncteVerzi.i*10,puncteVerzi.j*10, 5,circlePaint);
                puncteVerzi = puncteVerzi.parent;
            }
            start = p;
        }
        AStarPathFinder.Cell puncteVerzi = AStarPathFinder.getPath(101, 101, start.x/10, start.y/10, fin.x/10, fin.y/10);

        while(puncteVerzi!=null) {
            c.drawCircle(puncteVerzi.i*10,puncteVerzi.j*10, 5,circlePaint);
            puncteVerzi = puncteVerzi.parent;
        }

        return bm;
    }

    public Bitmap ImpartirePentruDesenat(Bitmap bm){
        ArrayList<Produs> listaProduse = new ArrayList<>(ListaDeCumparaturi.listaProduse);
        ArrayList<Categorie> listaCategorii = new ArrayList<>(ListaDeCumparaturi.listaCategorii);

        ArrayList<CategoriiProdusePeRafturi> cppr = new ArrayList<>();
        Iterator<Categorie> i = listaCategorii.iterator();
        while(i.hasNext()){
            Categorie c = i.next();
            if(c.getCategorieID() == 0){
                //bm = DesenareCategorie(c);
                cppr.add(new CategoriiProdusePeRafturi(c.getRaion(),c.getRaft(),c));
                i.remove();
            } else {
                int gasit = 0;
                for (CategoriiProdusePeRafturi cpr : cppr
                        ) {
                    if (c.getRaion() == cpr.raion && c.getRaft() == cpr.raft) {       //adaug categoria pe acelasi raft cu alta categorie daca exista
                        cpr.addCat(c);
                        gasit = 1;
                        i.remove();
                    }
                }
                if (gasit == 0) {
                    cppr.add(new CategoriiProdusePeRafturi(c.getRaion(), c.getRaft(), c));
                    i.remove();
                }
            }
        }
        Iterator<Produs> j = listaProduse.iterator();
        while(j.hasNext()){
            Produs p = j.next();
            HashMap hm = RaionRaftProdus(p);
            int gasit = 0;
            for (CategoriiProdusePeRafturi cpr: cppr
                 ) {
                if(cpr.raft == (int)hm.get("raft") && cpr.raion == (int)hm.get("raion")){
                    gasit = 1;
                    cpr.addProdus(p);
                    j.remove();
                }
            }
            if(gasit == 0){     //nu exista categorie in lista de cumparaturi dar a adaugat un produs din acea categorie
                cppr.add(new CategoriiProdusePeRafturi((int)hm.get("raion"),(int)hm.get("raft"),p,(int)hm.get("maxraft")));
                j.remove();
            }
        }

        return DeseneazaListaDeCumparaturi(cppr,bm);
    }

    public Bitmap DeseneazaListaDeCumparaturi(ArrayList<CategoriiProdusePeRafturi> cppr,Bitmap bmp){
        Bitmap bm = bmp.copy(Bitmap.Config.ARGB_8888, true);    //prelucrare pe bm si returnare imagine pentru afisat
        Canvas c = new Canvas(bm);

        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(3F);
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);

        Paint textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(15);

        Paint backPaint = new Paint();
        backPaint.setColor(Color.rgb(194, 216, 252));
        backPaint.setStyle(Paint.Style.FILL);
        backPaint.setAlpha(150);

        Paint testPaint = new Paint();
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        testPaint.setColor(Color.GREEN);
        testPaint.setStrokeWidth(6F);
        testPaint.setStyle(Paint.Style.FILL);
        testPaint.setColor(Color.BLACK);
        testPaint.setTextSize(15);
        Collections.sort(cppr, new Comparator<CategoriiProdusePeRafturi>() {
            @Override
            public int compare(CategoriiProdusePeRafturi o1, CategoriiProdusePeRafturi o2) {
                return o1.raion - o2.raion;
            }
        });

        for (CategoriiProdusePeRafturi cpr:cppr
             ) {
            if (cpr.raft == 0) {
                int index = cpr.raion - 1;
                c.drawRect(rects.get(index),rectPaint);
                c.drawText(cpr.getStringList().get(0),rects.get(index).centerX() - (rects.get(index).centerX()-rects.get(index).left),rects.get(index).centerY(),textPaint);
                Rect r = rects.get(index);
                if (raionAcces.get(index).compareTo("sus") == 0) {
                    //c.drawCircle(newRect.centerX(),newRect.top,20,testPaint);
                    puncte.add(new Point(r.centerX(), r.top - 10));
                } else if (raionAcces.get(index).compareTo("jos") == 0) {
                    //c.drawCircle(newRect.centerX(),newRect.top,20,testPaint);
                    puncte.add(new Point(r.centerX(), r.bottom + 10));
                } else if (raionAcces.get(index).compareTo("dreapta") == 0) {
                    //c.drawCircle(newRect.right,newRect.centerY(),20,testPaint);
                    puncte.add(new Point(r.right + 10, r.centerY()));
                } else if (raionAcces.get(index).compareTo("stanga") == 0) {
                    //c.drawCircle(r.left, r.centerY(), 20, testPaint);
                    puncte.add(new Point(r.left - 10, r.centerY()));
                }
            } else {
                int raion = cpr.raion;
                int nrMaxRafturi = cpr.nrMaxRafturi;
                int index = raion - 1;
                ArrayList<String> denumiri = cpr.getStringList();


                //partea de impartire a raionului in ....3.... parti
                if (rectsOrientation.get(index).compareTo("Horizontal") == 0) {    //raionul e desenat orizontal deci impartirea se face pe verticala
                    //c.drawRect(rects.get(index),rectPaint);
                    //c.drawText(p.getDenumire(),rects.get(index).centerX() - (rects.get(index).centerX()-rects.get(index).left),rects.get(index).centerY(),textPaint);
                    Rect r = rects.get(index);
                    int xdiff = Math.abs(r.right - r.left) / nrMaxRafturi;
                    for (int i = 0; i < nrMaxRafturi; i++) {
                        if (i + 1 == cpr.raft) {
                            Rect newRect = new Rect(r.left + xdiff * i, r.top, r.left + xdiff * (i + 1), r.bottom);

                            c.drawRect(newRect, rectPaint);
                            c.drawRect(newRect.left,newRect.top,newRect.right,newRect.top + denumiri.size() * 12,backPaint);
                            int count = 1;
                            for (String s : denumiri
                                    ) {
                                c.drawText(s, newRect.left, newRect.top + count * 12, textPaint);
                                count++;
                            }
                            if (raionAcces.get(index).compareTo("sus") == 0) {
                                //c.drawCircle(newRect.centerX(),newRect.top,20,testPaint);
                                puncte.add(new Point(newRect.centerX(), newRect.top - 10));
                            } else if (raionAcces.get(index).compareTo("jos") == 0) {
                                //c.drawCircle(newRect.centerX(),newRect.top,20,testPaint);
                                puncte.add(new Point(newRect.centerX(), newRect.bottom + 10));
                            }
                        }
                    }

                } else if (rectsOrientation.get(index).compareTo("Vertical") == 0) {    //raionul e desenat vertical deci impartirea se face pe orizontala
                    //c.drawRect(rects.get(index),rectPaint);

                    Rect r = rects.get(index);
                    int ydiff = Math.abs(r.bottom - r.top) / nrMaxRafturi;
                    for (int i = 0; i < nrMaxRafturi; i++) {
                        if (i + 1 == cpr.raft) {
                            Rect newRect = new Rect(r.left, r.top + ydiff * i, r.right, r.top + ydiff * (i + 1));
                            c.drawRect(newRect, rectPaint);
                            int count = 2;
                            c.drawRect(newRect.left,newRect.top,newRect.right,newRect.top + (denumiri.size() + 2) * 12,backPaint);
                            for (String s : denumiri
                                    ) {
                                c.drawText(s, newRect.left, newRect.top + count * 12, textPaint);
                                count++;
                            }
                            if (raionAcces.get(index).compareTo("dreapta") == 0) {
                                //c.drawCircle(newRect.right,newRect.centerY(),20,testPaint);
                                puncte.add(new Point(newRect.right + 10, newRect.centerY()));
                            } else if (raionAcces.get(index).compareTo("stanga") == 0) {
                                //c.drawCircle(newRect.left, newRect.centerY(), 20, testPaint);
                                puncte.add(new Point(newRect.left - 10, newRect.centerY()));
                            }
                        }
                    }
                }
            }
        }
        return bm;
    }

    private class CategoriiProdusePeRafturi{
        public ArrayList<Categorie> listaCat;
        public ArrayList<Produs> listaProd;
        public int raion;
        public int raft;
        public int nrMaxRafturi;

        public CategoriiProdusePeRafturi(int rai,int raf,Categorie categ){
            listaCat = new ArrayList<>();
            listaProd = new ArrayList<>();
            listaCat.add(categ);
            raion = rai;
            raft = raf;
            nrMaxRafturi = categ.getNrMaxRafturi();
        }

        public CategoriiProdusePeRafturi(int rai,int raf,Produs pr, int nrm){
            listaCat = new ArrayList<>();
            listaProd = new ArrayList<>();
            listaProd.add(pr);
            raion = rai;
            raft = raf;
            nrMaxRafturi = nrm;
        }

        public void addProdus(Produs p){
            listaProd.add(p);
        }

        public void addCat(Categorie c){
            listaCat.add(c);
        }

        public void Show(){
            Log.d("Android: ", "raion:" + raion + " raft:" + raft);
            for (Categorie c:listaCat
                 ) {
                Log.d("Android: ",c.getDenumire());
            }
            for (Produs p: listaProd
                 ) {
                Log.d("Android: ",p.getDenumire());
            }
        }

        public ArrayList<String> getStringList(){
            ArrayList<String> arls = new ArrayList<>();
            for (Categorie c:listaCat
                 ) {
                arls.add(c.getDenumire());
            }
            for (Produs p:listaProd
                 ) {
                arls.add(p.getDenumire());
            }
            return arls;
        }

    }

    public HashMap<String,Integer> RaionRaftProdus(Produs p){
        HashMap<String,Integer> map = new HashMap<>();
        for (Categorie c:SearchActivity.categorieArrayList
             ) {
            if(p.getCategorieID() == c.getId()){
                map.put("raion",c.getRaion());
                map.put("raft",c.getRaft());
                map.put("maxraft",c.getNrMaxRafturi());
            }
        }
        return map;
    }

}
