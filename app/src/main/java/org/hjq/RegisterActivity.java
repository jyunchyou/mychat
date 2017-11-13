package org.hjq;
import android.os.Bundle;
import org.hjq.context.ContextCacheTable;
import android.app.Activity;
import org.hjq.R;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;
import android.os.Message;
import org.hjq.storage.MessageStorage;
import java.io.IOException;
import android.content.Intent;

public class RegisterActivity extends Activity
{
    
    
    private EditText editTextUserName = null;

    private EditText editTextPassword = null;

    private Button buttonBack = null;

    private Button buttonRegister = null;
    
    MessageStorage messageStorage = MainActivity.messageStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //缓存Context
        ContextCacheTable.setContext(this.getClass().getName(),this);
        
        //拿到控件
        editTextPassword = (EditText) findViewById(R.id.editRegisterTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editRegisterTextUserName);
     
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        
        
        
        //注册观察
        buttonBack.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                    
                    
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                    // TODO: Implement this method
                }
                
            
        });
        
        buttonRegister.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                    String userName = editTextUserName.getText().toString();

                    String password = editTextPassword.getText().toString();
                    
                    
                   
                    if (userName.indexOf("&") != -1 || password.indexOf("&") != -1){
                        Toast.makeText(RegisterActivity.this,"用户名或密码不能包含'&'",Toast.LENGTH_SHORT).show();

                    }
                    if (userName == null || "".equals(userName) ||password == null || "".equals(password)){

                        Toast.makeText(RegisterActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();


                    }
                    try
                    {
                        boolean result = messageStorage.put(userName, password);
                        if (result == false) {
                            
                            Toast.makeText(RegisterActivity.this,"同一台设备只能注册一个帐号",Toast.LENGTH_SHORT).show();
                            
                        }else{
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (IOException e)
                    {}

                    
                    
                    
                }
                
            
        });
        
    
}
}
