package com.kamsung.testlistviewdeletemode;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<CheckBox> checkBoxes;
    ArrayList<Integer> deleteList;
    boolean deleteMode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxes = new ArrayList<>();
        deleteList = new ArrayList<>();
        final LinearLayout deleteModeLayout = (LinearLayout) findViewById(R.id.delete_mode_layout);
        ListView listView = (ListView) findViewById(R.id.list_view);
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            list.add(i + "a");
        }
        MyAdapter adapter = new MyAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        Button allSelect = (Button) findViewById(R.id.all_select);
        allSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMode = true;
                for(int i = 0; i < 100; i++){
                    deleteList.add(i);

                }

                for(CheckBox c : checkBoxes){
                    c.setChecked(true);
                }
            }
        });

        Button deleteMode = (Button) findViewById(R.id.delete_mode);
        deleteMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteModeLayout.setVisibility(View.VISIBLE);
                for(CheckBox c : checkBoxes){
                    c.setVisibility(View.VISIBLE);
                }
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i : deleteList){
                    Log.e("삭제리스트", i + "");
                }
            }
        });
    }


    private class MyAdapter extends BaseAdapter{
        Context context;
        ArrayList<String>items;

        public MyAdapter(Context context, int resource, List<String> objects) {
            this.context = context;
            this.items = (ArrayList)objects;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if(v == null){
                LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.list_item, null);
            }
            String item = items.get(position);
            TextView t = (TextView) v.findViewById(R.id.text);
            CheckBox c = (CheckBox) v.findViewById(R.id.check);
            if(deleteMode){
                c.setChecked(true);
            }else{
                c.setChecked(false);
            }

            c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        deleteList.add(position);
                    }
                }
            });
            checkBoxes.add(c);
            t.setText(item);

            return v;
        }

    }
}



