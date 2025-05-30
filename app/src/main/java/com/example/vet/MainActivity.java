package com.example.vet;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> typeOfAnimal;
    EditText ownerDataEditText;
    ListView typeListView;
    TextView ageTV;
    SeekBar valueSeekBar;
    EditText celEditText;
    Button okButton;
    int selectedItemPosition;
    String formattedHour;
    String formattedMinute;
    String selectedItem;
    TextView dataTV;
    TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        typeListView = findViewById(R.id.typeListView);
        okButton = findViewById(R.id.okButton);
        celEditText = findViewById(R.id.celEditText);
        ownerDataEditText = findViewById(R.id.ownerDataEditText);
        valueSeekBar = findViewById(R.id.valueSeekBar);
        ageTV = findViewById(R.id.ageTV);
        dataTV = findViewById(R.id.dataTV);
        timePicker = findViewById(R.id.timePicker);



        typeOfAnimal = new ArrayList<>();
        typeOfAnimal.add("Pies");
        typeOfAnimal.add("Kot");
        typeOfAnimal.add("Świnka morska");

        ArrayAdapter<String> customAdapter = new ArrayAdapter<>(
                this,
                R.layout.list_item_setting,
                R.id.customTextView,
                typeOfAnimal
        );
        typeListView.setAdapter(customAdapter);

        typeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemPosition = position;
                selectedItem = typeOfAnimal.get(position);
                if(position == 0){
                    valueSeekBar.setMax(18);
                    valueSeekBar.setProgress(0);
                    ageTV.setText(" " + 0);
                } else if(position == 1) {
                    valueSeekBar.setMax(20);
                    valueSeekBar.setProgress(0);
                    ageTV.setText(" " + 0);
                } else if(position == 2){
                    valueSeekBar.setMax(9);
                    valueSeekBar.setProgress(0);
                    ageTV.setText(" " + 0);
                }
            }
        });



        valueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                if(fromUser && selectedItemPosition != -1){
                    ageTV.setText(" " + progressValue);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        timePicker.setHour(16);
        timePicker.setMinute(00);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                int hour = hourOfDay;
                String amPm;
                if (hour == 0) {
                    hour += 12;
                    amPm = "AM";
                } else if (hour == 12) {
                    amPm = "PM";
                } else if (hour > 12) {
                    hour -= 12;
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                formattedHour = (hour < 10) ? "0" + hour : String.valueOf(hour);
                formattedMinute = (minute < 10) ? "0" + minute : String.valueOf(minute);
            }
        });



        okButton.setOnClickListener( view -> {
            dataTV.setText("Imię i nazwisko: " + ownerDataEditText.getText() + "\n Gatunek: " + selectedItem + " w wieku: " + valueSeekBar.getProgress() + "\n celem wizyty jest " + celEditText.getText() + "\nczas wizyty: " + formattedHour + ":" + formattedMinute);
        });
    }
}