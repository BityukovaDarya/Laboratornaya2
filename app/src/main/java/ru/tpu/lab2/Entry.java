package ru.tpu.lab2;

import android.os.Parcel;
import android.os.Parcelable;

public class Entry implements Parcelable, Comparable {
    public String name;
    public double rating;

    public Entry(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    private Entry(Parcel in) {
        name = in.readString();
        rating = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(name);
        out.writeDouble(rating);
    }

    public static final Parcelable.Creator<Entry> CREATOR
            = new Parcelable.Creator<Entry>() {
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }
        //CREATOR типа Parcelable.Creator<MyObject> используется для создания экземпляра нашего MyObject и заполнения его данными из Parcel.
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

    @Override
    public int compareTo(Object o) {
        double rating = ((Entry) o).rating;
        return (int) ((rating - this.rating) * 10);
    }
}
