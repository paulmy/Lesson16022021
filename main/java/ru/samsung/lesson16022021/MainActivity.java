package ru.samsung.lesson16022021;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView selected;
    ListView listView;
    Button btnadd, btndel;
    EditText inputtext;
    String[] countries = {"Россия", "США", "Франция", "Италия", "Германия", "Бразилия", "Чили", "Аргентина", "Япония"};
    List<String> mylist;
    ArrayAdapter<String> adapter;
    ArrayList<String> selectedremove = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selected = findViewById(R.id.selected);
        listView = findViewById(R.id.listview);
        btnadd = findViewById(R.id.btnadd);
        btndel = findViewById(R.id.btndel);
        inputtext = findViewById(R.id.inputtext);
        mylist = new ArrayList<>();

        addList(countries); // добавление в список элементов из статического массива countries

        //selected.setText(viewList((ArrayList<String>) mylist));

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = "";
                try {
                    input = inputtext.getText().toString();
                    if (input.equals(""))
                        Toast.makeText(getApplicationContext(), "Пустое поле ввода", Toast.LENGTH_SHORT).show();
                    //  }

                } catch (Exception e) {
                }
                if (!input.equals(""))
                    mylist.add(input);
                inputtext.setText("");
                // mylist.add("Hello");
                adapter.notifyDataSetChanged();
            }
        });
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < selectedremove.size(); i++) {
                    adapter.remove(selectedremove.get(i));
                }
                listView.clearChoices();
                selectedremove.clear();
                adapter.notifyDataSetChanged();
            }
        });

        //Arrays.sort(countries);


        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_multiple_choice,
                mylist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*SparseBooleanArray sp = listView.getCheckedItemPositions();
                String selectedItem = "";
                for (int i = 0; i < mylist.size(); i++) {
                    if (sp.get(i)) {
                        selectedItem += mylist.get(i) + ", ";
                    }
                }*/
                String countrie = adapter.getItem(position);
                if (listView.isItemChecked(position)) {
                    selectedremove.add(countrie);
                } else {
                    selectedremove.remove(countrie);
                }

                //selected.setText(countries[position]);
                selected.setText("Выбрано: " + viewList(selectedremove));
            }
        });
    }

    void addList(String[] arr) {
        for (String a : arr) {
            mylist.add(a);
        }

    }

    String viewList(ArrayList<String> list) {
        String str = "";
        for (String a : list) {
            str += " " + a;
        }
        return str;
    }
}