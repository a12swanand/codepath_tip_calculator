package com.example.tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TipCalcActivity extends Activity {

	private BigDecimal tipPercBd = new BigDecimal("10.00");
	private BigDecimal tipPercBd10 = new BigDecimal("10.00");
	private BigDecimal tipPercBd15 = new BigDecimal("15.00");
	private BigDecimal tipPercBd20 = new BigDecimal("20.00");
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calc);
		
		EditText etAmount = (EditText) findViewById(R.id.etAmount);
		Button bt10 = (Button) findViewById(R.id.bt10);
		Button bt15 = (Button) findViewById(R.id.bt15);
		Button bt20 = (Button) findViewById(R.id.bt20);
		SeekBar sbTipSel = (SeekBar) findViewById(R.id.sbTipSel);
		sbTipSel.setProgress(10);
		
		//etAmount events
		etAmount.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				calculate();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
			

		});
		
		
		//bt10 events
		bt10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tipPercBd = tipPercBd10;
				SeekBar sbTipSel = (SeekBar) findViewById(R.id.sbTipSel);
				sbTipSel.setProgress(10);

				calculate();
			}
		});
		
		
		//bt15 events
		bt15.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tipPercBd = tipPercBd15;
				SeekBar sbTipSel = (SeekBar) findViewById(R.id.sbTipSel);
				sbTipSel.setProgress(15);
				calculate();
			}
		});
		
		//bt20 events
		bt20.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				tipPercBd = tipPercBd20;
				SeekBar sbTipSel = (SeekBar) findViewById(R.id.sbTipSel);
				sbTipSel.setProgress(20);
				calculate();
			}
		});
	
		sbTipSel.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tipPercBd = new BigDecimal(progress);
				calculate();
			}
		});
	
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calc, menu);
		return true;
	}

	public void calculate() {
		View v1 = findViewById(R.id.etAmount);
		EditText et1 = (EditText)v1;
		String amt = et1.getText().toString();

		TextView tvTipAmt = (TextView) findViewById(R.id.tvTipAmt);

		TextView tvTipSelected = (TextView) findViewById(R.id.tvTipSelected);
		
		BigDecimal amtBd = null;
		if (amt.length() > 0) {
			amtBd = new BigDecimal(amt);
		} else {
			amtBd = new BigDecimal("0");
		}		

			tvTipSelected.setText("" + tipPercBd);
			tvTipAmt.setText(calcTip(amtBd, tipPercBd));
	}
	
	private String calcTip(BigDecimal amt, BigDecimal tip){
		String tipAmt = "0.00";
		if(amt != null && tip != null) {
			tipAmt = (amt.multiply(tip).divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_UP).toString();
			
		}
		return "$" + tipAmt;
	}
	
}
