package com.example.final_lab_assignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    List<Person> personList;
    Context context;

    public PersonAdapter(Context context) {
        this.context = context;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.custom_person_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        final Person person = personList.get(position);



        //holder.categories.setText(currCategory);
        holder.name.setText(person.getFname());
        holder.phone.setText(person.getPhone());
        holder.address.setText(person.getAddress());


        holder.mycardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //  Toast.makeText(context,"position = "+position,Toast.LENGTH_LONG).show();

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteItem(position);

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditEmployee.class);
                intent.putExtra("data",personList.get(position));
                context.startActivity(intent);
            }
        });





    }




    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void filterlist(List<Person> filteredList) {

        personList = filteredList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,address,phone;
        ImageView delete,edit;
        CardView mycardview;
        FloatingActionButton floatingActionButton;


        public ViewHolder(@NonNull View itemView) {


            super(itemView);


            mycardview = itemView.findViewById(R.id.newcardNote);
            name = itemView.findViewById(R.id.fName);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);

            delete = itemView.findViewById(R.id.delete);
            edit =itemView.findViewById(R.id.edit);







        }
    }

    public void deleteItem(int position) {

        Person person = personList.get(position);
        PersonDB userDatabase = PersonDB.getInstance(getContext());
        userDatabase.daoObjct().delete(person);
        Toast.makeText(getContext(),"Deleted",Toast.LENGTH_SHORT).show();
        personList.remove(position);
        notifyDataSetChanged();

    }
}