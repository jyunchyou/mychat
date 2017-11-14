package org.hjq;
import android.os.Bundle;
import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import org.hjq.ipml.ChatAdapter;
import org.hjq.ipml.PersonChat;
import org.hjq.R;
import android.widget.TextView;
import android.content.Intent;

public class ContactWindowActivity extends Activity {
    private ChatAdapter chatAdapter;
    /**
     * 声明ListView
     */
    private ListView lv_chat_dialog;
    /**
     * 集合
     */
    private List<PersonChat> personChats = new ArrayList<PersonChat>();
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int what = msg.what;
            switch (what) {
                case 1:
                    /**
                     * ListView条目控制在最后一行
                     */
                    lv_chat_dialog.setSelection(personChats.size());
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chat);
        
     
        lv_chat_dialog = (ListView) findViewById(R.id.lv_chat_dialog);
        Button btn_chat_message_send = (Button) findViewById(R.id.btn_chat_message_send);
        Button btn_chat_message_receive = (Button) findViewById(R.id.btn_chat_message_receive);
        TextView contactNameView = (TextView) findViewById(R.id.contactName);
        
        final EditText et_chat_message = (EditText) findViewById(R.id.et_chat_message);
        /**
         *取联系人
         */
        
        Intent intent=getIntent();
        String contactName=intent.getStringExtra("contactName");
        contactNameView.setText(contactName);
        
        /**
         *setAdapter
         */
        chatAdapter = new ChatAdapter(this, personChats);
        lv_chat_dialog.setAdapter(chatAdapter);
        /**
         * 发送按钮的点击事件
         */
        btn_chat_message_send.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    if (TextUtils.isEmpty(et_chat_message.getText().toString())) {
                        Toast.makeText(ContactWindowActivity.this, "发送内容不能为空", 0).show();
                        return;
                    }
                    PersonChat personChat = new PersonChat();
                    //代表自己发送
                    personChat.setMeSend(true);
                    //得到发送内容
                    personChat.setChatMessage(et_chat_message.getText().toString());
                    //加入集合
                    personChats.add(personChat);
                    //清空输入框
                    et_chat_message.setText("");
                    //刷新ListView
                    chatAdapter.notifyDataSetChanged();
                    handler.sendEmptyMessage(1);
                }
            });
            
            
            /**
            *接收点击事件
            */
        btn_chat_message_receive.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    if (TextUtils.isEmpty(et_chat_message.getText().toString())) {
                        Toast.makeText(ContactWindowActivity.this, "发送内容不能为空", 0).show();
                        return;
                    }
                    PersonChat personChat = new PersonChat();
                    //代表接收消息
                    personChat.setMeSend(false);
                    //得到发送内容
                    personChat.setChatMessage(et_chat_message.getText().toString());
                    //加入集合
                    personChats.add(personChat);
                    //清空输入框
                    et_chat_message.setText("");
                    //刷新ListView
                    chatAdapter.notifyDataSetChanged();
                    handler.sendEmptyMessage(1);
                }
            });
    }

}
