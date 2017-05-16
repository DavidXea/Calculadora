package com.company.david.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etOperando1;
    private EditText etOperando2;
    private TextView tvResultado;
    private RadioGroup rGroup;
    private RadioButton rButton;
    private Button bCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etOperando1 = (EditText) findViewById(R.id.etOp1);
        etOperando2 = (EditText) findViewById(R.id.etOp2);
        tvResultado = (TextView) findViewById(R.id.tvResult);

    }

    public void onClickCalcular(View quemDisparou) {

        final double op1, op2;

        rGroup = (RadioGroup)findViewById(R.id.radioGroup);
        bCalcular = (Button) findViewById(R.id.btCalcular);

        String strOperando1 = etOperando1.getText().toString();
        String strOperando2 = etOperando2.getText().toString();
        etOperando1.setError(null);
        etOperando2.setError(null);

        try{
            op1 = Double.parseDouble(strOperando1);
        }catch (Exception e){
            etOperando1.setError("Erro ao obter o valor");
            return;
        }

        try{
            op2 = Double.parseDouble(strOperando2);
        }catch (Exception e){
            etOperando2.setError("Erro ao obter o valor");
            return;
        }

        bCalcular.setOnClickListener(new View.OnClickListener() {
            double res;

            @Override
            public void onClick(View v) {

                int selectedId = rGroup.getCheckedRadioButtonId();
                rButton = (RadioButton) findViewById(selectedId);

                if (getResources().getString(R.string.sum) == rButton.getText().toString()) {
                    res = (op1 + op2);
                } else {
                    if (getResources().getString(R.string.subtraction) == rButton.getText().toString()) {
                        res = (op1 - op2);
                    } else {
                        if (getResources().getString(R.string.division) == rButton.getText().toString()) {
                            if(op2 == 0) {
                                etOperando2.setError("Não é possivel dividir por 0");
                                return;
                            }
                            res = (op1 / op2);
                        } else {
                            if (getResources().getString(R.string.multiplication) == rButton.getText().toString()) {
                                res = (op1 * op2);
                            }
                        }
                    }
                }
                    String resultado = getResources().getString(R.string.result) + res;
                    tvResultado.setText(resultado);
                    Log.d("CALCULO_VALOR", resultado);
                    Toast.makeText(MainActivity.this, "Calculado" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
