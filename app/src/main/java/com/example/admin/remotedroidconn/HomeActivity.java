package com.example.admin.remotedroidconn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button conn;
    EditText edit_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button btn=(Button)findViewById(R.id.button28);
        Button btnVLC=(Button)findViewById(R.id.button29);
        Button btnMachine=(Button)findViewById(R.id.button31);
        Button write=(Button)findViewById(R.id.button30);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,NavActivity.class);
                startActivity(i);
                return;
            }
        });


        btnVLC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
                return;

            }
        });


        btnMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,MachineActivity.class);
                startActivity(i);
                return;

            }
        });

        write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,WritingActivity.class);
                startActivity(i);
                return;

            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_menu) {
            Intent ii=new Intent(getApplicationContext(),ConnActivity.class);
            startActivity(ii);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
