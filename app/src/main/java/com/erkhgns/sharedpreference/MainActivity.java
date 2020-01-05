package com.erkhgns.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * SharedPreference - use to store/ save MAP data in application
     *
     * This is a simple application for SharedPreference.
     * Sample advance:
     * If users have a lot of data in a form, Convert it to JSON and save it to
     * shared Preference via putString. Use Gson Library
     */
    TextView textView;
    Button btnApplyText,btnSaveData;
    Switch toggle;
    EditText editText;
    public static final String SHARED_PREFS = "SharedPrefs";
    public static final String TEXT_PREFS = "textPrefs";
    public static final String SWITCH_PREFS = "switchPrefs";
    String text;
    boolean aBoolean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btnApplyText = findViewById(R.id.btnApplyText);
        btnSaveData = findViewById(R.id.btnSaveData);
        toggle = findViewById(R.id.toggle);
        editText = findViewById(R.id.editText);



        btnSaveData.setOnClickListener(onClickListener);

        btnApplyText.setOnClickListener(onClickListener);

        loadData();



    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnApplyText:
                    textView.setText(editText.getText().toString());
                    break;
                case R.id.btnSaveData:
                    //mode_private - no other app can modify the data
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

                    // for modifying of data in preference
                    SharedPreferences.Editor editor  = sharedPreferences.edit();

                    /**
                     * 1st params - unique key for each preference
                     * 2nd params - value of preference
                     */
                    editor.putString(TEXT_PREFS,textView.getText().toString());
                    editor.putBoolean(SWITCH_PREFS,toggle.isChecked());


                    //to apply changes in sharedPref
                    editor.apply();

                    break;
            }
        }
    };

    private void loadData(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        /**
         * 1st parameter - unique key
         * 2nd parameter - default value of each preference
         */
        text = sharedPreferences.getString(TEXT_PREFS,"");
        aBoolean = sharedPreferences.getBoolean(SWITCH_PREFS,false);

        textView.setText(text);
        toggle.setChecked(aBoolean);

    }

}
