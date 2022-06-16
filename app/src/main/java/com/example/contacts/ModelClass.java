package com.example.contacts;

public class ModelClass {
    private String contact1;
    private String contact2;
    private String email;
//    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//    public ModelClass(String contact1, String contact2){
//        this.contact1 = contact1;
//        this.contact2 = contact2;
//    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }
}
