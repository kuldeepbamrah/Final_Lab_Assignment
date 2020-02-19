package com.example.final_lab_assignment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="person")
public class Person implements Parcelable {

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "fname")
    String fname;

    @ColumnInfo(name = "lname")
    String lname;

    @ColumnInfo(name = "address")
    String address;

    @ColumnInfo(name = "phone")
    String phone;


    public Person(String fname, String lname, String address, String phone) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.phone = phone;
    }

    protected Person(Parcel in) {
        id = in.readInt();
        fname = in.readString();
        lname = in.readString();
        address = in.readString();
        phone = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void  setFname(String  fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fname);
        dest.writeString(lname);
        dest.writeString(address);
        dest.writeString(phone);
    }
}
