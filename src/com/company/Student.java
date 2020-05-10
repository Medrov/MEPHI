package com.company;

public class Student {
    private String mSurname;
    private String mProduct;
    private int mAmount;

    public Student(String line) {
        String[] customer = line.split(" ");
        mSurname = customer[0];
        mProduct = customer[1];
        mAmount = Integer.parseInt(customer[2]);
    }

    public String getSurname() {
        return mSurname;
    }

    public String getProduct() {
        return mProduct;
    }

    public int getAmount() {
        return mAmount;
    }
}
