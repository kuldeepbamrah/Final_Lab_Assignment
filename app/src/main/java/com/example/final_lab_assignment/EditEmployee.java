package com.example.final_lab_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditEmployee extends AppCompatActivity {

    EditText fname,lname,phone,address;
    Button save;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        person = getIntent().getParcelableExtra("data");
        fname = findViewById(R.id.fnameEDT);
        lname = findViewById(R.id.flnameEDT);
        address = findViewById(R.id.addressEDT);
        phone = findViewById(R.id.phoneEDT);
        save = findViewById(R.id.save);


        fname.setText(person.getFname());
        lname.setText(person.getLname());
        address.setText(person.getAddress());
        phone.setText(person.getPhone());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namef,namel,add,ph;

                namef = fname.getText().toString();
                namel = lname.getText().toString();
                add = address.getText().toString();
                ph = phone.getText().toString();


                if(namef!=null&&namel!=null&&add!=null&&ph!=null)
                {
                    //Person person = new Person(namef,namel,add,ph);

                    PersonDB personDB = PersonDB.getInstance(getApplicationContext());


                    //personDB.daoObjct().update(person);
                    //personAdapter.notifyDataSetChanged();
                    //linearLayout.setVisibility(View.GONE);
                    //.setVisibility(View.VISIBLE);
                    person.setFname(namef);
                    person.setLname(namel);
                    person.setAddress(add);
                    person.setPhone(ph);
                    personDB.daoObjct().update(person);

                    fname.setText("");
                    lname.setText("");
                    phone.setText("");
                    address.setText("");
                    finish();
                }

            }
        });



    }
}
