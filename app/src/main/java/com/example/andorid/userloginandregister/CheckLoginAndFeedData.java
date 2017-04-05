package com.example.andorid.userloginandregister;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//This class is used for performing tasks in the background.

public class CheckLoginAndFeedData extends AsyncTask<String, Void, String> {

    private Context mContext;


    //Constructor to initialise this class.
    CheckLoginAndFeedData (Context context) {
        mContext = context;
    }


    //Method carried out to perform background tasks.
    @Override
    protected String doInBackground (String... params) {

        //The first parameter of the background task method.
        String type = params[0];

        //Url for login page php file.
        String loginUrl = "http://192.168.2.8/student/login.php";

        //Url for register page php file.
        String registerUrl = "http://192.168.2.8/student/register.php";

        //It is an login call.
        if (type.equals("login")) {
            try {
                //Fetch the username and password from the background method call.
                String username = params[1];
                String password = params[2];

                //Creating a URL.
                URL url = new URL(loginUrl);
                //Connecting to the URL.
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //Setting request method POST.
                httpURLConnection.setRequestMethod("POST");
                //This connection include Input and output interaction.
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                //Creating the outputStream
                OutputStream outputStream = httpURLConnection.getOutputStream();
                //Writing in the outputStream.
                BufferedWriter bufferedWriter = new BufferedWriter(new
                        OutputStreamWriter(outputStream, "UTF-8"));

                //This is for connecting the variables in the app and in the php file.
                String postData = URLEncoder.encode("username", "UTF-8") + "=" +//$_POST["username"]
                        URLEncoder.encode(username, "UTF-8") +
                        "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" +//$_POST["password"]
                        URLEncoder.encode(password, "UTF-8");

                //Feeding the data.
                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //Creating an inputStream to fetch the results.
                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"));

                //Getting the results
                String result = " ";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //Returning the results
                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("register")) {//It is an register POST call.
            try {
                //Fetching the values to be registered.
                String firstName = params[1];
                String surname = params[2];
                String age = params[3];
                String username = params[4];
                String password = params[5];


                URL url = new URL(registerUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new
                        OutputStreamWriter(outputStream, "UTF-8"));

                String postData = URLEncoder.encode("name", "UTF-8") + "=" +//$_POST["name"]
                        URLEncoder.encode(firstName, "UTF-8") +
                        "&" +
                        URLEncoder.encode("surname", "UTF-8") + "=" +//$_POST["surname"]
                        URLEncoder.encode(surname, "UTF-8") +
                        "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" +//$_POST["age"]
                        URLEncoder.encode(age, "UTF-8") +
                        "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" +//$_POST["username"]
                        URLEncoder.encode(username, "UTF-8") +
                        "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" +//$_POST["password"]
                        URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"));

                String result = " ";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPostExecute (String result) {
        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
    }

}
