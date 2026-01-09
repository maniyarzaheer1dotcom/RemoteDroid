package com.example.admin.remotedroidconn;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isConnected=false;
    private boolean mouseMoved=false;
    private Socket socket;
    private PrintWriter out;
    TextView mousePad;
    Context context;
    Button btn;

    private float initX =0;
    private float initY =0;
    private float disX =0;
    private float disY =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        context = this;

        getConn();

        mousePad = (TextView)findViewById(R.id.mousePad);
        btn=(Button)findViewById(R.id.button13);

        btn.setOnClickListener(this);

        //capture finger taps and movement on the textview
        mousePad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LongOperation ll=new LongOperation();

                if(isConnected && out!=null){
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            //save X and Y positions when user touches the TextView
                            initX =event.getX();
                            initY =event.getY();
                            mouseMoved=false;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            disX = event.getX()- initX; //Mouse movement in x direction
                            disY = event.getY()- initY; //Mouse movement in y direction
                            /*set init to new position so that continuous mouse movement
                            is captured*/
                            initX = event.getX();
                            initY = event.getY();
                            if(disX !=0|| disY !=0){
                                //out.println(disX +","+ disY); //send mouse movement to server
                                ll.execute(disX +","+ disY);

                            }
                            mouseMoved=true;
                            break;
                        case MotionEvent.ACTION_UP:
                            //consider a tap only if usr did not move mouse after ACTION_DOWN
//                            if(!mouseMoved){
//                                out.println("left_click");
//                            }
                    }
                }
                return true;
            }
        });






    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onDestroy()
    {
        super.onDestroy();
        LongOperation ll=new LongOperation();

        if(isConnected && out!=null) {
            try {

                ll.execute("exit");
                //out.close();
                System.out.println("working");

                // out.println("exit"); //tell server to exit
                //socket.close(); //close socket
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
                //socket.close(); //close socket
            } catch (Exception e) {
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
            //out.println("left_click");//
            ll.execute("left_click");

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
            //TextView txt = (TextView) findViewById(R.id.output);
            //txt.setText("Executed"); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }



}
