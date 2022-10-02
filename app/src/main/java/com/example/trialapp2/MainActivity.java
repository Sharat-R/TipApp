package com.example.trialapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int txtTippercentDefault=15;
    int tipPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtTippercent = findViewById(R.id.txtTippercent);
        TextView tipDisplay = findViewById(R.id.tipDisplay);
        TextView totalDisplay = findViewById(R.id.totalDisplay);
        EditText edtBase = findViewById(R.id.edtBase);
        SeekBar seekBar = findViewById(R.id.seekBar);

        txtTippercent.setText(txtTippercentDefault+"%");
        tipPercent=15;

        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtTippercent.setText(i+"%");
                tipPercent=seekBar.getProgress();
                computeTipandTotal(edtBase, seekBar, txtTippercent, tipDisplay, totalDisplay);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
            computeTipandTotal(edtBase, seekBar, txtTippercent, tipDisplay, totalDisplay);
            }
        };
        edtBase.addTextChangedListener(textWatcher);

    }

    private void computeTipandTotal(EditText edtBase, SeekBar seekBar, TextView txtTippercent, TextView tipDisplay, TextView totalDisplay) {
        Double baseDouble;
        if(edtBase.getText().toString().contentEquals("")){
            baseDouble = 0.0;
            tipDisplay.setText("0.0");
            totalDisplay.setText("0.0");
        }
        else{
            baseDouble = new Double(edtBase.getText().toString());
        }
        double base = baseDouble.doubleValue();
        double tipAmount = base*tipPercent/100;
        double totalAmount = base + tipAmount;

        tipDisplay.setText(""+tipAmount);
        totalDisplay.setText(""+totalAmount);
    }

}