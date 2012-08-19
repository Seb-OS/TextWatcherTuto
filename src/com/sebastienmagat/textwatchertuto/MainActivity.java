package com.sebastienmagat.textwatchertuto;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends Activity implements TextWatcher {

	private EditText inputZone;
	private TextView displayCharRemaining;
	private int nbOfCharMax;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// On initialise notre integer
		nbOfCharMax = 20;
		// On cast notre EditText en le reliant à la vue
		inputZone = (EditText) findViewById(R.id.edit_text);
		inputZone.addTextChangedListener(this);
		// Idem pour la TextView
		displayCharRemaining = (TextView) findViewById(R.id.text_view);

	}

	public void afterTextChanged(Editable s) {
		/**
		 * On récupère le nombre de caractères entrés, et on le cast en integer
		 */
		int nbOfCharEntered = inputZone.getText().toString().length();
		/**
		 * Via la méthode privée result, on calcul le nombre de char pouvant
		 * encore être entré, et on l'affiche avec notre TextView
		 */
		displayCharRemaining.setText(Integer.toString(result(nbOfCharEntered))
				+ " char remaining");
		/**
		 * Enfin, on créé les conditions pour la coloration de la TextView
		 */
		if (result(nbOfCharEntered) < 0) {
			displayCharRemaining.setTextColor(Color.RED);
			displayCharRemaining.setText(Integer.toString(Math
					.abs(result(nbOfCharEntered))) + " excess characters !");
			return;
		} else if (result(nbOfCharEntered) == 0) {
			displayCharRemaining.setTextColor(Color.MAGENTA);
			displayCharRemaining.setText("Maximum size reached !");
			return;
		} else if (result(nbOfCharEntered) <= 10) {
			displayCharRemaining.setTextColor(Color.YELLOW);
			return;
		} else if (result(nbOfCharEntered) > 10) {
			displayCharRemaining.setTextColor(Color.GREEN);
		}
	}

	private int result(int nbOfCharEntered) {
		return (nbOfCharMax - nbOfCharEntered);
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}
