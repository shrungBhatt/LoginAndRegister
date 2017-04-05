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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class CheckLoginAndFeedData extends AsyncTask<String,Void,String> {

    private Context mContext;

    CheckLoginAndFeedData (Context context){
        mContext = context;
    }

    @Override
    protected String doInBackground (String... params) {
        String type = params[0];
        String loginUrl = "http://192.168.2.8/student/login.php";
        String registerUrl = "http://192.168.2.8/student/register.php";

        if(type.equals("login")){
            try{
                String username = params[1];
                String password = params[2];

                URL url = new URL(loginUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new
                        OutputStreamWriter(outputStream,"UTF-8"));

                String postData = URLEncoder.encode("username","UTF-8")+"="+
                        URLEncoder.encode(username,"UTF-8")+
                        "&"+
                        URLEncoder.encode("password","UTF-8")+"="+
                        URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream,"iso-8859-1"));

                String result = " ";
                String line = "";
                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")){
            try{
                String firstName = params[1];
                String surname = params[2];
                String age = params[3];
                String username = params[4];
                String password = params[5];


                URL url = new URL(registerUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new
                        OutputStreamWriter(outputStream,"UTF-8"));

                String postData = URLEncoder.encode("name","UTF-8")+"="+
                        URLEncoder.encode(firstName,"UTF-8")+
                        "&"+
                        URLEncoder.encode("surname","UTF-8")+"="+
                        URLEncoder.encode(surname,"UTF-8")+
                        "&"+
                        URLEncoder.encode("age","UTF-8")+"="+
                        URLEncoder.encode(age,"UTF-8")+
                        "&"+
                        URLEncoder.encode("username","UTF-8")+"="+
                        URLEncoder.encode(username,"UTF-8")+
                        "&"+
                        URLEncoder.encode("password","UTF-8")+"="+
                        URLEncoder.encode(password,"UTF-8");

                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream,"iso-8859-1"));

                String result = " ";
                String line = "";
                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected void onPostExecute(String result){
        Toast.makeText(mContext,result,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }


}
