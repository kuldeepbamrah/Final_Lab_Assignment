package com.example.final_lab_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Person> personList;
    EditText fname,lname,phone,address,search;
    Button save;
    PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PersonDB personDB = PersonDB.getInstance(this);
        personList = personDB.daoObjct().getDefault();
        RecyclerView recyclerView = findViewById(R.id.recyclerCategories);
         personAdapter = new PersonAdapter(this);


        personDB.daoObjct().getLiveDefault().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                personList = people;
                personAdapter.setPersonList(personList);
                personAdapter.notifyDataSetChanged();
            }
        });

        personAdapter.setPersonList( personList );
        recyclerView.setAdapter(personAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        personAdapter.notifyDataSetChanged();

        final LinearLayout linearLayout = findViewById(R.id.addView);

        final FloatingActionButton floatingActionButton = findViewById(R.id.add);

        fname = findViewById(R.id.fnameEDT);
        lname = findViewById(R.id.flnameEDT);
        address = findViewById(R.id.addressEDT);
        phone = findViewById(R.id.phoneEDT);
        search = findViewById(R.id.searchBar);

        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String fname1,lname1,phone1,address1;



                fname1 = fname.getText().toString();
                lname1 = lname.getText().toString();
                address1 = address.getText().toString();
                phone1 = phone.getText().toString();

                if(fname1!=null&&lname1!=null&&address1!=null&&phone1!=null)
                {
                    Person person = new Person(fname1,lname1,address1,phone1);
                    personDB.daoObjct().insert(person);
                    personAdapter.notifyDataSetChanged();
                    linearLayout.setVisibility(View.GONE);
                    floatingActionButton.setVisibility(View.VISIBLE);
                    fname.setText("");
                    lname.setText("");
                    phone.setText("");
                    address.setText("");
                }
            }
        });



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionButton.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);

            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });


    }
    private void filter(String s) {
        List<Person> filteredList = new ArrayList<>(  );
        for(Person person : personList)
        {
            if(person.getFname().toLowerCase().contains( s.toLowerCase() )
                    || person.getLname().toLowerCase().contains( s.toLowerCase() )
                    || person.getAddress().toLowerCase().contains( s.toLowerCase() )
                    || person.getPhone().toLowerCase().contains( s.toLowerCase() )

            )
            {
                filteredList.add( person );
            }
        }
        personAdapter.filterlist(filteredList);




    }
}
