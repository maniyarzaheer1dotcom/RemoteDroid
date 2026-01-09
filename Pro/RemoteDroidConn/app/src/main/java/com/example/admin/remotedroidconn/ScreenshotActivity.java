package com.example.admin.remotedroidconn;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import  android.widget.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ScreenshotActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    Context context;
    private boolean isConnected=false;
    private boolean mouseMoved=false;
    private Socket socket;
    private PrintWriter out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);

        context = this;

        getConn();

        btn=(Button)findViewById(R.id.button4);

        btn.setOnClickListener(this);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onDestroy()
    {
        super.onDestroy();

        if(isConnected && out!=null) {
            try {
                LongOperation ll=new LongOperation();

                ll.execute("exit");
                //out.close();
                System.out.println("working");


            } catch (Exception e) {
                Log.e("remotedroid", "Error in closing socket", e);
            }
        }
    }


    public void onPause()
    {
        super.onPause();
        if(isConnected && out!=null) {
            try {
               // out.println("exit"); //tell server to exit
               // socket.close(); //close socket
            } catch (Exception e) {
                Log.e("remotedroid", "Error in closing socket", e);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.action_menu) {
            Intent ii=new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(ii);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean getConn()
    {
        String ip_addr=IP_Conn.getIp_addr();
        Log.d("iip",ip_addr);
        ConnectPhoneTask connectPhoneTask = new ConnectPhoneTask();
        //connectPhoneTask.execute(Constants.SERVER_IP);
        connectPhoneTask.execute(ip_addr);
        return  true;
    }

    @Override
    public void onClick(View v) {
        LongOperation ll=new LongOperation();
        if (isConnected && out!=null) {
           // out.println("screenshot");//
            ll.execute("screenshot");
        }
    }

    public class ConnectPhoneTask extends AsyncTask<String,Void,Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            try {
                InetAddress serverAddr = InetAddress.getByName(params[0]);
                socket = new Socket(serverAddr, 8000);//Open socket on server IP and port
            } catch (IOException e) {
                Log.e("remotedroid", "Error while connecting", e);
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result)
        {
            isConnected = result;
            Toast.makeText(context,isConnected?"Connected to server!":"Error while connecting",Toast.LENGTH_LONG).show();
            try {
                if(isConnected) {
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                            .getOutputStream())), true); //create output stream to send data to server
                }
            }catch (IOException e){
                Log.e("remotedroid", "Error while creating OutWriter", e);
                Toast.makeText(context,"Error while connecting",Toast.LENGTH_LONG).show();
            }
        }
    }


    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            out.println(params[0]);
            System.out.println(params[0]);
            if(params[0].equals("exit"))
            {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
           }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }


}
