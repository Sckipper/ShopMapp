package sz.shopmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnCautaProdus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCautaProdus = (Button) findViewById(R.id.button);
        btnCautaProdus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SearchActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Button btnTest = (Button) findViewById(R.id.button3);
        btnTest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), GenerareImagine.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
