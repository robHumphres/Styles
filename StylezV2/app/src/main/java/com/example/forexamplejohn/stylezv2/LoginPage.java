package com.example.forexamplejohn.stylezv2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

public class LoginPage extends AppCompatActivity {

    private Context context;
    private ProgressDialog pd;
    private boolean bool = false;
    private EditText userID,passID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        context = this;
    }


    public void onClick (View v){
        userID = (EditText) findViewById(R.id.userNameID);
        passID = (EditText) findViewById(R.id.passKey);

        switch(v.getId()){

            case R.id.submitButton:
                /*if(TextUtils.isEmpty(userID.getText().toString())) {
                    userID.setError("User text Cannot be empty");

                    if (TextUtils.isEmpty(passID.getText().toString()))
                        passID.setError("Password text Cannot be empty");

                }// if user id empty
                if(!(TextUtils.isEmpty(userID.getText().toString())) && (!TextUtils.isEmpty(passID.getText().toString())))
                    new AsyncTask<String, Void, Boolean>() {
                        boolean running;
                        ProgressDialog progressDialog;

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            running = true;

                            progressDialog = ProgressDialog.show(LoginPage.this,
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
                                bool = false;
                                Toast.makeText(LoginPage.this, "Welcome back to Stylez " + userID.getText().toString(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            if (result) {
                                bool = true;
                                Toast.makeText(LoginPage.this, "This Name is already Taken try again", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }// post Exec
                    }.execute(userID.getText().toString());//.get();//checks user existence
                    */
                Intent nePage = new Intent(LoginPage.this,MainBlank.class);
                nePage.putExtra("userName", userID.getText().toString());
                startActivity(nePage);
                break;

            case R.id.signUpButton:
                Intent newPage = new Intent(this,SignUpPage.class);
                startActivity(newPage);
                break;

            default:
                Toast.makeText(getApplicationContext(), "Error switch Case didn't work", Toast.LENGTH_LONG).show();
                // you need a default in switch cases, and this ones handles a weird no Id found one. Have yet to see this one pop up.
                break;
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }



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
}
