
package com.example.internalflilesex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author		Yoad Wolfson.
 * @version     1.0
 *  application that adds content from text view to file and shows it.
 */
public class MainActivity extends AppCompatActivity {
EditText Input;
TextView file;
String in,line,read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file=(TextView)findViewById(R.id.file);
        Input=(EditText)findViewById(R.id.Input);
        try{
            FileInputStream fis=openFileInput("Input.txt");
            InputStreamReader isr=new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuffer data=new StringBuffer();
            line=br.readLine();
            while(!(line==null)){
                data.append(line+'\n');
                line=br.readLine();
            }
            read=data.toString();
            isr.close();
            file.setText(read);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * when the save button clicked adds the string to the text view (that shows the file content).
     * <p>
     * @param view the button that got clicked.
     */
    public void save(View view) {
         in=Input.getText().toString();
        try {
            FileOutputStream fos=openFileOutput("Input.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter BW= new BufferedWriter(osw);
            BW.write(in);
            BW.close();
        }
         catch (IOException e) {
            e.printStackTrace();
        }
       file.setText(file.getText().toString()+in);
    }

    /**
     * when reset button got clicked resets the text view of the file content;
     * @param view the button that got clicked.
     */
    public void reset(View view) {
        file.setText("");
        Input.setText("");
    }

    /**
     * when exit has been clicked saves and closing the app.
     * @param view the button that got clicked.
     */
    public void exit(View view) {
        in=Input.getText().toString();
        try {
            FileOutputStream fos=openFileOutput("Input.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter BW= new BufferedWriter(osw);
            BW.write(in);
            BW.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        file.setText(file.getText().toString()+in);
        finish();
    }
}