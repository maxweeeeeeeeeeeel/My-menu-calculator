package com.example.myapplicationmenu;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result, solution;
    MaterialButton buttonAc, buttonParenthese, buttonPourcent, buttonDivision, button1, button2, button3;
    MaterialButton button4, button5, button6, button7, button8, button9, button10, buttonSoustraction, buttonAddition;
    MaterialButton buttonPoint, button11, buttonSupprimer, buttonResultat;
    private StringBuilder currentExpression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            solution = findViewById(R.id.solution_tv);
            result = findViewById(R.id.result_tv);

            assignId(buttonAc, R.id.buttonAc);
            assignId(buttonParenthese, R.id.buttonParenthese);
            assignId(buttonPourcent, R.id.buttonPourcent);
            assignId(buttonDivision, R.id.buttonDivision);
            assignId(button1, R.id.button1);
            assignId(button2, R.id.button2);
            assignId(button3, R.id.button3);
            assignId(button4, R.id.button4);
            assignId(button5, R.id.button5);
            assignId(button6, R.id.button6);
            assignId(button7, R.id.button7);
            assignId(button8, R.id.button8);
            assignId(button9, R.id.button9);
            assignId(button10, R.id.button10);
            assignId(buttonSoustraction, R.id.buttonSoustraction);
            assignId(buttonAddition, R.id.buttonAddition);
            assignId(buttonPoint, R.id.buttonPoint);
            assignId(button11, R.id.button11);
            assignId(buttonSupprimer, R.id.buttonSupprimer);
            assignId(buttonResultat, R.id.buttonResultat);



            return insets;
        });
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }

        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        }else{
            dataToCalculate = dataToCalculate + buttonText;
        }

        solution.setText(dataToCalculate);

        String finalResut = getResult(dataToCalculate);

        if(!finalResut.equals("Error")){
            result.setText(finalResut);
        }
    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult =  context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return finalResult;
        }catch (Exception e){
            return "Error";
        }

    }

}
