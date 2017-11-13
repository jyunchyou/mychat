package org.hjq;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import android.widget.BaseExpandableListAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

public class ContactActivity extends Activity {
    
    private ExpandableListView listview = null;

    
 
        
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        
    
    
    listview = (ExpandableListView) findViewById(R.id.elv);
    MyExpandableListViewAdapter    adapter = new MyExpandableListViewAdapter(this);
    listview.setAdapter(adapter);
    
  
        
}


    /* ExpandableListViewAdapter 是一个接口,继承其中一个子类实现自定义adapter */
    public class MyExpandableListViewAdapter extends BaseExpandableListAdapter{

        
        
        
        private Context context;
        /* 布局填充器*/
        private LayoutInflater inflater;
        private String[] group = new String[]{"流年","未命名"};
        private String[][] childs= new String[][]{{ "唐磊","李子豪","黄涵芝", "冉智力","黄关月","邓巍靓","刘畅","唐子壹","袁志智"},
            {"brianway","codefowllower","一群手抓饼","Vczh","BYVoid","左耳朵耗子"}};
            
        private int[] images = {R.drawable.male01, 
            R.drawable.male02,
            R.drawable.male03,
            R.drawable.male04,
            R.drawable.male05,
            R.drawable.male06,
            R.drawable.male07,
            R.drawable.male08,
            R.drawable.male09
        };

        /* 构造,因为布局填充器填充布局需要使用到Context,通过构造来传递 */
        public MyExpandableListViewAdapter (Context context){
            this.context = context;
            inflater = LayoutInflater.from(context);
        }


        /*组数*/
        @Override
        public int getGroupCount() {
            return group.length;
        }

        /* 指定组的子Item数*/
        @Override
        public int getChildrenCount(int groupPosition) {
            return childs[groupPosition].length;
        }

        /*组数据*/
        @Override
        public Object getGroup(int groupPosition) {
            return group[groupPosition];
        }

        /*返回子选项的数据*/
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childs[groupPosition][childPosition];
        }

        /*组ID*/
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        /*子ID*/
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        /*ID是否唯一*/
        @Override
        public boolean hasStableIds() {
            return true;
        }

        /* 组选项的视图处理 */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            /* 填充布局*/
            View view = inflater.inflate(R.layout.item_elv_group,null);

            ImageView iv_group_icon = (ImageView) view.findViewById(R.id.iv_group_icon);
            TextView tv_group_name = (TextView) view.findViewById(R.id.tv_group_name);
            TextView tv_group_number = (TextView) view.findViewById(R.id.tv_group_number);

            tv_group_name.setText(group[groupPosition]);
            tv_group_number.setText(childs[groupPosition].length+"/"+childs[groupPosition].length);

            /*isExpanded 子列表是否展开*/
      /*      if(isExpanded){
                iv_group_icon.setImageResource(R..arrow_down);
            }else {
                iv_group_icon.setImageResource(R.mipmap.arrow_right);
            }
*/
            return view;

        }

        /* 子选项的视图处理 */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.item_elv_child,null);

            ImageView iv_child_icon = (ImageView) view.findViewById(R.id.iv_child_icon);
            TextView tv_child_info = (TextView) view.findViewById(R.id.tv_child_info);
            TextView tv_child_name = (TextView) view.findViewById(R.id.tv_child_name);
            TextView tv_child_network = (TextView) view.findViewById(R.id.tv_child_network);

           iv_child_icon.setImageResource(images[Math.abs(childPosition % images.length)]);
           
            
            tv_child_name.setText(childs[groupPosition][childPosition]);
            tv_child_network.setText(childPosition % 2 == 0?"wifi":"4G");
            return view;
        }

        /*子选项是否可选 */
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
        
        
        
        
    }
}
