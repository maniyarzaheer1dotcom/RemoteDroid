package com.example.admin.remotedroidconn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnActivity extends AppCompatActivity {

    Button submit,btn;
    EditText ip_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conn);

        ip_add=(EditText)findViewById(R.id.editText);
        submit=(Button)findViewById(R.id.button38);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip_val=ip_add.getText().toString().trim();
                Log.d("ip",ip_val);

                if(ip_val.length()>0)
                {
                    Toast.makeText(getApplicationContext(), "IP Address Accepted.", Toast.LENGTH_LONG).show();
                    IP_Conn.setIp_addr(ip_val);
                    Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                    return;
                }else
                {
                    Toast.makeText(getApplicationContext(),"Enter server IP Address",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
