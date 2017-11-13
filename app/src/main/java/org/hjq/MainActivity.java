package org.hjq;

import android.app.*;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.hjq.R;
import org.hjq.context.ContextCacheTable;
import org.hjq.storage.MessageStorage;
import java.io.IOException;
import android.widget.Toast;
import java.io.File;
import org.hjq.constant.ConstantLogin;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import android.content.Intent;

public class MainActivity extends Activity 
{
    private EditText editTextUserName = null;

    private EditText editTextPassword = null;

    private Button buttonLogin = null;

    private Button buttonRegister = null;
    
    static MessageStorage messageStorage = new MessageStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //缓存Context
        ContextCacheTable.setContext(this.getClass().getName(),this);

    
        //拿到控件
        editTextPassword = (EditText) findViewById(R.id.editLoginTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editLoginTextUserName);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
      

        try{

       // 缓存用户名和密码
        final String cacheUserName = messageStorage.getUserName();
        final String cachePassword = messageStorage.getPassword();
      
    

       Toast.makeText(this,cacheUserName+cachePassword,Toast.LENGTH_SHORT).show();
        //注册观察
        buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    
                  
              
                    String userName = editTextUserName.getText().toString();

                    String password = editTextPassword.getText().toString();
                    Toast.makeText(MainActivity.this,userName+password,Toast.LENGTH_LONG).show();
                    if (userName.indexOf("&") != -1 || password.indexOf("&") != -1){
                        Toast.makeText(MainActivity.this,"用户名或密码不能包含'&'",Toast.LENGTH_SHORT).show();
                        
                    }
                    if (userName == null || "".equals(userName) ||password == null || "".equals(password)){
                        
                        Toast.makeText(MainActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();

                        
                    }else if(!userName.equals(cacheUserName) || !password.equals(cachePassword))
                    {
                        

                        Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();

                        
                        
                    }else{
                        //跳转
                        
                        
                    }
                
                 


                    
                    
                }
                
                
            });
        //
        buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //跳转
                    Intent intent = new Intent(MainActivity.this,RegisterActivity.class);

                    startActivity(intent);
                }
            });
        //

        }catch(Exception e){
            
            
            
        }
    }
}
