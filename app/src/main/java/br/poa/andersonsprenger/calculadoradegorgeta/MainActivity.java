package br.poa.andersonsprenger.calculadoradegorgeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText quantText;
    private SeekBar percentBar;
    private TextView gorjetaOutText, totalOutText, percentText;
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private Double quant, total, gorjeta;
    private Integer percent;

    private void findViews(){
        quantText       = findViewById(R.id.quant);
        percentBar      = findViewById(R.id.percent);
        gorjetaOutText  = findViewById(R.id.gorjetaText);
        totalOutText    = findViewById(R.id.totalText);
        percentText     = findViewById(R.id.percentText);
        quant           = 0d;
        total           = 0d;
        gorjeta         = 0d;
        percent         = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        //quantText.setMa
        loadListeners();
    }

    private void loadListeners(){
        quantText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    quant = Double.parseDouble(quantText.getText().toString());
                    update();
                } catch (NumberFormatException e) {
                    toast(e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        percentBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percent = percentBar.getProgress();
                percentText.setText(percent + "%");
                update();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void update(){
        try {
            gorjeta = quant * percent / 100;
            total = quant + gorjeta;
            gorjetaOutText.setText("R$ " + decimalFormat.format(gorjeta));
            totalOutText.setText("R$ " + decimalFormat.format(total));
        } catch (ArithmeticException e) {
            toast(e.getMessage());
        }
    }

    private void toast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}