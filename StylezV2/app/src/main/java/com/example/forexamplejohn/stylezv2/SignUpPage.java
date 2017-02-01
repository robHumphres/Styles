package com.example.forexamplejohn.stylezv2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;



public class SignUpPage extends AppCompatActivity {

    private Spinner spinnerMonths;
    private boolean badWord = true;
    boolean userExistence = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }// on create

    public void clickedOn(View v){
        switch(v.getId()){
            case R.id.signUpButton:

                // MEMBER VARIABLES
                spinnerMonths = (Spinner) findViewById(R.id.spinnerMonths);
                EditText day = (EditText) findViewById(R.id.setDay);
                EditText year = (EditText) findViewById(R.id.setYear);
                String month = String.valueOf(spinnerMonths.getSelectedItem());
                final EditText userID = (EditText) findViewById(R.id.userName);


                // CHECK FOR DAY EMPTY
                if(TextUtils.isEmpty(day.getText().toString()))
                    day.setError("Text Field Cannot be empty");

                // CHECK FOR YEAR EMPTY
                if(TextUtils.isEmpty(year.getText().toString()))
                    year.setError("Text Field cannot be empty");

                // CHECK FOR USER ID
                if(TextUtils.isEmpty(userID.getText().toString()))
                    userID.setError("Text Field Cannot be empty");

              /*
              if on top stops from check if one of the values wasn't entered
              this if statement checks all three ifs above. Won't go past this comment if any of the three are null or not set.
              */
                if(!(TextUtils.isEmpty(year.getText().toString())) && !(TextUtils.isEmpty(day.getText().toString())) && !(TextUtils.isEmpty(userID.getText().toString())))
                    if (checkAge(month, day.getText().toString(), year.getText().toString()) > 18) {


                          /*
                           Checks for username entering a cuss word
                           */
                        if(!(TextUtils.isEmpty(userID.getText().toString())))
                            badWord = checkBadWord(userID.getText().toString());

                        if(badWord)
                            userID.setError("This userName just will not work");

                        if(!badWord){
                          /*
                          Checking for User Existence
                           */


                            new AsyncTask<String, Void, Boolean>() {
                                boolean running;
                                ProgressDialog progressDialog;

                                @Override
                                protected void onPreExecute() {
                                    super.onPreExecute();
                                    running = true;

                                    progressDialog = ProgressDialog.show(SignUpPage.this,
                                            "Connecting to Servers",
                                            "Wait!");

                                    progressDialog.setCanceledOnTouchOutside(true);
                                    progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                        @Override
                                        public void onCancel(DialogInterface dialog) {
                                            running = false;
                                        }
                                    });
                                }

                                @Override
                                protected Boolean doInBackground(String... params) {
                                    //Looper.prepare();
                                    //super.doInBackground(params);
                                    String results = "";
                                    while(results=="") {
                                        try {
                                            results = executeRemoteCommand("pi", "raspberry", "sshpi.homelinux.com", 22, "java userExistence " + params[0]);
                                            if(results.equalsIgnoreCase("false"))
                                                return false;
                                            if(results.equalsIgnoreCase("true"))
                                                return true;
                                        } catch (Exception e) {
                                            //Toast.makeText(getApplicationContext(), "Error connecting to Server", Toast.LENGTH_LONG).show();
                                        } // catch
                                    }
                                    return false;
                                }// background

                                @Override
                                protected void onPostExecute(Boolean result) {
                                    super.onPostExecute(result);
                                    Toast.makeText(getApplicationContext(), result + "\nmade it to toast on postExecute", Toast.LENGTH_SHORT).show();
                                    if (!result) {
                                        userExistence = false;
                                        Toast.makeText(SignUpPage.this, "Welcome to Stylez " + userID.getText().toString(), Toast.LENGTH_SHORT).show();
                                        Intent newPage = new Intent(SignUpPage.this,MainBlank.class);
                                        newPage.putExtra("userName", userID.getText().toString());
                                        startActivity(newPage);
                                    }
                                    if (result) {
                                        userExistence = true;
                                        Toast.makeText(SignUpPage.this, "This Name is already Taken try again", Toast.LENGTH_SHORT).show();
                                    }
                                    progressDialog.dismiss();// dismiss dialog
                                }// post Exec
                            }.execute(userID.getText().toString());//.get();//checks user existence


                         /*if(userExistence){
                             Toast.makeText(SignUpPage.this, "This Name is already Taken try again", Toast.LENGTH_SHORT).show();
                         }//end of if
                          else{

                         }// end of else
                         */
                        }// end of badword
                    }// end of if check age
                    else{
                        Toast.makeText(getApplicationContext(), "Your age doesn't meet the requirements. I'm sorry.", Toast.LENGTH_SHORT).show();
                        Intent newPage = new Intent(this,LoginPage.class);
                        startActivity(newPage);
                    }
                break;
            case R.id.cancelButton:
                Intent newPage = new Intent(this,LoginPage.class);
                startActivity(newPage);
                break;


        }// end of switch

    }// end of on click

    public String executeRemoteCommand(String username, String password, String hostname, int port, String name)
            throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, hostname, port);
        session.setPassword(password);

        // Avoid asking for key confirmation
        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking", "no");
        session.setConfig(prop);
        session.connect();

        // SSH Channel
        ChannelExec channelssh = (ChannelExec) session.openChannel("exec");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        channelssh.setOutputStream(baos);

        //String temp = "java checkbad " + name;

        // Execute command
        channelssh.setCommand(name);
        channelssh.connect();
        Thread.sleep(1500);
        channelssh.disconnect();

        return baos.toString();

    }// end of Execute Remote Command


    private void checkExistence(String s){
        Boolean result;




    }





    //CALCULATE AGE
    public int checkAge(String month,String userDay,String userYear ){
        String stringDate;
        if(Integer.parseInt(userDay)<10) {
            stringDate = "0";
            stringDate+=userDay;
        }

        else{
            stringDate = userDay;
        }



        String monthNumber = getMonth(month);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        Date date1 = null;

        Calendar dob = Calendar.getInstance();
        try {
            dob.setTime(dateFormat.parse(stringDate + "-" + monthNumber + "-" + userYear));
        }catch(ParseException e){
            Toast.makeText(getApplicationContext(), "parse exception happen", Toast.LENGTH_SHORT).show();
        }
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        //Toast.makeText(getApplicationContext(), String.valueOf(age), Toast.LENGTH_SHORT).show();
        return age;

    }

    public String getMonth(String s){
        switch(s){
            case "January":
                return "01";
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
        }
        return null;
    }
    //GOES THROUGH ASSET LIST OF BAD WORDS
    private boolean checkBadWord(String s){
        BufferedReader fin;
        try{
            fin = new BufferedReader(new InputStreamReader(getAssets().open("badwords.txt")));
            String temp = "";
            String toCheck = s;
            while((temp = fin.readLine())!=null){
                if(temp.substring(0,1).equalsIgnoreCase(toCheck.substring(0, 1)))
                    if(toCheck.equalsIgnoreCase(temp)){
                        return true;
                    }
            }
        }catch(Exception e){


        }
        return false;
    }



}//end of public class


// IF WANTED TO CHECK THROUGH SSH , BUT TIMES JUST TAKES A LITTLE BIT LONGER OF A TIME
 /*
                        new AsyncTask<String, Void, String>() {
                          @Override
                          protected String doInBackground(String... params) {
                              //Looper.prepare();
                              String results = "";
                              try {
                                  results = executeRemoteCommand("pi", "raspberry", "192.168.1.107", 22, "java checkbad "+ params[0]);
                              } catch (Exception e) {
                                  //Toast.makeText(getApplicationContext(), "Error connecting to Server", Toast.LENGTH_LONG).show();

                              } // catch
                              return results;
                          }// background
                            @Override
                        protected void onPostExecute(String result) {
                                super.onPostExecute(result);
                            Toast.makeText(getApplicationContext(), result + "\nmade it to toast on badword", Toast.LENGTH_SHORT).show();
                            if(result.equals("false"))
                             badWord = false;
                            if(result.equals("true")){
                                badWord = true;
                            }
                        }
                      }.execute(userID.getText().toString());// checks cuss word
                        */



