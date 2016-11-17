package at.fh.swengb.meterstofeet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int UNIT_TYPE_METER_TO_FOOT = 1;
    private static final int UNIT_TYPE_FOOT_TO_METER = 2;

    private int unitType = UNIT_TYPE_METER_TO_FOOT;
    private double convertedValue = 0;

    private EditText getValueEdit() {
        return (EditText) findViewById(R.id.valueEdit);
    }

    private Button getConvertButton() {
        return (Button) findViewById(R.id.convertButton);
    }

    private Button getChangeButton() {
        return (Button) findViewById(R.id.changeButton);
    }

    private TextView getValueText() {
        return (TextView) findViewById(R.id.valueView);
    }

    private TextView getUnitText() {
        return (TextView) findViewById(R.id.unitView);
    }

    private double getValue() {
        String value = getValueEdit().getText().toString();
        return (!value.equals(""))
                ? Double.parseDouble(value)
                : 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getConvertButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convert();
            }
        });

        getChangeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDirection();
            }
        });

    }

    private void convert() {
        switch (unitType) {
            case UNIT_TYPE_METER_TO_FOOT: convertedValue = getValue() / 0.3048; break;
            case UNIT_TYPE_FOOT_TO_METER: convertedValue = getValue() * 0.3048; break;
            default: break;
        }
        setValueText();
    }

    private void changeDirection() {
        switch (unitType) {
            case UNIT_TYPE_METER_TO_FOOT: unitType = UNIT_TYPE_FOOT_TO_METER; break;
            case UNIT_TYPE_FOOT_TO_METER: unitType = UNIT_TYPE_METER_TO_FOOT; break;
            default: break;
        }
        setUnitText();
        convert();
    }

    private void setValueText() {
        getValueText().setText(String.valueOf(convertedValue));
    }

    private void setUnitText() {
        switch (unitType) {
            case UNIT_TYPE_METER_TO_FOOT: getUnitText().setText("feet"); break;
            case UNIT_TYPE_FOOT_TO_METER: getUnitText().setText("meters"); break;
            default: break;
        }
    }

}
