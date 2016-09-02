package com.example.hcc.daymediohomework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.he.optionsBitmap.MyoptionBitmap;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     EditText editText,editText2;
     Button button,button2;
    ImageView imageView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String name;
    String password;
    Intent intent;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        editText= (EditText) findViewById(R.id.nameId);
        editText2= (EditText) findViewById(R.id.passwordId);
        button= (Button) findViewById(R.id.buttonId);
        button2= (Button) findViewById(R.id.buttonId2);
        imageView= (ImageView) findViewById(R.id.imageId);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        sharedPreferences=getSharedPreferences("use_info",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        initBitmap();


    }

    private void initBitmap() {
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.jianglai);
        Log.d("he","bitmap===="+bitmap);
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bo);
        byte [] images=bo.toByteArray();
        imageView.setImageBitmap(MyoptionBitmap.getOptionBitmap(images));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        name=editText.getText().toString();
        password=editText2.getText().toString();
        switch (v.getId()){
            case R.id.buttonId2:
                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                  editor.putString("name",name);
                    editor.putString("password",password);
                    editor.commit();
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();

                } else{
                    Toast.makeText(getApplicationContext(),"用户账户或密码不能为空",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonId:
                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                           if(name.equals(sharedPreferences.getString("name","hechongchong"))&&password.equals(sharedPreferences.getString("password","123456"))){
                              Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_LONG).show();

                               intent=new Intent("com.hcc.rose");
                               startActivity(intent);
                       } else{
                               Toast.makeText(getApplicationContext(),"账户或密码错误",Toast.LENGTH_LONG).show();
                       }
                }else{
                    Toast.makeText(getApplicationContext(),"用户账户或密码不能为空",Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
}
