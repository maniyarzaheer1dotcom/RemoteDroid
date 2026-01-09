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
import android.widget.Toast;
import android.widget.*;
import com.example.admin.remotedroidconn.SocketHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class NavActivity extends AppCompatActivity implements View.OnClickListener {
    //public   static PrintWriter out=null;
    Context context;
    private boolean isConnected=false;
    private boolean mouseMoved=false;
    private Socket socket;
    private PrintWriter out;

    Button left;
    Button right;
    Button top;
    Button bottom;
    Button enter;
    Button left_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        context = this;
        getConn();


        left=(Button)findViewById(R.id.button32);
        right=(Button)findViewById(R.id.button33);
        top=(Button)findViewById(R.id.button34);
        bottom=(Button)findViewById(R.id.button35);
        enter=(Button)findViewById(R.id.button36);
        left_click=(Button)findViewById(R.id.button37);

        left.setOnClickListener(this);
        right.setOnClickListener(this);
        top.setOnClickListener(this);
        bottom.setOnClickListener(this);
        enter.setOnClickListener(this);
        left_click.setOnClickListener(this);


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void onDestroy()
    {
        super.onDestroy();
        if(isConnected && out!=null) {
            try {
                out.println("exit"); //tell server to exit
                socket.close(); //close socket
            } catch (IOException e) {
                Log.e("remotedroid", "Error in closing socket", e);
            }
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_menu) {
            Intent ii=new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(ii);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button32:
                if (isConnected && out!=null) {
                    out.println("left");//
                }
                break;
            case R.id.button33:
                if (isConnected && out!=null) {
                    out.println("right"); //
                }
                break;
            case R.id.button34:
                if (isConnected && out!=null) {
                    out.println("top"); //
                }
                break;
            case R.id.button35:
                if(isConnected && out!=null){
                    out.println("bottom");
                }
                break;
            case R.id.button36:
                if(isConnected && out!=null){
                    out.println("enter");
                }
                break;
            case R.id.button37:
                if(isConnected && out!=null)
                {
                    out.println("left_click");
                }



        }

    }

    public void onPause()
    {
        super.onPause();
        if(isConnected && out!=null) {
            try {
                out.println("exit"); //tell server to exit
                socket.close(); //close socket
            } catch (IOException e) {
                Log.e("remotedroid", "Error in closing socket", e);
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public class ConnectPhoneTask extends AsyncTask<String,Void,Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            try {
                InetAddress serverAddr = InetAddress.getByName(params[0]);
                socket = new Socket(serverAddr, 8000);
                SocketHandler.setSocket(socket);
                //Open socket on server IP and port
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




}
