package com.androidtutorialpoint.alertdialog;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener{

    private static final String DIALOG_DATE = "DialogDate";
    private EditText dateOfBirthEditText;
    private TextView inputDialogTextView;
    private ImageView calendarImage;
    private Button getInputButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarImage = (ImageView)findViewById(R.id.image_view_date_of_birth);
        dateOfBirthEditText = (EditText) findViewById(R.id.edit_text_date_of_birth);
        inputDialogTextView = (TextView)findViewById(R.id.text_view_dialog);
        getInputButton = (Button)findViewById(R.id.button_dialog);
        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager, DIALOG_DATE);
            }
        });

        getInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View getEmpIdView = li.inflate(R.layout.dialog_get_emp_id, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                // set dialog_get_emp_id.xml to alertdialog builder
                alertDialogBuilder.setView(getEmpIdView);

                final EditText userInput = (EditText) getEmpIdView.findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // text view
                                inputDialogTextView.setText(userInput.getText().toString());
                            }
                        }).create()
                        .show();

            }
        });




    }

    @Override
    public void onFinishDialog(Date date) {
        dateOfBirthEditText.setText(formatDate(date));

    }

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hireDate = sdf.format(date);
        return hireDate;
    }
}
