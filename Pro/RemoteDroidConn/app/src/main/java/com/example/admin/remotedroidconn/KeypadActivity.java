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
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class KeypadActivity extends AppCompatActivity implements View.OnClickListener {


   Button btn,b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,Rr,S,T,U,V,W,X,Y,Z,b_atherate,hash,doller,percent,arrow;
    Button ampersand,star,brac1,brac2,dash,underscore,plus,equal,div,pipe,dot,tiled,excl,appostroph,curly1,curly2,rect1,rect2,slash;
    Button comma,semicolon,collen,ang1,ang2,que,dquote,quote;
    Button enter,space;
    private boolean isConnected=false;
    private Socket socket;
    private PrintWriter out;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keypad);

        context = this;

        getConn();


        comma=(Button)findViewById(R.id.id_comma);
        semicolon=(Button)findViewById(R.id.id_semicollen);
        collen=(Button)findViewById(R.id.id_collen);
        ang1=(Button)findViewById(R.id.id_angu1);
        ang2=(Button)findViewById(R.id.id_angu2);
        que=(Button)findViewById(R.id.id_ques);
        dquote=(Button)findViewById(R.id.id_doubleq);
        quote=(Button)findViewById(R.id.id_quote);
        enter=(Button)findViewById(R.id.id_enter) ;
        space=(Button)findViewById(R.id.id_space) ;



        b_atherate=(Button)findViewById(R.id.id_athe);
        hash=(Button)findViewById(R.id.id_hash);
        doller=(Button)findViewById(R.id.id_doller);
        percent=(Button)findViewById(R.id.id_percent);
        arrow=(Button)findViewById(R.id.id_arrow);
        ampersand=(Button)findViewById(R.id.id_and);
        star=(Button)findViewById(R.id.id_star);
        brac1=(Button)findViewById(R.id.id_brac1);
        brac2=(Button)findViewById(R.id.id_brac2);
        dash=(Button)findViewById(R.id.id_dash);
        underscore=(Button)findViewById(R.id.underscore);
        plus=(Button)findViewById(R.id.id_plus);
        equal=(Button)findViewById(R.id.id_equal);
        div=(Button)findViewById(R.id.id_divide);
        pipe=(Button)findViewById(R.id.id_pipe);
        dot=(Button)findViewById(R.id.id_dot);
        tiled=(Button)findViewById(R.id.id_tiled);
        excl=(Button)findViewById(R.id.id_excl);
        appostroph=(Button)findViewById(R.id.appostrph);
        curly1=(Button)findViewById(R.id.id_curly1);
        curly2=(Button)findViewById(R.id.id_curly2);
        rect1=(Button)findViewById(R.id.id_rect1);
        rect2=(Button)findViewById(R.id.id_rect2);
        slash=(Button)findViewById(R.id.id_baclslash);



        b0=(Button)findViewById(R.id.id_0);
        b1=(Button)findViewById(R.id.id_1);
        b2=(Button)findViewById(R.id.id_2);
        b3=(Button)findViewById(R.id.id_3);
        b4=(Button)findViewById(R.id.id_4);
        b5=(Button)findViewById(R.id.id_5);
        b6=(Button)findViewById(R.id.id_6);
        b7=(Button)findViewById(R.id.id_7);
        b8=(Button)findViewById(R.id.id_8);
        b9=(Button)findViewById(R.id.id_9);

        A=(Button)findViewById(R.id.a);
        B=(Button)findViewById(R.id.b);
        C=(Button)findViewById(R.id.c);
        D=(Button)findViewById(R.id.d);
        E=(Button)findViewById(R.id.e);
        F=(Button)findViewById(R.id.F);
        G=(Button)findViewById(R.id.G);
        H=(Button)findViewById(R.id.H);
        I=(Button)findViewById(R.id.i);
        J=(Button)findViewById(R.id.j);
        K=(Button)findViewById(R.id.k);
        L=(Button)findViewById(R.id.l);
        M=(Button)findViewById(R.id.m);
        N=(Button)findViewById(R.id.n);
        O=(Button)findViewById(R.id.o);
        P=(Button)findViewById(R.id.p);
        Q=(Button)findViewById(R.id.q);
        Rr=(Button)findViewById(R.id.r);
        S=(Button)findViewById(R.id.s);
        T=(Button)findViewById(R.id.t);
        U=(Button)findViewById(R.id.u);
        V=(Button)findViewById(R.id.v);
        X=(Button)findViewById(R.id.w);
        Y=(Button)findViewById(R.id.x);
        Z=(Button)findViewById(R.id.y);
        W=(Button)findViewById(R.id.z);

        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);

        A.setOnClickListener(this);
        B.setOnClickListener(this);
        C.setOnClickListener(this);
        D.setOnClickListener(this);
        E.setOnClickListener(this);
        F.setOnClickListener(this);
        G.setOnClickListener(this);
        H.setOnClickListener(this);
        I.setOnClickListener(this);
        J.setOnClickListener(this);
        K.setOnClickListener(this);
        L.setOnClickListener(this);
        M.setOnClickListener(this);
        N.setOnClickListener(this);
        O.setOnClickListener(this);
        P.setOnClickListener(this);
        Q.setOnClickListener(this);
        Rr.setOnClickListener(this);
        S.setOnClickListener(this);
        T.setOnClickListener(this);
        U.setOnClickListener(this);
        V.setOnClickListener(this);
        W.setOnClickListener(this);
        X.setOnClickListener(this);
        Y.setOnClickListener(this);
        Z.setOnClickListener(this);

       // Button ampersand,star,brac1,brac2,dash,underscore,plus,equal,div,pipe,dot,tiled,excl,appostroph,curly1,curly2,rect1,rect2,slash;
        //    Button comma,semicolon,collen,ang1,ang2,que,dquote,quote;

        enter.setOnClickListener(this);
        space.setOnClickListener(this);
        arrow.setOnClickListener(this);

        comma.setOnClickListener(this);
        b_atherate.setOnClickListener(this);
        doller.setOnClickListener(this);
        hash.setOnClickListener(this);
        percent.setOnClickListener(this);
        ang2.setOnClickListener(this);
        que.setOnClickListener(this);
        dquote.setOnClickListener(this);
        quote.setOnClickListener(this);
        semicolon.setOnClickListener(this);
        collen.setOnClickListener(this);
        ang1.setOnClickListener(this);
        ampersand.setOnClickListener(this);
        star.setOnClickListener(this);
        brac1.setOnClickListener(this);
        brac2.setOnClickListener(this);
        dash.setOnClickListener(this);
        underscore.setOnClickListener(this);
        plus.setOnClickListener(this);
        equal.setOnClickListener(this);
        div.setOnClickListener(this);
        pipe.setOnClickListener(this);
        dot.setOnClickListener(this);
        tiled.setOnClickListener(this);
        excl.setOnClickListener(this);
        appostroph.setOnClickListener(this);
        curly1.setOnClickListener(this);
        curly2.setOnClickListener(this);
        rect1.setOnClickListener(this);
        rect2.setOnClickListener(this);
        slash.setOnClickListener(this);
    }




    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
               // socket.close(); //close socket
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

        switch (v.getId()) {
            case R.id.id_0:
                if (isConnected && out != null) {
                   // out.println("0");
                    ll.execute("0");

                }
                break;

            case R.id.id_1:
                if (isConnected && out != null) {
                   // out.println("1"); //send "next" to server
                    ll.execute("1");

                }
                break;
            case R.id.id_2:
                if (isConnected && out != null) {
                  //  out.println("2"); //send "previous" to server
                    ll.execute("2");

                }
                break;
            case R.id.id_3:
                if (isConnected && out != null) {
                   // out.println("3"); //send "previous" to server
                    ll.execute("3");

                }
                break;
            case R.id.id_4:
                if (isConnected && out != null) {
                   // out.println("4"); //send "previous" to server
                    ll.execute("4");

                }
                break;
            case R.id.id_5:
                if (isConnected && out != null) {
                   // out.println("5"); //send "previous" to server
                    ll.execute("5");

                }
                break;
            case R.id.id_6:
                if (isConnected && out != null) {
                   // out.println("6"); //send "previous" to server
                    ll.execute("6");

                }
                break;
            case R.id.id_7:
                if (isConnected && out != null) {
                 //   out.println("7"); //send "previous" to server
                    ll.execute("7");

                }
                break;
            case R.id.id_8:
                if (isConnected && out != null) {
                   // out.println("8"); //send "previous" to server
                    ll.execute("8");

                }
                break;
            case R.id.id_9:
                if (isConnected && out != null) {
                   // out.println("9"); //send "previous" to server
                    ll.execute("9");

                }
                break;
            case R.id.a:
                if (isConnected && out != null) {
                   // out.println("a"); //send "previous" to server
                    ll.execute("a");

                }
                break;
            case R.id.b:
                if (isConnected && out != null) {
                   // out.println("b"); //send "previous" to server
                    ll.execute("b");

                }
                break;
            case R.id.c:
                if (isConnected && out != null) {
                   // out.println("c"); //send "previous" to server
                    ll.execute("c");

                }
                break;
            case R.id.d:
                if (isConnected && out != null) {
                    //out.println("d"); //send "previous" to server
                    ll.execute("d");

                }
                break;
            case R.id.e:
                if (isConnected && out != null) {
                   // out.println("e"); //send "previous" to server
                    ll.execute("e");

                }
                break;
            case R.id.F:
                if (isConnected && out != null) {
                   // out.println("f"); //send "previous" to server
                    ll.execute("f");

                }
                break;
            case R.id.G:
                if (isConnected && out != null) {
                  //  out.println("g"); //send "previous" to server
                    ll.execute("g");

                }
                break;
            case R.id.H:
                if (isConnected && out != null) {
                  //  out.println("h"); //send "previous" to server
                    ll.execute("h");

                }
                break;
            case R.id.i:
                if (isConnected && out != null) {
                  //  out.println("i"); //send "previous" to server
                    ll.execute("i");

                }
                break;
            case R.id.j:
                if (isConnected && out != null) {
                  //  out.println("j"); //send "previous" to server
                    ll.execute("j");

                }
                break;
            case R.id.k:
                if (isConnected && out != null) {
                   // out.println("k"); //send "previous" to server
                    ll.execute("k");

                }
                break;
            case R.id.l:
                if (isConnected && out != null) {
                   // out.println("l"); //send "previous" to server
                    ll.execute("l");

                }
                break;
            case R.id.m:
                if (isConnected && out != null) {
                    //out.println("m"); //send "previous" to server
                    ll.execute("m");

                }
                break;
            case R.id.n:
                if (isConnected && out != null) {
                    //out.println("n"); //send "previous" to server
                    ll.execute("n");

                }
                break;
            case R.id.o:
                if (isConnected && out != null) {
                    //out.println("o"); //send "previous" to server
                    ll.execute("o");

                }
                break;
            case R.id.p:
                if (isConnected && out != null) {
                   // out.println("p"); //send "previous" to server
                    ll.execute("p");

                }
                break;
            case R.id.q:
                if (isConnected && out != null) {
                   // out.println("q"); //send "previous" to server
                    ll.execute("q");

                }
                break;
            case R.id.r:
                if (isConnected && out != null) {
                   // out.println("r"); //send "previous" to server
                    ll.execute("r");

                }
                break;
            case R.id.s:
                if (isConnected && out != null) {
                   // out.println("s"); //send "previous" to server
                    ll.execute("s");

                }
                break;
            case R.id.t:
                if (isConnected && out != null) {
                   // out.println("t"); //send "previous" to server
                    ll.execute("t");

                }
                break;
            case R.id.u:
                if (isConnected && out != null) {
                  //  out.println("u"); //send "previous" to server
                    ll.execute("u");

                }
                break;
            case R.id.v:
                if (isConnected && out != null) {
                  //  out.println("v"); //send "previous" to server
                    ll.execute("v");

                }
                break;
            case R.id.w:
                if (isConnected && out != null) {
                  //  out.println("w"); //send "previous" to server
                    ll.execute("w");

                }
                break;
            case R.id.x:
                if (isConnected && out != null) {
                   // out.println("x"); //send "previous" to server
                    ll.execute("x");


                }
                break;
            case R.id.y:
                if (isConnected && out != null) {
                    //out.println("y"); //send "previous" to server
                    ll.execute("y");

                }
                break;
            case R.id.z:
                if (isConnected && out != null) {
                    //out.println("z"); //send "previous" to server
                    ll.execute("z");

                }
                break;
            case R.id.id_athe:
                if (isConnected && out != null) {
                    //out.println("atthe"); //send "previous" to server
                    ll.execute("atthe");

                }
                break;
            case R.id.id_hash:
                if (isConnected && out != null) {
                    //out.println("hash"); //send "previous" to server
                    ll.execute("hash");

                }
                break;
            case R.id.id_doller:
                if (isConnected && out != null) {
                    //out.println("dollar"); //send "previous" to server
                    ll.execute("dollar");

                }
                break;
            case R.id.id_percent:
                if (isConnected && out != null) {
                    //out.println("percentage"); //send "previous" to server
                    ll.execute("percentage");

                }
                break;
            case R.id.id_excl:
                if (isConnected && out != null) {
                    //out.println("exclamation"); //send "previous" to server
                    ll.execute("exclamation");

                }
                break;
            case R.id.id_tiled:
                if (isConnected && out != null) {
                    //out.println("tiled"); //send "previous" to server
                    ll.execute("tiled");

                }
                break;
            case R.id.appostrph:
                if (isConnected && out != null) {
                    //out.println("appo"); //send "previous" to server
                    ll.execute("appo");

                }
                break;
            case R.id.id_divide:
                if (isConnected && out != null) {
                    //out.println("slash"); //send "previous" to server
                    ll.execute("slash");

                }
                break;
            case R.id.id_arrow:
                if (isConnected && out != null) {
                    //out.println("circumflex"); //send "previous" to server
                    ll.execute("circumflex");

                }
                break;
            case R.id.id_and:
                if (isConnected && out != null) {
                    //out.println("ampersand"); //send "previous" to server
                    ll.execute("ampersand");

                }
                break;
            case R.id.id_star:
                if (isConnected && out != null) {
                   // out.println("star"); //send "previous" to server
                    ll.execute("star");

                }
                break;
            case R.id.id_brac1:
                if (isConnected && out != null) {
                    //out.println("VK_LEFT_PARENTHESIS"); //send "previous" to server
                    ll.execute("VK_LEFT_PARENTHESIS");

                }
                break;
            case R.id.id_brac2:
                if (isConnected && out != null) {
                   // out.println("VK_RIGHT_PARENTHESIS"); //send "previous" to server
                    ll.execute("VK_RIGHT_PARENTHESIS");

                }
                break;
            case R.id.id_dash:
                if (isConnected && out != null) {
                   // out.println("minus"); //send "previous" to server
                    ll.execute("minus");

                }
                break;
            case R.id.id_plus:
                if (isConnected && out != null) {
                    //out.println("plus"); //send "previous" to server
                    ll.execute("plus");

                }
                break;
            case R.id.id_equal:
                if (isConnected && out != null) {
                    //out.println("equal"); //send "previous" to server
                    ll.execute("equal");

                }
                break;
            case R.id.id_angu1:
                if (isConnected && out != null) {
                    //out.println("greaterthan"); //send "previous" to server
                    ll.execute("greaterthan");

                }
                break;
            case R.id.id_angu2:
                if (isConnected && out != null) {
                    //out.println("lessthan"); //send "previous" to server
                    ll.execute("lessthan");

                }
                break;
            case R.id.id_dot:
                if (isConnected && out != null) {
                    //out.println("dot"); //send "previous" to server
                    ll.execute("dot");

                }
                break;
            case R.id.id_curly1:
                if (isConnected && out != null) {
                    //out.println("openCr"); //send "previous" to server
                    ll.execute("openCr");

                }
                break;
            case R.id.id_curly2:
                if (isConnected && out != null) {
                    //out.println("closeCr"); //send "previous" to server
                    ll.execute("closeCr");

                }
                break;
            case R.id.id_rect1:
                if (isConnected && out != null) {
                    //out.println("openBracket"); //send "previous" to server
                    ll.execute("openBracket");

                }
                break;
            case R.id.id_rect2:
                if (isConnected && out != null) {
                    //out.println("closeBracket"); //send "previous" to server
                    ll.execute("closeBracket");

                }
                break;
            case R.id.id_doubleq:
                if (isConnected && out != null) {
                    //out.println("dq"); //send "previous" to server
                    ll.execute("dq");

                }
                break;
            case R.id.id_quote:
                if (isConnected && out != null) {
                    //out.println("sq"); //send "previous" to server
                    ll.execute("sq");

                }
                break;
            case R.id.id_ques:
                if (isConnected && out != null) {
                    //out.println("question"); //send "previous" to server
                    ll.execute("question");

                }
                break;
            case R.id.id_comma:
                if (isConnected && out != null) {
                    //out.println("comma"); //send "previous" to server
                    ll.execute("comma");

                }
                break;
            case R.id.id_semicollen:
                if (isConnected && out != null) {
                    //out.println("semicolon"); //send "previous" to server
                    ll.execute("semicolon");

                }
                break;
            case R.id.id_collen:
                if (isConnected && out != null) {
                    //out.println("colon"); //send "previous" to server
                    ll.execute("colon");

                }
                break;
            case R.id.id_pipe:
                if (isConnected && out != null) {
                    //out.println("pipe"); //send "previous" to server
                    ll.execute("pipe");

                }
                break;
            case R.id.id_baclslash:
                if (isConnected && out != null) {
                    //out.println("backslash"); //send "previous" to server
                    ll.execute("backslash");

                }
                break;
            case R.id.id_enter:
                if (isConnected && out != null) {
                    //out.println("enter"); //send "previous" to server
                    ll.execute("enter");

                }
                break;
            case R.id.id_space:
                if (isConnected && out != null) {
                    //out.println("space"); //send "previous" to server
                    ll.execute("space");

                }
                break;
            case R.id.underscore:
                if (isConnected && out != null) {
                    //out.println("underscore"); //send "previous" to server
                    ll.execute("underscore");

                }
                break;


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
        protected void onPostExecute(Boolean result) {
            isConnected = result;
            Toast.makeText(context, isConnected ? "Connected to server!" : "Error while connecting", Toast.LENGTH_LONG).show();
            try {
                if (isConnected) {
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                            .getOutputStream())), true); //create output stream to send data to server
                }
            } catch (IOException e) {
                Log.e("remotedroid", "Error while creating OutWriter", e);
                Toast.makeText(context, "Error while connecting", Toast.LENGTH_LONG).show();
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
